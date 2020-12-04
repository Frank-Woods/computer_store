<#import "../macro/main.ftlh" as main>
<#import "../macro/product.ftlh" as product>
<#import  "../../macro/pagination.ftlh" as pagination>

<#assign body>
    <div class="product-area pb-100 pt-100">
        <div class="container">
            <div class="section-title-8 mb-65">
                <h2>Товары акции - Название</h2>
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