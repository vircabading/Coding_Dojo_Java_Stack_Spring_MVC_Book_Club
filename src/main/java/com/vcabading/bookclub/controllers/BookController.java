package com.vcabading.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vcabading.bookclub.models.Book;
import com.vcabading.bookclub.models.User;
import com.vcabading.bookclub.services.BookService;
import com.vcabading.bookclub.services.UserService;

////////////////////////////////////////////////////////////////////
//	BOOK CONTROLLER
////////////////////////////////////////////////////////////////////

@Controller
public class BookController {

	//	//// FIELDS ////////////////////////////////////////////////
    
    @Autowired
    private UserService userServ;
    
    @Autowired
    private BookService bookServ;
    
    //	//// BOOKS NEW /////////////////////////////////////////////
    
    //	**** GET: Render Form **************************************
    @GetMapping("/books/new")
    public String booksNew(Model model, HttpSession session) {
//    	---- Check if User is Logged In  -----------------------
    	if (session.isNew() || session.getAttribute("user_id") == null) {
    		return "redirect:/";
    	}
    	//	---- Get the Log In User -------------------------------
    	User loggedInUser = this.userServ.retrieveUser((Long) session.getAttribute("user_id"));
    	model.addAttribute("loggedInUser", loggedInUser);
    	//	---- Get a New Book ------------------------------------
    	Book newBook = new Book();
    	model.addAttribute("newBook", newBook);
    	return "booksnew.jsp";
    }
    
    //	**** POST: Add New Book to database *************************
    @PostMapping("books/new")
    public String booksNewPost(@Valid @ModelAttribute("newBook") Book newBook,
    		BindingResult result, Model model, HttpSession session) {
    	// 	---- Check if User is Logged In  ------------------------
    	if (session.isNew() || session.getAttribute("user_id") == null) {
    		return "redirect:/";
    	}
    	//	---- Get the Log In User --------------------------------
    	User loggedInUser = this.userServ.retrieveUser((Long) session.getAttribute("user_id"));
    	model.addAttribute("loggedInUser", loggedInUser);
    	if (result.hasErrors()) {
            return "booksnew.jsp";
        } else {
        	newBook.setUser(loggedInUser);
            this.bookServ.create(newBook);
            return "redirect:/books";
        }
    }
	
}
