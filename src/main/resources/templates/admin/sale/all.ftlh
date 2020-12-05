<#import "../macro/main.ftlh" as main>
<#import "../../macro/pagination.ftlh" as pagination>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Все продажи</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Пользователь</th>
                        <th>Дата</th>
                        <th>Адрес</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Пользователь</th>
                        <th>Дата</th>
                        <th>Адрес</th>
                        <th>Действия</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <#if 0 < salesPage.numberOfElements>
                        <#list salesPage.getContent() as sale>
                            <tr>
                                <td>${sale.id}</td>
                                <td>${sale.user.email}</td>
                                <td>${sale.date}</td>
                                <td>${sale.address}</td>
                                <td class="dropdown no-arrow table-action">
                                    <a href="/admin/sale/${sale.id}/product/all" class="custom-button mt-0">Подробнее</a>
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
            url = "/admin/sale/all"
            page = salesPage
            ></@pagination.template>
        </div>
    </div>
</#assign>

<@main.template
links = links
body = body
page = "Продажи"
></@main.template>