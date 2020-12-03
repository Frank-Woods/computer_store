<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Добавление характеристики в продукт #${product.id}</h6>
        </div>
        <div class="card-body">
            <form name="create">
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"category", "inputValueFormat": { "type":"var", "pattern": ["name"] }, "itemVisibleValue":["name"], "outputValueFormat": { "type": "var", "pattern": ["name"] }}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/attributeCategories/get"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"name", "inputValueFormat": { "type":"json", "pattern": ["name"] }, "itemVisibleValue": ["name"], "outputValueFormat": { "type": "var", "pattern": ["name"] }}</span>
                        <span class="custom-select__settings__request">{"ajax":true,"url":"http://localhost:8080/admin/attribute/get/all","tracking":"category","urlAfterSelected":"http://localhost:8080/admin/attribute/get/info"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-textarea">
                    <div class="custom-textarea__settings">
                        <span class="custom-textarea__settings__attributes">{"name": "description", "inputValueFormat": { "type":"var", "pattern": ["name"] }, "outputValueFormat": { "type": "var", "pattern": ["name"] }}</span>
                        <span class="custom-textarea__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"value", "visibleValue":"value", "inputValueFormat": { "type":"json", "pattern": ["value"] }, "itemVisibleValue":["value"], "outputValueFormat": { "type": "var", "pattern": ["value"] }}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/value/get/all"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"unit", "visibleValue":"unit", "inputValueFormat": { "type":"json", "pattern": ["unit"] }, "itemVisibleValue":["unit"], "outputValueFormat": { "type": "var", "pattern": ["unit"] }}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/value/get/all"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <button class="custom-button">Создать</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script>const productId = ${product.id};</script>
    <script src="/static/js/admin/product/attribute/create.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Продукты"
scripts = scripts
></@main.template>