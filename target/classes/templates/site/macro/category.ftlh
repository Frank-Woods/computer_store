<#macro indexCart
ctg
>
    <div class="col-lg-6 col-md-6 col-12 col-sm-6">
        <div class="banner-wrap-2 mega-fashion-banner-bg1 mb-30 shadow">
            <div class="row">
                <div class="col-xl-12 col-lg-12">
                    <div class="banner-content-22">
                        <h3>${ctg.name}</h3>
                        <p class="mb-2"><#noautoesc>${ctg.description}</#noautoesc></p>
                        <div class="banner-btn-4 black">
                            <a href="/site/catalog/${ctg.id}">Дальше <img class="inject-me arrow-mrg-dec" src="/static/assets/images/icon-img/right-arrow-banner-2.svg" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>