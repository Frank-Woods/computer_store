const forms = document.getElementsByName("create");

const param = {
    body: 'product',
    url: `/admin/promotion/${promotionId}/product/create`,
    redirect: `/admin/promotion/${promotionId}/product/all`,
    method: 'post',
}