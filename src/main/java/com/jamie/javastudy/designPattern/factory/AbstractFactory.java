package com.jamie.javastudy.designPattern.factory;

import java.io.Serializable;
import java.util.Date;

/**
 * @PackageName: com.jamie.javastudy.designPattern.factory
 * @ClassName: AbstractFactory
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 3:10 下午
 */
public class AbstractFactory implements Serializable {
    private static final long serialVersionUID = 753736920165773418L;

    public static void main(String[] args) {
        mysqlFactory mysqlFactory = new mysqlFactory();
        IUser mysqlUser = mysqlFactory.createUser();
        ILogin login = mysqlFactory.createLogin();
        login.loadLogin(2222);
        mysqlUser.getUser(1);

        oracleFactory oracleFactory = new oracleFactory();
        IUser oracleUser = oracleFactory.createUser();
        ILogin factoryLogin = oracleFactory.createLogin();
        factoryLogin.loadLogin(2222);
        oracleUser.getUser(2);
    }

    public static class User {
        private int uid;
        private String uname;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }
    }

    public class Login {
        private int id;
        private Date date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public interface IUser {
        void insert(User user);
        User getUser(int uid);
    }

    public interface ILogin {
        void insert(Login login);
        Login loadLogin(int id);
    }

    public interface sqlFactory {
        IUser createUser();
        ILogin createLogin();
    }

    public static class mysqlUser implements IUser {
        @Override
        public void insert(User user) {
            System.out.println("在mysql中的user表中插入一条元素");
        }

        @Override
        public User getUser(int uid) {
            System.out.println("在mysql中的user表得到id为"+ uid + "的一条数据");
            return null;
        }
    }

    public static class mysqlLogin implements ILogin {
        @Override
        public void insert(Login login) {
            System.out.println("对 MySQL 里的 Login 表插入了一条数据");
        }

        @Override
        public Login loadLogin(int id) {
            System.out.println("通过 uid 在 MySQL 里的 Login 表加载了一条数据, id为" + id);
            return null;
        }
    }

    public static class oracleLogin implements ILogin {
        @Override
        public void insert(Login login) {
            System.out.println("对 oracle 里的 Login 表插入了一条数据");
        }

        @Override
        public Login loadLogin(int id) {
            System.out.println("通过 uid 在 oracle 里的 Login 表加载了一条数据, id为" + id);
            return null;
        }
    }

    public static class oracleUser implements IUser {

        @Override
        public void insert(User user) {
            System.out.println("在oracle中的user表中插入一条元素");
        }
        @Override
        public User getUser(int uid) {
            System.out.println("在oracle中的user表得到id为"+ uid + "的一条数据");
            return null;
        }
    }


    public static class mysqlFactory implements sqlFactory {

        @Override
        public IUser createUser() {
            return new mysqlUser();
        }

        @Override
        public ILogin createLogin() {
            return new mysqlLogin();
        }
    }


    public static class oracleFactory implements sqlFactory {
        @Override
        public IUser createUser() {
            return new oracleUser();
        }

        @Override
        public ILogin createLogin() {
            return new oracleLogin();
        }


    }


}
