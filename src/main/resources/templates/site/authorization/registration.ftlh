<#import "../macro/main.ftlh" as main>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="login-register-area bg-gray pt-155 pb-160">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                    <div class="login-register-wrapper">
                        <div class="login-register-tab-list nav">
                            <a href="/user/login">
                                <h4> вход </h4>
                            </a>
                            <a class="active" href="/user/registration">
                                <h4> регистрация </h4>
                            </a>
                        </div>
                        <div class="tab-content">
                            <div id="lg1" class="tab-pane">

                            </div>
                            <div id="lg2" class="tab-pane active">
                                <div class="login-form-container">
                                    <div class="login-register-form">
                                        <form name="registration">
                                            <div class="custom-input custom-input-strict-style">
                                                <div class="custom-input__settings">
                                                    <span class="custom-input__settings__attributes">{"name":"email", "label":"email"}</span>
                                                </div>
                                            </div>
                                            <div class="custom-input custom-input-strict-style">
                                                <div class="custom-input__settings">
                                                    <span class="custom-input__settings__attributes">{"name":"password", "label":"Пароль", "type":"password"}</span>
                                                </div>
                                            </div>
                                            <div class="button-box">
                                                <button type="submit">Регистрация</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/site/authorization/registration.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
scripts = scripts
></@main.template>