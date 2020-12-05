function addToLocalStorageCart(id, newCount = null) {
    if (isAuthorized) {
        addToDatabaseCart(id, newCount);
    } else {
        const cart = JSON.parse(localStorage.getItem('cart'));
        let newItem = true;
        for (let cartItem of cart) {
            if (cartItem.id === id) {
                let countValue = 1;
                if (newCount) {
                    countValue = newCount;
                } else if (cartItem.count) {
                    countValue = cartItem.count;
                }
                newItem = false;
                cartItem.count = countValue
                break;
            }
        }
        if (newItem) {
            cart.push({
                id: id,
                count: 1
            });

            loadProductFromDataBaseByProducts([cart[cart.length - 1]])
                .then(
                    data => {
                        createProductMiniCart(data[0]);
                    }
                )

            new Toast(ToastEvent.SUCCESS, 'Корзина', 'Продукт добавлен в корзину');
        }
        localStorage.setItem('cart', JSON.stringify(cart));
    }
}

function addToDatabaseCart(id, newCount = null) {
    const request = new XMLHttpRequest();
    request.open('post', '/basket/add/product', true);
    request.setRequestHeader(
        document.getElementsByTagName('meta').namedItem('_csrf_header').content,
        document.getElementsByTagName('meta').namedItem('_csrf').content
    );
    request.onload = () => {
        if (request.response !== "") {
            new Toast(ToastEvent.SUCCESS, 'Корзина', 'Продукт добавлен в корзину');
            createProductMiniCart(JSON.parse(request.response));
        }
    };
    const formData = new FormData();
    let countValue = 1;
    if (newCount) {
        countValue = newCount;
    }
    formData.append('product', new Blob([JSON.stringify({id: id, count: countValue})], {type: 'application/json'}));
    request.send(formData);
}

function changeProduct(id) {
    const wrapper = document.getElementsByClassName(`product-item-${id}`)[0];
    if (wrapper) {
        const btn = wrapper.getElementsByClassName('product-action-cart-button')[0];
        if (btn) {
            btn.onclick = () => { };
            const check = document.createElement('a');
            check.classList.add('product-action-cart-button');
            check.href = '/shop/cart';
            check.textContent = 'В корзине';

            btn.before(check);
            btn.remove();
        }
    }
}