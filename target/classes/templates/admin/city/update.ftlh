<#import "../macro/main.ftlh" as main>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Изменение города</h6>
        </div>
        <div class="card-body">
            <form name="update">
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"name", "label":"Название", "defaultValue":"${city.name}"}</span>
                    </div>
                </div>
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name":"country", "label":"Страна", "itemVisibleValue":["name"], "defaultValue":{"id":${city.country.id}, "name":"${city.country.name}"}}</span>
                        <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/country/get/search"}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"id", "type":"hidden", "defaultValue":${city.id}}</span>
                    </div>
                </div>
                <button class="custom-button">Изменить</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/city/update.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Города"
scripts = scripts
></@main.template>