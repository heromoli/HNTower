(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4d7f164c","chunk-2d0bfe95"],{4014:function(a,t,e){"use strict";e.r(t);var r=function(){var a=this,t=a.$createElement,e=a._self._c||t;return e("el-dialog",{attrs:{title:a.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:a.visible},on:{"update:visible":function(t){a.visible=t}}},[e("el-form",{ref:"dataForm",attrs:{model:a.dataForm,rules:a.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&a._k(t.keyCode,"enter",13,t.key,"Enter")?null:a.dataFormSubmit()}}},[e("el-form-item",{attrs:{label:"参数名",prop:"paramKey"}},[e("el-input",{attrs:{placeholder:"参数名"},model:{value:a.dataForm.paramKey,callback:function(t){a.$set(a.dataForm,"paramKey",t)},expression:"dataForm.paramKey"}})],1),e("el-form-item",{attrs:{label:"参数值",prop:"paramValue"}},[e("el-input",{attrs:{placeholder:"参数值"},model:{value:a.dataForm.paramValue,callback:function(t){a.$set(a.dataForm,"paramValue",t)},expression:"dataForm.paramValue"}})],1),e("el-form-item",{attrs:{label:"备注",prop:"remark"}},[e("el-input",{attrs:{placeholder:"备注"},model:{value:a.dataForm.remark,callback:function(t){a.$set(a.dataForm,"remark",t)},expression:"dataForm.remark"}})],1)],1),e("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{on:{click:function(t){a.visible=!1}}},[a._v("取消")]),e("el-button",{attrs:{type:"primary"},on:{click:function(t){return a.dataFormSubmit()}}},[a._v("确定")])],1)],1)},n=[],i={data:function(){return{visible:!1,dataForm:{id:0,paramKey:"",paramValue:"",remark:""},dataRule:{paramKey:[{required:!0,message:"参数名不能为空",trigger:"blur"}],paramValue:[{required:!0,message:"参数值不能为空",trigger:"blur"}]}}},methods:{init:function(a){var t=this;this.dataForm.id=a||0,this.visible=!0,this.$nextTick(function(){t.$refs["dataForm"].resetFields(),t.dataForm.id&&t.$http({url:t.$http.adornUrl("/sys/config/info/".concat(t.dataForm.id)),method:"get",params:t.$http.adornParams()}).then(function(a){var e=a.data;e&&0===e.code&&(t.dataForm.paramKey=e.config.paramKey,t.dataForm.paramValue=e.config.paramValue,t.dataForm.remark=e.config.remark)})})},dataFormSubmit:function(){var a=this;this.$refs["dataForm"].validate(function(t){t&&a.$http({url:a.$http.adornUrl("/sys/config/".concat(a.dataForm.id?"update":"save")),method:"post",data:a.$http.adornData({id:a.dataForm.id||void 0,paramKey:a.dataForm.paramKey,paramValue:a.dataForm.paramValue,remark:a.dataForm.remark})}).then(function(t){var e=t.data;e&&0===e.code?a.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){a.visible=!1,a.$emit("refreshDataList")}}):a.$message.error(e.msg)})})}}},o=i,l=e("2877"),d=Object(l["a"])(o,r,n,!1,null,null,null);t["default"]=d.exports},c8fe8:function(a,t,e){"use strict";e.r(t);var r=function(){var a=this,t=a.$createElement,e=a._self._c||t;return e("div",{staticClass:"mod-config"},[e("el-form",{attrs:{inline:!0,model:a.dataForm},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&a._k(t.keyCode,"enter",13,t.key,"Enter")?null:a.getDataList()}}},[e("el-form-item",[e("el-input",{attrs:{placeholder:"参数名",clearable:""},model:{value:a.dataForm.paramKey,callback:function(t){a.$set(a.dataForm,"paramKey",t)},expression:"dataForm.paramKey"}})],1),e("el-form-item",[e("el-button",{on:{click:function(t){return a.getDataList()}}},[a._v("查询")]),e("el-button",{attrs:{type:"primary"},on:{click:function(t){return a.addOrUpdateHandle()}}},[a._v("新增")]),e("el-button",{attrs:{type:"danger",disabled:a.dataListSelections.length<=0},on:{click:function(t){return a.deleteHandle()}}},[a._v("批量删除")])],1)],1),e("el-table",{directives:[{name:"loading",rawName:"v-loading",value:a.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:a.dataList,border:""},on:{"selection-change":a.selectionChangeHandle}},[e("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),e("el-table-column",{attrs:{prop:"id","header-align":"center",align:"center",width:"80",label:"ID"}}),e("el-table-column",{attrs:{prop:"paramKey","header-align":"center",align:"center",label:"参数名"}}),e("el-table-column",{attrs:{prop:"paramValue","header-align":"center",align:"center",label:"参数值"}}),e("el-table-column",{attrs:{prop:"remark","header-align":"center",align:"center",label:"备注"}}),e("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"150",label:"操作"},scopedSlots:a._u([{key:"default",fn:function(t){return[e("el-button",{attrs:{type:"text",size:"small"},on:{click:function(e){return a.addOrUpdateHandle(t.row.id)}}},[a._v("修改")]),e("el-button",{attrs:{type:"text",size:"small"},on:{click:function(e){return a.deleteHandle(t.row.id)}}},[a._v("删除")])]}}])})],1),e("el-pagination",{attrs:{"current-page":a.pageIndex,"page-sizes":[10,20,50,100],"page-size":a.pageSize,total:a.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":a.sizeChangeHandle,"current-change":a.currentChangeHandle}}),a.addOrUpdateVisible?e("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:a.getDataList}}):a._e()],1)},n=[],i=e("4014"),o={data:function(){return{dataForm:{paramKey:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,dataListSelections:[],addOrUpdateVisible:!1}},components:{AddOrUpdate:i["default"]},activated:function(){this.getDataList()},methods:{getDataList:function(){var a=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/sys/config/list"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,paramKey:this.dataForm.paramKey})}).then(function(t){var e=t.data;e&&0===e.code?(a.dataList=e.page.list,a.totalPage=e.page.totalCount):(a.dataList=[],a.totalPage=0),a.dataListLoading=!1})},sizeChangeHandle:function(a){this.pageSize=a,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(a){this.pageIndex=a,this.getDataList()},selectionChangeHandle:function(a){this.dataListSelections=a},addOrUpdateHandle:function(a){var t=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){t.$refs.addOrUpdate.init(a)})},deleteHandle:function(a){var t=this,e=a?[a]:this.dataListSelections.map(function(a){return a.id});this.$confirm("确定对[id=".concat(e.join(","),"]进行[").concat(a?"删除":"批量删除","]操作?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.$http({url:t.$http.adornUrl("/sys/config/delete"),method:"post",data:t.$http.adornData(e,!1)}).then(function(a){var e=a.data;e&&0===e.code?t.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){t.getDataList()}}):t.$message.error(e.msg)})}).catch(function(){})}}},l=o,d=e("2877"),s=Object(d["a"])(l,r,n,!1,null,null,null);t["default"]=s.exports}}]);
//# sourceMappingURL=chunk-4d7f164c.de8fe356.js.map