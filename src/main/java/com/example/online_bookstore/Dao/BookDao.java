package com.example.online_bookstore.Dao;

import com.example.online_bookstore.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Books,Integer> {

//    @Query("select p from Books p where p.category = ?1")
//    List<Books> findByCategory(String category);

    @Query(value = "select * from books order by id LIMIT 4",nativeQuery = true)
    List<Books> findAllByLimit();
}
