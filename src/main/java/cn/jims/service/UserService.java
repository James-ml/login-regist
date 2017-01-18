package cn.jims.service;

import cn.jims.dao.UserDao;
import cn.jims.entity.User;
import exception.UserException;


/**
 * Created by Jims on 2017/1/18.
 */
public class UserService {
    private UserDao userDao = new UserDao();
    /*
    注册
     */
    public void regist(User user) throws UserException {
        User _user = userDao.findByUsername(user.getUsername());
        if (_user!=null){
            throw new UserException("用户名"+user.getUsername()+",已被注册！");
        }
        userDao.add(user);
    }
    /*
    登录
     */
    public User login(User user) throws UserException{
        //使用form中的username进行查询，得到user对象
        User user1 = userDao.findByUsername(user.getUsername());
        //如果返回null，说明用户名不存在，抛出异常信息，为“用户名不存在”
        if (user1==null){
            throw new UserException("用户名不存在！");
        }
        //比较user的password和form的password，如果不同，抛出异常，异常信息为“密码错误”
        if (!user1.getPassword().equals(user.getPassword())){
            throw new UserException("密码错误！");
        }
        //返回数据库查出来的user，而不是form，因为form中只有用户名和密码，而user是多有用户信息
        return user1;
    }
}
