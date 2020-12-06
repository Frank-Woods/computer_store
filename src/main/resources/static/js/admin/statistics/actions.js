const customForm = customForms[0];

if (customForm) {
    const dateStartInput = customForm.searchFormItemByName('start');
    const dateEndInput = customForm.searchFormItemByName('end');

    customForm.node.dispatchEvent(new CustomEvent("submit", { }));

    dateStartInput.attributes.onChangeValue = () => {
        if (dateStartInput.getValue() !== '' && dateEndInput.getValue() !== '') {
            customForm.node.dispatchEvent(new CustomEvent("submit", { }));
        }
    }

    dateEndInput.attributes.onChangeValue = () => {
        if (dateStartInput.getValue() !== '' && dateEndInput.getValue() !== '') {
            customForm.node.dispatchEvent(new CustomEvent("submit", { }));
        }
    }
}