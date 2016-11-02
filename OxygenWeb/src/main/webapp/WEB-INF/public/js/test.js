$(function(){

	$("#prepareDownloadFile").click(function(){
		$("#downloadFile").attr("href","/OxygenWeb/commonService/fileDownloadSingle?id="+$("#downloadFileId").val());
		$("#downloadFile").text("ready to be downloaded");
	});
	
	$("#displayPicture").click(function(){
		var getResourceOwnerAllOption = {
			type: 'POST',
			url: '/OxygenWeb/commonService/fileDownloadSingle',
			data: { id: $("#displayPictureId").val() },
			success: function(data){
				console.log(data);
				if(data){
					$("#pictureToShow").attr("src","/OxygenWeb/"+data.result.refUrl);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
			},
			timeout: function(data){
			}
		};
		//initialize the page
		$.ajax(getResourceOwnerAllOption);
	});
	
});