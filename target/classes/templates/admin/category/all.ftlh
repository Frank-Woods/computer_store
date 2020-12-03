<#import "../macro/main.ftlh" as main>
<#import "../../macro/pagination.ftlh" as pagination>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>


<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Все категории</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Родитель</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Родитель</th>
                        <th>Действия</th>
                    </tr>
                    </tfoot>
                    <tbody>
                        <#if 0 < categoriesPage.numberOfElements>
                            <#list categoriesPage.getContent() as category>
                                <tr>
                                    <td>${category.id}</td>
                                    <td>${category.name}</td>
                                    <td><#noautoesc>${category.description}</#noautoesc></td>
                                    <td><#if category.parent??>${category.parent.name}</#if></td>
                                    <td class="dropdown no-arrow table-action">
                                        <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                            <i class="fas fa-caret-down"></i>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right shadow" aria-labelledby="dropdownMenuButton" x-placement="bottom-start">
                                            <a class="dropdown-item" href="/admin/productCategory/${category.id}/attributes/all">Все характеристики</a>
                                            <a class="dropdown-item" href="/admin/productCategory/${category.id}/attribute/create">Добавить характеристику</a>
                                            <a class="dropdown-item" href="/admin/productCategories/update/${category.id}">Изменить</a>
                                            <form class="dropdown-item" name="delete">
                                                <div class="custom-input">
                                                    <div class="custom-input__settings">
                                                        <span class="custom-input__settings__attributes">{"name": "id", "type": "hidden", "defaultValue": "${category.id}"}</span>
                                                    </div>
                                                </div>
                                                <button>Удалить</button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="5" class="text-center">Пусто</td>
                            </tr>
                        </#if>
                    </tbody>
                </table>
            </div>
            <@pagination.template
            url = "/admin/category/all"
            page = categoriesPage
            ></@pagination.template>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/category/delete.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Категории"
scripts = scripts
></@main.template>