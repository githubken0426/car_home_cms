/**
 * 选择图片并预览
 * @param obj file
 * @param viewId <img/>标签，图片显示
 * @return
 */
var isViewOK=true;
function viewUploadImg(obj,viewId) {
	var file = obj.files[0];
	var aa=obj.value.toLowerCase().split('.');//以“.”分隔上传文件字符串
	if (aa != "") {
		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test("." + aa[aa.length-1])) {
			alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
			obj.value = "";
			var img = document.getElementById(viewId);
			img.src = "";
			img.style.display = 'none';
			isViewOK=false;
			return isViewOK;
		}
	}
	if(file != undefined  && file.size>1024*1024){
		alert("图片不能大于1M");
		obj.value = "";
		var img = document.getElementById(viewId);
		img.src = "";
		img.style.display = 'none';
		isViewOK=false;
		return isViewOK;
	}
	
	var reader = new FileReader();
	//读取文件过程方法
	reader.onload = function(e) {
		var img = document.getElementById(viewId);
		img.src = e.target.result;
		img.style.display='';
		//或者 img.src = this.result;  
		//e.target == this
	}
	
	if (file) {
		reader.readAsDataURL(file);
	} else {
		var img = document.getElementById(viewId);
		img.src = "";
		img.style.display = 'none';
	}

}
/**
 * 修改页面预览图片
 * @param obj
 * @param viewId
 * @return
 */
 var isViewUpdateOK=true;
function updatePageViewImg(obj,viewId,flag) {
	var flag=document.getElementById(flag);
	flag.value="";
	var file = obj.files[0];
	var aa=obj.value.toLowerCase().split('.');//以“.”分隔上传文件字符串
	if (aa != "") {
		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test("." + aa[aa.length-1])) {
			alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
			obj.value = "";
			var img = document.getElementById(viewId);
			img.src = "";
			img.style.display = 'none';
			isViewUpdateOK=false;
			return isViewUpdateOK;
		}
	}
	if(file && file.size>1024*1024){
		alert("图片不能大于1M");
		isViewUpdateOK=false;
		return isViewUpdateOK;
	}
	var reader = new FileReader();
	reader.onload = function(e) {
		var img = document.getElementById(viewId);
		img.src = e.target.result;
		img.style.display='';
	}
	if (file) {
		reader.readAsDataURL(file);
	} else {
		var img = document.getElementById(viewId);
		img.src = "";
		img.style.display = 'none';
	}
}

/**
 * 删除图片按钮
 * @param element
 * @param flag
 * @return
 */
function deletePicture(element,flag){
	var flag=document.getElementById(flag);
	flag.value="";
	var img = document.getElementById(element);
	img.src = "";
	img.style.display = 'none';
}