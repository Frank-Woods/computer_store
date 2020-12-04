<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/form.ftlh" as form>
<#import "../../../macro/security.ftlh" as security>

<#assign links>
    <@form.links></@form.links>
</#assign>


<#assign body>
    <div class="product-details-area product-details-bg slider-mt-7">
        <div class="container">
            <div class="row">
                <div class="col-xl-6 col-lg-6 col-md-12">
                    <div class="product-details-tab-wrap">
                        <div class="product-details-tab-large tab-content pt-40 text-center">
                            <div class="tab-pane active" id="pro-details1">
                                <div class="product-details-2-img ">
                                    <img src="/static/assets/images/product-details/product-details-img-1.png" alt="">
                                </div>
                            </div>
                            <div class="tab-pane" id="pro-details2">
                                <div class="product-details-2-img ">
                                    <img src="/static/assets/images/product-details/product-details-slider-2.png" alt="">
                                </div>
                            </div>
                            <div class="tab-pane" id="pro-details3">
                                <div class="product-details-2-img">
                                    <img src="/static/assets/images/product-details/product-details-slider-3.png" alt="">
                                </div>
                            </div>
                            <div class="tab-pane" id="pro-details4">
                                <div class="product-details-2-img ">
                                    <img src="/static/assets/images/product-details/product-details-slider-4.png" alt="">
                                </div>
                            </div>
                            <div class="tab-pane" id="pro-details5">
                                <div class="product-details-2-img ">
                                    <img src="/static/assets/images/product-details/product-details-slider-5.png" alt="">
                                </div>
                            </div>
                            <div class="tab-pane" id="pro-details6">
                                <div class="product-details-2-img ">
                                    <img src="/static/assets/images/product-details/product-details-slider-2.png" alt="">
                                </div>
                            </div>
                            <div class="product-details-shape pro-dec-shape1">
                                <img src="/static/assets/images/product-details/product-details-shape.png" alt="">
                            </div>
                        </div>
                        <div class="product-details-tab-small nav">
                            <a class="active" href="#pro-details1">
                                <img src="/static/assets/images/product-details/small-7.jpg" alt="">
                            </a>
                            <a href="#pro-details2">
                                <img src="/static/assets/images/product-details/small-8.jpg" alt="">
                            </a>
                            <a href="#pro-details3">
                                <img src="/static/assets/images/product-details/small-1.jpg" alt="">
                            </a>
                            <a href="#pro-details4">
                                <img src="/static/assets/images/product-details/small-5.jpg" alt="">
                            </a>
                            <a href="#pro-details5">
                                <img src="/static/assets/images/product-details/small-3.jpg" alt="">
                            </a>
                            <a href="#pro-details6">
                                <img src="/static/assets/images/product-details/small-8.jpg" alt="">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-6 col-md-12">
                    <div class="product-details-content main-product-details-content">
                        <h2>${productData.name}</h2>
                        <div class="product-ratting-review-wrap">
                            <div class="product-ratting-digit-wrap">
                                <div class="product-ratting">
                                    <i class="icon-rating"></i>
                                    <i class="icon-rating"></i>
                                    <i class="icon-rating"></i>
                                    <i class="icon-rating"></i>
                                    <i class="icon-star-empty"></i>
                                </div>
                            </div>
                            <div class="product-review-order">
                                <span>Отзывов <#if productData.reviews??>${productData.reviews?size}<#else>0</#if></span>
                                <span>Продаж <#if productData.saleProducts??>${productData.saleProducts?size}<#else>0</#if></span>
                            </div>
                        </div>
                        <div class="pro-details-price">
                            <#if discountCost??>
                                <span>${discountCost} ₽</span>
                                <span class="old-price">${productData.cost} ₽</span>
                            <#else>
                                <span>${productData.cost} ₽</span>
                            </#if>
                        </div>
                        <div class="pro-details-quality">
                            <span>Количество:</span>
                            <div class="cart-plus-minus">
                                <input class="cart-plus-minus-box" type="text" name="qtybutton" value="1">
                            </div>
                        </div>
                        <div class="product-details-meta">
                            <ul>
                                <li><span>Категория:</span> <span>${productData.category.name}</span></li>
                                <li><span>Производитель:</span> <span class="pl-4">${productData.manufacturer.name}</span></li>
                            </ul>
                        </div>
                        <div class="pro-details-action-wrap">
                            <div class="pro-details-action">
                                <a title="Add to Cart" href="#"><i class="icon-basket"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="description-review-wrapper pt-160 pb-155">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="dec-review-topbar nav mb-65">
                        <a class="active" data-toggle="tab" href="#des-details1">Описание</a>
                        <a data-toggle="tab" href="#des-details2">Характеристики</a>
                        <a data-toggle="tab" href="#des-details4">Отзывы</a>
                    </div>
                    <div class="tab-content dec-review-bottom">
                        <div id="des-details1" class="tab-pane active">
                            <div class="description-wrap">
                                <p>${productData.description}</p>
                            </div>
                        </div>
                        <div id="des-details2" class="tab-pane">
                            <div class="specification-wrap table-responsive">
                                <table>
                                    <tbody>
                                    <#if attributeCategories?? && productData.attributes??>
                                        <#list attributeCategories as ac>
                                            <#assign atts = productData.attributes?filter(attr -> attr.attribute.category.id == ac.id)>
                                            <#list atts as at>
                                                <tr>
                                                    <#if at?index == 0>
                                                        <td rowspan="${atts?size}">${ac.name}</td>
                                                        <td class="width1">${at.attribute.name}</td>
                                                        <td>${at.value.value + " " + at.value.unit}</td>
                                                    </#if>
                                                </tr>
                                            </#list>
                                        </#list>
                                    </#if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div id="des-details4" class="tab-pane">
                            <div class="review-wrapper">
                                <div class="single-review">
                                    <div class="review-content">
                                        <div class="review-top-wrap">
                                            <div class="review-name">
                                                <h5><span>John Snow</span> - March 14, 2019</h5>
                                            </div>
                                            <div class="review-rating">
                                                <i class="yellow icon-rating"></i>
                                                <i class="yellow icon-rating"></i>
                                                <i class="yellow icon-rating"></i>
                                                <i class="yellow icon-rating"></i>
                                                <i class="yellow icon-rating"></i>
                                            </div>
                                        </div>
                                        <p>Donec accumsan auctor iaculis. Sed suscipit arcu ligula, at egestas magna molestie a. Proin ac ex maximus, ultrices justo eget, sodales orci. Aliquam egestas libero ac turpis pharetra, in vehicula lacus scelerisque</p>
                                    </div>
                                </div>
                            </div>
                            <div class="ratting-form-wrapper">
                                <span>Добавьте свой отзыв</span>
                                <div class="ratting-form">
                                    <form name="review">
                                        <div class="row">
                                            <#if !security.isAuthorized>
                                                <div class="col-lg-3 col-md-6">
                                                    <div class="custom-input custom-input-strict-style">
                                                        <div class="custom-input__settings">
                                                            <span class="custom-input__settings__attributes">{"name":"email", "label":"Почта"}</span>
                                                            <span class="custom-input__settings__validation">{"required":true}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </#if>
                                            <div class="col-lg-12">
                                                <div class="star-box-wrap">
                                                    <div class="single-ratting-star">
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                    </div>
                                                    <div class="single-ratting-star">
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                    </div>
                                                    <div class="single-ratting-star">
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                    </div>
                                                    <div class="single-ratting-star">
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                    </div>
                                                    <div class="single-ratting-star">
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                        <a href="#"><i class="icon-rating"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="custom-textarea custom-textarea-strict-style">
                                                    <div class="custom-textarea__settings">
                                                        <span class="custom-textarea__settings__attributes">{"name":"advantages", "label":"Плюсы"}</span>
                                                        <span class="custom-textarea__settings__validation">{"required":true}</span>
                                                    </div>
                                                </div>
                                                <div class="custom-textarea custom-textarea-strict-style">
                                                    <div class="custom-textarea__settings">
                                                        <span class="custom-textarea__settings__attributes">{"name":"disadvantage", "label":"Минусы"}</span>
                                                        <span class="custom-textarea__settings__validation">{"required":true}</span>
                                                    </div>
                                                </div>
                                                <div class="custom-textarea custom-textarea-strict-style">
                                                    <div class="custom-textarea__settings">
                                                        <span class="custom-textarea__settings__attributes">{"name":"comment", "label":"Комментарий"}</span>
                                                        <span class="custom-textarea__settings__validation">{"required":true}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="form-submit">
                                                    <input type="submit" value="Отправить">
                                                </div>
                                            </div>
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
</#assign>

<#assign scripts>
    <script src="/static/js/site/store/product/review/main.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
scripts = scripts
>
</@main.template>