const forms = document.getElementsByName("update");

const param = {
    body: 'attribute',
    url: '/admin/product/attribute/update',
    redirect: `/admin/product/${productId}/attribute/all`,
    method: 'post',
}