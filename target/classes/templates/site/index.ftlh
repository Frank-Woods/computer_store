<#import "macro/main.ftlh" as main>
<#import "macro/slider.ftlh" as slider>
<#import "macro/product.ftlh" as product>

<#assign body>
    <@slider.template></@slider.template>
    <div class="deal-product-service-area pt-160 pb-120">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="offer-product-wrap mr-60">
                        <div class="product-img product-img-zoom">
                            <a href="#">
                                <img src="/static/assets/images/product/product-132.jpg" alt="">
                            </a>
                            <div class="title-timer product-timer-2 text-center">
                                <div class="mega-fashion-pro-title">
                                    <h4><a href="#">Featured Product</a></h4>
                                </div>
                                <div class="timer-2 timer-style-1 mega-fashion-timer">
                                    <div data-countdown="2021/01/01"></div>
                                </div>
                            </div>
                        </div>
                        <div class="mega-fashion-pro-offer">
                            <p>Complete <br>Workstation Set</p>
                            <h3>75%</h3>
                            <span>Off</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="product-area pb-100">
        <div class="container">
            <div class="section-title-8 mb-65">
                <h2>Популярные товары</h2>
            </div>
            <div class="row">
                <@product.indexCart></@product.indexCart>
                <@product.indexCart></@product.indexCart>
                <@product.indexCart></@product.indexCart>
                <@product.indexCart></@product.indexCart>
                <@product.indexCart></@product.indexCart>
                <@product.indexCart></@product.indexCart>
                <@product.indexCart></@product.indexCart>
                <@product.indexCart></@product.indexCart>
            </div>
        </div>
    </div>
</#assign>

<@main.template
    body = body
>
</@main.template>