<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/security.ftlh" as security>
<#import "../../../macro/pagination.ftlh" as pagination>

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
                                    <a href="/user/profile/details">Профиль</a>
                                    <a href="/user/profile/reviews" class="active">Отзывы</a>
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
                                    <h3>Отзывы</h3>
                                    <div class="myaccount-table table-responsive text-center">
                                        <table class="table table-bordered">
                                            <thead class="thead-light">
                                            <tr>
                                                <th>#</th>
                                                <th>Товар</th>
                                                <th>Плюсы</th>
                                                <th>Минусы</th>
                                                <th>Комментарий</th>
                                                <th>Рейтинг</th>
                                                <th>Статус</th>
                                                <th>Действия</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <#if reviewsPage??>
                                                    <#list reviewsPage.getContent() as r>
                                                        <tr>
                                                            <td>${r.id}</td>
                                                            <td><a href="/store/product/${r.productData.id}" target="_blank">${r.productData.name}</a></td>
                                                            <td>${r.advantages}</td>
                                                            <td>${r.disadvantages}</td>
                                                            <td>${r.comment}</td>
                                                            <td>${r.rating}</td>
                                                            <td>${r.statusReview.text}</td>
                                                            <td><a href="/store/product/${r.productData.id}">Изменить</a></td>
                                                        </tr>
                                                    </#list>
                                                <#else>
                                                    <tr>
                                                        <td colspan="8">Пусто</td>
                                                    </tr>
                                                </#if>
                                            </tbody>
                                        </table>
                                    </div>
                                    <@pagination.template
                                    page = reviewsPage
                                    url = "/user/profile/reviews"
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