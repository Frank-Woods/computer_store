const forms = document.getElementsByName("update");

const param = {
    body: 'productCategory',
    url: '/admin/productCategory/update',
    redirect: '/admin/category/all',
    method: 'post',
}