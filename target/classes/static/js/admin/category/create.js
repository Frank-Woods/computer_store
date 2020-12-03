const forms = document.getElementsByName("create");

const param = {
    body: 'category',
    url: '/admin/productCategory/create',
    redirect: '/admin/category/all',
    method: 'post',
}