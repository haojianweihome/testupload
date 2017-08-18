package com.hjw.home.test;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hjw.home.core.MongoGridfsUtil;
import com.hjw.home.user.User;
import com.hjw.home.user.service.UserService;

@Controller
@RequestMapping("/test/")
public class testController {
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
	
	
	@RequestMapping("/upload")    
    public String upLoad(HttpServletRequest request,String description,MultipartFile file) throws IllegalStateException, IOException{
		if(file!=null){
			String path=request.getServletContext().getRealPath("/image/");
			String filename=file.getOriginalFilename();
			String filetype = filename.substring(filename.lastIndexOf(".") + 1,filename.length());
			//File filepath=new File(path,filename);
			/*if(!filepath.getParentFile().exists()){
				filepath.getParentFile().mkdirs();
			}*/
			MongoGridfsUtil mongoGridfsUtil = new MongoGridfsUtil();
			//FileInputStream in=new FileInputStream(path);
			mongoGridfsUtil.save(file.getInputStream(), "123123", filename, filetype);
			//file.transferTo(new File(path+File.separator+filename));
		}
		return "success";    
           
    }    
}
