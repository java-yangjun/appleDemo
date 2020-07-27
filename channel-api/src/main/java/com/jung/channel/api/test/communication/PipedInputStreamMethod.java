package com.jung.channel.api.test.communication;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Description TODO
 * Author yangjun
 * Date 2020/6/22 3:38 下午
 **/
public class PipedInputStreamMethod {

    public static void main(String[] args) throws IOException {

        PipedInputStream ip1 = new PipedInputStream();
        PipedInputStream ip2 = new PipedInputStream();

        PipedOutputStream op1 = new PipedOutputStream();
        PipedOutputStream op2 = new PipedOutputStream();

        ip1.connect(op2);
        ip2.connect(op1);

        String msg = "your turn";

        new Thread(() ->{
            byte[] bytes = new byte[9];
            for (int i = 1; i < 27; i++) {
                try {
                    ip1.read(bytes);
                    if(msg.equals(new String(bytes))){
                        System.out.println(i);
                    }
                    op1.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() ->{
            byte[] bytes = new byte[9];
            for (int i = 1; i < 27; i++) {
                try {
                    op2.write(msg.getBytes());
                    ip2.read(bytes);
                    if(msg.equals(new String(bytes))){
                        System.out.println((char) (i+96));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
