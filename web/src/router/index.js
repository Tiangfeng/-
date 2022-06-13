import { createRouter, createWebHistory } from 'vue-router'
import TheLayout from '@/layout/TheLayout';
import UserLogin from '@/components/UserLogin';

const routes = [
  {
    path: '/',
    redirect: '/login',
    name: 'Layout',
    component: TheLayout,
    children: [
      {
        path: '/home',
        name: 'Home',
        component: () => import('@/views/HomeView')
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: UserLogin
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
