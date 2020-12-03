const forms = document.getElementsByName("delete");

const param = {
    body: 'manufacturer',
    url: '/admin/manufacturer/delete',
    redirect: '/admin/manufacturer/all',
    method: 'post',
}