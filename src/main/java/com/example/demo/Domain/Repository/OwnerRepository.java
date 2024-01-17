package com.example.demo.Domain.Repository;

import com.example.demo.Domain.Entity.Hotel;
import com.example.demo.Domain.Entity.Owner;
import com.example.demo.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,String> {



}
