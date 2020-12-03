const forms = document.getElementsByName("create");

const param = {
    body: 'category',
    url: '/admin/category/create',
    redirect: '/admin/category/all',
    method: 'post',
}