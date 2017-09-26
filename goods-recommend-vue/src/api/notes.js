import fetch from '@/utils/fetch'

export function fetchList(query) {
  return fetch({
    url: 'http://localhost:8080/mongo/board/getNotesList',
    method: 'get',
    params: query
  })
}

export function fetchArticle() {
  return fetch({
    url: '/article/detail',
    method: 'get'
  })
}

export function fetchPv(pv) {
  return fetch({
    url: '/article/pv',
    method: 'get',
    params: { pv }
  })
}
