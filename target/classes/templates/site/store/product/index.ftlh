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
                            <#list productData.images as image>
                                <div class="tab-pane<#if image?index == 0> active</#if>" id="pro-details${image?index}">
                                    <div class="product-details-2-img ">
                                        <img src="/image/productImage/${image.filename}" alt="">
                                    </div>
                                </div>
                            </#list>
                        </div>
                        <div class="product-details-tab-small nav">
                            <#list productData.images as image>
                                <a <#if image?index == 0>class="active"</#if> href="#pro-details${image?index}">
                                    <img src="/image/productImage/${image.filename}" alt="">
                                </a>
                            </#list>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-6 col-md-12">
                    <div class="product-details-content main-product-details-content">
                        <h2>${productData.name}</h2>
                        <div class="product-ratting-review-wrap">
                            <div class="product-ratting-digit-wrap">
                                <div class="product-ratting">
                                    <#if 0 < rating>
                                        <#list 1..rating?floor as r>
                                            <span><i class="icon-rating"></i></span>
                                        </#list>
                                    </#if>
                                    <#if rating?floor != rating?round>
                                        <span><i class="icon-star-half"></i></span>
                                    </#if>
                                    <#if rating?round < 5>
                                        <#list rating?round + 1..5 as r>
                                            <span><i class="icon-star-empty"></i></span>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="product-review-order">
                                <span>Отзывов <#if productData.reviews??>${productData.reviews?filter(r -> r.statusReview.ordinal() == 1)?size}<#else>0</#if></span>
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
                                                    </#if>
                                                    <td class="width1">${at.attribute.name}</td>
                                                    <td>${at.value.value + " " + at.value.unit}</td>
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
                                <#if productData.reviews??>
                                    <#list productData.reviews?filter(r -> r.statusReview.ordinal() == 1) as r>
                                        <div class="single-review">
                                            <div class="review-content">
                                                <div class="review-top-wrap">
                                                    <div class="review-name">
                                                        <h5><span>${r.user.firstName + " " + r.user.lastName}</span> - ${r.date?string("dd.MM.yyyy")}</h5>
                                                    </div>
                                                    <div class="review-rating pl-3">
                                                        <#if 0 < rating>
                                                            <#list 1..rating?floor as r>
                                                               <i class="yellow icon-rating"></i>
                                                            </#list>
                                                        </#if>
                                                        <#if rating?floor != rating?round>
                                                            <i class="yellow icon-star-half"></i>
                                                        </#if>
                                                        <#if rating?round < 5>
                                                            <#list rating?round + 1..5 as r>
                                                               <i class="yellow icon-star-empty"></i>
                                                            </#list>
                                                        </#if>
                                                    </div>
                                                </div>
                                                <p><strong>Плюсы:</strong> ${r.advantages}</p>
                                                <p><strong>Минусы:</strong> ${r.disadvantages}</p>
                                                <p><strong>Комментарий:</strong> ${r.comment}</p>
                                            </div>
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                            <#if security.isAuthorized>
                                <#if review??>
                                    <div class="ratting-form-wrapper">
                                        <span>Измените свой отзыв</span>
                                        <div class="ratting-form">
                                            <form name="review">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="star-box-wrap">
                                                            <div class="single-ratting-star <#if review.rating == 1>current</#if>">
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star <#if review.rating == 2>current</#if>">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star <#if review.rating == 3>current</#if>">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star <#if review.rating == 4>current</#if>">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star <#if review.rating == 5>current</#if>">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="custom-textarea custom-textarea-strict-style">
                                                            <div class="custom-textarea__settings">
                                                                <span class="custom-textarea__settings__attributes">{"name":"advantages", "label":"Плюсы", "defaultValue":"${review.advantages}"}</span>
                                                                <span class="custom-textarea__settings__validation">{"required":true}</span>
                                                            </div>
                                                        </div>
                                                        <div class="custom-textarea custom-textarea-strict-style">
                                                            <div class="custom-textarea__settings">
                                                                <span class="custom-textarea__settings__attributes">{"name":"disadvantages", "label":"Минусы", "defaultValue":"${review.disadvantages}"}</span>
                                                                <span class="custom-textarea__settings__validation">{"required":true}</span>
                                                            </div>
                                                        </div>
                                                        <div class="custom-textarea custom-textarea-strict-style">
                                                            <div class="custom-textarea__settings">
                                                                <span class="custom-textarea__settings__attributes">{"name":"comment", "label":"Комментарий", "defaultValue":"${review.comment}"}</span>
                                                                <span class="custom-textarea__settings__validation">{"required":true}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="custom-input">
                                                        <div class="custom-input__settings">
                                                            <span class="custom-input__settings__attributes">{"name":"id", "type":"hidden", "defaultValue":${review.id}}</span>
                                                        </div>
                                                    </div>
                                                    <div class="custom-input">
                                                        <div class="custom-input__settings">
                                                            <span class="custom-input__settings__attributes">{"name":"productData", "type":"hidden", "defaultValue":${productData.id}}</span>
                                                        </div>
                                                    </div>
                                                    <div class="custom-input">
                                                        <div class="custom-input__settings">
                                                            <span class="custom-input__settings__attributes">{"name":"rating", "type":"hidden", "defaultValue":${review.rating}}</span>
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
                                <#else>
                                    <div class="ratting-form-wrapper">
                                        <span>Добавьте свой отзыв</span>
                                        <div class="ratting-form">
                                            <form name="review">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="star-box-wrap">
                                                            <div class="single-ratting-star">
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                            </div>
                                                            <div class="single-ratting-star">
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
                                                                <i class="icon-rating"></i>
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
                                                                <span class="custom-textarea__settings__attributes">{"name":"disadvantages", "label":"Минусы"}</span>
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
                                                    <div class="custom-input">
                                                        <div class="custom-input__settings">
                                                            <span class="custom-input__settings__attributes">{"name":"productData", "type":"hidden", "defaultValue":${productData.id}}</span>
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
                                </#if>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script>const hasReview = ${(review??)?then("true", "false")};</script>
    <script src="/static/js/site/store/product/review/main.js"></script>
    <@form.js></@form.js>
    <script src="/static/js/site/store/product/review/rating.js"></script>
</#assign>

<@main.template
links = links
body = body
scripts = scripts
>
</@main.template>