<#import "../macro/main.ftlh" as main>
<#import "../macro/product.ftlh" as product>
<#import "../macro/pagination.ftlh" as pagination>

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
    <form class="shop-area pt-160 pb-160 container" name="store" method="get" action="/store/index">
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
                                    <#if productsPage??>
                                        <#assign startIndex = productsPage.number * productsPage.size>
                                        <#assign lastIndex = startIndex + productsPage.size>
                                        <#list productsPage.getContent() as p>
                                            <#if startIndex <= p?index && p?index < lastIndex>
                                                <@product.indexCart
                                                prod = p
                                                ></@product.indexCart>
                                            </#if>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div id="shop-2" class="tab-pane ">
                                <#if productsPage??>
                                    <#assign startIndex = productsPage.number * productsPage.size>
                                    <#assign lastIndex = startIndex + productsPage.size>
                                    <#list productsPage.getContent() as p>
                                        <#if startIndex <= p?index && p?index < lastIndex>
                                            <@product.detailCart
                                            prod = p
                                            ></@product.detailCart>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                            <div class="mt-50">
                                <@pagination.template
                                    page = productsPage
                                ></@pagination.template>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="shop-sidebar-style">
                    <div class="sidebar-widget">
                        <h4 class="pro-sidebar-title">Сортировка</h4>
                        <div class="sidebar-widget mt-10">
                            <select class="nice-select nice-select-style-3 nice-select-shop" name="sort">
                                <option value="0">По возрастанию цены</option>
                                <option value="1">По убыванию цены</option>
                            </select>
                        </div>
                    </div>
                    <div class="sidebar-widget mt-80">
                        <h4 class="pro-sidebar-title">Фильтр по цене</h4>
                        <div class="price-filter mt-30 mb-30">
                            <div id="slider-range"></div>
                            <div class="price-slider-amount">
                                <div class="label-input">
                                    <span>Цена: </span><input type="text" id="amount" name="cost" placeholder="Введите стоимость" readonly/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <#if manufacturers??>
                        <div class="sidebar-widget">
                            <h4 class="pro-sidebar-title">Фильтр по производителю</h4>
                            <div class="sidebar-widget mt-10">
                                <ul>
                                    <#list manufacturers as m>
                                        <li>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" value="${m.id}" id="manufacturer-${m.id}" name="manufacturers">
                                                <label class="custom-control-label" for="manufacturer-${m.id}">
                                                    ${m.name}
                                                </label>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                        </div>
                    </#if>
                    <button class="mt-20 btn btn-primary">Показать</button>
                </div>
            </div>
        </div>
        <input type="hidden" name="page" value="${productsPage.number}">
        <input type="hidden" name="category" value="${category.id}">
    </form>
</#assign>

<#assign scripts>
    <script>
        var sliderrange = $('#slider-range');
        var amountprice = $('#amount');
        $(function() {
            sliderrange.slider({
                range: true,
                min: 0,
                max: ${maxCost?c},
                values: [0, ${maxCost?c}],
                slide: function(event, ui) {
                    amountprice.val("₽" + ui.values[0] + " - ₽" + ui.values[1]);
                }
            });
            amountprice.val("₽" + sliderrange.slider("values", 0) +
                " - ₽" + sliderrange.slider("values", 1));
        });
    </script>
    <script src="/static/js/site/store/index.js"></script>
    <script src="/static/js/site/cart/addProductToCart.js"></script>
</#assign>

<@main.template
links = links
body = body
scripts = scripts
>
</@main.template>