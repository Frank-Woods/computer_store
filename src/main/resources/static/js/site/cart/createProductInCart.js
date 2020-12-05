function createProductInCart(product) {
    const cartWrapper = document.getElementsByClassName('cart-product-wrapper')[0];

    if (cartWrapper) {
        const tr = document.createElement('tr');

        const cartProduct = document.createElement('td');
        cartProduct.classList.add('cart-product');

        const productImgInfo = document.createElement('div');
        productImgInfo.classList.add('product-img-info-wrap');

        const productImg = document.createElement('div');
        productImg.classList.add('product-img');

        const productImgHref = document.createElement('a');
        productImgHref.href = `/store/product/${product.id}`;

        const img = document.createElement('img');
        img.src = `/image/productImage/${product.image}`;

        const productInfo = document.createElement('div');
        productInfo.classList.add('product-info');

        const productInfoH = document.createElement('h4');

        const productInfoHA = document.createElement('a');
        productInfoHA.href = `/store/product/${product.id}`;
        productInfoHA.textContent = product.name;

        productInfoH.append(productInfoHA);
        productInfo.append(productInfoH);

        productImgHref.append(img);
        productImg.append(productImgHref);
        productImgInfo.append(productImg, productInfo);
        cartProduct.append(productImgInfo);

        const productPrice = document.createElement('td');
        productPrice.classList.add('product-price');

        const productPriceSpan = document.createElement('span');
        productPriceSpan.classList.add('amount');
        const costValue = product.discount > 0 ? product.discountCost : product.cost;
        productPriceSpan.textContent = costValue + "₽";

        productPrice.append(productPriceSpan);

        const cartQuality = document.createElement('td');
        cartQuality.classList.add('cart-quality');

        const proDetailsQuality = document.createElement('div');
        proDetailsQuality.classList.add('pro-details-quality');

        const divPlusMinus = document.createElement('div');
        divPlusMinus.classList.add('cart-plus-minus');

        const inputCount = document.createElement('input');
        inputCount.classList.add('cart-plus-minus-box', 'plus-minus-width-inc');
        inputCount.name = 'count';
        inputCount.value = product.count;
        inputCount.readOnly = true;

        divPlusMinus.append(inputCount);
        proDetailsQuality.append(divPlusMinus);
        cartQuality.append(proDetailsQuality);

        const totalPrice = document.createElement('td');
        totalPrice.classList.add('product-total');

        const totalPriceSpan = document.createElement('span');
        totalPriceSpan.textContent = costValue * product.count + '₽';

        totalPrice.append(totalPriceSpan);

        const productRemove = document.createElement('td');
        productRemove.classList.add('product-remove');
        productRemove.onclick = () => {
            deleteProductInLocalStorageCart(product.id, tr);
        }

        const productRemoveImage = document.createElement('img');
        productRemoveImage.src = '/static/assets/images/icon-img/close.svg';
        productRemoveImage.classList.add('inject-me');

        productRemove.append(productRemoveImage);

        tr.append(cartProduct, productPrice, cartQuality, totalPrice, productRemove);
        cartWrapper.append(tr);

        var CartPlusMinus = $('.cart-plus-minus');
        CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');
        CartPlusMinus.append('<div class="inc qtybutton">+</div>');
        $(".qtybutton").on("click", function() {
            var $button = $(this);
            var oldValue = $button.parent().find("input").val();
            if ($button.text() === "+") {
                var newVal = parseFloat(oldValue) + 1;
                addToLocalStorageCart(product.id, newVal);
            } else {
                // Don't allow decrementing below zero
                if (oldValue > 1) {
                    var newVal = parseFloat(oldValue) - 1;
                    addToLocalStorageCart(product.id, newVal);
                } else {
                    newVal = 1;
                }
            }
            $button.parent().find("input").val(newVal);
        });
    }
}