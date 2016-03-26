package org.me.contacts.uiservice;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author arjun
 * 
 *         Main Controller - This class handles all the requests for
 *         application pages such as home page, 404s, etc
 *         
 *         The methods of this class return a String value which is mapped to a 
 *         jsp by the spring dispatcher servlet/view resolver
 *
 */
@Controller
public class MainController {

	/* Initialize logger for any logging */
	private static final Logger logger = Logger.getLogger(MainController.class);
	
	/**
	 * Requests to the root context is handled by this method.
	 * Mapped to the / endpoint
	 * 
	 * Fetches all the contacts for intial display 
	 * 
	 * @return String (name of the JSP)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		logger.info("Requesting the index page..");		
		ModelAndView mav = new ModelAndView("index");		
		return mav;
	}
	
	
	

}
