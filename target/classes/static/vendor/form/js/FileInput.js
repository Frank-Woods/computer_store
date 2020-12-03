class FileInput extends Input {
    constructor(
        form,
        parentFormItem,
        attributes,
        validation,
        node
    ) {

        if (attributes) {
            attributes.type = 'file';
        } else {
            attributes = { type: 'file' };
        }

        super(
            form,
            parentFormItem,
            attributes,
            validation,
            node
        );

        this.files = [];


        if (this.attributes.files) {
            this.attributes.files.forEach(file => {
                const splitedSrc = file.src.split('/');
                const filename = splitedSrc[splitedSrc.length - 1];
                const createdFile = this.createFile(file.src, filename, true, this.files.length);
                this.files.push(
                    {
                        id: file.id !== undefined ? file.id : null,
                        file: null,
                        node: createdFile
                    }
                );
                this.fileWrapper.node.append(createdFile.node);
            });
        }

        if (this.attributes.defaultValue) {
            this.files.push(
                {
                    id: null,
                    src: this.attributes.defaultValue,
                    file: null,
                    node: this.createFile()
                }
            );
        }

        this.input.onchange = this.inputChange.bind(this);
    }

    static create(node, form, parentFormItem) {
        if (!node) return new FileInput(form, parentFormItem);
        const settings = node.getElementsByClassName('custom-file-input__settings')[0];
        if (!settings) return new FileInput(form, parentFormItem, null, null, node);
        else {
            let attributes = null, validation = null;
            try {
                attributes = JSON.parse(settings.getElementsByClassName('custom-file-input__settings__attributes')[0].textContent);
            } catch (e) { }
            try {
                validation = JSON.parse(settings.getElementsByClassName('custom-file-input__settings__validation')[0].textContent);
            } catch (e) { }
            settings.remove();
            return new FileInput(
                form,
                parentFormItem,
                attributes,
                validation,
                node
            );
        }
    }

    linkingNodes() {
        this.node.append(this.createTitle());
        this.fileWrapper = this.createFileWrapper();
        this.buttons = { node: this.createButtonsWrapper() };
        this.node.append(this.fileWrapper.node);
        this.label.append(this.input);
        this.buttons.node.append(this.label);
        this.node.append(this.buttons.node);
    }

    createTitle() {
        const text = document.createElement('span');
        text.classList.add('custom-file-input__text');
        text.textContent = this.attributes.label;

        return text;
    }

    createFileWrapper() {
        const fileWrapper = document.createElement('div');
        fileWrapper.classList.add('custom-file-input__file-wrapper');
        fileWrapper.onwheel = this.fileWrapperWheel.bind(this);

        return {
            node: fileWrapper
        };
    }

    fileWrapperWheel(e) {
        this.fileWrapper.node.scrollTo(this.fileWrapper.node.scrollLeft + e.deltaY / 10, 0);
    }

    createFile(imageSrc = '/static/image/icons/file.png', filename = null, deletable = false, index = 0) {
        const file = document.createElement('div');
        file.classList.add('custom-file-input__file-wrapper__file');

        const fileIcon = document.createElement('img');
        fileIcon.classList.add('custom-file-input__file-wrapper__file__icon');
        fileIcon.alt = 'file';
        fileIcon.src = imageSrc;

        file.append(fileIcon);

        let fileDeleteButton = null;
        if (deletable) {
            fileDeleteButton = document.createElement('div');
            fileDeleteButton.classList.add('custom-file-input__file-wrapper__file__delete-button');

            const fileDeleteButtonIcon = document.createElement('i');
            fileDeleteButtonIcon.classList.add('fas', 'fa-trash-alt');

            fileDeleteButton.onclick = () => this.removeFile(index);

            fileDeleteButton.append(fileDeleteButtonIcon);
            file.append(fileDeleteButton);
        }

        return {
            node: file,
            button: fileDeleteButton
        };
    }

    createLabel() {
        const label = document.createElement('label');
        label.classList.add('custom-file-input__buttons-wrapper__label');

        const img = document.createElement('i');
        img.classList.add('fas', 'fa-upload', 'custom-file-input__buttons-wrapper__label__image');

        label.append(img);

        return label;
    }

    createButtonsWrapper() {
        const wrapper = document.createElement('div');
        wrapper.classList.add('custom-file-input__buttons-wrapper');

        return wrapper;
    }

    removeFile(index) {
        this.files[index].node.node.remove();
        this.files.splice(index, 1);
        this.files.forEach((file, i) => file.node.button.onclick = () => this.removeFile(i));
    }

    inputChange() {
        const files = this.input.files;
        if (files.length) {
            if (this.attributes.multiple) {
                this.inputMultipleFile(files);
            } else {
                const file = files[0];
                this.inputSingleFile(file);
            }
        }
        this.removeError();
        this.input.value = null;
    }

    inputSingleFile(file) {
        const reader = new FileReader();
        reader.onload = (event) => {

            const createdFile = {
                id: null,
                file: file,
                node: this.createFile(event.target.result, file.name, true, 0)
            };

            this.files.push(createdFile);
            this.fileWrapper.node.append(this.files[this.files.length - 1].node.node);
        }
        reader.readAsDataURL(file);
    }

    inputMultipleFile(files) {
        for (let i = 0; i < files.length; i++) {
            const file = files.item(i);
            const reader = new FileReader();
            reader.onload = (event) => {
                const createdFile = {
                    id: null,
                    file: file,
                    node: this.createFile(event.target.result, file.name, true, this.files.length)
                };

                this.files.push(createdFile);
                this.fileWrapper.node.append(this.files[this.files.length - 1].node.node);
            }
            reader.readAsDataURL(file);
        }
    }

    setValue(files) {
        this.files = files;
    }

    getValue() {
        return this.files.map(
            file => {
                return {
                    id: file.id,
                    file: file.file
                }
            }
        );
    }

    createError(message) {
        if (this.validation.error) {
            this.validation.error.text.textContent = message;
        } else {
            this.validation.error = Validation.createError(message);
            this.fileWrapper.node.after(this.validation.error.node);
        }
    }

    removeError() {
        if (this.validation.error) {
            this.validation.error.node.remove();
            this.validation.error = null;
        }
    }

    validate() {
        const message = this.validation.validate(this.files);
        if (message) {
            this.createError(message);
            return false;
        } else {
            this.removeError();
        }
        return true;
    }
}