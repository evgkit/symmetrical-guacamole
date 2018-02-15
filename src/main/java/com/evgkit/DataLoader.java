package com.evgkit;

import com.evgkit.dao.RoleDao;
import com.evgkit.dao.UserDao;
import com.evgkit.domain.Role;
import com.evgkit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role = roleDao.findRoleByName("ROLE_USER");
        if (role == null) {
            role = new Role("ROLE_USER");
            roleDao.save(role);
        }

        User user = userDao.findByUsername("admin");
        if (null == user) {
            user = new User("admin", "password");
            user.setRole(role);
            userDao.save(user);
        }
    }
}
