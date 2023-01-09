package dev.ranieri.datautils;

import com.uni.daos.UserDAO;
import com.uni.datautils.ConnectionUtil;
import com.uni.entities.ImUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConnectionTest {

    @BeforeEach
    public void populateDatabase() {
        ConnectionUtil.populateH2Database(ConnectionUtil.getConnection());
    }

    @AfterEach
    public void clearDatabase() {
        ConnectionUtil.clearH2Database(ConnectionUtil.getConnection());
    }

    @Test
    void connects_to_database(){
        System.out.println(ConnectionUtil.getConnection());
    }

    @Test
    void sample(){
        UserDAO userDao = UserDAO.getSingleton();
        ImUser user = userDao.getByUsername("gatorFan99");
        System.out.println(user);
    }
}
