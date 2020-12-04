<#import "../macro/main.ftlh" as main>
<#import "../../macro/pagination.ftlh" as pagination>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Все отзывы</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Пользователь</th>
                        <th>Товар</th>
                        <th>Плюсы</th>
                        <th>Минусы</th>
                        <th>Комментарий</th>
                        <th>Рейтинг</th>
                        <th>Статус</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Пользователь</th>
                        <th>Товар</th>
                        <th>Плюсы</th>
                        <th>Минусы</th>
                        <th>Комментарий</th>
                        <th>Рейтинг</th>
                        <th>Статус</th>
                        <th>Действия</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <#if 0 < reviewsPage.numberOfElements>
                        <#list reviewsPage.getContent() as review>
                            <tr>
                                <td>${review.id}</td>
                                <td>${review.user.email}</td>
                                <td><a href="/store/product/${review.productData.id}" target="_blank">${review.productData.name}</a></td>
                                <td>${review.advantages}</td>
                                <td>${review.disadvantages}</td>
                                <td>${review.comment}</td>
                                <td>${review.rating}</td>
                                <td>${review.statusReview.text}</td>
                                <td class="dropdown no-arrow table-action">
                                    <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                        <i class="fas fa-caret-down"></i>
                                    </button>
                                    <div class="dropdown-menu dropdown-menu-right shadow" aria-labelledby="dropdownMenuButton" x-placement="bottom-start">
                                        <a class="dropdown-item" href="/admin/review/update/${review.id}">Изменить</a>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="7" class="text-center">Пусто</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <@pagination.template
            url = "/admin/review/all"
            page = reviewsPage
            ></@pagination.template>
        </div>
    </div>
</#assign>

<@main.template
links = links
body = body
page = "Отзывы"
scripts = scripts
></@main.template>