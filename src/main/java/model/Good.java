package model;

import java.util.Objects;

public class Good {
	private String name;
	private String description;
	private String price;

	public Good() {
	}

	public Good(String name, String description, String price) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Good good = (Good) o;
		return Objects.equals(name, good.name) &&
				Objects.equals(description, good.description) &&
				Objects.equals(price, good.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, price);
	}
}
