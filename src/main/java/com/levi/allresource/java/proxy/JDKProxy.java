package com.levi.allresource.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* �ο�JavaGuide
* */
public class JDKProxy {
    public static void main(String[] args) {
        Class[] interfaces = {UserDao.class};
        UserDaoImpl userDao = new UserDaoImpl();
        // �ӿ�Ҳ����ͨ�� userDao.getClass.getInterfaces()���
        UserDao dao = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        int result = dao.add(1, 3);
        System.out.println(dao.update("999"));
        System.out.println("result=" + result);
    }

}

class UserDaoProxy implements InvocationHandler {
    private Object obj;

    public UserDaoProxy(Object obj) {
        this.obj = obj;
    }

    // ��ǿ���߼�
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("����֮ǰִ��...");
        Object res = method.invoke(obj, args);
        System.out.println("����֮��ִ��...");

        return res;
    }
}
