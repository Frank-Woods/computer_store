const forms = document.getElementsByName("create");

const param = {
    body: 'attribute',
    url: `/admin/category/${categoryId}/attribute/create`,
    redirect: `/admin/category/${categoryId}/attribute/all`,
    method: 'post',
}