package test.dao;

import cn.jims.dao.UserDao;
import cn.jims.entity.User;
import org.junit.Test;

/**
 * Created by Jims on 2017/1/18.
 */
public class UserDaoTest {
    @Test
    public void testFindByUsername(){
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername("赵柳");
        System.out.println(user);
    }
    @Test
    public void testAdd(){
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("赵六");
        user.setPassword("456");
        userDao.add(user);
    }
}
