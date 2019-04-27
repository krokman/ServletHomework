public class User {
	private String nickname;
	private String password;
	private String email;

	public User() {
	}

	public User(String nickname, String password, String email) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
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
}
