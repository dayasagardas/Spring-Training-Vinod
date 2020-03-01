package co.vinod.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
	@Id
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "product_name")
	private String productName;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;

	@Column(name = "unit_price")
	private Double unitPrice;

	@Column(name = "units_in_stock")
	private Integer unitsInStock;

	public void setUnitsInStock(Integer unitsInStock) {
		if (unitsInStock < 0) {
			throw new RuntimeException("unitsInStock cannot be negative");
		}
		this.unitsInStock = unitsInStock;
	}

	@Column(name = "units_on_order")
	private Integer unitsOnOrder;

	@Column(name = "reorder_level")
	private Integer reorderLevel;

	private Boolean discontinued;

	@Transient
	private String remarks;
}
