(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6c4614c1","chunk-2d20f5c3"],{"805b":function(t,e,a){"use strict";a.r(e);var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"mod-user"},[a("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.getDataList()}}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"用户名",clearable:""},model:{value:t.dataForm.userName,callback:function(e){t.$set(t.dataForm,"userName",e)},expression:"dataForm.userName"}})],1),a("el-form-item",[a("el-button",{on:{click:function(e){return t.getDataList()}}},[t._v("查询")]),t.isAuth("sys:user:save")?a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.addOrUpdateHandle()}}},[t._v("新增")]):t._e(),t.isAuth("sys:user:delete")?a("el-button",{attrs:{type:"danger",disabled:t.dataListSelections.length<=0},on:{click:function(e){return t.deleteHandle()}}},[t._v("批量删除")]):t._e()],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:""},on:{"selection-change":t.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),a("el-table-column",{attrs:{prop:"userId","header-align":"center",align:"center",width:"80",label:"ID"}}),a("el-table-column",{attrs:{prop:"username","header-align":"center",align:"center",label:"用户名"}}),a("el-table-column",{attrs:{prop:"email","header-align":"center",align:"center",label:"邮箱"}}),a("el-table-column",{attrs:{prop:"mobile","header-align":"center",align:"center",label:"手机号"}}),a("el-table-column",{attrs:{prop:"status","header-align":"center",align:"center",label:"状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[0===e.row.status?a("el-tag",{attrs:{size:"small",type:"danger"}},[t._v("禁用")]):a("el-tag",{attrs:{size:"small"}},[t._v("正常")])]}}])}),a("el-table-column",{attrs:{prop:"createTime","header-align":"center",align:"center",width:"180",label:"创建时间"}}),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[t.isAuth("sys:user:update")?a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){return t.addOrUpdateHandle(e.row.userId)}}},[t._v("修改")]):t._e(),t.isAuth("sys:user:delete")?a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){return t.deleteHandle(e.row.userId)}}},[t._v("删除")]):t._e()]}}])})],1),a("el-pagination",{attrs:{"current-page":t.pageIndex,"page-sizes":[10,20,50,100],"page-size":t.pageSize,total:t.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.sizeChangeHandle,"current-change":t.currentChangeHandle}}),t.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:t.getDataList}}):t._e()],1)},s=[],o=a("b2fc"),i={data:function(){return{dataForm:{userName:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:o["default"]},activated:function(){this.getDataList()},methods:{getDataList:function(){var t=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/sys/user/list"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,username:this.dataForm.userName})}).then(function(e){var a=e.data;a&&0===a.code?(t.dataList=a.page.list,t.totalPage=a.page.totalCount):(t.dataList=[],t.totalPage=0),t.dataListLoading=!1})},sizeChangeHandle:function(t){this.pageSize=t,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(t){this.pageIndex=t,this.getDataList()},selectionChangeHandle:function(t){this.dataListSelections=t},addOrUpdateHandle:function(t){var e=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){e.$refs.addOrUpdate.init(t)})},deleteHandle:function(t){var e=this,a=t?[t]:this.dataListSelections.map(function(t){return t.userId});this.$confirm("确定对[id=".concat(a.join(","),"]进行[").concat(t?"删除":"批量删除","]操作?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$http({url:e.$http.adornUrl("/sys/user/delete"),method:"post",data:e.$http.adornData(a,!1)}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.getDataList()}}):e.$message.error(a.msg)})}).catch(function(){})}}},l=i,n=a("2877"),d=Object(n["a"])(l,r,s,!1,null,null,null);e["default"]=d.exports},b2fc:function(t,e,a){"use strict";a.r(e);var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{title:t.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:t.visible},on:{"update:visible":function(e){t.visible=e}}},[a("el-form",{ref:"dataForm",attrs:{model:t.dataForm,rules:t.dataRule,"label-width":"80px"},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"用户名",prop:"userName"}},[a("el-input",{attrs:{placeholder:"登录帐号"},model:{value:t.dataForm.userName,callback:function(e){t.$set(t.dataForm,"userName",e)},expression:"dataForm.userName"}})],1),a("el-form-item",{class:{"is-required":!t.dataForm.id},attrs:{label:"密码",prop:"password"}},[a("el-input",{attrs:{type:"password",placeholder:"密码"},model:{value:t.dataForm.password,callback:function(e){t.$set(t.dataForm,"password",e)},expression:"dataForm.password"}})],1),a("el-form-item",{class:{"is-required":!t.dataForm.id},attrs:{label:"确认密码",prop:"comfirmPassword"}},[a("el-input",{attrs:{type:"password",placeholder:"确认密码"},model:{value:t.dataForm.comfirmPassword,callback:function(e){t.$set(t.dataForm,"comfirmPassword",e)},expression:"dataForm.comfirmPassword"}})],1),a("el-form-item",{attrs:{label:"邮箱",prop:"email"}},[a("el-input",{attrs:{placeholder:"邮箱"},model:{value:t.dataForm.email,callback:function(e){t.$set(t.dataForm,"email",e)},expression:"dataForm.email"}})],1),a("el-form-item",{attrs:{label:"手机号"}},[a("el-input",{attrs:{placeholder:"手机号"},model:{value:t.dataForm.mobile,callback:function(e){t.$set(t.dataForm,"mobile",e)},expression:"dataForm.mobile"}})],1),a("el-form-item",{attrs:{label:"角色",size:"mini",prop:"roleIdList"}},[a("el-checkbox-group",{model:{value:t.dataForm.roleIdList,callback:function(e){t.$set(t.dataForm,"roleIdList",e)},expression:"dataForm.roleIdList"}},t._l(t.roleList,function(e){return a("el-checkbox",{key:e.roleId,attrs:{label:e.roleId}},[t._v(t._s(e.roleName)+"\n                ")])}),1)],1),a("el-form-item",{attrs:{label:"分组",size:"mini",prop:"groupIdList"}},[a("el-checkbox-group",{model:{value:t.dataForm.groupIdList,callback:function(e){t.$set(t.dataForm,"groupIdList",e)},expression:"dataForm.groupIdList"}},[a("el-checkbox",{key:3,attrs:{label:3}},[t._v("需求收集\n                ")]),a("el-checkbox",{key:4,attrs:{label:4}},[t._v("站址筛查\n                ")]),a("el-checkbox",{key:5,attrs:{label:5}},[t._v("建设需求确认\n                ")]),a("el-checkbox",{key:6,attrs:{label:6}},[t._v("需求变更\n                ")])],1)],1),a("el-form-item",{attrs:{label:"状态",size:"mini",prop:"status"}},[a("el-radio-group",{model:{value:t.dataForm.status,callback:function(e){t.$set(t.dataForm,"status",e)},expression:"dataForm.status"}},[a("el-radio",{attrs:{label:0}},[t._v("禁用")]),a("el-radio",{attrs:{label:1}},[t._v("正常")])],1)],1)],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.visible=!1}}},[t._v("取消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.dataFormSubmit()}}},[t._v("确定")])],1)],1)},s=[],o=a("61f7"),i={data:function(){var t=this,e=function(e,a,r){t.dataForm.id||/\S/.test(a)?r():r(new Error("密码不能为空"))},a=function(e,a,r){t.dataForm.id||/\S/.test(a)?t.dataForm.password!==a?r(new Error("确认密码与密码输入不一致")):r():r(new Error("确认密码不能为空"))},r=function(t,e,a){Object(o["a"])(e)?a():a(new Error("邮箱格式错误"))},s=function(t,e,a){Object(o["d"])(e)?a():a(new Error("手机号格式错误"))};return{visible:!1,roleList:[],dataForm:{id:0,userName:"",password:"",comfirmPassword:"",salt:"",email:"",mobile:"",roleIdList:[],groupIdList:[],status:1},dataRule:{userName:[{required:!0,message:"用户名不能为空",trigger:"blur"}],password:[{validator:e,trigger:"blur"}],comfirmPassword:[{validator:a,trigger:"blur"}],email:[{required:!0,message:"邮箱不能为空",trigger:"blur"},{validator:r,trigger:"blur"}],mobile:[{required:!0,message:"手机号不能为空",trigger:"blur"},{validator:s,trigger:"blur"}]}}},methods:{init:function(t){var e=this;this.dataForm.id=t||0,this.$http({url:this.$http.adornUrl("/sys/role/select"),method:"get",params:this.$http.adornParams()}).then(function(t){var a=t.data;e.roleList=a&&0===a.code?a.list:[]}).then(function(){e.visible=!0,e.$nextTick(function(){e.$refs["dataForm"].resetFields()})}).then(function(){e.dataForm.id&&e.$http({url:e.$http.adornUrl("/sys/user/info/".concat(e.dataForm.id)),method:"get",params:e.$http.adornParams()}).then(function(t){var a=t.data;a&&0===a.code&&(e.dataForm.userName=a.user.username,e.dataForm.salt=a.user.salt,e.dataForm.email=a.user.email,e.dataForm.mobile=a.user.mobile,e.dataForm.roleIdList=a.user.roleIdList,e.dataForm.status=a.user.status,e.dataForm.groupIdList=a.user.groupIdList)})})},dataFormSubmit:function(){var t=this;this.$refs["dataForm"].validate(function(e){e&&t.$http({url:t.$http.adornUrl("/sys/user/".concat(t.dataForm.id?"update":"save")),method:"post",data:t.$http.adornData({userId:t.dataForm.id||void 0,username:t.dataForm.userName,password:t.dataForm.password,salt:t.dataForm.salt,email:t.dataForm.email,mobile:t.dataForm.mobile,status:t.dataForm.status,roleIdList:t.dataForm.roleIdList,groupIdList:t.dataForm.groupIdList})}).then(function(e){var a=e.data;a&&0===a.code?t.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){t.visible=!1,t.$emit("refreshDataList")}}):t.$message.error(a.msg)})})}}},l=i,n=a("2877"),d=Object(n["a"])(l,r,s,!1,null,null,null);e["default"]=d.exports}}]);
//# sourceMappingURL=chunk-6c4614c1.c415929d.js.map