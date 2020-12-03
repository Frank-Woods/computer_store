const forms = document.getElementsByName("create");

const param = {
    body: 'promotion',
    url: `/admin/promotion/${promotionId}/product/create`,
    redirect: `/admin/promotion/${promotionId}/product/all`,
    method: 'post',
}