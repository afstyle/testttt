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
        path: '/',
        name: 'admin',
        meta: {
            title: '首页'
        },
        component: (resolve) => require(['./views/admin.vue'], resolve),
        children: [
            {
                path: 'welcome', // 子路由的配置不要用'/'开头！！！
                name: 'welcome',
                meta: {
                    title: '欢迎'
                },
                component: (resolve) => require(['./views/admin/welcome.vue'], resolve)
            },{
                path: 'business/chapter', // 子路由的配置不要用'/'开头！！！
                name: 'business/chapter',
                meta: {
                    title: '大章管理'
                },
                component: (resolve) => require(['./views/admin/chapter.vue'], resolve)
            },{
                path: 'business/section', // 子路由的配置不要用'/'开头！！！
                name: 'business/section',
                meta: {
                    title: '小节管理'
                },
                component: (resolve) => require(['./views/admin/section.vue'], resolve)
            },{
                path: 'business/course', // 子路由的配置不要用'/'开头！！！
                name: 'business/course',
                meta: {
                    title: '课程管理'
                },
                component: (resolve) => require(['./views/admin/course.vue'], resolve)
            },
        ]
    },
];
export default routers;