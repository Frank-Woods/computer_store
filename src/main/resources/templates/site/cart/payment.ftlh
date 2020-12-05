<#import "../macro/main.ftlh" as main>

<#assign body>
    <div class="cart-area bg-gray pt-140">
        <div class="container d-flex justify-content-center">
            <form action="#" class="col-lg-6">
                <div class="payment-details mb-40">
                    <h4 class="checkout-title">Оплата</h4>
                    <ul>
                        <#list cartProducts as cp>
                            <li>
                                ${cp.name} x ${cp.count}
                                <#if 0 < cp.discount>
                                    <span>${cp.discountCost} ₽</span>
                                    <span class="old-price">${cp.cost} ₽</span>
                                <#else>
                                    <span>${cp.cost} ₽</span>
                                </#if>
                            </li>
                        </#list>
                        <li>Продукт x 1 <span>$155.00</span></li>
                        <li>Продукт x 2 <span>$152.00</span></li>
                        <li>Продукт x 1 <span>$185.00</span></li>
                        <li>Продукт x 5 <span>$295.00</span></li>
                        <li class="pt-20">Итого без скидки <span>$295.00</span></li>
                        <li>Итого со скидкой <span>$295.00</span></li>
                    </ul>
                    <div class="total-order">
                        <ul>
                            <li>Итого <span>$170.00</span></li>
                        </ul>
                    </div>
                    <button class="mt-40 w-100">Оплатить</button>
                </div>
            </form>
        </div>
    </div>
</#assign>

<@main.template
body = body
>
</@main.template>