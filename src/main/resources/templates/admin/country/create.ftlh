<#import "../macro/main.ftlh" as main>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Добавление страны</h6>
        </div>
        <div class="card-body">
            <form name="create">
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"name", "label":"Название"}</span>
                        <span class="custom-input__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <button class="custom-button">Создать</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/country/create.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
    links = links
    body = body
    page = "Страны"
    scripts = scripts
></@main.template>