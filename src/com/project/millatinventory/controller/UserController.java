package com.project.millatinventory.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.millatinventory.common.ApplicationConstants;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.service.CommonService;
import com.project.millatinventory.service.LoginService;
import com.project.millatinventory.service.SitesService;
import com.project.millatinventory.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private CommonService commonService;
	

	@Autowired
	public LoginService loginService;

	@RequestMapping(value = "/showAddUser", method = RequestMethod.GET)
	public ModelAndView showAddUser(@ModelAttribute("users") Users user,
			BindingResult result) {
		logger.info("UserController.showAddUser");
		ModelAndView mv = new ModelAndView("addUser");
		logger.info("Hi");

		mv.addObject("siteList", getSites());
		mv.addObject("genders", getGenders());
		mv.addObject("roles", getRoles());
		logger.info("return to Add User page ");
		return mv;
	}

	@Autowired
	private SitesService sitesService;
	
	private List<Sites> getSites() {
		// TODO Auto-generated method stub
		return sitesService.getSites();
	}
	private Map getGenders() {
		return commonService.getGenders();
	}

	private Map getRoles() {
		return commonService.getRoles();
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(/*@RequestParam("Img") MultipartFile imgFile,
			@RequestParam("file") MultipartFile file,*/
			HttpServletRequest req, Model model,
			@ModelAttribute("users") Users user, BindingResult result) {
		logger.info("saveUser!");
		
		//System.out.println(imgFile.getOriginalFilename());
		//System.out.println(file.getOriginalFilename());
		/*byte[] imgBytes = null;
		byte[] fileBytes = null;
		try {
			imgBytes = IOUtils.toByteArray(imgFile.getInputStream());
			fileBytes = IOUtils.toByteArray(file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setImage(imgBytes);
		user.setFile(fileBytes);
		
		user.setFileName(file.getOriginalFilename());
		*/
		//System.out.println(user);
		Users usersFromSession = (Users) req.getSession().getAttribute("User");
		//System.out.println(user);

		if (null == user.getId() || 0 == user.getId()) {
			user.setCreatedBy(usersFromSession.getUserName());
			user.setCreatedDate(new Date());
			model.addAttribute("message", "User added successfully");
			logger.info("ADD");
			
		} else {
			user.setModifiedBy(usersFromSession.getUserName());
			user.setModifiedDate(new Date());
			model.addAttribute("message", "User updated successfully");
			logger.info("Edit");
			
		}
		user.setStatus(ApplicationConstants.STATUS.ACTIVE);
		userService.saveUser(user);
		return "forward:/user/users.do";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		
		logger.info("UserController.getAllCustomers()");
		List<Users> users = userService.getUsers();
		model.addAttribute("userList", users);
		return "userList";
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String getUsers(Model model) {
		logger.info("UserList", model.asMap());
		logger.info("UserController.getAllCustomers()");
		List<Users> users = userService.getUsers();
		model.addAttribute("userList", users);
		logger.info("show User list"+users.size());
		return "userList";
	}

	@RequestMapping(value = "/ShowUpdateUser/{userId}", method = RequestMethod.GET)
	public ModelAndView ShowUpdateUser(@PathVariable("userId") Integer userId,
			ModelMap map) {
		logger.info("CustomerController.ShowUpdateUser()");
		Users userById = userService.getUserById(userId);
		ModelAndView mv = new ModelAndView("addUser");
		mv.addObject("roles", getRoles());
		mv.addObject("genders", getGenders());
		mv.addObject("siteList", getSites());
		mv.addObject(userById);
		return mv;
	}

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteUser(Model model, @PathVariable("userId") int userId) {
		logger.info("deleteUser", userId);
		logger.info("UserController.deleteUser()" + userId);
		int deleteUserById = userService.deleteUserById(userId);
		if (deleteUserById == 0)
			model.addAttribute("message", "User not deleted successfully");
		else
			model.addAttribute("message", "User deleted successfully");
		
		logger.info("return to user List ");
		
		return "forward:/user/users.do";
	}

	@RequestMapping(value = "/viewUser/{userId}", method = RequestMethod.GET)
	public ModelAndView viewUser(@PathVariable("userId") Integer userId,
			ModelMap map) {
		logger.info("viewUser"+userId);
		logger.info("UserController.viewUser()");
		Users userById = userService.getUserById(userId);
		ModelAndView mv = new ModelAndView("viewUser");
		/*byte[] bytes = userById.getImage();
		if (null != bytes) {
			byte[] encodeBase64 = Base64.encodeBase64(bytes);
			String base64Encoded = "";
			try {
				base64Encoded = new String(encodeBase64, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userById.setImageFile(base64Encoded);
		}
*/
	
		mv.addObject(userById);
		logger.info("Return viewUser");
		return mv;
	}

	@RequestMapping(value = "/checkLoginId", method = RequestMethod.POST, headers = "Accept=*/*")
	public @ResponseBody String checkLoginId(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("checkLoginId AJAX");
		
		logger.info(request.getParameter("loginId"));
		logger.info(request.getParameter("id"));
		String loginId = request.getParameter("loginId");
		int id = Integer.parseInt(request.getParameter("id"));
		Users u = new Users();
		u.setId(id);
		u.setLoginId(loginId);
		Users userByLoginId = userService.getUserCriteria(u);
		logger.info("USer "+userByLoginId);
		if (null != userByLoginId)
			return "Login Id is already exist";
		else
			return "";
	}
	@RequestMapping(value = "/changePasswordPage", method = RequestMethod.GET)
	public String changePasswordPage(Model model) {
		logger.info("changePasswordPage ");
		model.addAttribute("user",new Users() );
		logger.info("Return to Change password page");
		return "changePassword";
	}
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changePasswordPage(@ModelAttribute("user") Users user ) {
		logger.info("changePassword "+user);		
		Users loginUser = loginService.checkLogin(user);
		ModelAndView mv = new ModelAndView("changePassword");
		if(null!= loginUser && null!=loginUser.getId()){
			int updatePassword = userService.updatePassword(user);
			if(updatePassword!=0){
				logger.info("UPDATE PASSWORD");			
			mv.addObject("message", "Password Updated Successfully");
			}else
			{
				mv.addObject("message", "Password could not updated Successfully");
			}			
		}else{
			
			mv.addObject("message", "Invalid Current Password");			
			
		}
		logger.info("Return to Change password page");
		mv.addObject("user",user );
		return mv;
	}
	@RequestMapping(value="/download/{userId}" ,method = RequestMethod.GET)
    public void doDownload(@PathVariable("userId") Integer userId,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
	    /**
	     * Size of a byte buffer to read/write file
	     */
	    final int BUFFER_SIZE = 4096;
		Users userById = userService.getUserById(userId);
		/* byte[] file = userById.getFile();
		
		InputStream inputStream = new ByteArrayInputStream(file);

        // get MIME type of the file
       String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        //response.setContentType(mimeType);
        //response.setContentLength((int) file.length());
 
        // set headers for the response
	      MimetypesFileTypeMap mimetypesFileTypeMap=new MimetypesFileTypeMap();
	      response.setContentType(mimetypesFileTypeMap.getContentType(userById.getFileName()));
		String mimeType = "application/octet-stream";
		response.setContentType(mimeType);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
        		userById.getFileName());
        response.setHeader(headerKey, headerValue);
        */
        // get output stream of the response
       /* OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
 */
    }
}
