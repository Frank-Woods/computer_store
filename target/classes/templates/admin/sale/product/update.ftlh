<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Утверждение продажи</h6>
        </div>
        <div class="card-body">
            <form name="update">
                <div class="custom-input-style">
                    <div class="custom-input-style__label">
                        <span class="custom-input-style__label__text">Пользователь</span>
                        <span class="custom-input-style__label__input">${saleProduct.sale.user.email}</span>
                    </div>
                </div>
                <div class="custom-input-style">
                    <div class="custom-input-style__label">
                        <span class="custom-input-style__label__text">Продукт</span>
                        <span class="custom-input-style__label__input"><a href="/store/product/${saleProduct.productData.id}" target="_blank">${saleProduct.productData.name}</a></span>
                    </div>
                </div>
                <div class="custom-input-style">
                    <div class="custom-input-style__label">
                        <span class="custom-input-style__label__text">Стоимость</span>
                        <span class="custom-input-style__label__input">${saleProduct.cost}</span>
                    </div>
                </div>

                <div class="custom-select">
                    <div class="custom-select__settings">
                        <span class="custom-select__settings__attributes">{"name": "statusReview", "inputValueFormat":{"type":"json", "pattern":["id", "name"]}, "outputValueFormat":{"type":"var", "pattern":["id"]}, "defaultValue": {"id":${saleProduct.statusSale.ordinal()}, "name":"${saleProduct.statusSale.text}"}, "options":[<#list statuses as status>{"id":${status.ordinal()}, "name":"${status.text}"}<#if status?has_next>,</#if></#list>], "itemVisibleValue":["name"]}</span>
                        <span class="custom-select__settings__request">{"searchParam":false}</span>
                    </div>
                </div>
                <div class="custom-input">
                    <div class="custom-input__settings">
                        <span class="custom-input__settings__attributes">{"name":"id", "type": "hidden", "defaultValue":"${saleProduct.id}"}</span>
                    </div>
                </div>
                <button class="custom-button">Изменить</button>
            </form>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script>const saleId = ${saleProduct.sale.id};</script>
    <script src="/static/js/admin/sale/update.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Продажи"
scripts = scripts
></@main.template>