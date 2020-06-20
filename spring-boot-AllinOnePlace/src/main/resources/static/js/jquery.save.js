$(function(){
	/*alert("yo");*/
	
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
	
});

function saveRequestedData(frm, data, type) {
	$.ajax({
		contentType:"application/json; charset=utf-8",
		type:frm.attr("method"),
		url:frm.attr("action"),
		dataType:'json',
		data:JSON.stringify(data),
		success:function(data) {
			alert(data.message);
			toastr.success(data.message, data.title, {
				closeButton:true
			});
			fetchList(type);
		}
	});
}	
