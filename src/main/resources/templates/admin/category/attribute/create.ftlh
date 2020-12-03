<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Добавление характеристики в категорию #${category.id}</h6>
        </div>
        <div class="card-body">
            <form name="create">
                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="edjll-select__settings__attributes">{"name":"name", "inputValueFormat": { "type":"json", "pattern": ["name"] }, "itemVisibleValue": ["name"], "outputValueFormat": { "type": "var", "pattern": ["name"] }}</span>
                        <span class="edjll-select__settings__request">{"ajax":true,"url":"http://localhost:8080/admin/attribute/get/all","tracking":"category","excludedValues":"attribute","urlAfterSelected":"http://localhost:8080/admin/attribute/get/info"}</span>
                        <span class="edjll-select__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="edjll-input__settings__attributes">{"name": "description", "inputValueFormat": { "type":"var", "pattern": ["name"] }, "outputValueFormat": { "type": "var", "pattern": ["name"] }}</span>
                        <span class="edjll-input__settings__validation">{"required":true}</span>
                    </div>
                </div>
                <button class="custom-button">Создать</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script>const categoryId = ${category.id};</script>
    <script src="/static/js/admin/category/attribute/create.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Категории"
scripts = scripts
></@main.template>