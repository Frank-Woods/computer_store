const forms = document.getElementsByName("login");

const param = {
    body: 'user',
    url: '/user/login',
    redirect: '/',
    method: 'post',
}