<#import "header.ftlh" as header>
<#import "miniCart.ftlh" as miniCart>
<#import "footer.ftlh" as footer>
<#import "../../macro/security.ftlh" as security>

<#macro template
    links = ""
    body = ""
    scripts = ""
    mc = true
>
    <!doctype html>
    <html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Store</title>
        <meta name="robots" content="noindex, follow" />
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="_csrf" content="${_csrf.token}">
        <meta name="_csrf_header" content="${_csrf.headerName}">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="/static/icons/favicon.ico">

        <!-- All CSS is here
        ============================================ -->

        <link rel="stylesheet" href="/static/assets/css/vendor/bootstrap.min.css">
        <link rel="stylesheet" href="/static/assets/css/vendor/vandella.css">
        <link rel="stylesheet" href="/static/assets/css/vendor/jellybelly.css">
        <link rel="stylesheet" href="/static/assets/css/vendor/icofont.min.css">
        <link rel="stylesheet" href="/static/assets/css/vendor/fontello.css">
        <link rel="stylesheet" href="/static/assets/css/plugins/easyzoom.css">
        <link rel="stylesheet" href="/static/assets/css/plugins/slick.css">
        <link rel="stylesheet" href="/static/assets/css/plugins/nice-select.css">
        <link rel="stylesheet" href="/static/assets/css/plugins/animate.css">
        <link rel="stylesheet" href="/static/assets/css/plugins/magnific-popup.css">
        <link rel="stylesheet" href="/static/assets/css/plugins/jquery-ui.css">
        <link rel="stylesheet" href="/static/assets/scss/style.css">
        <link href="/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Use the minified version files listed below for better performance and remove the files listed above
        <link rel="stylesheet" href="/static/assets/css/vendor/vendor.min.css">
        <link rel="stylesheet" href="/static/assets/css/plugins/plugins.min.css">
        <link rel="stylesheet" href="/static/assets/css/style.min.css"> -->

        ${links}

    </head>

    <body>

    <@header.template
        mc = mc
    ></@header.template>
    <@miniCart.template></@miniCart.template>

    ${body}

    <@footer.template></@footer.template>

    <script>const isAuthorized = <#if security.isAuthorized>true<#else>false</#if>;</script>
    <script src="/static/assets/js/vendor/modernizr-3.6.0.min.js"></script>
    <script src="/static/assets/js/vendor/jquery-3.5.1.min.js"></script>
    <script src="/static/assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
    <script src="/static/assets/js/vendor/bootstrap.bundle.min.js"></script>
    <script src="/static/assets/js/plugins/slick.js"></script>
    <script src="/static/assets/js/plugins/countdown.js"></script>
    <script src="/static/assets/js/plugins/wow.js"></script>
    <script src="/static/assets/js/plugins/instafeed.js"></script>
    <script src="/static/assets/js/plugins/svg-injector.min.js"></script>
    <script src="/static/assets/js/plugins/jquery.nice-select.min.js"></script>
    <script src="/static/assets/js/plugins/mouse-parallax.js"></script>
    <script src="/static/assets/js/plugins/images-loaded.js"></script>
    <script src="/static/assets/js/plugins/isotope.js"></script>
    <script src="/static/assets/js/plugins/jquery-ui-touch-punch.js"></script>
    <script src="/static/assets/js/plugins/jquery-ui.js"></script>
    <script src="/static/assets/js/plugins/magnific-popup.js"></script>
    <script src="/static/assets/js/plugins/easyzoom.js"></script>
    <script src="/static/assets/js/plugins/scrollup.js"></script>
    <script src="/static/assets/js/main.js"></script>
    <script src="/static/js/site/cart/main.js"></script>
    <script src="/static/js/site/cart/addProductToCart.js"></script>
    <script src="/static/js/site/cart/loadProductFromCart.js"></script>
    <script src="/static/js/site/cart/deleteProductInCart.js"></script>
    <script src="/static/js/site/cart/createProductInMiniCart.js"></script>

    ${scripts}

    </body>

    </html>
</#macro>