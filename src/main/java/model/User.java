package model;


import util.HashPasswordUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "Id")
	private int id;
	@Column(name = "Nickname")
	private String nickname;
	@Column(name = "Password")
	private String password;
	@Column(name = "Email")
	private String email;
	@Column(name = "Role")
	private String role;
	@Column(name = "Salt")
	private String salt;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id &&
				Objects.equals(nickname, user.nickname) &&
				Objects.equals(password, user.password) &&
				Objects.equals(email, user.email) &&
				Objects.equals(role, user.role) &&
				Objects.equals(salt, user.salt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nickname, password, email, role, salt);
	}
}
