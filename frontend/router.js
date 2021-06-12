import Vue from 'vue'
import Router from 'vue-router'

import index from '~/pages/index'
import search from '~/pages/search'

Vue.use(Router)

export function createRouter() {
  return new Router({
    mode: 'history',
    routes: [
      {
        path: '/',
        component: index,
        name: 'index'
      },
      {
        path: '/search',
        component: search,
        name: 'search'
      },
    ]
  })
}