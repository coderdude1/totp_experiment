package com.dood.app.dao;

import com.dood.app.entities.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.junit.Assert.*;

//@ContextConfiguration(locations = { "/application-context.xml", "/test-application-context.xml" })
@ContextConfiguration(locations = { "/test-application-context.xml" })
@TransactionConfiguration(defaultRollback=true)
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void testCreate() {
        LOG.info("IN test");
        assertNotNull(userDao);

        User user1 = createUser("fname1", "lname1", "email1@blah.com.dood.web.config");
        assertNull(user1.getId());
        userDao.save(user1);
        assertNotNull(user1.getId());
        User user2 = createUser("fname2", "lname2", "email2@blah.com.dood.web.config");
        assertNull(user2.getId());
        userDao.save(user2);
        assertNotNull(user1.getId());
    }

    @Test
    public void testGet() {
        userDao.save(createUser("one", "one", "one.com.dood.web.config"));
        userDao.save(createUser("two", "one", "two.com.dood.web.config"));
        List<User> users = userDao.findAll();
        assertNotNull(users);
        assertEquals("Size", 3, users.size());
        long count = userDao.count();
        assertEquals("Count test", 3, count);
    }

    private User createUser(String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }
}