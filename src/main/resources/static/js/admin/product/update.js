const forms = document.getElementsByName("update");

const param = {
    body: 'product',
    url: '/admin/product/update',
    redirect: '/admin/product/all',
    method: 'post',
}