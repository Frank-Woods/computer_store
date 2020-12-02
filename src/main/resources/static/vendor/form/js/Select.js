class Select {
    constructor(
        form,
        parentFormItem,
        attributes,
        request,
        validation = null,
        node = null
    ) {
        this.form = form;
        this.parentFormItem = parentFormItem;

        this.attributes = {
            name: attributes && attributes.name ? attributes.name : 'custom-select',
            label: attributes && attributes.label ? attributes.label : null,
            defaultValue: attributes && attributes.defaultValue ? attributes.defaultValue : null,
            options: attributes && attributes.options ? attributes.options : [],
            inputValueFormat: {
                pattern: attributes && attributes.inputValueFormat && attributes.inputValueFormat.pattern ? attributes.inputValueFormat.pattern : ["id", "name"],
                type: attributes && attributes.inputValueFormat && attributes.inputValueFormat.type ? attributes.inputValueFormat.type : "json"
            },
            outputValueFormat: {
                pattern: attributes && attributes.outputValueFormat && attributes.outputValueFormat.pattern ? attributes.outputValueFormat.pattern : ["id"],
                type: attributes && attributes.outputValueFormat && attributes.outputValueFormat.type ? attributes.outputValueFormat.type : "var"
            },
            visibleValue: attributes && attributes.visibleValue ? attributes.visibleValue : "name",
            itemVisibleValue: attributes && attributes.itemVisibleValue ? attributes.itemVisibleValue : ["id", "name"],
            onChangeValue: attributes && attributes.onChangeValue ? attributes.onChangeValue : null,
        }

        this.request = {
            ajax: request && request.ajax ? request.ajax : false,
            url: request && request.url ? request.url : window.location,
            searchParams: request && request.searchParams ? request.searchParams : [],
            excludedValues: request && request.excludedValues ? request.excludedValues : [],
            urlAfterSelected: request && request.urlAfterSelected ? request.urlAfterSelected : null,
            tracking: request && request.tracking ? request.tracking : null,
            searchParam: request && (request.searchParam !== undefined && request.searchParam !== null) ? request.searchParam : true,
        }

        this.value = { };

        this.setValue(this.attributes.defaultValue);

        this.validation = new Validation(form, validation);
        if (validation && validation.error) {
            this.createError(validation.error);
        }

        if (typeof this.request.excludedValues === 'string') {
            if (typeof excludedValues !== "undefined" && excludedValues[this.request.excludedValues]) {
                this.request.excludedValues = excludedValues[this.request.excludedValues];
            } else {
                this.request.excludedValues = [];
            }
        }

        this.node = node ? node : this.createNode();
        this.content = this.createContent();
        this.inputs = this.createInputs();
        this.node.append(this.content.node);
        this.node.append(this.inputs.node);
        this.node.setAttribute('tabindex', '1');
        if (!this.request.searchParam) this.inputs.search.disabled = true;
        this.load();
    }

    static create(node, form, parentFormItem) {
        if (!node) return new Select(form, parentFormItem);
        const settings = node.getElementsByClassName('custom-select__settings')[0];
        if (!settings) return new Select(form, parentFormItem, null, null, null, node);
        else {
            const params = Select.parseSettings(settings);
            settings.remove();
            return new Select(
                form,
                parentFormItem,
                params.attributes,
                params.request,
                params.validation,
                node
            );
        }
    }

    static parseSettings(node) {
        let settings = {
            attributes: null,
            request: null,
            validation: null
        };
        try {
            settings.attributes = JSON.parse(node.getElementsByClassName('custom-select__settings__attributes')[0].textContent);
        } catch (e) { }
        try {
            settings.request = JSON.parse(node.getElementsByClassName('custom-select__settings__request')[0].textContent);
        } catch (e) { }
        try {
            settings.validation = JSON.parse(node.getElementsByClassName('custom-select__settings__validation')[0].textContent);
        } catch (e) { }

        return settings;
    }

    createNode() {
        const node = document.createElement('div');
        node.classList.add('custom-select');

        return node;
    }

    createContent() {
        const content = document.createElement('div');
        content.classList.add('custom-select__content');

        const contentItems = document.createElement('div');
        contentItems.classList.add('custom-select__content__items');

        content.append(contentItems);

        return {
            node: content,
            items: {
                node: contentItems,
                values: []
            }
        };
    }

    createInputs() {
        const label = document.createElement('label');
        label.classList.add('custom-select__label');

        const inputSearch = document.createElement('input');
        inputSearch.type = 'text';
        inputSearch.name = 'searchParam';
        inputSearch.oninput = () => this.inputChange();
        inputSearch.autocomplete = 'off';
        if (this.attributes.defaultValue) {
            if (this.attributes.inputValueFormat.type === 'var') {
                inputSearch.value = this.attributes.defaultValue;
                this.value = this.attributes.defaultValue;
            } else {
                inputSearch.value = this.attributes.defaultValue[this.attributes.visibleValue];
                this.value[this.attributes.visibleValue] = this.attributes.defaultValue[this.attributes.visibleValue];
            }
        }

        label.append(inputSearch);

        let labelText = null;

        if (this.attributes.label) {
            labelText = document.createElement('span');
            labelText.classList.add('custom-select__label__text');
            labelText.textContent = this.attributes.label;

            label.append(labelText);
        }

        return {
            node: label,
            search: inputSearch,
            labelText: labelText
        }
    }

    inputChange() {
        this.removeError();
        if (this.getValue()) {
            if (this.request.excludedValues.indexOf(this.getValue()) > -1) {
                this.request.excludedValues.splice(this.request.excludedValues.indexOf(this.getValue()), 1);
            }
        }
        const value = {};
        value[this.attributes.visibleValue] = this.inputs.search.value;
        this.setValue(value);

        if (this.getValue() && this.attributes.outputValueFormat.type === 'var') {
            this.request.excludedValues.push(this.value);
        }
        this.load();
    }

    load() {
        if (this.request.ajax) {
            return this.sendRequest()
                .then(
                    data => {
                        this.clearContent();
                        data.forEach(item => {
                            const contentItem = this.createContentItem(item);
                            this.content.items.node.append(contentItem);
                            this.content.items.values.push(contentItem);
                        });
                    }
                );
        } else {
            return new Promise((resolve, reject) => {
                this.clearContent();
                let content = [...this.attributes.options];

                if (this.request.searchParam && this.inputs.search.value.length) {
                    content = content.filter(value => new RegExp(this.inputs.search.value.toUpperCase()).test(this.attributes.inputValueFormat.type === 'var' ? value.toUpperCase() : value.name.toUpperCase()));
                }

                content.forEach(item => {
                    const contentItem = this.createContentItem(item);
                    this.content.items.node.append(contentItem);
                    this.content.items.values.push(contentItem);
                });

                resolve();
            });
        }
    }

    sendRequest() {
        return new Promise((resolve, reject) => {
            let url = new URL(this.request.url);
            if (this.request.searchParam) url.searchParams.set('searchParam', this.inputs.search.value);
            if (this.request.tracking) {
                const item = this.parentFormItem.searchFormItemByName(this.request.tracking);
                if (item !== null) url.searchParams.set('tracking', item.getValue());
            }
            this.request.searchParams.forEach(searchParam => {
                const formItem = this.parentFormItem.searchFormItemByName(searchParam.name, searchParam.level ? searchParam.level : 0, searchParam.depth ? searchParam.depth : true);
                url.searchParams.set(searchParam.requestName, formItem.getValue());
            });
            let excluded = [...this.request.excludedValues];
            if (this.getValue() && excluded.indexOf(this.getValue()) > -1) {
                excluded.splice(excluded.indexOf(this.getValue()), 1);
            }
            url.searchParams.set('excluded', excluded.map(exc => JSON.stringify(exc)));

            const request = new XMLHttpRequest();
            request.open('get', url.href, true);
            request.setRequestHeader('Content-Type', 'application/json');
            request.onload = () => {
                if (request.status === 200) resolve(JSON.parse(request.response));
                else reject(new Error('bad request'));
            };
            request.send();
        });
    }

    clearContent() {
        while (this.content.items.values.length) {
            this.content.items.values.pop().remove();
        }
    }

    createContentItem(data) {
        const item = document.createElement('div')
        item.classList.add('custom-select__content__item');

        if (this.attributes.inputValueFormat.type === 'var') {
            const itemElement = document.createElement('span');
            if (data instanceof Object) {
                itemElement.textContent = data && data[this.attributes.itemVisibleValue[0]] !== undefined && data[this.attributes.itemVisibleValue[0]] !== null ? data[this.attributes.itemVisibleValue[0]] : null;
            } else {
                itemElement.textContent = data ? data : null;
            }
            item.append(itemElement);
        } else {
            for (let index of this.attributes.itemVisibleValue) {
                const itemElement = document.createElement('span');
                itemElement.textContent = data && data[index] ? data[index] : null;
                item.append(itemElement);
            }
        }

        item.onclick = () => this.contentItemClick(data);

        return item;
    }

    contentItemClick(data) {
        this.setValue(data);
        this.node.blur();
        this.removeError();
        if (this.request.excludedValues.indexOf(this.getValue()) === -1) {
            this.request.excludedValues.push(this.getValue());
        }
        if (this.request.urlAfterSelected && this.getValue()) {
            this.fillingData();
        }
    }

    gettingDataToFill() {
        return new Promise((resolve, reject) => {
            const xhr = new XMLHttpRequest();
            const url = new URL(this.request.urlAfterSelected);
            url.searchParams.set(this.attributes.name, this.getValue());
            xhr.open('get', url.href, true);
            xhr.onload = () => {
                resolve(JSON.parse(xhr.response));
            }
            xhr.send();
        });
    }

    fillingData() {
        this.gettingDataToFill()
            .then(
                data => {
                    for (let key in data) {
                        const formItem = this.parentFormItem.searchFormItemByName(key);
                        if (formItem) formItem.setValue(data[key]);
                    }
                }
            );
    }

    createError(message) {
        if (this.validation.error) {
            this.validation.error.text.textContent = message;
        } else {
            this.validation.error = Validation.createError(message);
            this.inputs.search.after(this.validation.error.node);
            this.validation.error.text.style.maxWidth = this.node.offsetWidth - this.validation.error.node.offsetWidth + 'px';
        }
    }

    removeError() {
        if (this.validation.error) {
            this.validation.error.node.remove();
            this.validation.error = null;
        }
    }

    setValue(data) {
        if (this.attributes.inputValueFormat.type === "var") {
            if (data instanceof Object) {
                this.value = data && data[this.attributes.inputValueFormat.pattern[0]] !== undefined && data[this.attributes.inputValueFormat.pattern[0]] !== null ? data[this.attributes.inputValueFormat.pattern[0]] : null;
                if (this.inputs) this.inputs.search.value = data && data[this.attributes.visibleValue] !== undefined && data[this.attributes.visibleValue] !== null ? data[this.attributes.visibleValue] : null;
            } else {
                this.value = data ? data : null;
                if (this.inputs) this.inputs.search.value = data ? data : null;
            }
        } else {
            if (data instanceof Object) {
                for (let index of this.attributes.inputValueFormat.pattern) {
                    this.value[index] = data && data[index] !== undefined && data[index] !== null ? data[index] : null;
                    if (this.inputs) this.inputs.search.value = data && data[this.attributes.visibleValue] !== undefined && data[this.attributes.visibleValue] !== null ? data[this.attributes.visibleValue] : null;
                }
            } else {
                this.value[this.attributes.inputValueFormat.pattern[0]] = data ? data : null;
                if (this.inputs) this.inputs.search.value = data ? data : null;
            }
        }
        if (this.value) {
            if (this.attributes.outputValueFormat.type === 'var') {
                if (this.value[this.attributes.outputValueFormat.pattern[0]] !== null) {
                    if (this.attributes.onChangeValue) this.attributes.onChangeValue(this.getValue());
                }
            } else {
                let flag = true;
                for (let key of this.attributes.outputValueFormat.pattern) {
                    if (this.value[key] === null) {
                        flag = false;
                        break;
                    }
                }
                if (flag && this.attributes.onChangeValue) this.attributes.onChangeValue(this.getValue());
            }
        }
    }

    getValue() {
        const outputValue = { };
        for (let index in this.attributes.outputValueFormat.pattern) {
            if (this.attributes.outputValueFormat.type === "var") {
                if (this.attributes.inputValueFormat.type === "var") {
                    return this.value;
                }
                return this.value[this.attributes.outputValueFormat.pattern[index]];
            }
            outputValue[this.attributes.outputValueFormat.pattern[index]] = this.value[this.attributes.outputValueFormat.pattern[index]];
        }
        return outputValue;
    }

    delete() {
        if (this.getValue()) {
            this.request.excludedValues.splice(this.request.excludedValues.indexOf(this.getValue()), 1);
            this.setValue(null);
        }
        this.node.remove();
    }

    validate() {
        const message = this.validation.validate(this.getValue());
        if (message) {
            this.createError(message);
            return false;
        } else {
            this.removeError();
        }
        return true;
    }
}