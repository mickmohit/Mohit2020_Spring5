$(function(){

	$("#userList").click(function(){
		fetchList("user");	
	});
	
	$("#addressList").click(function(){
		fetchList("address");
	});
	
});


function fetchList(type)
{
 $.ajax({
	 type:"GET",
	 url:"/mohitproject/"+type+"/list",
	 success: function(data){
		 $(".inner-jsp").html(data);
	 }
 });
}


function addForm(type){
	$.ajax({
		type:"GET",
		 url:"/mohitproject/"+type+"/form",
		 success: function(data){
			 $(".inner-jsp").html(data);
		 }
	});
}


function editForm(type, id){
	$.ajax({
		type:"GET",
		 url:"/mohitproject/"+type+"/edit/"+id,
		 success: function(data){
			 $(".inner-jsp").html(data);
		 }
	});
}


function deleteData(type, id){
	
	toastr.warning("<div>Are you sure you want to delete this?</div>" +
			"<div class='btn-group pull-right'>" +
			"<button type='button' id='confirmationYes' class='btn btn-xs btn-default'><i class='glyphicon glyphicon-ok'></i> Yes</button>" +
			"<button type='button' class='btn btn-xs btn-default clear'><i class='glyphicon glyphicon-remove'></i> No</button>" +
			"</div>", "Delete Confirmation", {
		allowHtml:true,
		closeButton:true,
		onShown: function() {
			$("#confirmationYes").click(function() {
				if(confirm("You want to delete it, Sure?"))
				{
				$.ajax({
					type:"GET",
					 url:"/mohitproject/"+type+"/delete/"+id,
					 success: function(data){
						 fetchList(type);
						 alert(data.message);
					 }
				});
				}
			else return false;
			});
		}
	});
	
	
}