<#macro indexCart
    prom
>
    <div class="col-lg-6 col-md-6 col-12 col-sm-6">
        <div class="banner-wrap-2 mega-fashion-banner-bg1 mb-30">
            <div class="row">
                <div class="col-xl-8 col-lg-12">
                    <div class="banner-content-22">
                        <h3>${prom.name}</h3>
                        <p class="mb-2"><#noautoesc>${prom.description}</#noautoesc></p>
                        <p class="m-0">${prom.dateStart}</p>
                        <p class="m-0">${prom.dateEnd}</p>
                        <div class="banner-btn-4 black">
                            <a href="/promotions/${prom.id}/products">Продукты <img class="inject-me arrow-mrg-dec" src="/static/assets/images/icon-img/right-arrow-banner-2.svg" alt=""></a>
                        </div>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-12">
                    <div class="banner-img-2 banner-img-2-mrg1">
                        <a href="/promotions/${prom.id}/products"><img src="/image/promotionBanner/${prom.banner.filename}" alt=""></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>