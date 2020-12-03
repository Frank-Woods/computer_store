<#import "../../macro/main.ftlh" as main>
<#import "../../../macro/pagination.ftlh" as pagination>
<#import "../../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Характеристики категории #${productCategory.id}</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Действия</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <#if 0 < attributes.numberOfElements>
                        <#list attributes.getContent() as attribute>
                            <tr>
                                <td>${attribute.id}</td>
                                <td>${attribute.name}</td>
                                <td>${attribute.description}</td>
                                <td class="dropdown no-arrow table-action">
                                    <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                        <i class="fas fa-caret-down"></i>
                                    </button>
                                    <div class="dropdown-menu dropdown-menu-right shadow" aria-labelledby="dropdownMenuButton" x-placement="bottom-start">
                                        <a class="dropdown-item" href="/admin/productCategories/attributes/update/${attribute.id}">Изменить</a>
                                        <form class="dropdown-item" name="delete">
                                            <div class="custom-input">
                                                <div class="custom-input__settings">
                                                    <span class="custom-input__settings__attributes">{"name": "id", "type": "hidden", "defaultValue": "${attribute.id}"}</span>
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
                            <td colspan="4" class="text-center">Пусто</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <@pagination.template
            url = "/admin/productCategories/${productCategory.id}/attributes/all"
            page = attributes
            ></@pagination.template>
        </div>
    </div>
</#assign>

<#assign scripts>
    <script src="/static/js/admin/category/attribute/delete.js"></script>
    <@form.js></@form.js>
</#assign>

<@main.template
links = links
body = body
page = "Категории"
scripts = scripts
></@main.template>