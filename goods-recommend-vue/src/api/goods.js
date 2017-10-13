import fetch from '@/utils/fetch'

export function fetchList(query) {
  return fetch({
    url: '/api/mongo/board/getGoodsList',
    method: 'get',
    params: query
  })
}

export function fetchSyblings(goodsid) {
  return fetch({
    url: '/api/mongo/board/getSyblings',
    method: 'get',
    params: { goodsid }
  })
}

export function fetchPv(pv) {
  return fetch({
    url: '/article/pv',
    method: 'get',
    params: { pv }
  })
}

export function fetchArticle() {
  return fetch({
    url: '/article/detail',
    method: 'get'
  })
}
