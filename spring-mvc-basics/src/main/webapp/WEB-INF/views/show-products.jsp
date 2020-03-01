<%@ include file="header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>${title}</h3>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>Sl no</th>
			<th>Product name</th>
			<th>Quantity per unit</th>
			<th>Unit price</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${products}" var="p" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>
			<a href="./view-product-details?id=${p.productId}">
			${p.productName}
			</a>
			</td>
			<td>${p.quantityPerUnit}</td>
			<td>${p.unitPrice}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<%@ include file="footer.jspf" %>