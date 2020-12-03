const forms = document.getElementsByName("update");

const param = {
    body: 'product',
    url: '/admin/promotion/product/update',
    redirect: `/admin/promotion/${promotionId}/product/all`,
    method: 'post',
}