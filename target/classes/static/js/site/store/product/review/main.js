const forms = document.getElementsByName("review");

const param = {
    body: 'review',
    url: hasReview ? '/review/update' : '/review/create',
    redirect: `${window.location}`,
    method: 'post',
}