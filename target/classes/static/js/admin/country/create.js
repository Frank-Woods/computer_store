const forms = document.getElementsByName("create");

const param = {
    body: 'country',
    url: '/admin/country/create',
    redirect: '/admin/country/all',
    method: 'post',
}