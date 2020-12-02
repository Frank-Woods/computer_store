class Input {
    constructor(
        form,
        parentFormItem,
        attributes,
        validation,
        node
    ) {
        this.form = form;
        this.parentFormItem = parentFormItem;

        this.attributes = {
            name: attributes && attributes.name ? attributes.name : 'custom-input',
            label: attributes && attributes.label ? attributes.label :  null,
            type: attributes && attributes.type ? attributes.type :  'text',
            defaultValue: attributes && attributes.defaultValue ? attributes.defaultValue :  '',
            multiple: attributes && attributes.multiple ? attributes.multiple : false,
            accept: attributes && attributes.accept ? attributes.accept : null,
            files: attributes && attributes.files ? attributes.files : null,
            controllers: attributes && attributes.controllers ? attributes.controllers : false
        }
        this.validation = new Validation(form, validation);

        if (validation && validation.error) {
            this.createError(validation.error);
        }

        this.node = node ? node : this.createNode();
        this.input = this.createInput();

        if (this.attributes.type === 'hidden') {
            this.node.remove();
        } else {
            this.label = this.createLabel();
            this.linkingNodes();
        }
        if (this.attributes.type === 'number' && this.attributes.controllers) {
            this.controllers = this.createControllers();
            this.node.firstElementChild.before(this.controllers.reduce);
            this.node.append(this.controllers.increase);
        }
    }

    static create(node, form, parentFormItem) {
        if (!node) return new Input(form, parentFormItem);
        const settings = node.getElementsByClassName('custom-input__settings')[0];
        if (!settings) return new Input(form, parentFormItem, null, null, node);
        else {
            let attributes = null, validation = null;
            try {
                attributes = JSON.parse(settings.getElementsByClassName('custom-input__settings__attributes')[0].textContent);
            } catch (e) { }
            try {
                validation = JSON.parse(settings.getElementsByClassName('custom-input__settings__validation')[0].textContent);
            } catch (e) { }
            settings.remove();
            if (attributes && attributes.type && attributes.type === 'file') {
                node.classList.replace('custom-input', 'custom-file-input');
                return new FileInput(
                    form,
                    parentFormItem,
                    attributes,
                    validation,
                    node
                );
            }
            return new Input(
                form,
                parentFormItem,
                attributes,
                validation,
                node
            );
        }
    }

    inputChange(e) {
        this.removeError();
    }

    linkingNodes() {
        this.label.append(this.input);
        this.node.append(this.label);
    }

    createNode() {
        const node = document.createElement('div');
        node.classList.add('custom-input');

        return node;
    }

    createLabel() {
        const label = document.createElement('label');
        label.classList.add('custom-input__label');

        if (this.attributes.label) {
            const text = document.createElement('span');
            text.classList.add('custom-input__label__text');
            text.textContent = this.attributes.label;

            label.append(text);
        }

        return label;
    }

    createControllers() {
        return {
            increase: this.createController(NumberInputControllerType.INCREASE, this.increaseControllerClick.bind(this)),
            reduce: this.createController(NumberInputControllerType.REDUCE, this.reduceControllerClick.bind(this))
        };
    }

    createController(type, action) {
        const wrapper = document.createElement('div');
        wrapper.classList.add(type);

        const icon = document.createElement('i');
        if (type === NumberInputControllerType.INCREASE) {
            icon.classList.add('ion-plus-round');
        } else if (type === NumberInputControllerType.REDUCE) {
            icon.classList.add('ion-minus-round');
        }
        wrapper.append(icon);
        
        wrapper.onclick = () => action();

        return wrapper;
    }
    
    increaseControllerClick() {
        if (this.getValue()) {
            if (this.validation.settings.max == null || this.validation.settings.max > Number(this.getValue())) {
                this.input.value = Number(this.getValue()) + 1;
                this.input.onchange();
            }
        }
    }

    reduceControllerClick() {
        if (this.getValue()) {
            if (this.validation.settings.min == null || this.validation.settings.min < Number(this.getValue())) {
                this.input.value = Number(this.getValue()) - 1;
                this.input.onchange();
            }
        }
    }

    createInput() {
        const input = document.createElement('input');

        input.classList.add('custom-input__label__input');
        input.name = this.attributes.name;
        input.type = this.attributes.type;
        input.value = this.attributes.defaultValue;
        input.oninput = this.inputChange.bind(this);
        if (this.attributes.type === 'number') {
            input.value = Number(this.attributes.defaultValue);
            input.addEventListener('change', this.numberInputChange.bind(this));
        }
        if (this.attributes.multiple) {
            input.multiple = this.attributes.multiple;
        }
        if (this.attributes.accept) {
            input.accept = this.attributes.accept;
        }
        if (this.validation.settings.maxLength) {
            input.maxLength = this.validation.settings.maxLength;
        }

        return input;
    }

    numberInputChange() {
        if (this.validation.settings.min && this.validation.settings.min > this.getValue()) {
            this.input.value = this.validation.settings.min;
        } else if (this.validation.settings.max && this.validation.settings.max < this.getValue()) {
            this.input.value = this.validation.settings.max;
        }
    }

    setValue(value) {
        this.input.value = value;
    }

    getValue() {
        return this.input.value;
    }

    createError(message) {
        if (this.validation.error) {
            this.validation.error.text.textContent = message;
        } else {
            this.validation.error = Validation.createError(message);
            this.node.append(this.validation.error.node);
        }
    }

    removeError() {
        if (this.validation.error) {
            this.validation.error.node.remove();
            this.validation.error = null;
        }
    }

    delete() {
        if (this.parentFormItem) {
            this.parentFormItem.items.splice(this.parentFormItem.items.indexOf(this), 1);
        }
        this.node.remove();
    }

    validate() {
        const message = this.validation.validate(this.input.value);
        if (message) {
            this.createError(message);
            return false;
        } else {
            this.removeError();
        }
        return true;
    }
}