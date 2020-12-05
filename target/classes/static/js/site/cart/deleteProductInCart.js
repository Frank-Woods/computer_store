function deleteProductInLocalStorageCart(id, wrapper) {
    const cart = JSON.parse(localStorage.getItem('cart'));
    const i = cart.map(item => item.id).indexOf(id);
    cart.splice(i, 1);
    if (wrapper) {
        wrapper.remove();
    }
    localStorage.setItem('cart', JSON.stringify(cart));
}