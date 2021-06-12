import Vue from 'vue'
import Router from 'vue-router'

import index from '~/pages/index'
import search from '~/pages/search'
import bookinstance from '~/pages/bookinstance'
import notfound from '~/pages/notfound'

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
      {
        path: '/book/:id',
        component: bookinstance,
        name: 'bookinstance',
      },



      // catchall 404
      {
        path: '/:catchAll(.*)',
        component: notfound,
        name: 'notfound',
        meta: {title: "404"}
      }
    ]
  })
}