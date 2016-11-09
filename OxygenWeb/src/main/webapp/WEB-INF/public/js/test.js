$(function() {

	$("#prepareDownloadFile").click(
			function() {
				$("#downloadFile").attr(
						"href",
						"/OxygenWeb/commonService/fileDownloadSingle?id="
								+ $("#downloadFileId").val());
				$("#downloadFile").text("ready to be downloaded");
			});

	$("#displayPicture").click(
			function() {
				var getResourceOwnerAllOption = {
					type : 'POST',
					url : '/OxygenWeb/commonService/fileDownloadSingle',
					data : {
						id : $("#displayPictureId").val()
					},
					success : function(data) {
						console.log(data);
						if (data) {
							$("#pictureToShow").attr("src",
									"/OxygenWeb/" + data.result.refUrl);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
					},
					timeout : function(data) {
					}
				};
				// initialize the page
				$.ajax(getResourceOwnerAllOption);
			});

	$("#submitAjaxFile").click(function() {
		
		var secoreLevelVal = parseInt($("#secureAjaxLevel").val());
		$.ajaxFileUpload({
			url : '/OxygenWeb/commonService/fileUploadSingle', // 需要链接到服务器地址
			secureuri : false,
			fileElementId : 'fileAjaxUpload', // 文件选择框的id属性
			dataType : 'json', // 服务器返回的格式，可以是json
			type: 'post',
			data : {
				secureLevel : secoreLevelVal
			},
			success : function(data, status) // 相当于java中try语句块的用法
			{
				if(data.result){
					if(secoreLevelVal==0 || secoreLevelVal==1){
						$("#downloadFileId").val(data.result);
						$("#prepareDownloadFile").click();
					}else if(secoreLevelVal==2){
						$("#displayPictureId").val(data.result);
						$("#displayPicture").click();
					}
				};
			},
			error : function(data, status, e) // 相当于java中catch语句块的用法
			{
			}
		});
	});

});