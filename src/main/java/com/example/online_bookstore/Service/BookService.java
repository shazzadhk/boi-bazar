package com.example.online_bookstore.Service;


import com.example.online_bookstore.Dao.BookDao;
import com.example.online_bookstore.Model.Books;
import com.example.online_bookstore.Model.CartItem;
import com.example.online_bookstore.Model.ShoppingCart;
import com.example.online_bookstore.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    String line = "";
    public void saveBookData(){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/88017/IdeaProjects/online-bookstore/src/main/resources/books.csv"));
            while ((line = bufferedReader.readLine())!=null){
                String [] data = line.split(",");
                Books book = new Books();
                book.setTitle(data[0]);
                book.setAuthor(data[1]);
                book.setFormat(data[2]);
                book.setNumberOfPages(Integer.parseInt(data[3]));
                book.setPublicationDate(data[4]);
                book.setPublisher(data[5]);
                book.setIsbn(data[6]);
                book.setLanguage(data[7]);
                book.setCategory(data[8]);
                book.setListPrice(Double.parseDouble(data[9]));
                book.setOurPrice(Double.parseDouble(data[10]));
                book.setStatus(Boolean.parseBoolean(data[11]));
                book.setInStockNumber(Integer.parseInt(data[12]));
                book.setDescription(data[13]);

                bookDao.save(book);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Books> findBook(int book_id){
        return bookDao.findById(book_id);
    }

    public Page<Books> findPaginated(int pageNo, int pageSize){

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return bookDao.findAll(pageable);
    }

    public boolean findBookAvailable(Users users, Books book, ShoppingCart shoppingCart){

        for (CartItem cartItem:shoppingCart.getCartItemList()) {

            if (cartItem.getBooks().getId() == book.getId())
                return true;
        }

        return false;
    }
}
