const forms = document.getElementsByName("delete");

const param = {
    body: 'attribute',
    url: '/admin/productCategory/attribute/delete',
    redirect: `${window.location}`,
    method: 'post',
}