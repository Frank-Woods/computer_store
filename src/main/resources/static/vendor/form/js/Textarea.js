class Textarea {
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
            name: attributes && attributes.name ? attributes.name : 'custom-textarea',
            label: attributes && attributes.label ? attributes.label : 'custom-textarea',
            defaultValue: attributes && attributes.defaultValue ? attributes.defaultValue : '',
        }
        this.validation = new Validation(form, validation);

        this.node = node ? node : this.createNode();
        this.label = this.createLabel();
        this.textarea = this.createTextarea();

        this.label.append(this.textarea);
        this.node.append(this.label);
    }

    static create(node, form, parentFormItem) {
        if (!node) return new Textarea(form, parentFormItem);
        const settings = node.getElementsByClassName('custom-textarea__settings')[0];
        if (!settings) return new Textarea(form, parentFormItem, null, null, node);
        else {
            let attributes = null, validation = null;
            try {
                attributes = JSON.parse(settings.getElementsByClassName('custom-textarea__settings__attributes')[0].textContent);
            } catch (e) { }
            try {
                validation = JSON.parse(settings.getElementsByClassName('custom-textarea__settings__validation')[0].textContent);
            } catch (e) { }
            settings.remove();
            return new Textarea(
                form,
                parentFormItem,
                attributes,
                validation,
                node
            );
        }
    }

    createNode() {
        const node = document.createElement('div');
        node.classList.add('custom-textarea');

        return node;
    }

    createLabel() {
        const label = document.createElement('label');
        label.classList.add('custom-textarea__label');

        const text = document.createElement('span');
        text.classList.add('custom-textarea__label__text');
        text.textContent = this.attributes.label;

        label.append(text);

        return label;
    }

    createTextarea() {
        const textarea = document.createElement('textarea');
        textarea.classList.add('custom-textarea__label__textarea');
        textarea.name = this.attributes.name;
        textarea.textContent = this.attributes.defaultValue;
        textarea.oninput = this.textareaChange.bind(this);

        return textarea;
    }

    textareaChange() {
        this.removeError();
    }

    setValue(value) {
        this.textarea.value = value;
    }

    getValue() {
        return this.textarea.value;
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

    validate() {
        const message = this.validation.validate(this.textarea.value);
        if (message) {
            this.createError(message);
            return false;
        } else {
            this.removeError();
        }
        return true;
    }
}