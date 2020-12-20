package com.example.online_bookstore.Controller;

import com.example.online_bookstore.Dao.*;
import com.example.online_bookstore.Model.*;
import com.example.online_bookstore.Service.BookService;
import com.example.online_bookstore.Service.OrderServiceImp;
import com.example.online_bookstore.Service.ShoppingCartServiceImp;
import com.example.online_bookstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Book;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderServiceImp orderServiceImp;

    @Autowired
    private ShoppingCartServiceImp shoppingCartServiceImp;

    @RequestMapping("/cart")
    public String shoppingCart(Model model,Principal principal){

        Users users = userService.findUsers(principal);
        ShoppingCart shoppingCart = users.getShoppingCart();

        Set<CartItem> cartItemList = shoppingCart.getCartItemList();

        double totalSubPrice = 0.0;

        for (CartItem cartItem:cartItemList) {
            totalSubPrice += cartItem.getSubTotal();
        }

        double grandTotal = totalSubPrice + 2.0;

        shoppingCart.setGrandTotal(grandTotal);
        shoppingCartDao.save(shoppingCart);

        model.addAttribute("totalSubPrice",totalSubPrice);
        model.addAttribute("grandTotal",grandTotal);

        if (cartItemList.size() == 0){
            model.addAttribute("zero_item",true);
        }
        else {
            model.addAttribute("zero_item",false);
        }

        model.addAttribute("cartItemList",cartItemList);
        model.addAttribute("shoppingCart",shoppingCart);

        return "cart";
    }

    @RequestMapping("/addToCart")
    public String addBookToCartItem(@RequestParam("book_id")int book_id ,Principal principal){

        Books book = bookService.findBook(book_id).get();

        Users users = userService.findUsers(principal);
        ShoppingCart shoppingCart = users.getShoppingCart();

        boolean isBookAvailable = bookService.findBookAvailable(users,book,shoppingCart);

        if (isBookAvailable){

            Set<CartItem> cartItems= shoppingCart.getCartItemList();
            for (CartItem c:cartItems) {

                if (c.getBooks().getId() == book_id){
                    c.setQuantity(c.getQuantity() + 1);
                    c.setSubTotal(c.getSubTotal() + (c.getSubTotal()));
                    cartItemDao.save(c);
                    return "redirect:/cart";
                }
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setBooks(book);
        cartItem.setQuantity(1);
        cartItem.setSubTotal(book.getOurPrice());
        cartItem.setShoppingCart(shoppingCart);
        cartItemDao.save(cartItem);

        return "redirect:/cart";
    }

    @RequestMapping("/deleteItem")
    public String deleteItemFromCart(@RequestParam("cartItemId")Integer cartItemId){

        cartItemDao.deleteById(cartItemId);
        return "redirect:/cart";
    }

    @PostMapping("/updateCartItem")
    public String updateCartItem(HttpServletRequest request, Model model, Principal principal){
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int available = Integer.parseInt(request.getParameter("available"));
        if (quantity > available){
//            model.addAttribute("outOfStock",true);
            return "redirect:/cart";
        }
        Integer cart_item_id = Integer.parseInt(request.getParameter("cartItemId"));
        if (cart_item_id != 0) {
            CartItem cartItem = cartItemDao.findById(cart_item_id).get();
            Books book = cartItem.getBooks();
            double price = book.getOurPrice();
            cartItem.setQuantity(quantity);
            cartItem.setSubTotal(price * quantity);
            cartItemDao.save(cartItem);
        }

        return "redirect:/cart";
    }

    @RequestMapping("/checkout")
    public String checkout(ShippingAddress shippingAddress,Model model,Principal principal){

        Users users = userService.findUsers(principal);
        ShoppingCart shoppingCart = users.getShoppingCart();

        if (shoppingCart.getCartItemList().isEmpty()){
            model.addAttribute("message",true);
            return "checkout";
        }

        List<CartItem> cartItemList = cartItemDao.findByShoppingCart(shoppingCart);
        model.addAttribute("grandTotal",shoppingCart.getGrandTotal());
        model.addAttribute("cartItemList",cartItemList);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkoutPost(@Valid ShippingAddress shippingAddress, BindingResult result,
                               Model model, Principal principal){

        if (result.hasErrors()){
            return "checkout";
        }

        Users users = userService.findUsers(principal);

        ShoppingCart shoppingCart = users.getShoppingCart();

        Orders orders = orderServiceImp.createOrder(shoppingCart,shippingAddress,users);

        shoppingCartServiceImp.clearShoppingCart(shoppingCart,users);

        LocalDate today = LocalDate.now();
        LocalDate estimatedDeliveryDate = today.plusDays(7);

        model.addAttribute("estimatedDeliveryDate",estimatedDeliveryDate);

        return "orderSubmittedPage";

    }

    @GetMapping("/order_list")
    public String orderList(Model model,Principal principal){

        Users users = userService.findUsers(principal);
        List<Orders> orderList = orderDao.findByUsers(users);
        model.addAttribute("orderList",orderList);
        boolean isZero = false;
        if (orderList.size() == 0)
            isZero = true;

        model.addAttribute("isZero",isZero);
        return "viewOrderList";
    }
}
