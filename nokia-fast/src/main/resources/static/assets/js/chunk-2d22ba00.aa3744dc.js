(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d22ba00"],{f088:function(e,n,t){"use strict";t.r(n);var u=function(){var e=this,n=e.$createElement,t=e._self._c||n;return e.menu.list&&e.menu.list.length>=1?t("el-submenu",{attrs:{index:e.menu.menuId+"","popper-class":"site-sidebar--"+e.sidebarLayoutSkin+"-popper"}},[t("template",{slot:"title"},[t("icon-svg",{staticClass:"site-sidebar__menu-icon",attrs:{name:e.menu.icon||""}}),t("span",[e._v(e._s(e.menu.name))])],1),e._l(e.menu.list,function(n){return t("sub-menu",{key:n.menuId,attrs:{menu:n,dynamicMenuRoutes:e.dynamicMenuRoutes}})})],2):t("el-menu-item",{attrs:{index:e.menu.menuId+""},on:{click:function(n){return e.gotoRouteHandle(e.menu)}}},[t("icon-svg",{staticClass:"site-sidebar__menu-icon",attrs:{name:e.menu.icon||""}}),t("span",[e._v(e._s(e.menu.name))]),e.menu.proGroupId?t("el-badge",{staticClass:"item",model:{value:e.badgeCount,callback:function(n){e.badgeCount=n},expression:"badgeCount"}}):e._e()],1)},a=[],o=(t("7f7f"),{name:"sub-menu",data:function(){return{badgeCount:0}},props:{menu:{type:Object,required:!0},dynamicMenuRoutes:{type:Array,required:!0}},components:{SubMenu:m},computed:{sidebarLayoutSkin:{get:function(){return this.$store.state.common.sidebarLayoutSkin}}},created:function(){this.getBadgeCount()},methods:{gotoRouteHandle:function(e){var n=this.dynamicMenuRoutes.filter(function(n){return n.meta.menuId===e.menuId});n.length>=1&&this.$router.push({name:n[0].name})},getBadgeCount:function(){var e=this;this.badgeCount=0,this.$http({url:this.$http.adornUrl("/api/wf/findProcessCount"),method:"get",params:this.$http.adornParams({groupId:this.menu.proGroupId})}).then(function(n){var t=n.data;t.count>0?e.badgeCount=t.count:e.badgeCount=""})}}}),s=o,i=t("2877"),r=Object(i["a"])(s,u,a,!1,null,null,null),m=n["default"]=r.exports}}]);
//# sourceMappingURL=chunk-2d22ba00.aa3744dc.js.map