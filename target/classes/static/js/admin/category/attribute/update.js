const forms = document.getElementsByName("update");

const param = {
    body: 'attribute',
    url: '/admin/category/attribute/update',
    redirect: `${window.location}`,
    method: 'post',
}