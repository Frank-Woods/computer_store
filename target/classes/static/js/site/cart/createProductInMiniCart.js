function createProductMiniCart(product) {
    const wrapper = document.getElementsByClassName('cart-products')[0];

    if (wrapper) {
        const li = document.createElement('li');
        li.classList.add('single-product-cart');

        const imageWrapper = document.createElement('div');
        imageWrapper.classList.add('cart-img');

        const imageHref = document.createElement('a');
        imageHref.href = `/store/product/${product.id}`;

        const img = document.createElement('img');
        img.src = `/image/productImage/${product.image}`;
        img.alt = 'product';

        imageHref.append(img);
        imageWrapper.append(imageHref);

        const cartTitle = document.createElement('div');
        cartTitle.classList.add('cart-title');

        const h4 = document.createElement('h4');

        const titleHref = document.createElement('a');
        titleHref.href = `/store/product/${product.id}`;
        titleHref.textContent = product.name;

        const cost = document.createElement('span');
        const costValue = product.discount > 0 ? product.discountCost : product.cost;
        cost.textContent = costValue + "₽ × " + product.count;

        h4.append(titleHref);
        cartTitle.append(h4, cost);

        const closeWrapper = document.createElement('div');
        closeWrapper.classList.add('cart-delete');
        closeWrapper.textContent = '×';
        closeWrapper.onclick = () => {
            deleteProductInLocalStorageCart(product.id, li);
            const totalCost = document.getElementsByClassName('cart-total-cost')[0];
            if (totalCost) totalCost.textContent = Number(totalCost.textContent.replace(/₽/, '')) - costValue * product.count + '₽';
        }

        const totalCost = document.getElementsByClassName('cart-total-cost')[0];
        if (totalCost) totalCost.textContent = Number(totalCost.textContent.replace(/₽/, '')) + costValue * product.count + '₽';

        li.append(imageWrapper, cartTitle, closeWrapper);

        wrapper.append(li);
    }
}