package com.group6.careu.repository;

import com.group6.careu.entity.User;;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

    public Long countById(Integer id);

    @Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
    @Modifying
    public void updateEnabledStatus(Integer id, boolean enabled);

    @Query(value = "SELECT * from users u where u.doctor_id=:doctor_id", nativeQuery = true)
    public User getUserByDoctorId(@Param("doctor_id") int doctor_id);

    @Query(value = "SELECT * from users u where u.role=:role", nativeQuery = true)
    public List<User> getAllDoctor(@Param("role") String role);

    @Query(value = "SELECT * from users u where u.first_name like %:keyword%" +
            " or u.last_name like %:keyword% and u.role=:role", nativeQuery = true)
    public List<User> getFilteredDoctors(@Param("role") String role, @Param("keyword") String keyword);
}
