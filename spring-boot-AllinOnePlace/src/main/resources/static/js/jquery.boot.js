$(function(){

	$("#userList").click(function(){
		fetchList("user");	
	});
	
	$("#addressList").click(function(){
		fetchList("address");
	});
	
	$("#bookList").click(function(){
		fetchList("book");
	});
	
	$("#changePassword").click(function(){
		modifyPassword("user/changePassword/"+$("#loginUserId").val());
	});
	
});

//var pageConstant= "?page=0&size=5"; //older way of passing pagination params
var pageConstant= "?page=1";

function fetchList(type)
{
 $.ajax({
	 type:"GET",
	 url:"/mohitproject/"+type+"/list"+pageConstant,
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
			 alert(data);
			 $(".inner-jsp").html(data);
		 }
	});
}

function modifyPassword(suffix) {
	$.ajax({
		type : "GET",
		url : "/mohitproject/"+suffix,
		success : function(data) {
			$(".inner-jsp").html(data);
		}
	});
}


function refresh(type){
	$.ajax({
		type:"GET",
		 url:"/mohitproject/"+type+"/refresh"+pageConstant,
		 success: function(data){
			 $(".inner-jsp").html(data);
		 }
	});
}

//function list(type,page,size) 
function list(type,page) //size param removed
{
	$.ajax({
		type:"GET",
		// url:"/mohitproject/"+type+"/list?page="+page+"&size="+size, //older way of pagination
		 url:"/mohitproject/"+type+"/list?page="+(page),
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