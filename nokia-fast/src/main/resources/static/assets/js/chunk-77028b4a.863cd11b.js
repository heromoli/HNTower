(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-77028b4a","chunk-2d0dd013"],{"7b01":function(e,t,a){},8016:function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{title:"上传CRM文件","close-on-click-modal":!1,visible:e.visible},on:{close:e.closeHandle,"update:visible":function(t){e.visible=t}}},[a("el-upload",{staticStyle:{"text-align":"center"},attrs:{drag:"",action:e.url,"before-upload":e.beforeUploadHandle,"on-success":e.successHandle,data:{groupId:e.groupId},"file-list":e.fileList}},[a("i",{staticClass:"el-icon-upload"}),a("div",{staticClass:"el-upload__text"},[e._v("将文件拖到此处，或"),a("em",[e._v("点击上传")])]),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只支持xls、xlsx 格式的文件！")])])],1)},n=[],l={data:function(){return{visible:!1,url:"",num:0,successNum:0,groupId:"",fileList:[]}},methods:{init:function(e){this.url=this.$http.adornUrl("/api/wf/crmUpload?token=".concat(this.$cookie.get("token"))),this.visible=!0,this.groupId=e},beforeUploadHandle:function(e){if("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"!==e.type&&"application/vnd.ms-excel"!==e.type)return this.$message.error("只支持xls、xlsx 格式的文件！"),!1;this.num++},successHandle:function(e,t,a){var i=this;this.fileList=a,this.successNum++,e&&0===e.code?this.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){i.visible=!1}}):this.$message.error(e.msg)},closeHandle:function(){this.fileList=[],this.$emit("refreshDataList")}}},s=l,r=a("2877"),o=Object(r["a"])(s,i,n,!1,null,null,null);t["default"]=o.exports},a9e7:function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"mod-log"},[a("el-form",{attrs:{inline:!0,model:e.dataForm},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.getDataList()}}},[a("el-form-item",[a("el-input",{attrs:{size:"mini",placeholder:"站点名称",clearable:""},model:{value:e.dataForm.param,callback:function(t){e.$set(e.dataForm,"param",t)},expression:"dataForm.param"}})],1),a("el-form-item",[a("el-button",{attrs:{size:"mini"},on:{click:function(t){return e.getDataList()}}},[e._v("查询")]),a("el-button",{attrs:{type:"success",size:"mini"},on:{click:function(t){return e.uploadHandle()}}},[e._v("导入")])],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.dataListLoading,expression:"dataListLoading"}],staticStyle:{height:"auto",width:"auto"},attrs:{data:e.dataList,border:"","row-class-name":e.tableRowClassName,size:"mini"}},[a("el-table-column",{attrs:{fixed:"",prop:"id","header-align":"center",align:"center",width:"50",label:"ID"}}),a("el-table-column",{attrs:{fixed:"",prop:"orderNum","header-align":"center",align:"center",width:"140",label:"订单号"}}),a("el-table-column",{attrs:{fixed:"",prop:"operatorName","header-align":"center",align:"center",width:"80",label:"运营商"}}),a("el-table-column",{attrs:{fixed:"",prop:"region","header-align":"center",align:"center",width:"80",label:"区县"}}),a("el-table-column",{attrs:{fixed:"",prop:"towerStationName","header-align":"center",align:"center",width:"150",label:"站点名称"}}),a("el-table-column",{attrs:{fixed:"",prop:"scene","header-align":"center",align:"center",width:"100",label:"场景划分"}}),a("el-table-column",{attrs:{"header-align":"center",align:"center",label:"站点位置及要求"}},[a("el-table-column",{attrs:{fixed:"",prop:"longitude","header-align":"center",align:"center",width:"140",label:"经度"}}),a("el-table-column",{attrs:{fixed:"",prop:"latitude","header-align":"center",align:"center",width:"140",label:"纬度"}}),a("el-table-column",{attrs:{fixed:"right",prop:"address","header-align":"center",align:"center","show-overflow-tooltip":!0,label:"详细地址"}})],1),a("el-table-column",{attrs:{fixed:"right",prop:"towerType","header-align":"center",align:"center",width:"80",label:"铁塔种类"}}),a("el-table-column",{attrs:{fixed:"right","header-align":"center",label:"操作",align:"center",width:"230"},scopedSlots:e._u([{key:"default",fn:function(t){var i=t.row;t.$index;return[a("el-button",{attrs:{type:"primary",size:"small"},nativeOn:{click:function(t){return e.showProcessesHiList(i)}}},[e._v("查看")])]}}])})],1),a("el-pagination",{attrs:{"current-page":e.pageIndex,"page-sizes":[10,20,50,100],"page-size":e.pageSize,total:e.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}}),e.showProcessesVisible?a("show-processes",{ref:"showProcesses"}):e._e(),e.uploadVisible?a("upload",{ref:"upload",on:{refreshDataList:e.getDataList}}):e._e()],1)},n=[],l=a("1915"),s=a("8016"),r={data:function(){return{dataForm:{param:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,showProcessesVisible:!1,uploadVisible:!1,selectionDataList:[]}},created:function(){this.getDataList()},components:{ShowProcesses:l["default"],Upload:s["default"]},methods:{getDataList:function(){var e=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/api/wf/queryCRMData"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,queryParam:this.dataForm.param})}).then(function(t){var a=t.data;null!=a.page&&0===a.code?(e.dataList=a.page.list,e.totalPage=a.page.totalCount):(e.dataList=[],e.totalPage=0),e.dataListLoading=!1})},sizeChangeHandle:function(e){this.pageSize=e,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(e){this.pageIndex=e,this.getDataList()},tableRowClassName:function(e){e.row;var t=e.rowIndex;return t%2===1?"success-row":""},uploadHandle:function(){var e=this;this.uploadVisible=!0,this.$nextTick(function(){e.$refs.upload.init(e.dataForm.groupId)})},exportHandle:function(){window.location.href=this.$http.adornUrl("/api/wf/export?groupId=".concat(this.dataForm.groupId,"&token=").concat(this.$cookie.get("token")))}}},o=r,c=(a("efa4"),a("2877")),d=Object(c["a"])(o,i,n,!1,null,null,null);t["default"]=d.exports},efa4:function(e,t,a){"use strict";var i=a("7b01"),n=a.n(i);n.a}}]);
//# sourceMappingURL=chunk-77028b4a.863cd11b.js.map