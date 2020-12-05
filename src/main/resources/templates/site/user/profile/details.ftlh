<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/form.ftlh" as form>
<#import "../../../macro/security.ftlh" as security>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="my-account-wrapper bg-gray pt-160 pb-160">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <!-- My Account Page Start -->
                    <div class="myaccount-page-wrapper">
                        <!-- My Account Tab Menu Start -->
                        <div class="row">
                            <div class="col-lg-3 col-md-4">
                                <div class="myaccount-tab-menu nav">
                                    <a href="/user/profile/orders">Заказы</a>
                                    <a href="/user/profile/details" class="active">Профиль</a>
                                    <a href="/user/profile/reviews">Отзывы</a>
                                    <#if security.isAdmin><a href="/admin">Админка</a></#if>
                                    <form action="/logout" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                                        <button>Выйти</button>
                                    </form>
                                </div>
                            </div>
                            <!-- My Account Tab Menu End -->
                            <!-- My Account Tab Content Start -->
                            <div class="col-lg-9 col-md-8">
                                <div class="myaccount-content">
                                    <h3>Данные аккаунта</h3>
                                    <div class="account-details-form">
                                        <form name="details">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="custom-input custom-input-strict-style">
                                                        <div class="custom-input__settings">
                                                            <span class="custom-input__settings__attributes">{"name":"firstName", "label":"Имя", "defaultValue":"${security.user.firstName}"}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="custom-input custom-input-strict-style">
                                                        <div class="custom-input__settings">
                                                            <span class="custom-input__settings__attributes">{"name":"lastName", "label":"Фамилия", "defaultValue":"${security.user.lastName}"}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="custom-input custom-input-strict-style">
                                                <div class="custom-input__settings">
                                                    <span class="custom-input__settings__attributes">{"name":"patronymic", "label":"Отчество", "defaultValue":"${security.user.patronymic}"}</span>
                                                </div>
                                            </div>
                                            <div class="custom-input custom-input-strict-style">
                                                <div class="custom-input__settings">
                                                    <span class="custom-input__settings__attributes">{"name":"phone", "label":"Телефон", "defaultValue":"${security.user.phone}"}</span>
                                                </div>
                                            </div>
                                            <div class="custom-input custom-input-strict-style">
                                                <div class="custom-input__settings">
                                                    <span class="custom-input__settings__attributes">{"name":"email", "label":"Почта", "defaultValue":"${security.user.email}"}</span>
                                                </div>
                                            </div>
                                            <div class="custom-select custom-select-strict-style">
                                                <div class="custom-select__settings">
                                                    <span class="custom-select__settings__attributes">{"name":"city", "label":"Город", "itemVisibleValue":["name"], "defaultValue":{"id":${security.user.city.id},"name":"${security.user.city.name}"} }</span>
                                                    <span class="custom-select__settings__request">{"ajax":true, "url":"http://localhost:8080/admin/city/get/search"}</span>
                                                    <span class="custom-select__settings__validation">{"required":true}</span>
                                                </div>
                                            </div>
                                            <fieldset>
                                                <legend>Смена пароля</legend>
                                                <div class="custom-input custom-input-strict-style">
                                                    <div class="custom-input__settings">
                                                        <span class="custom-input__settings__attributes">{"name":"password", "label":"Текущий пароль", "type":"password"}</span>
                                                    </div>
                                                </div>
                                                <div class="custom-input custom-input-strict-style">
                                                    <div class="custom-input__settings">
                                                        <span class="custom-input__settings__attributes">{"name":"newPassword", "label":"Новый пароль", "type":"password"}</span>
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <div class="custom-input custom-input-strict-style">
                                                <div class="custom-input__settings">
                                                    <span class="custom-input__settings__attributes">{"name":"id", "type":"hidden", "defaultValue":${security.user.id}}</span>
                                                </div>
                                            </div>
                                            <div class="single-input-item">
                                                <button class="check-btn sqr-btn ">Сохранить изменения</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div> <!-- My Account Tab Content End -->
                        </div>
                    </div> <!-- My Account Page End -->
                </div>
            </div>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/site/user/profile/details.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
scripts = scripts
>
</@main.template>