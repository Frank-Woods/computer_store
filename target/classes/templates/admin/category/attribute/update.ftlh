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
                        <span class="custom-select__settings__attributes">{"name":"name", "inputValueFormat": { "type":"json", "pattern": ["name"] }, "itemVisibleValue": ["name"], "outputValueFormat": { "type": "var", "pattern": ["name"] }, "defaultValue":{"name":"${categoryAttribute.attribute.name}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/country/get/search"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"description", "label":"Описание", "defaultValue":"${categoryAttribute.attribute.description}"}</span>
                        <span class="custom-input__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"id", "type":"hidden", "defaultValue":"${categoryAttribute.id}"}</span>
                    </div>
                </div>
                <button class="custom-button">Изменить</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/category/attribute/update.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Категории"
scripts = scripts
></@main.template>