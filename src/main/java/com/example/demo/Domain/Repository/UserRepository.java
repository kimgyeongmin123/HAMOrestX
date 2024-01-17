package com.example.demo.Domain.Repository;


import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    //회원정보
    @Query("SELECT U FROM User U WHERE U.role = 'ROLE_USER'")
    List<User> userList();

    //사업자정보
    @Query("SELECT U FROM User U WHERE U.role = 'ROLE_MEMBER'")
    List<User> ownerList();

    //아이디로 회원조회
    @Query("SELECT U FROM User U WHERE U.userId = :userId")
    User getUserOne(@Param("userId") String userId);

}