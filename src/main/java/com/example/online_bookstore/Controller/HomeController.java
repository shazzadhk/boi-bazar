package com.example.online_bookstore.Controller;

import com.example.online_bookstore.Dao.BookDao;
import com.example.online_bookstore.Dao.ShoppingCartDao;
import com.example.online_bookstore.Dao.UserDao;
import com.example.online_bookstore.Model.Books;
import com.example.online_bookstore.Model.Role;
import com.example.online_bookstore.Model.ShoppingCart;
import com.example.online_bookstore.Model.Users;
import com.example.online_bookstore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @GetMapping(value = {"/home","/"})
    public String home(Model model){
        List<Books> books = bookDao.findAllByLimit();
        model.addAttribute("books",books);
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration1(Users users){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid Users users, Errors errors,Model model){

        if (errors.hasErrors()){
            return "registration";
        }

//        Users users1 = new Users();
//        users1.setFirstName(users.getFirstName());
//        users1.setLastName(users.getLastName());
//        users1.setEmail(users.getEmail());
//        users1.setUserName(users.getUserName());
//        users1.setEnabled(true);
//        users1.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
//        users1.setPhone(users.getPhone());

        Role role = new Role();
        role.setRoleName("ROLE_USER");


        List<Role> roles = new ArrayList<>();
        roles.add(role);

        users.setRoles(roles);

        role.setUser(users);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCartDao.save(shoppingCart);
        users.setShoppingCart(shoppingCart);

        userDao.save(users);

        model.addAttribute("successMessage",true);
        return "registration";
    }

    @GetMapping("/reset-password")
    public String reset_password(){
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String reset_password2(HttpServletRequest request){
        String userName = request.getParameter("username");
        String pass = request.getParameter("new_password");
        Users users = userDao.findByUserName(userName);
        if (users != null){
            users.setPassword(pass);
            userDao.save(users);
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String my_account(Principal principal, Model model){

        Users user = userDao.findByUserName(principal.getName());
        model.addAttribute("users",user);
        return "my-profile";
    }

    @PostMapping("/profile")
    public String my_account2(@Valid Users user, BindingResult result){

        if (result.hasErrors())
            return "my-profile";

        userDao.save(user);
        return "redirect:/home";
    }

    @GetMapping("/save-book")
    public void save_book(){
        bookService.saveBookData();
    }

    @RequestMapping("/book_details")
    public String bookDetails(@RequestParam("book_id")int id, Model model){

        Books book = bookDao.findById(id).get();
        model.addAttribute("book",book);

        return "book-details";
    }

    @RequestMapping("/books")
    public String allBooks(Model model,@RequestParam(value = "category") String category){

        List<Books> books = bookDao.findAll();

        model.addAttribute("books",books);

        return "all-books";
    }

    @RequestMapping("/paginated-books")
    public String paginatedBooks(Model model,@RequestParam(value = "pageNo") int pageNo){

        int pageSize = 3;
        Page<Books> paginatedBooksList = bookService.findPaginated(pageNo,pageSize);
        List<Books> listOfBooks = paginatedBooksList.getContent();

        model.addAttribute("books",listOfBooks);
        model.addAttribute("current_page",pageNo);
        model.addAttribute("total_pages",paginatedBooksList.getTotalPages());
        return "all-books";
    }

}
