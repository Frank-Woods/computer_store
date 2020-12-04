const forms = document.getElementsByName("login");

const param = {
    body: 'user',
    url: '/login',
    redirect: '/user/profile/details',
    method: 'post',
}