const forms = document.getElementsByName("update");

const param = {
    body: 'attribute',
    url: '/admin/product/attribute/update',
    redirect: `${window.location}`,
    method: 'post',
}