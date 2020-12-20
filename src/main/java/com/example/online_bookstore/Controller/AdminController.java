package com.example.online_bookstore.Controller;

import com.example.online_bookstore.Dao.BookDao;
import com.example.online_bookstore.Dao.OrderDao;
import com.example.online_bookstore.Dao.UserDao;
import com.example.online_bookstore.Model.Books;
import com.example.online_bookstore.Model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        List<Books> booksList = bookDao.findAll();
        model.addAttribute("bookList",booksList);
        model.addAttribute("totalUsers",userDao.count());
        model.addAttribute("totalBooks",bookDao.count());
        model.addAttribute("totalOrders",orderDao.count());
        return "admin/dashboard";
    }

    @GetMapping("/addBookAdmin")
    public String adminAddBook(Books books){
        return "admin/admin_add_book";
    }

    @PostMapping("/addBookAdmin")
    public String adminAddBook2(@Valid Books books, BindingResult result,Model model){

        if (result.hasErrors())
            return "admin/admin_add_book";


        books.setStatus(true);
        bookDao.save(books);

        model.addAttribute("successMsg",true);

        return "admin/admin_add_book";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("book_id") int book_id,Model model){

        Books book = bookDao.findById(book_id).get();
        model.addAttribute("books",book);
        return "admin/admin_add_book";
    }

    @RequestMapping("/deleteBook")
    public String deleteBooks(@RequestParam("book_id")int book_id){

        bookDao.deleteById(book_id);
        return "redirect:/dashboard";
    }

    @GetMapping("/viewAllOrders")
    public String viewAllOrders(Model model){

        List<Orders> ordersList = (List<Orders>) orderDao.findAll();

        model.addAttribute("orderList",ordersList);
        return "admin/viewAdminOrderList";

    }
}
