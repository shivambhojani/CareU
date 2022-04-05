package com.group6.careu;

import com.group6.careu.entity.User;
import com.group6.careu.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithDoctorRole() {
        User userNamHM = new User("Doctor", "doctor 1", "male", "236541789","test@mail.com","12344", true, "doctor");
        User savedUser = repo.save(userNamHM);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithAdminRole() {
        User userNamHM = new User("admin", "admin 1", "female", "7856321479","test@mail.com","123654", true, "admin");
        User savedUser = repo.save(userNamHM);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithPatientRole() {
        User userNamHM = new User("Patient", "patient 1", "male", "9017896325","test2@mail.com","669854", true, "patient");
        User savedUser = repo.save(userNamHM);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
       Iterable<User> listUsers = repo.findAll();
       listUsers.forEach(user-> System.out.println(user));
    }
}
