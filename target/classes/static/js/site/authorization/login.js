const forms = document.getElementsByName("login");

const param = {
    body: 'user',
    url: '/user/login',
    redirect: '/user/profile/details',
    method: 'post',
}