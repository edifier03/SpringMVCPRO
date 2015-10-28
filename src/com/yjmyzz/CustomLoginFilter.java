package com.yjmyzz;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		//瑙ｅ喅涓枃璇楀彞鐨刾ost涔辩爜闂
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// if (!request.getMethod().equals("POST")) {
		// throw new AuthenticationServiceException(
		// "Authentication method not supported: "
		// + request.getMethod());
		// }

		String username = obtainUsername(request).toUpperCase().trim();
		String password = obtainPassword(request);
		//鑾峰彇鐢ㄦ埛杈撳叆鐨勪笅涓�彞绛旀
		String answer = obtainAnswer(request);
		//鑾峰彇闂Id(鍗� hashTable鐨刱ey)
		Integer questionId = obtainQuestionId(request);

		//杩欓噷灏嗗師鏉ョ殑UsernamePasswordAuthenticationToken鎹㈡垚鎴戜滑鑷畾涔夌殑CustomAuthenticationToken
		CustomAuthenticationToken authRequest = new CustomAuthenticationToken(
				username, password, questionId, answer);

		//杩欓噷灏卞皢token浼犲埌鍚庣画楠岃瘉鐜妭浜�		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainAnswer(HttpServletRequest request) {
		return request.getParameter(answerParameter);
	}

	protected Integer obtainQuestionId(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter(questionIdParameter));
	}

	private String questionIdParameter = "questionId";
	private String answerParameter = "answer";

	public String getQuestionIdParameter() {
		return questionIdParameter;
	}

	public void setQuestionIdParameter(String questionIdParameter) {
		this.questionIdParameter = questionIdParameter;
	}

	public String getAnswerParameter() {
		return answerParameter;
	}

	public void setAnswerParameter(String answerParameter) {
		this.answerParameter = answerParameter;
	}

}
