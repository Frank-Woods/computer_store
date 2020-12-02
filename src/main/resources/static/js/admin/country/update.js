const forms = document.getElementsByName("update");

const param = {
    body: 'country',
    url: '/admin/country/update',
    redirect: '/admin/country/all',
    method: 'post',
}