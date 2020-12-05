function loadCartFromLocalStorage() {
    const cart = JSON.parse(localStorage.getItem('cart'));

    const request = new XMLHttpRequest();
    request.open('get', '/', true);

    request.setRequestHeader("products", cart.map(item => item.id));

    request.onload = () => {
        console.log(request.response);
    }
    request.send();
}