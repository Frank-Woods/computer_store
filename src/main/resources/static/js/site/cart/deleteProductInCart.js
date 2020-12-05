function deleteProductInLocalStorageCart(id, wrapper) {
    if (isAuthorized) {
        deleteProductInDatabaseCart(id);
    } else {
        const cart = JSON.parse(localStorage.getItem('cart'));
        const i = cart.map(item => item.id).indexOf(id);
        cart.splice(i, 1);
        localStorage.setItem('cart', JSON.stringify(cart));
    }

    if (wrapper) {
        wrapper.remove();
    }
}

function deleteProductInDatabaseCart(id) {
    const request = new XMLHttpRequest();
    request.open('post', '/basket/delete/product', true);
    request.setRequestHeader(
        document.getElementsByTagName('meta').namedItem('_csrf_header').content,
        document.getElementsByTagName('meta').namedItem('_csrf').content
    );
    const formData = new FormData();
    formData.append('product', new Blob([JSON.stringify({id: id, count: null})], {type: 'application/json'}));
    request.send(formData);
}