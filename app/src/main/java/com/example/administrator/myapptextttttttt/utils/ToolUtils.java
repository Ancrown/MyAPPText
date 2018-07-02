package com.example.administrator.myapptextttttttt.utils;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.views.photoview.NewsModifyAvatarPopupWindow;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */

public class ToolUtils {

    private static Activity activity;
    public static Toast mToast;

    /**
     * 无延时的Toast
     *
     * @param mContext
     * @param msg
     */
    public static void showToast(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 关闭软键盘
     */
    public static void closeKeyboard(Activity activity) {
        ToolUtils.activity = activity;
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 获取屏幕高
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    /**
     * 获取屏幕宽
     */
    public static int getScreenWith(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /***
     * 改变某些字段的颜色
     *
     * @param text
     * @param change
     * @param color
     * @param textView
     */
    public static void toStringChangeColor(String text, String change, String color, TextView textView) {
        if (change != null) {
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            text += " ";
            if (text.indexOf(change) != -1) {
                String[] s = text.split(change);
                int temp = 0;
                for (int i = 0; i < s.length; i++) {
                    if (i != s.length - 1) {
                        if (i == 0) {
                            temp += s[i].length();
                        } else {
                            temp += s[i].length() + change.length();
                        }
                    }
                    style.setSpan(
                            new ForegroundColorSpan(Color.parseColor(color)),
                            temp,
                            temp + change.length(),
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE); // 设置指定位置文字的颜色
                    textView.setText(style);
                }
            }
        }
    }

    /***
     * 改变某些文字的颜色
     *
     * @param text
     * @param change
     * @param color
     * @param textView
     */
    public static void toStringChangeColor(String text, String[] change, String color, TextView textView) {
        if (change != null) {
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            text += " ";
            for (int j = 0; j < change.length; j++) {
                if (change.length != -1) {
                    String[] s = text.split(change[j]);
                    int temp = 0;
                    for (int i = 0; i < s.length; i++) {
                        if (i != s.length - 1) {
                            if (i == 0) {
                                temp += s[i].length();
                            } else {
                                temp += s[i].length() + change[j].length();
                            }
                        }
                        style.setSpan(
                                new ForegroundColorSpan(Color.parseColor(color)),
                                temp,
                                temp + change[j].length(),
                                Spannable.SPAN_EXCLUSIVE_INCLUSIVE); // 设置指定位置文字的颜色
                        textView.setText(style);
                    }
                }
            }

        }
    }

    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     *
     * @param activity
     */
    public static void fitsSystemWindows(Activity activity) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            //布局预留状态栏高度的 padding
            parentView.setFitsSystemWindows(true);
            if (parentView instanceof DrawerLayout) {
                DrawerLayout drawer = (DrawerLayout) parentView;
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                drawer.setClipToPadding(false);
            }
        }
    }


    //弹出新的popupWindow
    //selectModel =  -1剪切  0 单选  1-9 多选
    public static void getNewsPopupWindow(View v, Activity activity, int selectModel) {
        ToolUtils.activity = activity;
       NewsModifyAvatarPopupWindow modifyAvatarPopupWindow = new NewsModifyAvatarPopupWindow(activity, 1, selectModel);
//        ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
//        modifyAvatarPopupWindow.setSelectModel(selectModel);
//        modifyAvatarPopupWindow.setBackgroundDrawable(colorDrawable);
//        modifyAvatarPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        modifyAvatarPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        modifyAvatarPopupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
    }

    /**
     * 查看应用通知栏是否打开
     * 项目中用到日程提醒功能，
     * 如果应用的通知栏没有打开，则需要提示用户前去打开通知栏,判断通知栏是否打开代码如下
     *
     * @param context
     */
    public static boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
     /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
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

    public static String[] returnImageUrlsFromHtml() {
        List<String> imageSrcList = new ArrayList<String>();
        String htmlCode = null;

        htmlCode = returnExampleHtml();

        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            Log.e("imageSrcList", "资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }

    public static String returnExampleHtml() {

        return "<div class=\"contentbox\"><div class=\"article-title article-title-share\">穆里尼奥的欧联实验再次证明: 博格巴, 你在曼联只能打后腰!</div>\n" +
                "<div class=\"article-info article-info-share\">\n" +
                "    <div class=\"article-info-share-media\">\n" +
                "        <span class=\"article-info-photo\"><img width=\"20\" height=\"20\" src=\"http://image.uc.cn/o/wemedia/s/upload/2017/17010414216e9e01ac2d3d39ac6b9c81c4eeef70c8x200x200x30.png;,3,jpegx;3,40x\"></span>\n" +
                "        <span class=\"article-wmname\">体育玩玩玩</span>\n" +
                "    </div>\n" +
                "    <div class=\"article-info-share-date\">\n" +
                "        \n" +
                "        <span class=\"uc_brand_tip\">UC订阅号 刘  晓  伟</span>\n" +
                "        \n" +
                "        \n" +
                "        <span class=\"article-date\">02-17 13:19</span>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"article-content\">  <p class=\"imgbox\"><img data-original=\"http://image.uc.cn/s/wemedia/s/2017/d2cda0ec5eb51cc39ff00ea6be5ae0b0x500x360x29.jpeg\" src=\"http://image.uc.cn/o/wemedia/s/2017/d2cda0ec5eb51cc39ff00ea6be5ae0b0x500x360x29.jpeg;,3,jpegx;3,700x.jpg\" img_width=\"500\" img_height=\"360\" uploaded=\"1\" data-infoed=\"1\" data-width=\"500\" data-height=\"360\" data-format=\"JPEG\" data-size=\"29406\" class=\"image-loaded\" heavypress=\"http://image.uc.cn/o/wemedia/s/2017/d2cda0ec5eb51cc39ff00ea6be5ae0b0x500x360x29.jpeg;,3,jpegx;3,700x.jpg\" style=\"width: 500px !important; height: 360px !important;\">昨夜，当看到首发的时候，相信球迷们和小编的心理状态一样——<span>穆里尼奥还是不死心，他还要试试博格巴打前腰曼联会怎么样！</span>姆希塔良轮休，<span>博格巴与伊布的默契</span>，也许是这个实验的动因。<br><img data-original=\"http://image.uc.cn/s/wemedia/s/2017/07425ddb1aac2573385bb6c56989d9f1x589x300x25.jpeg\" src=\"http://image.uc.cn/o/wemedia/s/2017/07425ddb1aac2573385bb6c56989d9f1x589x300x25.jpeg;,3,jpegx;3,700x.jpg\" img_width=\"589\" img_height=\"300\" uploaded=\"1\" data-infoed=\"1\" data-width=\"589\" data-height=\"300\" data-format=\"JPEG\" data-size=\"24809\" class=\"\" heavypress=\"http://image.uc.cn/o/wemedia/s/2017/07425ddb1aac2573385bb6c56989d9f1x589x300x25.jpeg;,3,jpegx;3,700x.jpg\" style=\"width: 589px !important; height: 300px !important;\"></p><p class=\"imgbox\">事实证明，博格巴上提占据了中前卫位置，<span>造成的是曼联进攻上的慢速，攻击线上马塔、马夏尔、拉什福德甚至姆希塔良等人得牺牲位置配合这一变化</span>，并不是否认博格巴的攻击能力，也不是博格巴在这个位置一无是处，而是<span>他个人喜拿球爱摆脱的节奏与曼联前场非人快即球快的最佳进攻套路有些格格不入</span>，凡事就怕比，有了英超赛场的博格巴后撤后曼联3场零封完胜，再看看昨夜对阵圣埃蒂安的上半场，<span>想必睿智如穆里尼奥，终将放弃这个实验</span>。<br><img data-original=\"http://image.uc.cn/s/wemedia/s/2017/7fb1dc97fefc17622990406f5167d50ax590x350x26.jpeg\" src=\"http://image.uc.cn/o/wemedia/s/2017/7fb1dc97fefc17622990406f5167d50ax590x350x26.jpeg;,3,jpegx;3,700x.jpg\" img_width=\"590\" img_height=\"350\" uploaded=\"1\" data-infoed=\"1\" data-width=\"590\" data-height=\"350\" data-format=\"JPEG\" data-size=\"26369\" class=\"\" heavypress=\"http://image.uc.cn/o/wemedia/s/2017/7fb1dc97fefc17622990406f5167d50ax590x350x26.jpeg;,3,jpegx;3,700x.jpg\" style=\"width: 590px !important; height: 350px !important;\"></p><p class=\"imgbox\">下半场，<span>穆里尼奥果断放弃实验，费莱尼下场为博格巴腾出一个后腰位置，马塔回到前腰，林加德去右路，曼联水银泻地的进攻重启</span>，而博格巴的后撤明显<span>增加了后场出球的稳定性和质量，且并没有影响博格巴个人参与到曼联的进攻中，这种前场快速倒球转移组织，加上博格巴的后插上的进攻，让曼联完全扭转并控制了场上局面</span>，老特拉福德球迷等待的，只是进球的早晚，到底谁会进球。<br><img data-original=\"http://image.uc.cn/s/wemedia/s/2017/3ca682606e6392166391b2aa1c75cc41x481x300x24.jpeg\" src=\"http://image.uc.cn/o/wemedia/s/2017/3ca682606e6392166391b2aa1c75cc41x481x300x24.jpeg;,3,jpegx;3,700x.jpg\" img_width=\"481\" img_height=\"300\" uploaded=\"1\" data-infoed=\"1\" data-width=\"481\" data-height=\"300\" data-format=\"JPEG\" data-size=\"24201\" class=\"\" heavypress=\"http://image.uc.cn/o/wemedia/s/2017/3ca682606e6392166391b2aa1c75cc41x481x300x24.jpeg;,3,jpegx;3,700x.jpg\" style=\"width: 481px !important; height: 300px !important;\"></p><p class=\"imgbox\">或许，<span>这就是穆里尼奥对博格巴前腰位置的最后一次实验了</span>，赛季后半段剩余的比赛，相信穆里尼奥宁愿再使用费莱尼去充当前场第二高点，也不会让费莱尼再去搭档埃雷拉而把博格巴推上去了，<span>毕竟，博格巴前腰的曼联和博格巴后腰的曼联，怎么看都差了半个档次。</span><br><img data-original=\"http://image.uc.cn/s/wemedia/s/2017/af5007d48496005f34f3a586e1d495b9x299x300x8.jpeg\" src=\"http://image.uc.cn/o/wemedia/s/2017/af5007d48496005f34f3a586e1d495b9x299x300x8.jpeg;,3,jpegx;3,700x.jpg\" img_width=\"299\" img_height=\"300\" uploaded=\"1\" data-infoed=\"1\" data-width=\"299\" data-height=\"300\" data-format=\"JPEG\" data-size=\"7381\" class=\"\" heavypress=\"http://image.uc.cn/o/wemedia/s/2017/af5007d48496005f34f3a586e1d495b9x299x300x8.jpeg;,3,jpegx;3,700x.jpg\" style=\"width: 299px !important; height: 300px !important;\">\u200B</p>  <div class=\"weixin\"></div>\n" +
                "</div></div>";


    }

    /**
     * @param aUrl    网址
     * @param aEncode 编码
     * @return 返回的HTML代码
     * @throws Exception 对外抛出异常
     */
    public static String getHTML(String aUrl, String aEncode) throws Exception {

        URL url = new URL(aUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            String htmlStr = new String(outStream.toByteArray(), aEncode);
            inputStream.close();
            outStream.close();
            return htmlStr;
        }
        return null;
    }

}
