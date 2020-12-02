const forms = document.getElementsByName("create");

const param = {
    body: 'city',
    url: '/admin/city/create',
    redirect: '/admin/city/all',
    method: 'post',
}