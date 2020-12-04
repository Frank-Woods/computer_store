<#import "../macro/main.ftlh" as main>
<#import "../macro/promotion.ftlh" as promotion>
<#import  "../../macro/pagination.ftlh" as pagination>

<#assign body>
    <div class="product-area pb-100 pt-100">
        <div class="container">
            <div class="row">
                <@promotion.indexCart></@promotion.indexCart>
                <@promotion.indexCart></@promotion.indexCart>
                <@promotion.indexCart></@promotion.indexCart>
                <@promotion.indexCart></@promotion.indexCart>
                <@promotion.indexCart></@promotion.indexCart>
                <@promotion.indexCart></@promotion.indexCart>
                <@promotion.indexCart></@promotion.indexCart>
                <@promotion.indexCart></@promotion.indexCart>
            </div>
        </div>
<#--        <@pagination.template-->
<#--            page = ""-->
<#--            url= ""-->
<#--        ></@pagination.template>-->
    </div>
</#assign>

<@main.template
body = body
>
</@main.template>