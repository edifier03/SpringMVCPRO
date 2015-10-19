package publicTools.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * �?��自定义的service用来和数据库进行操作. 即以后我们要通过数据库保存权�?则需要我们继承UserDetailsService
 * 
 * @author liukai
 * 
 */
public class CustomUserDetailsService implements UserDetailsService {

	protected static Logger logger = Logger.getLogger("service");

	private UserDao userDAO = new UserDao();

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserDetails user = null;

		try {

			DbUser dbUser = userDAO.getDatabase(username);

			// Populate the Spring User object with details from the dbUser
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct
			// role type

			user = new User(dbUser.getUsername(), dbUser.getPassword()
					.toLowerCase(), true, true, true, true,
					getAuthorities(dbUser.getAccess()));

		} catch (Exception e) {
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		return user;
	}

	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer access) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// �?��的用户默认拥有ROLE_USER权限
		logger.debug("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		// 如果参数access�?.则拥有ROLE_ADMIN权限
		if (access.compareTo(1) == 0) {
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return authList;
	}
}
