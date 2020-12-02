class Checkbox {
    constructor(
        form,
        parentFormItem,
        attributes,
        node
    ) {
        this.form = form;
        this.parentFormItem = parentFormItem;

        this.attributes = {
            name: attributes && attributes.name ? attributes.name : 'custom-checkbox',
            label: attributes && attributes.label ? attributes.label :  'custom-checkbox',
            defaultValue: attributes && attributes.defaultValue ? attributes.defaultValue : false
        }

        this.node = node ? node : this.createNode();
        this.input = this.createInput();
        this.label = this.createLabel();
        this.node.append(this.label);
    }

    static create(node, form, parentFormItem) {
        if (!node) return new Checkbox(form, parentFormItem);
        const settings = node.getElementsByClassName('custom-checkbox__settings')[0];
        if (!settings) return new Checkbox(form, parentFormItem, null, node);
        else {
            let attributes = null;
            try {
                attributes = JSON.parse(settings.getElementsByClassName('custom-checkbox__settings__attributes')[0].textContent);
            } catch (e) { }
            settings.remove();
            return new Checkbox(
                form,
                parentFormItem,
                attributes,
                node
            );
        }
    }

    createNode() {
        const node = document.createElement('div');
        node.classList.add('custom-checkbox');

        return node;
    }

    createLabel() {
        const label = document.createElement('label');
        label.classList.add('custom-checkbox__label');

        const text = document.createElement('span');
        text.classList.add('custom-checkbox__label__text');
        text.textContent = this.attributes.label;

        label.append(this.input, text);

        return label;
    }

    createInput() {
        const input = document.createElement('input');

        input.classList.add('custom-checkbox__label__input');
        input.name = this.attributes.name;
        input.type = 'checkbox';
        input.checked = this.attributes.defaultValue;

        return input;
    }

    setValue(value) {
        this.input.value = value;
    }

    getValue() {
        return this.input.checked;
    }

    validate() {
        return true;
    }
}