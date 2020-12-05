<#import "../macro/main.ftlh" as main>

<#assign body>
    <div class="cart-area bg-gray pt-140">
        <div class="container">
            <form action="#">
                <div class="cart-table-content mb-0">
                    <div class="table-content table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th>Product</th>
                                <th class="th-text-center"> Price</th>
                                <th class="th-text-center">Quantity</th>
                                <th class="th-text-center">Total Prce</th>
                                <th class="th-text-center">Remove</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="cart-product">
                                    <div class="product-img-info-wrap">
                                        <div class="product-img">
                                            <a href="#"><img src="/static/assets/images/cart/cart-1.jpg" alt=""></a>
                                        </div>
                                        <div class="product-info">
                                            <h4><a href="#">Demo Product Title</a></h4>
                                            <span>Color :  Black</span>
                                            <span>Size :     SL</span>
                                        </div>
                                    </div>
                                </td>
                                <td class="product-price"><span class="amount">$56.00</span></td>
                                <td class="cart-quality">
                                    <div class="pro-details-quality">
                                        <div class="cart-plus-minus">
                                            <input class="cart-plus-minus-box plus-minus-width-inc" type="text" name="qtybutton" value="1" readonly>
                                        </div>
                                    </div>
                                </td>
                                <td class="product-total"><span>$112.00</span></td>
                                <td class="product-remove"><a href="#"><img class="inject-me" src="/static/assets/images/icon-img/close.svg" alt=""></a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</#assign>

<@main.template
body = body
>
</@main.template>