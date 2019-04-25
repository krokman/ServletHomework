class User {
	private String nickName;
	private String password;

	User(String nickName, String password) {
		this.nickName = nickName;
		this.password = password;
	}

	User() {
	}

	String getNickName() {
		return nickName;
	}

	void setNickName(String nickName) {
		this.nickName = nickName;
	}

	String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}
}
