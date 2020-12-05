<#macro template
    page = ""
    user = "Алексей Спицын"
>
    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
        </button>

        <span class="ml-3">
            ${page}
        </span>

        <ul class="navbar-nav ml-auto">

            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user}</span>
                    <img class="img-profile rounded-circle"
                         src="/static/icons/undraw_profile.svg"
                         alt="profile">
                </a>
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                     aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="/user/profile/details">
                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                        Профиль
                    </a>
                    <a class="dropdown-item" href="/">
                        <i class="fas fa-store fa-sm fa-fw mr-2 text-gray-400"></i>
                        Сайт
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/logout" data-toggle="modal" data-target="#logoutModal">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        <form action="/logout" method="post" class="d-inline">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button class="header-button">Выйти</button>
                        </form>
                    </a>
                </div>
            </li>

        </ul>

    </nav>
</#macro>