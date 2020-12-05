function loadCartFromLocalStorageToMiniCart() {
    const cart = JSON.parse(localStorage.getItem('cart'));

    loadProductFromDataBaseByProducts(cart)
        .then(
            data => {
                data.forEach(product => createProductMiniCart(product));
            }
        );
}

function loadCartFromLocalStorageToCart() {
    const cart = JSON.parse(localStorage.getItem('cart'));

    loadProductFromDataBaseByProducts(cart)
        .then(
            data => {
                data.forEach(product => createProductInCart(product));
            }
        );
}

function loadProductFromDataBaseByProducts(products) {
    return new Promise((resolve, reject) => {
        const url = new URL(window.location.origin + '/basket/get/product');
        url.searchParams.set('products', JSON.stringify(products));

        const request = new XMLHttpRequest();
        request.open('get', url.href, true);

        request.onload = () => {
            resolve(JSON.parse(request.response));
        }
        request.send();
    });
}

loadCartFromLocalStorageToMiniCart();