<#import "macro/main.ftlh" as main>
<#import "../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <main>
        <div class="row mx-3">

            <div class="col-xl-8 col-lg-7">

                <!-- Area Chart -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Продажи</h6>
                        <form name="sales">
                            <div class="custom-input">
                                <div class="custom-input__settings">
                                    <span class="custom-input__settings__attributes">{"name":"dateStart", "label":"С", "type":"datetime-local"}</span>
                                </div>
                            </div>
                            <div class="custom-input">
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

                <!-- Bar Chart -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Bar Chart</h6>
                    </div>
                    <div class="card-body">
                        <div class="chart-bar">
                            <canvas id="myBarChart"></canvas>
                        </div>
                    </div>
                </div>

            </div>

            <!-- Donut Chart -->
            <div class="col-xl-4 col-lg-5">
                <div class="card shadow mb-4">
                    <!-- Card Header - Dropdown -->
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Donut Chart</h6>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <div class="chart-pie pt-4">
                            <canvas id="myPieChart"></canvas>
                        </div>
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
    <script src="/static/js/demo/chart-bar-demo.js"></script>
    <script src="/static/js/demo/chart-pie-demo.js"></script>
</#assign>

<@main.template
links = links
body = body
page = "Главная"
scripts = scripts
></@main.template>