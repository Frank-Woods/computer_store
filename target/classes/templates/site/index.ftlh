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
                            <a href="/promotions/${promotion.id}/products">
                                <img src="/image/promotionBanner/${promotion.banner.filename}" alt="">
                            </a>
                            <div class="title-timer product-timer-2 text-center">
                                <div class="timer-2 timer-style-1 mega-fashion-timer">
                                    <div data-countdown="${promotion.dateEnd?string("yyyy/dd/MM")}"></div>
                                </div>
                            </div>
                        </div>
                        <#if promotion.promotionProducts?? && 0 < promotion.promotionProducts?size>
                            <div class="mega-fashion-pro-offer">
                                <p>${promotion.name}</p>
                                <h3>${promotion.promotionProducts?sort_by("discount")?last.discount}%</h3>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if products??>
        <div class="product-area pb-100">
            <div class="container">
                <div class="section-title-8 mb-65">
                    <h2>Популярные товары</h2>
                </div>
                <div class="row">
                    <#list products as p>
                        <@product.indexCart
                            prod = p
                        ></@product.indexCart>
                    </#list>
                </div>
            </div>
        </div>
    </#if>
</#assign>

<#assign scripts>
</#assign>

<@main.template
    body = body
    scripts = scripts
>
</@main.template>