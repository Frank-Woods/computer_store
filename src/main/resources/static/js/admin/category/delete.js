const forms = document.getElementsByName("delete");

const param = {
    body: 'category',
    url: '/admin/category/delete',
    redirect: '/admin/category/all',
    method: 'post',
}