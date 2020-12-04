const forms = document.getElementsByName("update");

const param = {
    body: 'review',
    url: '/admin/review/update',
    redirect: '/admin/review/all',
    method: 'post',
}