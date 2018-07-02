package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClass {
    public static void main(String[] args) {
        E e = new E();
        e.Fa();
        getDate("yyyy-MM-dd HH:mm:ss");

        dd();


        System.out.println(1%4);

        System.out.println(2%4);

        System.out.println(3%4);

        System.out.println(4%4);

        System.out.println(5%4);
    }


    static class F {
        public void Fa() {
            System.out.print("我是父亲");
        }
    }

    static class E extends F {
        @Override
        public void Fa() {
            super.Fa();
            System.out.print("我是儿子");
        }
    }

    //"yyyy-MM-dd HH:mm:ss"
    public static void getDate(String type) {
        long timeStamp = 1527326758 * 1000;//直接是时间戳
        //  long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
        SimpleDateFormat sdf = new SimpleDateFormat(type);//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
        System.out.println(sd);//打印出你要的时间
    }

    public static String dd() {
        String data = "";
        try {
            //创建一个URL实例
            URL url = new URL("http://www.baidu.com");
            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");

                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                data = br.readLine();//读取数据

                while (data != null) {//循环读取数据
                    data = br.readLine();
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return data;
    }


}
