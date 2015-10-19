package publicTools.Security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class UserDao {

	protected static Logger logger = Logger.getLogger("dao");

	public DbUser getDatabase(String username) {

		List<DbUser> users = internalDatabase();

		for (DbUser dbUser : users) {
			if (dbUser.getUsername().equals(username) == true) {
				logger.debug("User found");
				return dbUser;
			}
		}
		logger.error("User does not exist!");
		throw new RuntimeException("User does not exist!");

	}


	private List<DbUser> internalDatabase() {

		List<DbUser> users = new ArrayList<DbUser>();
		DbUser user = null;

		user = new DbUser();
		user.setUsername("admin");

		user.setPassword("c4ca4238a0b923820dcc509a6f75849b");
		user.setAccess(1);

		users.add(user);

		user = new DbUser();
		user.setUsername("user");

		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		user.setAccess(2);

		users.add(user);

		return users;

	}
}