package com.yjmyzz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// 杞崲涓鸿嚜瀹氫箟鐨則oken
		CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
		String poem = LoginQuestion.getQuestions().get(token.getQuestionId());
		// 鏍￠獙涓嬩竴鍙ョ殑绛旀鏄惁姝ｇ‘
		if (!poem.split("/")[1].equals(token.getAnswer())) {
			throw new BadAnswerException("the answer is wrong!");
		}

	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		System.out
				.println("CustomAuthenticationProvider.retrieveUser() is called!");

		String[] whiteLists = new String[] { "ADMIN", "SUPERVISOR", "JIMMY" };

		// 濡傛灉鐢ㄦ埛鍦ㄧ櫧鍚嶅崟閲�鐩存帴鏀捐(娉�浠呬粎鍙槸婕旂ず,鍗冧竾涓嶈鍦ㄥ疄闄呴」鐩腑杩欎箞骞�)
		if (Arrays.asList(whiteLists).contains(username)) {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			UserDetails user = new User(username, "whatever", authorities);
			return user;
		}

		return new User(username, "no-password", false, false, false, false,
				new ArrayList<GrantedAuthority>());

	}

}
