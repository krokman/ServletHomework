package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "goods")
public class Good {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private String price;

	public Good() {
	}

	public Good(String name, String description, String price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Good(int id, String name, String description, String price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Good good = (Good) o;
		return id == good.id &&
				Objects.equals(name, good.name) &&
				Objects.equals(description, good.description) &&
				Objects.equals(price, good.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, price);
	}
}
