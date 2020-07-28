package com.VideoClub.Movies.controller;

import com.VideoClub.Movies.model.Bookmark;
import com.VideoClub.Movies.model.BookmarkId;
import com.VideoClub.Movies.model.User;
import com.VideoClub.Movies.service.BookmarkService;
import com.VideoClub.Movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AppController {

    @Autowired
    private UserService service;

    @Autowired
    private BookmarkService bookmarkService;


    @RequestMapping("/")
    public String homePage(){
        return "index";
    }

    @RequestMapping("/login")
    public String logIn(@ModelAttribute User user, Model model, HttpSession session){ //modelattribute annotation is creating a user object based on name tags on html

        System.out.println(session.getId());

        User userToValidate = service.get(user.getEmail());



        if(userToValidate == null){
            model.addAttribute("replyText", "*Email is not Valid*");
            return "index";
        }else {

            if(userToValidate.getPassword().equals(user.getPassword().trim())) {
                session.setAttribute("user", userToValidate.getEmail());
                return "redirect:search" + "/user=" + userToValidate.getUserName();
            }else{
                model.addAttribute("replyText", "*Password is not Valid*");
                return "index";
            }//end if/else
        }//end if/else
    }//end login handle

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Model model, HttpSession session){
        User userToValidate = service.get(user.getEmail());

        if(userToValidate == null){
            service.save(user);
            session.setAttribute("user", user.getEmail());
            return "redirect:search" + "/user=" + user.getUserName();
        }else{
            model.addAttribute("replyText", "*Email already in use*");
            return "index";
        }
    }//end of register handler

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("Log out: " + session.getId());
        session.invalidate();
        return "index";
    }

    @RequestMapping("/search")
    public String searchPage(HttpSession session){
        String userEmail = (String) session.getAttribute("user");
        User user = service.get(userEmail);
        return "redirect:search" + "/user=" + user.getUserName();
    }

    @RequestMapping("/bookmarks")
    public String bookmarksPage(HttpSession session){
        String userEmail = (String) session.getAttribute("user");
        User user = service.get(userEmail);
        return "redirect:bookmarks" + "/user=" + user.getUserName();
    }

    @RequestMapping("search/*")
    public String search(){
        return "search";
    }

    @RequestMapping("/*")
    public String error(){
        return "index";
    }

    @RequestMapping("bookmarks/*")
    public String bookmarks(){
        return "my_bookmarks";
    }


    @PostMapping("/add")
    public String addBookmark(HttpSession session, Model model, @RequestBody String title){

        System.out.println("Adding Movie.......");
        String userEmail = (String) session.getAttribute("user");
        User user = service.get(userEmail);
        Bookmark bookmark = new Bookmark(new BookmarkId(user.getEmail(), title));
        bookmarkService.save(bookmark);

        return "redirect:search" + "/user=" + user.getUserName();

    }

    @RequestMapping("/bookmarkedMovies")
    public @ResponseBody ArrayList<String> userBookmarks(HttpSession session){
        String userEmail = (String) session.getAttribute("user");
        System.out.println(bookmarkService.getTitles(userEmail).size());
        ArrayList<String> titles = (ArrayList<String>) bookmarkService.getTitles(userEmail);
        return titles;
    }
}
