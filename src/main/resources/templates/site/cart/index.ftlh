<#import "../macro/main.ftlh" as main>

<#assign body>
    <div class="cart-area bg-gray pt-140">
        <div class="container">
            <form action="#">
                <div class="cart-table-content mb-0">
                    <div class="table-content table-responsive">
                        <table class="w-100">
                            <thead>
                            <tr>
                                <th>Product</th>
                                <th class="th-text-center">Price</th>
                                <th class="th-text-center">Quantity</th>
                                <th class="th-text-center">Total Prce</th>
                                <th class="th-text-center">Remove</th>
                            </tr>
                            </thead>
                            <tbody class="cart-product-wrapper">
                            </tbody>
                        </table>
                    </div>
                    <a href="/cart/payment" class="btn btn-outline-primary">Оплатить</a>
                </div>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/site/cart/createProductInCart.js"></script>
    <script>loadCartFromLocalStorageToCart();</script>
</#assign>

<@main.template
body = body
mc = false
scripts = scripts
>
</@main.template>