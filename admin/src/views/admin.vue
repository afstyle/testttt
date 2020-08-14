<template>
    <div class="layout">
        <Layout style="height: 100%;">
            <Header>
                <Menu mode="horizontal" theme="dark" @on-select="navClick">
                    <div class="layout-logo"></div>
                    <div class="layout-nav">
                        <MenuItem name="logout" >
                            <Icon type="ios-person" />
                            用户登出
                        </MenuItem>
                        <!--<Submenu name="2">
                            <template slot="title">
                                <Icon type="ios-settings" />
                                消息
                            </template>
                            <MenuGroup title="使用">
                                <MenuItem name="2-1">新增和启动</MenuItem>
                                <MenuItem name="2-2">活跃分析</MenuItem>
                                <MenuItem name="2-3">时段分析</MenuItem>
                            </MenuGroup>
                            <MenuGroup title="留存">
                                <MenuItem name="2-4">用户留存</MenuItem>
                                <MenuItem name="2-5">流失用户</MenuItem>
                            </MenuGroup>
                        </Submenu>-->
                    </div>
                    
                </Menu>
            </Header>
            <Layout>
                <Sider hide-trigger :style="{background: '#fff'}">
                    <Menu :active-name="menuActiveName" theme="light" width="auto" v-for="(item, index) in menuList" @on-select="menuClick">
                        <MenuItem v-if="!item.subMenu" :name="item.route">
                            <Icon :type="item.icon"></Icon>
                            {{ item.name }}
                        </MenuItem>
                        <Submenu v-else :name="item.route">
                            <template slot="title">
                                <Icon :type="item.icon"></Icon>
                                {{ item.name }}
                            </template>
                            <MenuItem :name="subMenu.route" v-for="subMenu in item.subMenu"> {{ subMenu.name }} </MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Layout :style="{padding: '24px 24px 0'}">
<!--                    <Row :style="{margin: '24px 0'}">-->
<!--&lt;!&ndash;                        <BreadcrumbItem>Home</BreadcrumbItem>&ndash;&gt;-->
<!--&lt;!&ndash;                        <BreadcrumbItem>Components</BreadcrumbItem>&ndash;&gt;-->
<!--&lt;!&ndash;                        <BreadcrumbItem>Layout</BreadcrumbItem>&ndash;&gt;-->
<!--                        <Notification style="float: right; margin-right: 100px;"-->
<!--                                auto-count-->
<!--                                @on-load-more="handleLoadMore"-->
<!--                                @on-clear="handleClear"-->
<!--                        >-->
<!--                            <NotificationTab-->
<!--                                    title="通知"-->
<!--                                    name="message"-->
<!--                                    :count="unreadMessage"-->
<!--                                    :loaded-all="messageList.length >= 15"-->
<!--                                    :loading="messageLoading"-->
<!--                                    :scroll-to-load="false"-->
<!--                            >-->
<!--                                <NotificationItem-->
<!--                                        v-for="(item, index) in messageList"-->
<!--                                        :key="index"-->
<!--                                        :title="item.title"-->
<!--                                        :icon="item.icon"-->
<!--                                        :icon-color="item.iconColor"-->
<!--                                        :time="item.time"-->
<!--                                        :read="item.read"-->
<!--                                />-->
<!--                            </NotificationTab>-->
<!--                            <NotificationTab-->
<!--                                    title="关注"-->
<!--                                    name="follow"-->
<!--                                    :count="unreadFollow"-->
<!--                                    :loaded-all="followList.length >= 15"-->
<!--                                    :loading="followLoading"-->
<!--                                    :scroll-to-load="false"-->
<!--                            >-->
<!--                                <NotificationItem-->
<!--                                        v-for="(item, index) in followList"-->
<!--                                        :key="index"-->
<!--                                        :avatar="item.avatar"-->
<!--                                        :title="item.title"-->
<!--                                        :time="item.time"-->
<!--                                        :read="item.read"-->
<!--                                />-->
<!--                            </NotificationTab>-->
<!--                            <NotificationTab-->
<!--                                    title="待办"-->
<!--                                    name="todo"-->
<!--                                    :count="unreadTodo"-->
<!--                                    :loaded-all="todoList.length >= 15"-->
<!--                                    :loading="todoLoading"-->
<!--                                    :scroll-to-load="false"-->
<!--                            >-->
<!--                                <NotificationItem-->
<!--                                        v-for="(item, index) in todoList"-->
<!--                                        :key="index"-->
<!--                                        :title="item.title"-->
<!--                                        :content="item.content"-->
<!--                                        :tag="item.tag"-->
<!--                                        :tag-props="item.tagProps"-->
<!--                                        :read="item.read"-->
<!--                                />-->
<!--                            </NotificationTab>-->
<!--                        </Notification>-->
<!--                    </Row>-->
                    
                    <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
                        <router-view></router-view>
                    </Content>
                    <GlobalFooter style="margin-top: 15px" :links="links" :copyright="copyright" />
                </Layout>
            </Layout>
        </Layout>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                menuActiveName: '',
                menuList:[
                    {
                        route: 'welcome',
                        name: '首页',
                        icon: 'ios-navigate',
                    },
                    {
                        route: 'business',
                        name: '业务管理',
                        icon: 'ios-keypad',
                        subMenu: [
                            {
                                route: 'chapter',
                                name: '大章管理'
                            },
                        ]
                    },
                ],
                messageBaseList: [
                    {
                        icon: 'md-mail',
                        iconColor: '#3391e5',
                        title: '蒂姆·库克回复了你的邮件',
                        read: 1,
                        time: 1557297198
                    },
                ],
                followBaseList: [
                    {
                        avatar: 'https://dev-file.iviewui.com/BbnuuEiM0QXNPHVCvb3E2AFrawIjCkqW/avatar',
                        title: '史蒂夫·乔布斯 关注了你',
                        read: 1,
                        time: 1557299299
                    },
                ],
                todoBaseList: [
                    {
                        title: '2019 下半年 OKR',
                        content: '需要在 2019-06-14 之前完成',
                        tag: '未开始',
                        tagProps: {
                            color: 'default'
                        },
                        read: 1
                    },
                ],
                messageList: [],
                followList: [],
                todoList: [],
                messageLoading: false,
                followLoading: false,
                todoLoading: false,
                links: [
                    {
                        key: '帮助',
                        title: '帮助',
                        href: 'https://www.iviewui.com',
                        blankTarget: true
                    },
                    {
                        key: 'github',
                        icon: 'logo-github',
                        href: 'https://github.com/iview/iview',
                        blankTarget: true
                    },
                    {
                        key: '条款',
                        title: '条款',
                        href: '',
                        blankTarget: true
                    }
                ],
                copyright: 'Copyright © 2020 AfSty1e'
            }
        },
        computed: {
            unreadMessage () {
                let unread = 0;
                this.messageList.forEach(item => {
                    if (!item.read) unread++;
                });
                return unread;
            },
            unreadFollow () {
                let unread = 0;
                this.followList.forEach(item => {
                    if (!item.read) unread++;
                });
                return unread;
            },
            unreadTodo () {
                let unread = 0;
                this.todoList.forEach(item => {
                    if (!item.read) unread++;
                });
                return unread;
            }
        },
        methods: {
            menuClick(route) {
                if (!route) {
                    return;
                }
                this.menuActiveName = route;
                let routePath = '';

                let menuClick = this.menuList.find(menu => menu.route === route || (menu.subMenu && menu.subMenu.find(submenu => submenu.route === route)));
                if (menuClick.subMenu) {
                    routePath =  '/admin/' + menuClick.route + '/' + menuClick.subMenu.find(submenu => submenu.route === route).route;
                } else {
                    routePath =  '/admin/' + menuClick.route;
                }
                if (routePath && routePath.length > 0) {
                    console.log('跳转至路由：' + routePath)
                    this.$router.push(routePath);
                }
            },
            navClick(name) {
                if (name && name === 'logout') {
                    this.$router.push('/login');
                }
            },
            handleLoadMore (tab) {
                this.loadMore(tab.name);
            },
            loadMore (type) {
                if (this[`${type}Loading`]) return;
                this[`${type}Loading`] = true;
                setTimeout(() => {
                    this[`${type}List`] = this[`${type}List`].concat([...this[`${type}BaseList`]]);
                    this[`${type}Loading`] = false;
                }, 1000);
            },
            handleClear (tab) {
                this.clearUnread(tab.name);
            },
            clearUnread (type) {
                this[`${type}List`] = this[`${type}List`].map(item => {
                    item.read = 1;
                    return item;
                });
            }
        },
        mounted () {
            this.menuActiveName = this.menuList[0].route;
            this.messageList = [...this.messageBaseList];
            this.followList = [...this.followBaseList];
            this.todoList = [...this.todoBaseList];
        }
    }
</script>
<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
        height: 100%;
    }
    .layout-logo{
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }
    .layout-nav{
        width: 130px;
        margin: 0 auto;
        margin-right: 20px;
    }
</style>