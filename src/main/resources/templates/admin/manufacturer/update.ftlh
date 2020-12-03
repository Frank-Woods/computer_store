<#import "../macro/main.ftlh" as main>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Изменение производителя</h6>
        </div>
        <div class="card-body">
            <form name="update">
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"name", "label":"Название", "defaultValue":"${manufacturer.name}"}</span>
                        <span class="custom-input__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-textarea">
                    <div class="custom-textarea__settings">
                        <span class="custom-textarea__settings__attributes">{"name":"description", "label":"Описание", "defaultValue":"${manufacturer.description}"}</span>
                        <span class="custom-textarea__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"country", "label":"Страна", "itemVisibleValue":["name"], "defaultValue":{"id":${manufacturer.country.id}, "name":"${manufacturer.country.name}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/country/get/search"}</span>
                        <span class="custom-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-file-input">
                    <div class="custom-file-input__settings">
                        <span class="custom-file-input__settings__attributes">{"name": "logo", "label": "Логотип", "type": "file", "files": [{"id":${manufacturer.logo.id}, "src": "/image/manufacturerLogo/${manufacturer.logo.filename}"}]}</span>
                        <span class="custom-file-input__settings__validation">{"imageTypes":["image.png", "image.jpeg"], "required": true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"id", "type":"hidden", "defaultValue":${manufacturer.id}}</span>
                    </div>
                </div>
                <button class="custom-button">Изменить</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/manufacturer/update.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Производители"
scripts = scripts
></@main.template>