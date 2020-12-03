const forms = document.getElementsByName("delete");

const param = {
    body: 'productCategory',
    url: '/admin/productCategory/delete',
    redirect: '/admin/category/all',
    method: 'post',
}