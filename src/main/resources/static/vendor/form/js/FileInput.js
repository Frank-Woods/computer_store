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
                this.fileWrapper.node.append(createdFile);
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

        this.actionHistory = [
            // {
            //     actionType: FileInputActionType,
            //     file: {}
            //     position: 0
            // }
        ];

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
        this.buttons.returnButton = this.createReturnButton();
        this.node.append(this.fileWrapper.node);
        this.label.append(this.input);
        this.buttons.node.append(this.label, this.buttons.returnButton);
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

        const preview = this.createPreview();

        fileWrapper.append(preview);

        return {
            node: fileWrapper,
            preview: preview
        };
    }

    fileWrapperWheel(e) {
        this.fileWrapper.node.scrollTo(this.fileWrapper.node.scrollLeft + e.deltaY / 10, 0);
    }

    createPreview() {
        const preview = document.createElement('div');
        preview.classList.add('custom-file-input__file-wrapper__preview');

        return preview;
    }

    createFile(imageSrc = '/static/image/icons/file.png', filename = null, deletable = false, index = 0) {
        const file = document.createElement('div');
        file.classList.add('custom-file-input__file-wrapper__file');

        const fileIcon = document.createElement('img');
        fileIcon.classList.add('custom-file-input__file-wrapper__file__icon');
        fileIcon.alt = 'file';
        fileIcon.src = imageSrc;

        const fileName = document.createElement('div');
        fileName.classList.add('custom-file-input__file-wrapper__file__name');

        const fileNameText = document.createElement('span');
        fileNameText.classList.add('custom-file-input__file-wrapper__file__name__text');
        if (filename) fileNameText.textContent = filename;

        fileName.append(fileNameText);

        file.append(fileName, fileIcon);

        let fileDeleteButton = null;
        if (deletable) {
            fileDeleteButton = document.createElement('div');
            fileDeleteButton.classList.add('custom-file-input__file-wrapper__file__delete-button');
            fileDeleteButton.onclick = () => this.removeFile(index);

            file.append(fileDeleteButton);
        }

        return file;
    }

    createLabel() {
        const label = document.createElement('label');
        label.classList.add('custom-file-input__buttons-wrapper__label');

        const img = document.createElement('i');
        img.classList.add('ion-upload', 'custom-file-input__buttons-wrapper__label__image');

        label.append(img);

        return label;
    }

    createButtonsWrapper() {
        const wrapper = document.createElement('div');
        wrapper.classList.add('custom-file-input__buttons-wrapper');

        return wrapper;
    }

    createReturnButton() {
        const returnButton = document.createElement('div');
        returnButton.classList.add('custom-file-input__buttons-wrapper__return-button');
        returnButton.onclick = this.returnAction.bind(this);

        const returnButtonImg = document.createElement('i');
        returnButtonImg.classList.add('ion-arrow-return-left', 'custom-file-input__buttons-wrapper__return-button__image');

        returnButton.append(returnButtonImg);

        return returnButton;
    }

    removeFile(index) {
        this.files[index].node.remove();
        this.actionHistory.push({ actionType: FileInputActionType.REMOVE_FILE, file: this.files[index], position: index});
        this.files.splice(index, 1);
    }

    replaceFile(createdFile, replacedFile) {
        replacedFile.node.remove();
        this.actionHistory.push(
            {
                actionType: FileInputActionType.REPLACE_FILE,
                file: createdFile,
                position: 0,
                replacedFile: replacedFile
            }
        );

    }

    returnAction() {
        if (this.actionHistory.length) {
            this.removeError();
            const action = this.actionHistory.pop();
            if (action.actionType === FileInputActionType.UPLOAD_FILES) {
                const removedFiles = this.files.splice(action.position);
                removedFiles.forEach(removedFile => removedFile.node.remove());
            } else if (action.actionType === FileInputActionType.REMOVE_FILE) {
                if (action.position) {
                    this.files[action.position - 1].node.after(action.file.node);
                } else {
                    this.fileWrapper.node.append(action.file.node);
                }
                this.files.splice(action.position, 0, action.file);
            } else if (action.actionType === FileInputActionType.REPLACE_FILE) {
                action.file.node.remove();
                this.files.splice(action.position, 1, action.replacedFile);
                this.fileWrapper.node.append(action.replacedFile.node);
            }
        }
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

            if (this.files.length) {
                this.replaceFile(createdFile, this.files.pop());
            } else {
                this.actionHistory.push(
                    {
                        actionType: FileInputActionType.UPLOAD_FILES,
                        file: createdFile,
                        position: 0
                    }
                );
            }

            this.files.push(createdFile);
            this.fileWrapper.node.append(this.files[this.files.length - 1].node);
        }
        reader.readAsDataURL(file);
    }

    inputMultipleFile(files) {
        this.actionHistory.push(
            {
                actionType: FileInputActionType.UPLOAD_FILES,
                file: null,
                position: this.files.length
            }
        );
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
                this.fileWrapper.node.append(this.files[this.files.length - 1].node);
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
        if (!this.fileWrapper.preview.classList.contains('custom-file-input__file-wrapper__preview-error')) {
            this.fileWrapper.preview.classList.add('custom-file-input__file-wrapper__preview-error');
        }
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
            this.fileWrapper.preview.classList.remove('custom-file-input__file-wrapper__preview-error');
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