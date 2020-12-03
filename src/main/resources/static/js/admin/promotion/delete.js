const forms = document.getElementsByName("delete");

const param = {
    body: 'promotion',
    url: '/admin/promotion/delete',
    redirect: '/admin/promotion/all',
    method: 'post',
}