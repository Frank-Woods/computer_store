<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/security.ftlh" as security>

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
                                                <th>Дата</th>
                                                <th>Статус</th>
                                                <th>Стоимость</th>
                                                <th>Действия</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>Aug 22, 2018</td>
                                                <td>Pending</td>
                                                <td>$3000</td>
                                                <td><a href="cart.html" class="check-btn sqr-btn ">View</a></td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>July 22, 2018</td>
                                                <td>Approved</td>
                                                <td>$200</td>
                                                <td><a href="cart.html" class="check-btn sqr-btn ">View</a></td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>June 12, 2017</td>
                                                <td>On Hold</td>
                                                <td>$990</td>
                                                <td><a href="cart.html" class="check-btn sqr-btn ">View</a></td>
                                            </tr>
                                            </tbody>
                                        </table>
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

<@main.template
body = body
>
</@main.template>