const routers = [
    {
        path: '*',
        redirect: '/login'
    },
    {
        path: '/login',
        meta: {
            title: '登录'
        },
        component: (resolve) => require(['./views/login/login.vue'], resolve)
    },
    {
        path: '/admin',
        meta: {
            title: '首页'
        },
        component: (resolve) => require(['./views/admin.vue'], resolve),
        children: [
            {
                path: 'welcome', // 子路由的配置不要用'/'开头！！！
                meta: {
                    title: '欢迎'
                },
                component: (resolve) => require(['./views/admin/welcome.vue'], resolve)
            }
        ]
    },
];
export default routers;