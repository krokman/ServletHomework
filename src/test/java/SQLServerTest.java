import org.junit.Assert;
import org.junit.Test;

public class SQLServerTest {

	UserDao ud = new UserDao();

	@Test
	public void SQLTest(){
		User userTest = new User("str","srt1");
		ud.addUser(userTest);
		Assert.assertEquals(ud.getUserByNickname("str").getNickName(), userTest.getNickName());
		ud.deleteUser("str");
	}

}
