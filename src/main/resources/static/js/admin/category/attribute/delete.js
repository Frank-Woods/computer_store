const forms = document.getElementsByName("delete");

const param = {
    body: 'attribute',
    url: '/admin/category/attribute/delete',
    redirect: `${window.location}`,
    method: 'post',
}