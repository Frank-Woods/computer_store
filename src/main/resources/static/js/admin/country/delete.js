const forms = document.getElementsByName("delete");

const param = {
    body: 'country',
    url: '/admin/country/delete',
    redirect: '/admin/country/all',
    method: 'post',
}