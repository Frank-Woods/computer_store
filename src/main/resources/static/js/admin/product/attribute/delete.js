const forms = document.getElementsByName("delete");

const param = {
    body: 'attribute',
    url: '/admin/product/attribute/delete',
    redirect: `${window.location}`,
    method: 'post',
}