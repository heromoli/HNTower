<template>
    <el-submenu
            v-if="menu.list && menu.list.length >= 1"
            :index="menu.menuId + ''"
            :popper-class="'site-sidebar--' + sidebarLayoutSkin + '-popper'">
        <template slot="title">
            <icon-svg :name="menu.icon || ''" class="site-sidebar__menu-icon"></icon-svg>
            <span>{{ menu.name }}</span>
        </template>
        <sub-menu
                v-for="item in menu.list"
                :key="item.menuId"
                :menu="item"
                :dynamicMenuRoutes="dynamicMenuRoutes">
        </sub-menu>
    </el-submenu>
    <el-menu-item v-else :index="menu.menuId + ''" @click="gotoRouteHandle(menu)">
        <icon-svg :name="menu.icon || ''" class="site-sidebar__menu-icon"></icon-svg>
        <span>{{ menu.name }}</span>
        <el-badge v-if="menu.proGroupId" v-model="badgeCount" class="item">
        </el-badge>
    </el-menu-item>
</template>

<script>
    import SubMenu from './main-sidebar-sub-menu'

    export default {
        name: 'sub-menu',
        data() {
            return {
                badgeCount: 0,
            }
        },
        props: {
            menu: {
                type: Object,
                required: true
            },
            dynamicMenuRoutes: {
                type: Array,
                required: true
            }
        },
        components: {
            SubMenu
        },
        computed: {
            sidebarLayoutSkin: {
                get() {
                    return this.$store.state.common.sidebarLayoutSkin
                }
            }
        },
        created() {
            this.getBadgeCount()
        },
        methods: {
            // 通过menuId与动态(菜单)路由进行匹配跳转至指定路由
            gotoRouteHandle(menu) {
                var route = this.dynamicMenuRoutes.filter(item => item.meta.menuId === menu.menuId);
                if (route.length >= 1) {
                    this.$router.push({name: route[0].name})
                }
            },
            getBadgeCount() {
                this.badgeCount = 0;
                this.$http({
                    url: this.$http.adornUrl('/api/wf/findProcessCount'),
                    method: 'get',
                    params: this.$http.adornParams({
                        'groupId': this.menu.proGroupId,
                    })
                }).then(({data}) => {
                    if (data.count > 0) {
                        this.badgeCount = data.count
                    } else {
                        this.badgeCount = ''
                    }
                })
            }
        }
    }
</script>