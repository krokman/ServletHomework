import org.junit.Test;

public class SQLServerTest {

	public UserDao ud = new UserDao();

	@Test
	public void SQLAddTest(){
		ud.addUser(new User("qwe","qwerty","qe"));

	}
	@Test
	public void SQLDeleteTest(){
		ud.deleteUser("qwe");
	}
}

