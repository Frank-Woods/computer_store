<#import "../macro/main.ftlh" as main>
<#import "../macro/slider.ftlh" as slider>
<#import "../macro/product.ftlh" as product>

<#assign links>

</#assign>

<#assign body>
    <div class="breadcrumb-area breadcrumb-mt bg-gray breadcrumb-ptb-1">
        <div class="container">
            <div class="breadcrumb-content">
                <h2>${category.name}</h2>
                <p class="left">${category.description}</p>
            </div>
        </div>
    </div>
    <div class="shop-area pt-160 pb-160 container">
        <div class="row flex-row-reverse">
            <div class="col-lg-9">
                <div class="tab-content">
                    <div id="shop-categorie-1" class="tab-pane active">
                        <div class="shop-top-bar">
                            <div class="shop-top-bar-left">
                                <div class="shop-tab nav">
                                    <a href="#shop-1" class="active" data-toggle="tab"><img class="inject-me" src="/static/assets/images/icon-img/shop-grid.svg" alt=""></a>
                                    <a href="#shop-2" data-toggle="tab"><img class="inject-me" src="/static/assets/images/icon-img/shop-list.svg" alt=""></a>
                                </div>
                            </div>
                        </div>
                        <div class="tab-content pt-30">
                            <div id="shop-1" class="tab-pane active">
                                <div class="row">
                                    <#list productsPage.getContent() as p>
                                        <@product.indexCart
                                            prod = p
                                        ></@product.indexCart>
                                    </#list>
                                </div>
                            </div>
                            <div id="shop-2" class="tab-pane ">
                                <#list productsPage.getContent() as p>
                                    <@product.detailCart
                                        prod = p
                                    ></@product.detailCart>
                                </#list>
                            </div>
                            <div class="pro-pagination-style text-center mt-50">
                                <ul>
                                    <li><a class="active" href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#"><i class="icofont-long-arrow-right"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="shop-sidebar-style">
                    <div class="sidebar-widget">
                        <h4 class="pro-sidebar-title">Фильтр по цене</h4>
                        <div class="price-filter mt-55 mb-65">
                            <div id="slider-range"></div>
                            <div class="price-slider-amount">
                                <div class="label-input">
                                    <span>Цена: </span><input type="text" id="amount" name="price" placeholder="Введите стоимость" readonly/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar-widget">
                        <h4 class="pro-sidebar-title">Фильтр по производителю</h4>
                        <div class="sidebar-widget mt-50">
                            <ul>
                                <li>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="manufacturer-1">
                                        <label class="custom-control-label" for="manufacturer-1">
                                            Производитель 1
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-control custom-checkbox">
                                        <label class="custom-control-label">
                                            <input type="checkbox" class="custom-control-input">
                                            <span>Производитель 1</span>
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-control custom-checkbox">
                                        <label class="custom-control-label">
                                            <input type="checkbox" class="custom-control-input">
                                            <span>Производитель 1</span>
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-control custom-checkbox">
                                        <label class="custom-control-label">
                                            <input type="checkbox" class="custom-control-input">
                                            <span>Производитель 1</span>
                                        </label>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script>
        var sliderrange = $('#slider-range');
        var amountprice = $('#amount');
        $(function() {
            sliderrange.slider({
                range: true,
                min: 0,
                max: 1000,
                values: [0, 300],
                slide: function(event, ui) {
                    amountprice.val("₽" + ui.values[0] + " - ₽" + ui.values[1]);
                }
            });
            amountprice.val("₽" + sliderrange.slider("values", 0) +
                " - ₽" + sliderrange.slider("values", 1));
        });
    </script>
    <script src="/static/js/site/cart/addProductToCart.js"></script>
</#assign>

<@main.template
links = links
body = body
scripts = scripts
>
</@main.template>