const forms = document.getElementsByName("update");

const param = {
    body: 'attribute',
    url: '/admin/productCategory/attribute/update',
    redirect: `${window.location}`,
    method: 'post',
}