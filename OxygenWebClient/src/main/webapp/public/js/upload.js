/**
 * 对jQuery扩展出upload插件
 */
(function($) {
	var headers={};
	var settings={};
//	headers.Authorization=
	$.upload = {//上传插件默认参数
		showType:0,//上传后展示类型：0，无 1，图片 2，列表
		uploadURL:null,
		selectButton:null,
		showDown:false,//上传后，是否需要下载
		showDele:false,//上传后，是否需要删除
		downloadURL:sysConfig.apiBasePath+"/upload/tAttachFile/download",//下载默认路径
		deleteURL:sysConfig.apiBasePath+"/upload/tAttachFile/delete",//删除默认路径	
		uploadButton:null,//上传按钮id:null 时自动上传，存在id时，点击对应按钮上传文件。
		defualtOptions : {
			url : sysConfig.apiBasePath+"/upload/tAttachFile/uploadFile", //上传地址
	        multipart:true,
	        filters: {//默认运行上传所有类型的文件
	        	  mime_types : [ //只允许上传图片和zip文件
//	        	    { title : "Image files", extensions : "jpg,gif,png" },
//	        	    { title : "Zip files", extensions : "zip" }
	        	  ],
//	        	  max_file_size : 0, //最大只能上传400kb的文件
	        	  prevent_duplicates : true //不允许选取重复文件
	        	},
				//可以使用该参数对将要上传的图片进行压缩，该参数是一个对象，里面包括5个属性：
				resize: {
//				  width: 100,
//				  height: 100,
//				  crop: true, //是否裁剪图片
//				  quality: 60, //压缩后图片的质量，只对jpg格式的图片有效，默认为90。
//				  preserve_headers: false
				},
//				headers:headers,
	        flash_swf_url : 'upload/Moxie.swf', 
	        silverlight_xap_url : 'upload/Moxie.xap'
		},
		/**
		 * 初始化
		 * @param selectButtonId	选择文件按钮id
		 * @param uploadButtonId	上传文件按钮id
		 * @param uploadOptions		配置参数
		 * @param showDown			是否显示下载按钮 true 需要下载按钮, false 不需要下载按钮
		 * @param showDele			是否显示删除按钮 true 需要删除按钮, false 不需要删除按钮
		 * @param downloadURL		下载url 为null时，下载地址为默认值
		 * @param deleteURL			删除url 为null时，删除地址为默认值
		 * @returns
		 */
		init : function(selectButtonId,uploadButtonId,uploadOptions,showDown,showDele,deleteURL,downloadURL) {
			if(isUndefind(uploadButtonId)){
				this.uploadButton=uploadButtonId;
			};
			if(uploadOptions!=null){
				var temp={};
				if(isUndefind(uploadOptions.fileType)){
					this.defualtOptions.filters.mime_types=[{title:"文件类型",extensions:uploadOptions.fileType}];
				};
				if(isUndefind(uploadOptions.maxFileSize)){//上传文件大小限制
					this.defualtOptions.filters.max_file_size=uploadOptions.maxFileSize;
				};
				if(isUndefind(uploadOptions.ImageWidth)){//图片宽度
					this.defualtOptions.resize.width=uploadOptions.ImageWidth;
				};
				if(isUndefind(uploadOptions.ImageHeight)){//高度
					this.defualtOptions.resize.height=uploadOptions.ImageHeight;
				};
				if(isUndefind(uploadOptions.ImageCrop)){//是否裁剪
					this.defualtOptions.resize.crop=uploadOptions.ImageCrop;
				};
				if(isUndefind(uploadOptions.uploadURL)){//上传路径
					this.defualtOptions.url=uploadOptions.uploadURL;
					this.uploadURL=uploadOptions.uploadURL;
				};
//				settings=$.extend(defualtOptions,uploadOptions);
			};
			if(isUndefind(selectButtonId)){
				if($("#"+selectButtonId)){
					this.defualtOptions.browse_button=selectButtonId;
					this.selectButton=selectButtonId;
				}else{
					alert("找不到此id对应的选择文件按钮!");
					return false;
				};
			};
			if(showDown){
				this.showDown=showDown;
			};
			if(showDele){
				this.showDele=showDele;
			};
			if(downloadURL){
				this.downloadURL=downloadURL;
			};
			if(deleteURL){
				this.deleteURL=deleteURL;
			};
				settings=this.defualtOptions;
				var uploader=new plupload.Uploader(settings);
				//在实例对象上调用init()方法进行初始化
				uploader.selectButton=this.selectButton;
				uploader.uploadButton=this.uploadButton;
				uploader.showDown=this.showDown;
				uploader.showDele=this.showDele;
				uploader.downloadURL=this.downloadURL;
				uploader.deleteURL=this.deleteURL;
				return uploader;
	        },
	        start:function(uploader){
	    		 //绑定各种事件，并在事件监听函数中做你想做的事
	        	uploader.init();
	        	/**
	        	 * 选择文件后的事件
	        	 */
	    		uploader.bind('FilesAdded',function(uploader,files){
	    			
	    			$.each(uploader.files, function (i, file) {
	    		        if (uploader.files.length > 1) {
	    		        	uploader.removeFile(file);
	    		        };
	    		    });
	    			
	    			if($("#"+uploader.selectButton).parent().find("#preploadRead")
	    					&&$("#"+uploader.selectButton).parent().find("#preploadRead").length>0){
	    				$("#"+uploader.selectButton).parent().find("#preploadRead").remove();
	    			};
					if($("#"+uploader.selectButton).parent().find("#delete")
							&&$("#"+uploader.selectButton).parent().find("#delete").length>0){
						$("#"+uploader.selectButton).parent().find("#delete").remove();
					};
	    			if($("#"+uploader.selectButton).parent().find("#download")
	    					&&$("#"+uploader.selectButton).parent().find("#download").length>0){
	    				$("#"+uploader.selectButton).parent().find("#download").remove();
	    			};
	    			//上传提示隐藏
    				$("#"+uploader.selectButton).parent().find("strong[name='uploadSuccessStrong']").hide();
    				$("#"+uploader.selectButton).parent().find("strong[name='uploadErrorStrong']").hide();
	    			if($("#"+uploader.selectButton)){
	    				var fileInfo="<p id='preploadRead'>文件名："+files[0].name+"</p>";
	    				//判断文件是否为图片:是则展示出来
//	    				var image=null;
//	    				if(files[0].type.indexOf("image")!=-1){
//	    					image='<p><img src="'+files[0].+'"></img><p>';
//	    				}
	    				$("#"+uploader.selectButton).parent().append(fileInfo);
	    			};
	    			if(uploader.uploadButton){
						$("#"+uploader.uploadButton).on("click",function(){
							uploader.start();//手动提交上传
						});
					}else{
						uploader.start();//添加文件后,自动上传
					};
	    					
	    		});
	    		/**
	    		 * 上传完成后的事件
	    		 */
	    		uploader.bind('FileUploaded',function(uploader,file,object){
	    		    //每个事件监听函数都会传入一些很有用的参数，
	    		    //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
	    				file.destroy(); 
	    				var response=JSON.parse(object.response);
	    				if(response!=null){
	    						if(response.success){
	    								if(response.result&&response.result.length>0){
//	    									if(!downloadURL){
//	    										downloadURL="http://localhost:8080/financial/msok/upload/tAttachFile/download";
//	    									}
//	    									if(!deleteURL){
//	    										deleteURL="http://localhost:8080/financial/msok/upload/tAttachFile/delete";
//	    									}
	    									//显示上传成功
	    									$("#"+uploader.selectButton).parent().find("strong[name='uploadSuccessStrong']").show();
	    				    				$("#"+uploader.selectButton).parent().find("strong[name='uploadErrorStrong']").hide();
	    				    				//保存文件id
	    				    				uploader.fileId = response.result[0].id;
	    		    						//显示下载按钮
	    		    						if(uploader.showDown){
	    		    							var downloadEle='<a id="download" href="'+uploader.downloadURL+'?id='+response.result[0].id+'" style="text-decoration:none;display:none;" >下载文件</a>';
	    		    							$("#"+uploader.selectButton).parent().append(downloadEle);
    		    								$("#"+uploader.selectButton).parent().find("#download").show();
	    		    						};
	    		    						//显示删除按钮
	    		    						if(uploader.showDele){//是否影藏下载和删除 按钮
	    		    							var deleteEle='<a id="delete" href="javascript:void(0);" style="color:red;text-decoration: none;display:none;" >删除文件</a>';
	    		    							var deleURL=uploader.deleteURL+'/'+response.result[0].id;
	    		    							$("#"+uploader.selectButton).parent().append(deleteEle);
    		    								$("#"+uploader.selectButton).parent().find("#delete").show();
    		    								$("#"+uploader.selectButton).parent().on("click","#delete",function(){
	    		    								deleteFile(uploader.selectButton,deleURL);
	    		    							});
	    		    						};
	    								};
	    						}else{
	    							//显示上传失败
	    							$("#"+uploader.selectButton).parent().find("strong[name='uploadSuccessStrong']").hide();
				    				$("#"+uploader.selectButton).parent().find("strong[name='uploadErrorStrong']").show();
	    						};
	    				};
	    		});
	    		/**
	    		 * 上传进度
	    		 */
	    		 uploader.bind('UploadProgress',function(uploader,file){
	    		        //每个事件监听函数都会传入一些很有用的参数，
	    		        //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
//	    		     	root.find("#"+file.id+"_progress").html(file.percent)
	    		    });
	    		 uploader.bind('UploadComplete',function(uploader,file){
	    		        //每个事件监听函数都会传入一些很有用的参数，
	    		        //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
	    		    	for(var i in file){
	    		    		if(file[i].percent == 100){
	    		    			file[i].destroy();
	    		    		};
	    		    	};
	    		    });
	    		uploader.bind('Error',function(uploader,obj){
	    		    //每个事件监听函数都会传入一些很有用的参数，
	    		    //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
	    		    if(obj.code==-601){
	    		    	var fileTypes=uploader.settings.filters.mime_types;
	    		    	var allTypes="";
	    		    	for(i in fileTypes){
	    		    		if(i==fileTypes.length-1){
	    		    			allTypes+=fileTypes[i].extensions;
	    		    		}else{
	    		    			allTypes+=fileTypes[i].extensions+",";
	    		    		};
	    		    	};
	    		    	alert("不支持此类型的文件上传! 只支持:"+allTypes);
	    		    }else if(obj.code==-500){
	    		    	alert("上传组件初始化失败!");
	    		    }else if(obj.code==-600){
	    		    	var fileSize=uploader.settings.filters.max_file_size;//KB
	    		    	alert("上传的文件大小不能超过: "+fileSize);
	    		    }else if(obj.code==-602){
	    		    	//选择相同文件不报错。
	    		    }else{
	    		    	alert("上传失败!");
	    		    };
	    		});
	        }
		   
		};
	function isUndefind(data){
		if (typeof (data) == "undefined") {
			return false;
		}else if(data==null){
			return false;
		}else if(data=="undefind"){
		    return false;
		}else if(data==""){
			return false;
		}else{
			return data;
		};
		
	};
	function deleteFile(selectButton,deleteURL){
		$.ajax({
			url:deleteURL,
			type:"DELETE",
			success:function(data){
				if(data.success){
					//删除成功后
					$("#"+selectButton).parent().find("strong[name='uploadSuccessStrong']").hide();
    				$("#"+selectButton).parent().find("strong[name='uploadErrorStrong']").hide();
					//删除成功后,移除下载和删除按钮
					$("#"+selectButton).parent().find("#delete").remove();
					$("#"+selectButton).parent().find("#download").remove();
					$("#"+selectButton).parent().find("#preploadRead").remove();
				};
			},
			error:function(data){
				alert(data.message);
			}
		});
	}
})(jQuery);