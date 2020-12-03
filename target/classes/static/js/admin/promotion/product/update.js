const forms = document.getElementsByName("update");

const param = {
    body: 'promotion',
    url: '/admin/promotion/product/update',
    redirect: `/admin/promotion/${promotionId}/product/all`,
    method: 'post',
}