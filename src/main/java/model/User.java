package model;


import util.HashPasswordUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NICKNAME")
	private String nickname;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "ROLE")
	private String role;
	@Column(name = "SALT")
	private String salt;
	@OneToOne(mappedBy = "user")
	private Order order;

	public User() {
	}

	public User(int id, String nickname, String password, String email, String role, String salt) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.role = role;
		this.salt = salt;
	}

	public User(String nickname, String password, String email, String role, boolean withSalt) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.role = role;
		if (withSalt) {
			salt = HashPasswordUtil.getSalt();
			this.password = HashPasswordUtil.getHashPassword(password, salt);
		}
	}

	public User(int id, String nickname, String password, String email, String role, boolean withSalt) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.role = role;
		if (withSalt) {
			salt = HashPasswordUtil.getSalt();
			this.password = HashPasswordUtil.getHashPassword(password, salt);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
