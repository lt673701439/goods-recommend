import fetch from '@/utils/fetch'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return fetch({
    url: '/api/api/login',
    // url: '/login/login',
    method: 'post',
    data
  })
}

export function logout() {
  return fetch({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return fetch({
    // url: '/user/info',
    url: '/api/api/isLogin',
    method: 'get'
    // params: { token }
  })
}

