package org.qyl.ant;

import org.qyl.ant.core.HttpServer;

/**
 * Created by qinyuanlong on 2018/5/19.
 */
public class Ant {

    public static void main(String[] args) throws Exception{
        HttpServer httpServer = new HttpServer();
        httpServer.start();
    }

}
