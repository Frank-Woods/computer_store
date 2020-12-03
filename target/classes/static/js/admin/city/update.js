const forms = document.getElementsByName("update");

const param = {
    body: 'city',
    url: '/admin/city/update',
    redirect: '/admin/city/all',
    method: 'post',
}