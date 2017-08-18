package com.hjw.home.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hjw.home.user.service.UserService;
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("test")
	public ModelAndView testmvc() throws Exception{
		User user=new User();
		user.setId(8);
		user.setName("admin1");
		user.setAge(15);
		user.setSex("男");
		userService.saveuser(user);
		return new ModelAndView("test");
	}
}
