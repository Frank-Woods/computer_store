<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/pagination.ftlh" as pagination>
<#import "../../../macro/form.ftlh" as form>

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
                        <th>Продукт</th>
                        <th>Статус</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Продукт</th>
                        <th>Статус</th>
                        <th>Действия</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <#if 0 < saleProducts.numberOfElements>
                        <#list saleProducts.getContent() as sp>
                            <tr>
                                <td>${sp.id}</td>
                                <td><a href="/store/product/${sp.productData.id}">${sp.productData.id}</a></td>
                                <td>${sp.statusSale.text}</td>
                                <td class="dropdown no-arrow table-action">
                                    <a href="/admin/sale/product/update/${sp.id}" class="custom-button mt-0">Изменить</a>
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
            url = "/admin/sale/${sale.id}/product/all"
            page = saleProducts
            ></@pagination.template>
        </div>
    </div>
</#assign>

<@main.template
links = links
body = body
page = "Продажи"
></@main.template>