<#import "../macro/main.ftlh" as main>
<#import "../../macro/pagination.ftlh" as pagination>
<#import "../../macro/form.ftlh" as form>

<#assign links>
    <@form.links></@form.links>
</#assign>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Все пользователи</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>email</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Дата регистрации</th>
                        <th>Роль</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>#</th>
                        <th>email</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Дата регистрации</th>
                        <th>Роль</th>
                        <th>Действия</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <#if 0 < usersPage.numberOfElements>
                        <#list usersPage.getContent() as user>
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.registrationDate}</td>
                                <td>${user.roles?last.name()}</td>
                                <td>
                                    <#if user.roles?last.ordinal() == 1>
                                        <form method="post" action="/admin/user/dismiss">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <button>Уволить</button>
                                        </form>
                                    <#else>
                                        <form method="post" action="/admin/user/add">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <button>Принять</button>
                                        </form>
                                    </#if>
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
            url = "/admin/user/all"
            page = usersPage
            ></@pagination.template>
        </div>
    </div>
</#assign>

<@main.template
links = links
body = body
page = "Пользователи"
scripts = scripts
></@main.template>