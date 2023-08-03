package com.loginusers.pdiaz.repository;

import com.loginusers.pdiaz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<User,Integer> {

    default User saveUserWithPhones(User user) {
        return save(user);
    }
}
