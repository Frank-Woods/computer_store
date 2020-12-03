<#import "macro/main.ftlh" as main>
<#import "../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
    <link rel="stylesheet" href="/static/scss/admin/index.css">
</#assign>

<#assign body>
    <main>
        <div class="container">

            <!-- Area Chart -->
            <div class="card shadow mb-4">
                <div class="card-header py-3 d-flex align-items-center">
                    <h6 class="m-0 font-weight-bold text-primary mt-3">Продажи</h6>
                    <form name="sales" class="d-flex align-items-center ml-3 justify-content-end w-100">
                        <div class="custom-input mb-0 mr-3">
                            <div class="custom-input__settings">
                                <span class="custom-input__settings__attributes">{"name":"dateStart", "label":"С", "type":"datetime-local"}</span>
                            </div>
                        </div>
                        <div class="custom-input mb-0">
                            <div class="custom-input__settings">
                                <span class="custom-input__settings__attributes">{"name":"dateEnd", "label":"До", "type":"datetime-local"}</span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card-body">
                    <div class="chart-area">
                        <canvas id="myAreaChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </main>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/index.js"></script>
    <@form.js></@form.js>
    <script src="/static/vendor/chart.js/Chart.min.js"></script>
    <script src="/static/js/demo/chart-area-demo.js"></script>
</#assign>

<@main.template
links = links
body = body
page = "Главная"
scripts = scripts
></@main.template>