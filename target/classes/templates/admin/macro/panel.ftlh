<#macro template>
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/admin">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Store</div>
        </a>

        <hr class="sidebar-divider my-0">

        <li class="nav-item active">
            <a class="nav-link" href="/admin">
                <span>Главная</span></a>
        </li>

        <hr class="sidebar-divider">

        <div class="sidebar-heading">
            Сущности
        </div>

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <span>Страны</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Страницы:</h6>
                    <a class="collapse-item" href="/admin/country/all">Все страны</a>
                    <a class="collapse-item" href="/admin/country/create">Добавить</a>
                </div>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
               aria-expanded="true" aria-controls="collapseUtilities">
                <span>Города</span>
            </a>
            <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Страницы:</h6>
                    <a class="collapse-item" href="/admin/city/all">Все города</a>
                    <a class="collapse-item" href="/admin/city/create">Добавить</a>
                </div>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseManufacturer"
               aria-expanded="true" aria-controls="collapseManufacturer">
                <span>Производители</span>
            </a>
            <div id="collapseManufacturer" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Страницы:</h6>
                    <a class="collapse-item" href="/admin/manufacturer/all">Все производители</a>
                    <a class="collapse-item" href="/admin/manufacturer/create">Добавить</a>
                </div>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePromotion"
               aria-expanded="true" aria-controls="collapsePromotion">
                <span>Акции</span>
            </a>
            <div id="collapsePromotion" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Страницы:</h6>
                    <a class="collapse-item" href="/admin/promotion/all">Все акции</a>
                    <a class="collapse-item" href="/admin/promotion/create">Добавить</a>
                </div>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProduct"
               aria-expanded="true" aria-controls="collapseProduct">
                <span>Продукты</span>
            </a>
            <div id="collapseProduct" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Страницы:</h6>
                    <a class="collapse-item" href="/admin/product/all">Все продукты</a>
                    <a class="collapse-item" href="/admin/product/create">Добавить</a>
                </div>
            </div>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProductCategory"
               aria-expanded="true" aria-controls="collapseProductCategory">
                <span>Категории</span>
            </a>
            <div id="collapseProductCategory" class="collapse" aria-labelledby="headingUtilities"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">Страницы:</h6>
                    <a class="collapse-item" href="/admin/category/all">Все категории</a>
                    <a class="collapse-item" href="/admin/category/create">Добавить</a>
                </div>
            </div>
        </li>

        <hr class="sidebar-divider">

        <div class="sidebar-heading">
            Обработка
        </div>

        <li class="nav-item">
            <a class="nav-link" href="#">
                <span>Продажи</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/admin/review/all">
                <span>Отзывы</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/admin/mail/create">
                <span>Рассылка</span></a>
        </li>


        <hr class="sidebar-divider d-none d-md-block">

        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
</#macro>