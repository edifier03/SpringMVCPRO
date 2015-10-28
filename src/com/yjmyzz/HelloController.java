package com.yjmyzz;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcome() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Welcome - Spring Security Custom login/logout Filter");
		model.addObject("message", "This is welcome page!");
		model.setViewName("/yjmyzz/hello");
		return model;

	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Admin - Spring Security Custom login/logout Filter");
		model.addObject("message", "This is protected page!");
		model.setViewName("/yjmyzz/admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		//从预定义的诗句中,随机挑一个上句
		Random rnd = new Random();
		int questionId = rnd.nextInt(LoginQuestion.getQuestions().size() + 1);
		if (questionId == 0) {
			questionId = 1;
		}
		model.addObject("questionId", questionId);
		model.addObject("question", LoginQuestion.getQuestions()
				.get(questionId).split("/")[0]);
		
		model.setViewName("/yjmyzz/login");

		return model;

	}

	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession()
				.getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof BadAnswerException) {
			error = exception.getMessage();
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

}
