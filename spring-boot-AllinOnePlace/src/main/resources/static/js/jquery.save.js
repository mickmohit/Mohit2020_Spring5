$(function(){
	/*alert("yo");*/
	
	$("#moveLeftBtnId").click(function() {
		$.map($('#leftSelectionId option:selected') ,function(option) {
			$("#rightSelectionId").append('<option value="'+option.value+'" selected>'+option.text+'</option>');
		});  
		$("#leftSelectionId option:selected").remove();
	});
	
	$("#moveRightBtnId").click(function() {
		$.map($('#rightSelectionId option:selected') ,function(option) {
			$("#leftSelectionId").append('<option id="'+option.value+'">'+option.text+'</option>');
		});  
		$("#rightSelectionId option:selected").remove();
	});
	
	
	
	
	
	$("#submitUserForm").submit(function(e){
		e.preventDefault();
		var frm =$("#submitUserForm");
		var data={};
		$.each(this, function(key,value){
			var input=$(value); //jquery obj
			data[input.attr("name")]=input.val();
			delete data[undefined];
		});
		saveRequestedData(frm, data, "user");
		
		/*$("#uploadImage").on("submit",function(e){
			e.preventDefault();
			var formData = new FormData(this);
			$.ajax({
				type:"POST",
				url:"/mohitproject/user/upload",
				data=formdata,
				cache:false,
				dataType:"json",
				contentType:false,
				processData:false,
				success:function(data) {
					var title="Upload Confirmation";
					if(data.status == "success") {
					toastr.success(data.message, title, {
					closeButton:true
					});
					fetchList("user");
					$("#uploadModal").modal("hide");
					$("body").removeClass("modal-open");
					$(".modal-backdrop").remove();
					}
					else {
						toastr.error(data.message, title, {
							allowHtml:true,
							closeButton:true
						});
					}
				}
			}
			)
		});*/
		
	});
	
	$("#submitAddressForm").submit(function(e){
		e.preventDefault();
		var frm =$("#submitAddressForm");
		var data={};
		$.each(this, function(key,value){
			var input=$(value); //jquery obj
			data[input.attr("name")]=input.val();
			delete data[undefined];
		});
		saveRequestedData(frm, data, "address");
	});
	
	$("#submitBookForm").submit(function(e){
		e.preventDefault();
		var frm =$("#submitBookForm");
		var data={};
		$.each(this, function(key,value){
			var input=$(value); //jquery obj
			data[input.attr("name")]=input.val();
			delete data[undefined];
		});
		alert(JSON.stringify(data));
		saveRequestedData(frm, data, "book");
	});
	
	$("#submituserPasswordForm").submit(function(e){
		e.preventDefault();
		var frm =$("#submituserPasswordForm");
		var data={};
		$.each(this, function(key,value){
			var input=$(value); //jquery obj
			data[input.attr("name")]=input.val();
			delete data[undefined];
		});
		alert(JSON.stringify(data));
		saveRequestedData(frm, data, null);
	});
	
	$("#submitCategoryForm").submit(function(e) {
		e.preventDefault();
		var frm = $("#submitCategoryForm");
		var data = {};
		$.each(this, function(i, v){
			var input = $(v);
			data[input.attr("name")] = input.val();
			delete data["undefined"];
		});
		saveRequestedData(frm, data, "category");
	});
	
	$("#submitVideoForm").submit(function(e) {
		e.preventDefault();
		var frm = $("#submitVideoForm");
		var data = {};
		$.each(this, function(i, v){
			var input = $(v);
			data[input.attr("name")] = input.val();
			delete data["undefined"];
		});
		saveRequestedData(frm, data, "video");
	});
	
});

function saveRequestedData(frm, data, type) {
	alert("calling");
	$.ajax({
		contentType:"application/json; charset=utf-8",
		type:frm.attr("method"),
		url:frm.attr("action"),
		dataType:'json',
		data:JSON.stringify(data),
		success:function(data) {
			if(data.status == "success") {
			alert(data.message);
			alert(data.source);
			toastr.success(data.message, data.title, {
				closeButton:true
			});
			if(data.source == "register") {
				alert(data.source);
				setTimeout(function () {
					window.location.href = "/mohitproject";
				}, 2000);
			} else {
				
				if(type==null){}
				else{
			fetchList(type);}
				   }
			}
			else {
				toastr.error(data.message, data.title, {
					allowHtml:true,
					closeButton:true
				});
			}
		}
	});
}	
