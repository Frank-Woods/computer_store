<#import "../../../macro/main.ftlh" as main>
<#import "../../../../macro/security.ftlh" as security>
<#import "../../../../macro/pagination.ftlh" as pagination>

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
                                    <a href="/user/profile/orders" class="active">Заказы</a>
                                    <a href="/user/profile/details">Профиль</a>
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
                                    <h3>Заказы</h3>
                                    <div class="myaccount-table table-responsive text-center">
                                        <table class="table table-bordered">
                                            <thead class="thead-light">
                                            <tr>
                                                <th>#</th>
                                                <th>Продукт</th>
                                                <th>Стоимость</th>
                                                <th>Статус</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <#if salePage??>
                                                <#list salesPage.getContent() as sale>
                                                    <tr>
                                                        <td>${sale.id}</td>
                                                        <td>${sale.date}</td>
                                                        <td>${sale.address}</td>
                                                        <td><a href="/user/profile/orders/${sale.id}/product/all" class="check-btn sqr-btn ">Подробнее</a></td>
                                                    </tr>
                                                </#list>
                                            <#else>
                                                <tr>
                                                    <td colspan="4">Пусто</td>
                                                </tr>
                                            </#if>
                                            </tbody>
                                        </table>
                                    </div>
                                    <@pagination.template
                                    page = salesPage
                                    url = "/user/profile/orders"
                                    ></@pagination.template>
                                </div>
                            </div> <!-- My Account Tab Content End -->
                        </div>
                    </div> <!-- My Account Page End -->
                </div>
            </div>
        </div>
    </div>
</#assign>

<@main.template
body = body
>
</@main.template>