
package me.woemler.springblog.controllers;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.woemler.springblog.models.User;
import me.woemler.springblog.repositories.UserRepository;
import me.woemler.springblog.repositories.UserService;


@Controller
public class LoginController {
	 @Autowired
	 private UserService userService;

    private static Logger logger  = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(ModelMap map){
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    
    
    @RequestMapping(value= {"/loginfailed"}, method=RequestMethod.GET)
    public String loginError(ModelMap map){
        map.addAttribute("error", "true");
        return "login";
    }
    
    
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        System.out.println("insidereg");

        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
       
    	
    	System.out.println("in reg");

        if (bindingResult.hasErrors()) {
        	System.out.println("error in reg if");
            return "registration";
            
        }
        
        System.out.println(userForm);

        userService.save(userForm);

      

        return "redirect:/home";
    }
}
