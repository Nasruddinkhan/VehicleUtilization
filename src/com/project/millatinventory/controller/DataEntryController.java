package com.project.millatinventory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.DataEntryExpense;
import com.project.millatinventory.model.Expenses;
import com.project.millatinventory.model.Moter;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Users;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.model.Vendors;
import com.project.millatinventory.service.CommonService;
import com.project.millatinventory.service.DataEntryService;
import com.project.millatinventory.service.LoginService;
import com.project.millatinventory.service.SitesService;
import com.project.millatinventory.service.VehicleService;

@Controller
@RequestMapping(value = "/dataEntry")
public class DataEntryController {

	 private static final Logger logger = LoggerFactory.getLogger(DataEntryController.class);
	@Autowired
	private DataEntryService dataEntryService;

	@Autowired
	private CommonService commonService;	
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private SitesService sitesService;
	@Autowired
	public LoginService loginService;

	@RequestMapping(value = "/addDataEntry")
	public ModelAndView addDataEntry(@ModelAttribute("dataEntry") DataEntry dataEntry,
			BindingResult result) {
		logger.info("UserController.showAddUser");
		ModelAndView mv = new ModelAndView("addDataEntry");
		//mv.addObject("vehicleno", getVehicleNum());
		mv.addObject("hours", getHours());
		mv.addObject("minutes", getMinutes());
		//mv.addObject("vehicles", getDepartments());
		mv.addObject("vendor", getVendor());
		mv.addObject("sites", getSites());
		//mv.addObject("vehicle", getVehicle());
		//mv.addObject("moter", getallmoter());
		mv.addObject("expenses", getExpenses());
		mv.addObject("dataentryId",getDataEntry());
		logger.info("return to Add User page ");
		return mv;
	}
	private int getDataEntry() {
		Integer dataEntryID = commonService.getDateEntryId();
		System.out.println(dataEntryID);
		if(dataEntryID==null) {
			dataEntryID=000;
		}
		return dataEntryID+1;
	}
	private List<Expenses> getExpenses() {
		return commonService.getExpenses();
	
	}
	private List<Moter> getallmoter() {
		return commonService.getmoter();
	
	}
	
	private List<Vehicle> getVehicleNum() {
		// TODO Auto-generated method stub
		return commonService.getVehicleNum();
	}
	private List<Vehicle> getVehicleNumByVehicleType(int vehicleType, int vendorId ) {
		// TODO Auto-generated method stub
		return vehicleService.getVehiclesByVehicleType(vehicleType, vendorId);
	}
	private List<String> getExcavatorTypeByVendorAndVehicleType(int vehicleType, int vendorId ) {
		// TODO Auto-generated method stub
		return vehicleService.getExcavatorTypeByVendorAndVehicleType(vendorId, vehicleType);
	}
	private List<Moter> getVehicleTypeByVendor(int vendorId) {
		// TODO Auto-generated method stub
		Vendors vendor = new Vendors();
		vendor.setVendorId(vendorId);
		return vehicleService.getVehicleTypeByVendor(vendor);
	}
	private List<Vehicle> getVehicle() {
		// TODO Auto-generated method stub
		return vehicleService.getVehicle();
	}
	private List<Vendors> getVendor() {
		// TODO Auto-generated method stub
		return commonService.getVendor();
	}
	private List<Sites> getSites() {
		// TODO Auto-generated method stub
		return commonService.getSites();
	}

	private List<String> getMinutes() {
		
		return commonService.getMinutes();
	}

	private List<String> getHours() {
		// TODO Auto-generated method stub
		return commonService.getHours();
	}
	

	private List getDepartments() {
		return commonService.getDepartments();
	}

	private Map getGenders() {
		return commonService.getGenders();
	}

	private Map getRoles() {
		return commonService.getRoles();
	}

	@RequestMapping(value = "/saveDataEntry", method = RequestMethod.POST)
	public String saveDataEntry(HttpServletRequest req, Model model,@ModelAttribute("DataEntry") DataEntry dataEntry, BindingResult result) {
		System.out.println(dataEntry);
		logger.info("saveDataEntry!"+dataEntry);
		
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
		user.setFileName(file.getOriginalFilename());*/
		//System.out.println(user);
		Users usersFromSession = (Users) req.getSession().getAttribute("User");
		//System.out.println(user);
		int update=Integer.parseInt(req.getParameter("flagName"));
		if (update == 0) {
			dataEntry.setCreatedBy(usersFromSession.getUserName());
			dataEntry.setCreatedDate(new Date());
			model.addAttribute("message", "Trip added successfully");
			logger.info("ADD");
			/*System.out.println("dataEntry="+dataEntry);*/
		} else {
			dataEntry.setModifiedBy(usersFromSession.getUserName());
			dataEntry.setModifiedDate(new Date());
			model.addAttribute("message", "Trip updated successfully");
			logger.info("Edit");
			/*System.out.println("dataEntry="+dataEntry);*/
		}
		//dataEntry.setStatus(ApplicationConstants.STATUS.ACTIVE);
		
		dataEntryService.saveDataEntry(dataEntry);
		
		//delete existing expense
		System.out.println("Delete exisitng entry ="+dataEntry.getDataEntryId());
		 int deleteExpenseByDataEntryId = dataEntryService.deleteExpenseByDataEntryId(dataEntry.getDataEntryId());
		
		//Expense 
	//	List<DataEntryExpense> expenseList=new ArrayList<DataEntryExpense>();
		DataEntryExpense dataEntryExpense=null;
	    Expenses expense=null;
			for(int i=1;i<=15;i++) {
				    String expenseType= req.getParameter("expenseType_"+i);
				    String expenseAmount = req.getParameter("expenseAmount_"+i);
				    Optional<String> expNullable = Optional.ofNullable(expenseType);
				    Optional<String> amtNullable = Optional.ofNullable(expenseAmount);
				    if(expNullable.isPresent() && amtNullable.isPresent() ){
				    dataEntryExpense =new DataEntryExpense();
					expense=new Expenses();
				    Integer expenseId = Integer.parseInt(expNullable.get());				    
				    				    
				    expense.setExpenseId(expenseId);
					dataEntryExpense.setExpenses(expense);
					dataEntryExpense.setExpenseAmount(amtNullable.get());
					dataEntryExpense.setDataEntryId(dataEntry.getDataEntryId());
				//	dataEntry.getDataEntryExpenses().add(dataEntryExpense);
					System.out.println("Saving Expense"+dataEntryExpense);
					dataEntryService.saveExpenses(dataEntryExpense);
					//expenseList.add(dataEntryExpense);
				    }
				    else {
				    	continue;
				    }
			}
			//dataEntry.setDataEntryExpenses(expenseList);
			//dataEntryService.saveDataEntry(dataEntry);
			//
			//if(expenseList.size()>0)
			//dataEntryService.saveExpenses(expenseList);	//
			//End Expense
				
		return "forward:/dataEntry/dataEntryList.do";
	}


	@RequestMapping(value = "/dataEntryList",method = {RequestMethod.GET,RequestMethod.POST})
	public String getAllEntries(Model model) {
		System.out.println("UserController.getEntries()");
		List<DataEntry> dataEntries = dataEntryService.getEntries();
		/*List<DataEntryExpense> expenseList = dataEntryService.getExpenses();*/
		model.addAttribute("dataEntryList", dataEntries);
		/*model.addAttribute("expenseList", expenseList);*/
		System.out.println("dataEntries= "+dataEntries);
		return "dataEntryList";
	}
	@RequestMapping(value = "/GetExpenseList/{expences}", method = RequestMethod.GET)
	@ResponseBody
	public String GetExpenseList(@PathVariable("expences") Integer expences) {
		
		List<DataEntryExpense> expenseList = dataEntryService.getExpenseByDataEntryId(expences);
		Gson gson = new Gson();
		return gson.toJson(expenseList);
	}
	
	@RequestMapping(value = "/ShowUpdateUser/{dataEntryId}", method = RequestMethod.GET)
	public ModelAndView ShowUpdateUser(@PathVariable("dataEntryId") Integer dateEntry,
			ModelMap map) {
		System.out.println("CustomerController.ShowUpdateUser()");
		DataEntry dataEntryById = dataEntryService.getDataEntryById(dateEntry);
		ModelAndView mv = new ModelAndView("addUser");
		mv.addObject("roles", getRoles());
		mv.addObject("genders", getGenders());
		mv.addObject("departmentList", getDepartments());
		mv.addObject(dataEntryById);
		logger.info("ShowUpdateUser", dataEntryById.getDataEntryId());
		
		return mv;
	}
	@RequestMapping(value = "/editDataEntryById/{dataEntryId}", method = RequestMethod.GET)
	public ModelAndView editVehicleById(@PathVariable("dataEntryId") int dataEntryId,
			ModelMap map) {
		logger.info("editDataEntryById"+dataEntryId);
		DataEntry DataEntryById = dataEntryService.getDataEntryById(dataEntryId);
		List<DataEntryExpense> expenseList = dataEntryService.getExpenseByDataEntryId(dataEntryId);
		
		ModelAndView mv = new ModelAndView("addDataEntry");
		mv.addObject(DataEntryById);
		mv.addObject("moter", getVehicleTypeByVendor(DataEntryById.getVendorType().getVendorId()));
		mv.addObject("vehicleno", getVehicleNumByVehicleType(DataEntryById.getVehicleType().getM_Id(),DataEntryById.getVendorType().getVendorId()));
		mv.addObject("sites", getSites());
		mv.addObject("vehicle", getExcavatorTypeByVendorAndVehicleType(DataEntryById.getVehicleType().getM_Id(),DataEntryById.getVendorType().getVendorId()));
		mv.addObject("expenseList", expenseList);
		mv.addObject("expenses", getExpenses());
		mv.addObject("vendor", getVendor());
		mv.addObject("flag", "1");
		mv.addObject("expenseCount",expenseList.size());
		logger.info("Return addUser");
		return mv;
	}
	
	@RequestMapping(value = "/deleteDataEntryById/{dataEntryId}", method = RequestMethod.GET)
	public String deleteDataEntryById(Model model, @PathVariable("dataEntryId") Integer dataEntryId) {
		logger.info("deleteUser", dataEntryId);
		System.out.println("UserController.deleteUser()" + dataEntryId);
		int deleteUserById = dataEntryService.deleteDataEntryById(dataEntryId);
		if (deleteUserById == 0)
			model.addAttribute("message", "Trip not deleted successfully");
		else
			model.addAttribute("message", "Trip deleted successfully");
		
		logger.info("return to user List ");
		
		return "forward:/dataEntry/dataEntryList.do";
	}

	@RequestMapping(value = "/viewDataEntry/{DataEntryId}", method = RequestMethod.GET)
	public ModelAndView viewDataEntry(@PathVariable("DataEntryId") Integer DataEntryId,ModelMap map) {
		logger.info("viewUser"+DataEntryId);
		System.out.println("UserController.viewUser()");
		DataEntry dataEntryById = dataEntryService.getDataEntryById(DataEntryId);
		List<DataEntryExpense> expenseList = dataEntryService.getExpenseByDataEntryId(DataEntryId);
		ModelAndView mv = new ModelAndView("viewDataEntry");
		System.out.println("150");
		mv.addObject(dataEntryById);
		mv.addObject("expenseList", expenseList);
		logger.info("Return dataEntryList"+dataEntryById);
		return mv;
	}
	
/*	@RequestMapping(value = "/checkEntry", method = RequestMethod.POST, headers = "Accept=")*/
	/*public @ResponseBody String checkLoginId(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("checkEntry AJAX");
		logger.info(request.getParameter("siteid"));
		int siteid = Integer.parseInt(request.getParameter("siteid"));
		Sites site=new Sites();
		site.setSiteId(siteid);
		DataEntry de=new DataEntry();
		de.setSite(site.getSiteId());
		DataEntry siteBySiteId = dataEntryService.getDataEntrySiteCriteria(de);
		logger.info("DataEntry "+siteBySiteId);
		if (null != siteBySiteId)
			return "Can't be delete this site Becouse the Entry of this site in another table ";
		else
			return "farword/site/deleteSite/"+siteid+".do";*/
	/*}*/
	
	
	
	
	
	
	
	
	
	//@RequestMapping(value = "/checkLoginId", method = RequestMethod.POST, headers = "Accept=*/*")
//	public @ResponseBody String checkLoginId(HttpServletRequest request,
//			HttpServletResponse response) {
//		logger.info("checkLoginId AJAX");
//		
//		logger.info(request.getParameter("loginId"));
//		logger.info(request.getParameter("id"));
//		String loginId = request.getParameter("loginId");
//		int id = Integer.parseInt(request.getParameter("id"));
//		Users u = new Users();
//		u.setId(id);
//		u.setLoginId(loginId);
//		DataEntry userByLoginId = dataEntryService.getDataEntryCriteria(u);
//		logger.info("USer "+userByLoginId);
//		if (null != userByLoginId)
//			return "Login Id is already exist";
//		else
//			return "";
//	}
	
	/*
	@RequestMapping(value="/download/{userId}" ,method = RequestMethod.GET)
    public void doDownload(@PathVariable("userId") Integer userId,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
	   
	     // Size of a byte buffer to read/write file
	     
	    final int BUFFER_SIZE = 4096;
		Users userById = dataEntryService.getUserById(userId);
		byte[] file = userById.getFile();
		
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
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();

    }
*/ 
}
