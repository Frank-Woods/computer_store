<#import "panel.ftlh" as panel>
<#import "header.ftlh" as header>

<#macro template
    links = ""
    body = ""
    page = ""
    scripts = ""
>
    <!DOCTYPE html>
    <html lang="ru">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="_csrf" content="${_csrf.token}">
        <meta name="_csrf_header" content="${_csrf.headerName}">

        <title>Admin</title>

        <link href="/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <link href="/static/css/sb-admin-2.css" rel="stylesheet">
        <link rel="icon" href="/static/icons/favicon.ico">

        ${links}

    </head>
    <body id="page-top">

    <div id="wrapper">

        <@panel.template></@panel.template>

        <div id="content-wrapper" class="d-flex flex-column">

            <div id="content">

                <@header.template
                    page = page
                ></@header.template>

                ${body}

            </div>

        </div>
    </div>

    <script src="/static/vendor/jquery/jquery.min.js"></script>
    <script src="/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/static/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="/static/js/sb-admin-2.min.js"></script>
    <script src="/static/vendor/form/js/ToastEvent.js"></script>
    <script src="/static/vendor/form/js/Timer.js"></script>
    <script src="/static/vendor/form/js/Toast.js"></script>

    ${scripts}

    </body>
    </html>
</#macro>