<#import "../macro/main.ftlh" as main>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Изменение акции</h6>
        </div>
        <div class="card-body">
            <form name="update">
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"name", "label":"Название", "defaultValue":"${promotion.name}"}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"dateStart", "label":"Дата начала", "type": "datetime-local", "defaultValue":"${promotion.dateStart?iso_m_nz("Europe/Samara")}"}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"dateEnd", "label":"Дата окончания", "type": "datetime-local", "defaultValue":"${promotion.dateEnd?iso_m_nz("Europe/Samara")}"}</span>
                        <span class="custom-input__settings__validation">{"laterThan": "dateStart"}</span>
                    </div>
                </div>
                <div class="custom-textarea">
                    <div class="custom-textarea__settings">
                        <span class="custom-textarea__settings__attributes">{"name":"description", "label":"Описание", "defaultValue":"${promotion.description}"}</span>
                    </div>
                </div>
                <div class="custom-file-input">
                    <div class="custom-file-input__settings">
                        <span class="custom-file-input__settings__attributes">{"name": "banner", "label": "Логотип", "type": "file", "files": [{"id":${promotion.banner.id}, "src": "/image/promotionBanner/${promotion.banner.filename}"}]}</span>
                        <span class="custom-file-input__settings__validation">{"imageTypes":["image.png", "image.jpeg"], "required": true}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"id", "type": "hidden", "defaultValue":"${promotion.id}"}</span>
                    </div>
                </div>
                <button class="custom-button">Создать</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/promotion/update.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Акции"
scripts = scripts
></@main.template>