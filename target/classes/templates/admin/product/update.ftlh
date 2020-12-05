<#import "../macro/main.ftlh" as main>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Изменение продукта</h6>
        </div>
        <div class="card-body">
            <form name="update">
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"name", "label":"Название", "defaultValue":"${productData.name}"}</span>
                        <span class="custom-input__settings__validation">{"required": true}</span>
                    </div>
                </div>
                <div class="custom-textarea">
                    <div class="custom-textarea__settings">
                        <span class="custom-textarea__settings__attributes">{"name":"description", "label":"Описание", "defaultValue":"${productData.description}"}</span>
                        <span class="custom-textarea__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"category", "label":"Категория", "itemVisibleValue":["name"], "defaultValue":{"id":${productData.category.id}, "name":"${productData.category.name}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/category/children/null"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"manufacturer", "label":"Производитель", "itemVisibleValue":["name"], "defaultValue":{"id":${productData.manufacturer.id}, "name":"${productData.manufacturer.name}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/manufacturer/get/search"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"country", "label":"Страна", "itemVisibleValue":["name"], "defaultValue":{"id":${productData.country.id}, "name":"${productData.country.name}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/country/get/search"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"cost", "label":"Стоимость", "type":"number", "defaultValue":${productData.cost?c}}</span>
                        <span class="custom-input__settings__validation">{"required":true, "min":0}</span>
                    </div>
                </div>
                <div class="custom-file-input">
                    <div class="custom-file-input__settings">
                        <span class="custom-file-input__settings__attributes">{"name": "images", "label": "Изображения", "multiple":true, "type": "file", "files": [<#list productData.images as image>{"id":${image.id}, "src": "/image/productImage/${image.filename}"}<#if image?has_next>,</#if></#list>]}</span>
                        <span class="custom-file-input__settings__validation">{"imageTypes":["image.png", "image.jpeg"], "required": true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"id", "type":"hidden", "defaultValue":${productData.id}}</span>
                    </div>
                </div>
                <button class="custom-button">Изменить</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/product/update.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Продукты"
scripts = scripts
></@main.template>