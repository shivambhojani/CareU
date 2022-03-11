package com.group6.careu.repository;
import com.group6.careu.entity.UserDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserDocumentRepository extends CrudRepository<UserDocument, Integer> {
    @Query("SELECT ud FROM UserDocument ud WHERE ud.userId = :userId")
    public List<UserDocument> gerDocumentsByUserId(@Param("userId") Integer userId);
}
