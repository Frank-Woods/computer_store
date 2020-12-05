<#import "../macro/main.ftlh" as main>

<#assign body>
    <div class="cart-area bg-gray pt-140">
        <div class="container d-flex justify-content-center">
            <form action="/store/payment" class="col-lg-6" method="post">
                <div class="payment-details mb-40">
                    <h4 class="checkout-title">Оплата</h4>
                    <ul>
                        <#assign total = 0>
                        <#assign discountTotal = 0>
                        <#list cartProducts as cp>
                            <li class="product-price">
                                ${cp.name} x ${cp.count}
                                <#if 0 < cp.discount>
                                    <span>${cp.discountCost} ₽</span>
                                    <span class="old-price pr-2">${cp.cost} ₽</span>
                                <#else>
                                    <span>${cp.cost} ₽</span>
                                </#if>
                                <#assign total = total + cp.cost * cp.count>
                                <#assign discountTotal = discountTotal + cp.discountCost * cp.count>
                            </li>
                        </#list>
                        <li class="pt-20">Итого без скидки <span>${total} ₽</span></li>
                        <li>Итого со скидкой <span>${discountTotal} ₽</span></li>
                    </ul>
                    <div class="total-order pb-15">
                        <ul>
                            <li>Итого <span>${discountTotal} ₽</span></li>
                        </ul>
                    </div>
                    <input type="text" name="address">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button class="mt-40 w-100 btn btn-outline-primary">Оплатить</button>
                </div>
            </form>
        </div>
    </div>
</#assign>

<@main.template
body = body
>
</@main.template>