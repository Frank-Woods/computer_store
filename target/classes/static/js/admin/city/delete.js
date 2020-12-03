const forms = document.getElementsByName("delete");

const param = {
    body: 'city',
    url: '/admin/city/delete',
    redirect: '/admin/city/all',
    method: 'post',
}