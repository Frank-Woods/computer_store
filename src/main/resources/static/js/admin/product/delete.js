const forms = document.getElementsByName("delete");

const param = {
    body: 'product',
    url: '/admin/product/delete',
    redirect: '/admin/product/all',
    method: 'post',
}