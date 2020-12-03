const forms = document.getElementsByName("update");

const param = {
    body: 'promotion',
    url: '/admin/promotion/update',
    redirect: '/admin/promotion/all',
    method: 'post',
}