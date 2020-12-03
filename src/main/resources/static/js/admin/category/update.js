const forms = document.getElementsByName("update");

const param = {
    body: 'category',
    url: '/admin/category/update',
    redirect: '/admin/category/all',
    method: 'post',
}