<#import "../macro/main.ftlh" as main>
<#import "../macro/promotion.ftlh" as promotion>
<#import  "../../macro/pagination.ftlh" as pagination>

<#assign body>
    <div class="product-area pb-100 pt-100">
        <div class="container">
            <div class="section-title-8 mb-65">
                <h2>Акции</h2>
            </div>
            <div class="row">
                <#list promotionsPage.getContent() as p>
                    <@promotion.indexCart
                        prom = p
                    ></@promotion.indexCart>
                </#list>
            </div>
            <@pagination.template
            page=promotionsPage
            url="/promotions"
            ></@pagination.template>
        </div>
    </div>
</#assign>

<@main.template
body = body
>
</@main.template>