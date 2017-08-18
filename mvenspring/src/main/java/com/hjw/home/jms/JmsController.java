package com.hjw.home.jms;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hjw.home.jms.service.ProducerService;
import com.hjw.home.user.User;

@Controller
@RequestMapping("/jms/")
public class JmsController {
	@Autowired
	private ProducerService producerService;
	@Autowired
	@Qualifier("topicDestination")  
	private Destination destination;  
	@RequestMapping("send")
	public ModelAndView send(){
		/*User user=new User();
		user.setId(8);
		user.setName("admin1");
		user.setAge(15);
		user.setSex("男");
		producerService.sendMessage(destination, user);*/ 
        producerService.sendMessage(destination, "你好，生产者！这是消息：" + (1));  

		return new ModelAndView("test");
	}
}
