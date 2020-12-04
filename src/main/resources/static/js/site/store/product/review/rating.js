const rating = document.getElementsByClassName('star-box-wrap')[0];

if (rating) {
    let customInput = customForms[0].searchFormItemByName('rating');
    if (customInput == null) {
        customInput = new Input(customForms[0], customForms[0], {name: "rating", type: "hidden", defaultValue:5});
        customForms[0].items.push(customInput);
    }
    let checked = false;
    for (let i = 0; i < rating.childElementCount; i++) {
        rating.children[i].onclick = () => {
            for (let child of rating.children) { child.classList.remove('current') }
            if (!rating.children[i].classList.contains('current')) {
                rating.children[i].classList.add('current');
            }
            customInput.setValue(i + 1);
        }
        if (rating.children[i].classList.contains('current')) {
            checked = true;
        }
    }
    if (!checked && !rating.children[4].classList.contains('current')) {
        rating.children[4].classList.add('current');
    }
}