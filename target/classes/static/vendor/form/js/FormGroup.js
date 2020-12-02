class FormGroup {
    constructor(
        form,
        parentFormItem,
        attributes,
        node
    ) {
        this.form = form;
        this.parentFormItem = parentFormItem;

        this.attributes = {
            name: attributes && attributes.name ? attributes.name : 'custom-form-group',
            deleteNodeClassName: attributes && attributes.deleteNodeClassName ? attributes.deleteNodeClassName : null,
            deleteNode: attributes && attributes.deleteNode ? attributes.deleteNode : null,
            createDeleteNode: attributes && attributes.createDeleteNode !== undefined && attributes.createDeleteNode !== null ? attributes.createDeleteNode : false
        }

        this.items = [];

        this.node = node ? node : this.createNode();


        if (!this.attributes.deleteNode) {
            if (this.attributes.deleteNodeClassName) {
                this.attributes.deleteNode = this.node.getElementsByClassName(this.attributes.deleteNodeClassName)[0];
                if (this.attributes.deleteNode) {
                }
            } else if (this.attributes.createDeleteNode) {
                this.attributes.deleteNode = this.createDeleteNode();
                this.node.append(this.attributes.deleteNode);
            }
        }

        if (this.attributes.deleteNode) {
            this.attributes.deleteNode.addEventListener('click', () => {
                this.delete();
            });
        }
    }

    static create(node, form, parentFormItem) {
        let formGroup;
        if (!node) {
            formGroup = new FormGroup(form, parentFormItem);
        } else {
            const settings = node.getElementsByClassName('custom-form-group__settings')[0];
            if (!settings) {
                formGroup = new FormGroup(form, parentFormItem, null, node);
            } else {
                let attributes = null;
                try {
                    attributes = JSON.parse(settings.getElementsByClassName('custom-form-group__settings__attributes')[0].textContent);
                } catch (e) { }
                settings.remove();
                formGroup = new FormGroup(form, parentFormItem, attributes, node);
            }
        }
        formGroup.scan();
        return formGroup;
    }

    createNode() {
        const node = document.createElement('div');
        node.classList.add('custom-form-group');

        return node;
    }

    scan() {
        this.items = this.items.concat(Form.scanFormGroup(this.node, this.form, this));
        this.items = this.items.concat(Form.scanSelect(this.node, this.form, this));
        this.items = this.items.concat(Form.scanCheckbox(this.node, this.form, this));
        this.items = this.items.concat(Form.scanFileInput(this.node, this.form, this));
        this.items = this.items.concat(Form.scanInput(this.node, this.form, this));
        this.items = this.items.concat(Form.scanTextarea(this.node, this.form, this));
    }

    searchFormItemByName(name, level = 0, depth = true) {
        if (level) {
            if (this.parentFormItem) {
                return this.parentFormItem.searchFormItemByName(name, level--, depth);
            }
        }
        return Form.searchFormItemByName(name, this.items, depth);
    }

    addItem(formItem) {
        this.items.push(formItem);
    }

    getValue() {
        return Form.getValue(this.items);
    }

    createDeleteNode() {
        const formGroupDelete = document.createElement('div');
        formGroupDelete.classList.add('custom-form-group__delete');

        return formGroupDelete;
    }

    delete() {
        if (this.parentFormItem) {
            this.parentFormItem.items.splice(this.parentFormItem.items.indexOf(this), 1);
        }
        this.items.forEach(item => item.delete());
        this.node.remove();
    }

    validate() {
        let validation = true;
        this.items.forEach(item => {
            if (!item.validate()) validation = false;
        });
        return validation;
    }
}