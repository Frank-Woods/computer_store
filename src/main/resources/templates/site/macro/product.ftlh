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

<#macro detailCart
    prod
>
    <div class="shop-list-wrap mb-50">
        <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-4">
                <div class="product-list-img">
                    <a href="/store/product/${prod.id}">
                        <img src="/image/productImage/${prod.image}" alt="">
                    </a>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-sm-8">
                <div class="shop-list-content ml-20">
                    <h3><a href="/store/product/${prod.id}">${prod.name}</a></h3>
                    <div class="pro-list-price">
                        <#if 0 < prod.discount>
                            <span>${prod.discountCost} ₽</span>
                            <span class="old-price">${prod.cost} ₽</span>
                        <#else>
                            <span>${prod.cost} ₽</span>
                        </#if>
                    </div>
                    <p>${prod.description}</p>
                    <div class="product-list-action">
                        <div class="product-action-cart">
                            <button title="Add to Cart" class="m-0">В корзину</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>