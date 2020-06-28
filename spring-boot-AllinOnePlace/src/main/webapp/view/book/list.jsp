<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<div class="container col-md-11">
	<div class="card">
		<div class="card-header">
			<strong>
				<i class="fa fa-th-list"></i> Book List
			</strong>
			<div class="btn-group pull-right">
				<a href="javascript:void(0);" class="btn btn-outline-secondary btn-sm" onclick="addForm('book')" title="Add New Book">
					<i class="fa fa-plus-circle fa-lg"></i>
				</a>
			</div>
		</div>
		<div class="card-body">
			<table class="table table-bordered table-condensed table-hover table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>IsbnNumber</th>
						<th>Price</th>
						<th>Language</th>
						<th>Edit</th>
					    <th>Delete</th>
						
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${books.size() gt 0}">
							<c:forEach items="${books}" var="book">
								<tr>
									<td>${book.title}</td>
									<td>${book.author}</td>
									<td>${book.isbnNumber}</td>
									<td>${book.price}</td>
									<td>${book.language}</td>
									
									
									<td>
										<a href="javascript:void(0);" onclick="editForm('book', '${book.id}')"><i class="fa fa-edit fa-lg"></i></a>
									<td>
										<a href="javascript:void(0);" onclick="deleteData('book', '${book.id}')"><i class="fa fa-trash fa-lg"></i></a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr align="center">
							<td colspan="5">No Books available</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				
			</table>
		</div>
		
	</div>
</div>