const forms = document.getElementsByName("create");

const param = {
    body: 'attribute',
    url: `/admin/product/${productId}/attribute/create`,
    redirect: `/admin/product/${productId}/attribute/all`,
    method: 'post',
}