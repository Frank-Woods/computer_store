class Form {
    constructor(node = null, spinner = null) {
        this.node = node ? node : this.createNode();
        this.spinner = spinner;
        this.items = [];
    }

    static create(node) {
        const spinner = node.getElementsByClassName('custom-spinner')[0];
        if (spinner) return new Form(node, spinner);
        return new Form(node);
    }

    createNode() {
        const node = document.createElement('div');
        node.classList.add('custom-form');

        return node;
    }

    activateSpinner() {
        if (this.spinner) {
            if (!this.spinner.classList.contains('custom-spinner-blurred')) {
                this.spinner.classList.add('custom-spinner-blurred');
            }
            this.node.append(this.spinner);
        }
    }

    deactivateSpinner() {
        if (this.spinner) this.spinner.remove();
    }

    scan() {
        this.items = this.items.concat(Form.scan(this.node, this, this));
        this.deactivateSpinner();
    }

    static scan(node, form, parentFormItem) {
        let items = [];
        items = items.concat(this.scanFormGroup(node, form, parentFormItem));
        items = items.concat(this.scanSelect(node, form, parentFormItem));
        items = items.concat(this.scanCheckbox(node, form, parentFormItem));
        items = items.concat(this.scanFileInput(node, form, parentFormItem));
        items = items.concat(this.scanInput(node, form, parentFormItem));
        items = items.concat(this.scanTextarea(node, form, parentFormItem));
        return items;
    }

    static scanFormGroup(node, form, parentFormItem = null) {
        return Form.scanFormItem(node, FormGroup, form, parentFormItem);
    }

    static scanSelect(node, form, parentFormItem = null) {
        return Form.scanFormItem(node, Select, form, parentFormItem);
    }

    static scanCheckbox(node, form, parentFormItem = null) {
        return Form.scanFormItem(node, Checkbox, form, parentFormItem);
    }

    static scanInput(node, form, parentFormItem = null) {
        return Form.scanFormItem(node, Input, form, parentFormItem);
    }

    static scanFileInput(node, form, parentFormItem = null) {
        return Form.scanFormItem(node, FileInput, form, parentFormItem);
    }

    static scanTextarea(node, form, parentFormItem = null) {
        return Form.scanFormItem(node, Textarea, form, parentFormItem);
    }

    static scanFormItem(node, classType, form, parentFormItem = null) {
        let className, items = [];

        if (classType === FormGroup) className = FormType.FORM_GROUP;
        else if (classType === Select) className = FormType.SELECT;
        else if (classType === Checkbox) className = FormType.CHECKBOX;
        else if (classType === FileInput) className = FormType.FILE_INPUT;
        else if (classType === Input) className = FormType.INPUT;
        else if (classType === Textarea) className = FormType.TEXTAREA;

        const itemsDOM = node.getElementsByClassName(className);
        let startLength = itemsDOM.length;
        for (let i = 0; i < itemsDOM.length; i++) {
            if (

                itemsDOM[i].parentNode.closest(`.${FormType.FORM_GROUP}`) === node
                || itemsDOM[i].parentNode.closest(`.${FormType.FORM_GROUP}`) === null

            ) {
                const item = classType.create(itemsDOM[i], form, parentFormItem);
                items.push(item);
            }
            if (itemsDOM.length !== startLength) {
                i += itemsDOM.length - startLength;
                startLength = itemsDOM.length;
            }
        }
        return items;
    }

    getValue() {
        return Form.getValue(this.items);
    }

    static getValue(items) {
        const object = {};
        let files = [];
        items.forEach(item => {
            if (item instanceof FileInput) {
                files.push({name: item.attributes.name, files: item.getValue()});
            } else {
                const name = item.attributes.name.replace(/\[]/, '');
                if (object[name] !== undefined && !(object[name] instanceof Array)) {
                    const value = object[name];
                    object[name] = [];
                    object[name].push(value);
                }
                if (object[name] instanceof Array) {
                    if (item instanceof FormGroup) {
                        const value = item.getValue();
                        object[name].push(value.object);
                        files = files.concat(value.files);
                    } else {
                        object[name].push(item.getValue());
                    }
                } else {
                    if (item instanceof FormGroup) {
                        let value;
                        if (/\[]/.test(item.attributes.name)) {
                            value = item.getValue();
                            object[name] = [];
                            object[name].push(value.object);
                        } else {
                            value = item.getValue();
                            object[name] = value.object;
                        }
                        files = files.concat(value.files);
                    } else {
                        object[name] = item.getValue();
                    }
                }
            }
        });
        return {
            object: object,
            files: files
        };
    }

    setErrors(errors) {
        errors = errors.replace(/[{}]/g, '');
        errors = errors.split('",');
        errors = errors.map(error => {
            error += '"';
            let errorAfterSplit = error.split(':');
            errorAfterSplit = errorAfterSplit.map(errorAfterSplitItem => errorAfterSplitItem.replace(/"/g, ''));
            return errorAfterSplit;
        });
        const unused = [];
        errors.forEach(error => {
            const formItem = this.searchFormItem(error[0]);
            if (formItem) {
                formItem.createError(error[1]);
            } else  {
                unused.push(error);
            }
        });
        return unused;
    }

    searchFormItem(nameChain) {
        return Form.searchFormItem(nameChain, this);
    }

    static searchFormItem(nameChain, formItem) {
        let names = nameChain;
        if (typeof names === 'string') {
            names = nameChain.split('.');
            names.shift();
        }

        if (!names.length) return null;

        const dirtyName = names.shift();
        let index = 0;

        const name = dirtyName.match(/[^\[\]]*/g)[0];
        const indexMatch = dirtyName.match(/(?<=\[)\d*/g);

        if (indexMatch) index = indexMatch[0];

        const formItems = formItem.items.filter(item => item.attributes.name.replace(/\[]/, '') === name);
        if (formItems.length) {
            if (names.length) {
                return Form.searchFormItem(names, formItems[index]);
            }
            return formItems[index];
        }
        return null;
    }

    searchFormItemByName(name, level = 0, depth = true) {
        return Form.searchFormItemByName(name, this.items, depth);
    }

    static searchFormItemByName(name, items, depth = true) {
        const searchedItem = items.find(item => {
            if (item.attributes.name === name) return item;
            if (depth) {
                if (item instanceof FormGroup) {
                    return Form.searchFormItemByName(name, item.items);
                }
            }
        });
        return searchedItem !== undefined ? searchedItem : null;
    }

    validate() {
        let validation = true;
        this.items.forEach(item => {
            if (!item.validate()) validation = false;
        });
        return validation;
    }
}