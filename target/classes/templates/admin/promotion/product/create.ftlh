<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Добавление продукта в акцию #${promotion.id}</h6>
        </div>
        <div class="card-body">
            <form name="create">
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"product", "label":"Продукт", "itemVisibleValue":["name"]}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/product/get/search"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"discount", "label":"Скидка", "type":"number"}</span>
                        <span class="custom-input__settings__validation">{"required":true, "min":0, "max":100}</span>
                    </div>
                </div>
                <button class="custom-button">Создать</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script>const promotionId = ${promotion.id};</script>
    <script src="/static/js/admin/promotion/product/create.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Акции"
scripts = scripts
></@main.template>