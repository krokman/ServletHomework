package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private int id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_GOOD", joinColumns = {
			@JoinColumn(name = "ORDER_ID", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "GOOD_ID", nullable = false, updatable = false)})
	private Set<Good> orders = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public Order() {
	}

	public Order(Set<Good> orders, User user) {
		this.orders = orders;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Good> getOrders() {
		return orders;
	}

	public void setOrders(Set<Good> orders) {
		this.orders = orders;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
