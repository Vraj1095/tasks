package com.spring_jpa.jpademo.controller;

import com.spring_jpa.jpademo.model.User;
import com.spring_jpa.jpademo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public ModelAndView load()
    {
        System.out.println("Called.....");
        return new ModelAndView("User","user",new User());
    }

    @RequestMapping(value="insert",method=RequestMethod.POST)
    public ModelAndView Insert(@ModelAttribute User user,@RequestParam int id)
    {
        userService.addUser(user);
        return new ModelAndView("redirect:/insertUser");
    }

    @RequestMapping(value="search",method=RequestMethod.GET)
    public ModelAndView Search()
    {
        List<User> searchList = userService.findAllUser();
        return new ModelAndView("Search","searchList",searchList);
    }

    @RequestMapping(value="delete",method=RequestMethod.GET)
    public ModelAndView Delete(@ModelAttribute User user,@RequestParam int Id)
    {

        user.setId(Id);
        userService.deleteUser(user);
        return new ModelAndView("redirect:/search");
    }

    @RequestMapping(value="edit",method=RequestMethod.GET)
    public ModelAndView Edit(@ModelAttribute User user,@RequestParam int Id)
    {

        user.setId(Id);
        User user1 =this.userService.findUserById(user);
        return new ModelAndView("User","user",user1);
    }

}
