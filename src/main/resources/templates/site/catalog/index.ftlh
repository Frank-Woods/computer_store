<#import "../macro/main.ftlh" as main>
<#import "../macro/category.ftlh" as category>
<#import  "../../macro/pagination.ftlh" as pagination>

<#assign body>
    <div class="product-area pb-100 pt-100">
        <div class="container">
            <div class="section-title-8 mb-65">
                <h2>Каталог</h2>
            </div>
            <div class="row">
                <#list categories as c>
                    <@category.indexCart
                    ctg = c
                    ></@category.indexCart>
                </#list>
            </div>
        </div>
    </div>
</#assign>

<@main.template
body = body
>
</@main.template>