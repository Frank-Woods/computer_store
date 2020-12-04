function addToLocalStorageCart(id) {
    const cart = JSON.parse(localStorage.getItem('cart'));
    const count = document.getElementsByClassName('product-count')[0];
    let newItem = true;
    for (let cartItem of cart) {
        if (cartItem.id === id) {
            let countValue = 1;
            //if (count && count.value > 0) countValue = count.value;
            //else if (cartItem.count) countValue = cartItem.count;
            newItem = false;
            cartItem.count = countValue
            break;
        }
    }
    if (newItem) {
        cart.push({
            id: id,
            count: 1 //count && count.value > 0 ? count.value : 1
        });
        changeProduct(id);
        new Toast(ToastEvent.SUCCESS, 'Корзина', 'Продукт добавлен в корзину');
    }
    localStorage.setItem('cart', JSON.stringify(cart));
}

function addToDatabaseCart(id) {
    const request = new XMLHttpRequest();
    request.open('post', '/basket/add/product', true);
    request.setRequestHeader(
        document.getElementsByTagName('meta').namedItem('_csrf_header').content,
        document.getElementsByTagName('meta').namedItem('_csrf').content
    );
    request.onload = () => {
        new Toast(ToastEvent.SUCCESS, 'Корзина', 'Продукт добавлен в корзину');
        changeProduct(id);
    };
    const formData = new FormData();
    let countValue = 1;
    //if (count && count.value > 0) countValue = count.value;
    formData.append('product', new Blob([JSON.stringify({id: id, count: countValue})], {type: 'application/json'}));
    request.send(formData);
}

function changeProduct(id) {
    const wrapper = document.getElementsByClassName(`product-item-${id}`)[0];
    if (wrapper) {
        const btn = wrapper.getElementsByClassName('btn-add-to-cart')[0];
        if (btn) {
            btn.onclick = () => { };
            const check = document.createElement('a');
            check.classList.add('btn-add-to-cart');
            check.href = '/shop/cart';

            const icon = document.createElement('i');
            icon.classList.add('ion-android-checkmark-circle');

            check.append(icon);

            btn.before(check);
            btn.remove();
        }
    }
}