const forms = document.getElementsByName("update");

const param = {
    body: 'sale',
    url: '/admin/sale/update',
    redirect: `/admin/sale/${saleId}/product/all`,
    method: 'post',
}