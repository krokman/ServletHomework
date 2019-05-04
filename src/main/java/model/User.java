package model;

import java.util.Objects;

public class User {
	private String nickname;
	private String password;
	private String email;
	private String role;

	public User() {
	}

	public User(String nickname, String password, String email, String role) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.role = role;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(nickname, user.nickname) &&
				Objects.equals(password, user.password) &&
				Objects.equals(email, user.email) &&
				Objects.equals(role, user.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickname, password, email, role);
	}
}
