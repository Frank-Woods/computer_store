const forms = document.getElementsByName("delete");

const param = {
    body: 'product',
    url: '/admin/promotion/product/delete',
    redirect: `${window.location}`,
    method: 'post',
}