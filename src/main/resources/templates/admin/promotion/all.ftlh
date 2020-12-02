<#import "../macro/main.ftlh" as main>

<#assign body>
    <div class="card shadow container p-0">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Все акции</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Логотип</th>
                        <th>Дата начала</th>
                        <th>Дата окончания</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Логотип</th>
                        <th>Дата начала</th>
                        <th>Дата окончания</th>
                        <th>Действия</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr>
                        <td>Tiger Nixon</td>
                        <td>System Architect</td>
                        <td>Edinburgh</td>
                        <td><a href="/static/icons/undraw_posting_photo.svg" target="_blank"><img src="/static/icons/undraw_posting_photo.svg" alt="logo"></a></td>
                        <td>Tiger Nixon</td>
                        <td>System Architect</td>
                        <td class="dropdown no-arrow table-action">
                            <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <i class="fas fa-caret-down"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right shadow" aria-labelledby="dropdownMenuButton" x-placement="bottom-start">
                                <a class="dropdown-item" href="#">Изменить</a>
                                <a class="dropdown-item" href="#">Удалить</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Garrett Winters</td>
                        <td>Accountant</td>
                        <td>Tokyo</td>
                        <td><a href="/static/icons/undraw_posting_photo.svg" target="_blank"><img src="/static/icons/undraw_posting_photo.svg" alt="logo"></a></td>
                        <td>Tiger Nixon</td>
                        <td>System Architect</td>
                        <td class="dropdown no-arrow table-action">
                            <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <i class="fas fa-caret-down"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right shadow" aria-labelledby="dropdownMenuButton" x-placement="bottom-start">
                                <a class="dropdown-item" href="#">Изменить</a>
                                <a class="dropdown-item" href="#">Удалить</a>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</#assign>

<@main.template
body = body
page = "Акции"
></@main.template>