<#macro indexCart
    prod
>
    <div class="col-lg-3 col-md-6 col-sm-6 col-12">
        <div class="product-wrap product-item-${prod.id} mb-50">
            <div class="product-img product-img-zoom mb-25">
                <a href="/store/product/${prod.id}">
                    <img src="/image/productImage/${prod.image}" alt="">
                </a>
            </div>
            <div class="product-content">
                <h4><a href="/store/product/${prod.id}">${prod.name}</a></h4>
                <div class="product-price">
                    <#if 0 < prod.discount>
                        <span>${prod.discountCost} ₽</span>
                        <span class="old-price">${prod.cost} ₽</span>
                    <#else>
                        <span>${prod.cost} ₽</span>
                    </#if>
                </div>
            </div>
            <div class="product-action-position-1 text-center">
                <div class="product-content">
                    <h4><a href="/store/product/${prod.id}">${prod.name}</a></h4>
                    <div class="product-price">
                        <#if 0 < prod.discount>
                            <span>${prod.discountCost} ₽</span>
                            <span class="old-price">${prod.cost} ₽</span>
                        <#else>
                            <span>${prod.cost} ₽</span>
                        </#if>
                    </div>
                </div>
                <div class="product-action-wrap">
                    <div class="product-action-cart">
                        <button title="Add to Cart" class="product-action-cart-button" onclick="addToLocalStorageCart(${prod.id})">В корзину</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>