function createPageInput(page) {
    const form = document.forms.namedItem('store');

    if (form) {
        let input = form.getElementsByTagName('input').namedItem('page');

        if (input) {
            input.value = page;
        } else {
            input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'page';
            input.value = page;

            form.append(input);
        }
    }
}