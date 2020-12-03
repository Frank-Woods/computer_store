const forms = document.getElementsByName("update");

const param = {
    body: 'attribute',
    url: '/admin/category/attribute/update',
    redirect: `/admin/category/${categoryId}/attribute/all`,
    method: 'post',
}