<%@ include file="header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Product details for id ${param.id}</h3>

<table class="table table-striped">
	<tbody>
		<tr>
			<td>Name</td>
			<td>: ${product.productName}</td>
		</tr>
		<tr>
			<td>Category</td>
			<td>: ${product.category.categoryName}
			(${product.category.description})
			</td>
		</tr>
		<tr>
			<td>Quantity per unit</td>
			<td>: ${product.quantityPerUnit}</td>
		</tr>
		<tr>
			<td>Unit price</td>
			<td>: $${product.unitPrice}</td>
		</tr>
		<tr>
			<td>Units in stock</td>
			<td>: ${product.unitsInStock}</td>
		</tr>
		<tr>
			<td>Units on order</td>
			<td>: ${product.unitsOnOrder}</td>
		</tr>
		<tr>
			<td>Reorder level</td>
			<td>: ${product.reorderLevel}</td>
		</tr>
		<tr>
			<td>Discontinued?</td>
			<td>: ${product.discontinued? "Yes": "No"}</td>
		</tr>

	</tbody>
</table>

<%@ include file="footer.jspf" %>