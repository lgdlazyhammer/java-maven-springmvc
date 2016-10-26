$(function () {
	
	//上传对象参数设置
	var uploadOptions={
			uploadURL:sysConfig.apiBasePath+"/upload/tAttachFile/uploadFile",//文件上传路径   [必须]
			fileType:"jpg,png,gif,zip,7z,rar",//文件上传类型  [不必须,(此项为空)默认支持所有文件上传, 类型之间用空格隔开]
			maxFileSize:"500kb",//上传文件最大值 [不必须,可以设置"kb,mb,gb,tb"]
			ImageWidth:100,//图片文件压缩宽度 [不设置此项,默认原始宽度]
			ImageHeight:100,//图片文件压缩高度[不设置此项,默认原始高度]
			ImageCrop:true//是否裁剪图片
	}

    //参数说明:选择文件按钮id,手动上传按钮id(为null表示选择文件后自动上传),上传对象参数配置项,是否显示下载/删除按钮, 下载请求URL,删除请求URL	(如果输入为null,则默认为:自己去(config.js)配置)
	var uploader=$.upload.init("upload-finance-file-select-button",null,uploadOptions,true,null,null);
	$.upload.start(uploader);
	
	/*保存*/
    $("#save").on("click",function () {
    	var applyId = $("#applyId").val();
    	var businessApprovalType = $("#business-approval-type").val();
    	var riskReportName = $("#preploadRead").text().split("：")[1];
        /*保存处理逻辑*/
    	//console.log(uploader.fileId);
    	if($.validate.checkValid(uploader.fileId)){
    		if($.validate.checkValid(applyId)){
    			if($.validate.checkValid(businessApprovalType)){
    				//赋值参数
    				var params = {
    					ajaxData : "",
    					applyId : applyId,
    					riskReportId : uploader.fileId,
    					riskReportName : riskReportName,
    					businessApprovalType : businessApprovalType
    				};
    				$.ajax({
    		            type: 'POST',
    		            url: sysConfig.apiBasePath+"/financingapply/tFinancingApply/riskManagementApproval",
    		            dataType: 'json',
    		            data: params,
    		            success: function (data) {
    		            	if(data&&data.success){
    		            	$().save($.trim($("#saveRedirect").attr("tt")));
    		            	}else{
    		            		alertMsg(data.message,"错误提示");
    		            	}
    		            },
    		            error: function () {
    		                alertMsg("出错了，请联系管理员！","温馨提示！");
    		            }
    				});
    			}else{
    				alertMsg("未审批。","温馨提示！");
    			}
    		}else{
    			alertMsg("订单号未设置正确。","温馨提示！");
    		}
    	}else{
    		alertMsg("文件未上传！","温馨提示！");
    	}
        /*保存完以后调用函数*/
        //$("#saveRedirect").save($.trim($(this).attr("tt")));
    });
});