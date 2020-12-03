const forms = document.getElementsByName("create");

const param = {
    body: 'productCategory',
    url: '/admin/productCategory/create',
    redirect: '/admin/category/all',
    method: 'post',
}