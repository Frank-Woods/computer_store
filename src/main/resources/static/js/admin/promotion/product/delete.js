const forms = document.getElementsByName("delete");

const param = {
    body: 'promotion',
    url: '/admin/promotion/product/delete',
    redirect: `${window.location}`,
    method: 'post',
}