const forms = document.getElementsByName("update");

const param = {
    body: 'manufacturer',
    url: '/admin/manufacturer/update',
    redirect: '/admin/manufacturer/all',
    method: 'post',
}