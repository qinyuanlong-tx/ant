package org.qyl.ant.init;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by qinyuanlong on 2018/7/20.
 */
public class InitAnt {

    private void loadClass(){}

    private void loadLibs() throws NoSuchMethodException, MalformedURLException{
        // todo: read lib path from configure file
        File libPath = new File("/Users/qinyuanlong/.m2/repository/joda-time/joda-time/2.10/");

        // get all jars file and zip file
        File[] jarFiles = libPath.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".jar") || name.endsWith(".zip");
            }
        });

        if (jarFiles != null) {
            // 从URLClassLoader类中获取类所在文件夹的方法
            // 对于jar文件，可以理解为一个存放class文件的文件夹
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            boolean accessible = method.isAccessible();     // 获取方法的访问权限
            try {
                if (accessible == false) {
                    method.setAccessible(true);     // 设置方法的访问权限
                }
                // 获取系统类加载器
                URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                for (File file : jarFiles) {
                    URL url = file.toURI().toURL();
                    try {
                        method.invoke(classLoader, url);
                    } catch (Exception e) {
                        System.out.println("error...");
                    }
                }
            } finally {
                method.setAccessible(accessible);
            }
        }

    }

}
