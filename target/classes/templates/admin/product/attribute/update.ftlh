<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Изменение характеристики #${attribute.id}</h6>
        </div>
        <div class="card-body">
            <form name="update">
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"attributeCategory", "label":"Категория", "inputValueFormat": { "type":"var", "pattern": ["name"] }, "itemVisibleValue":["name"], "outputValueFormat": { "type": "var", "pattern": ["name"] }, "defaultValue":"${attribute.attributeCategory}"}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/attributeCategories/get"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"name", "label":"Название", "inputValueFormat": { "type":"json", "pattern": ["name"] }, "itemVisibleValue": ["name"], "outputValueFormat": { "type": "var", "pattern": ["name"] }, "defaultValue":{"name":"${attribute.name}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true,"url":"http://localhost:8080/admin/attribute/get/all","tracking":"category","urlAfterSelected":"http://localhost:8080/admin/attribute/get/info"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-textarea">
                    <div class="custom-textarea__settings">
                        <span class="custom-textarea__settings__attributes">{"name": "description", "label":"Описание", "defaultValue":"${attribute.description}", "inputValueFormat": { "type":"var", "pattern": ["name"] }, "outputValueFormat": { "type": "var", "pattern": ["name"] }}</span>
                        <span class="custom-textarea__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"value", "label":"Значение", "visibleValue":"value", "inputValueFormat": { "type":"json", "pattern": ["value"] }, "itemVisibleValue":["value"], "outputValueFormat": { "type": "var", "pattern": ["value"] }, "defaultValue":{"value":"${attribute.value}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/value/get/all"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"unit", "label":"Единицы измерения", "visibleValue":"unit", "inputValueFormat": { "type":"json", "pattern": ["unit"] }, "itemVisibleValue":["unit"], "outputValueFormat": { "type": "var", "pattern": ["unit"] }, "defaultValue":{"unit":"${attribute.unit}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/value/get/all"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"id", "type":"hidden", "defaultValue":${attribute.id}}</span>
                    </div>
                </div>
                <button class="custom-button">Изменить</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script>const productId = ${productData.id};</script>
    <script src="/static/js/admin/product/attribute/update.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Продукты"
scripts = scripts
></@main.template>