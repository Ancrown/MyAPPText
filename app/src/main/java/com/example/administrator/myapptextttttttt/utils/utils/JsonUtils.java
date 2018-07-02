package com.example.administrator.myapptextttttttt.utils.utils;

import com.example.administrator.myapptextttttttt.utils.AppUtils;

import java.io.InputStream;

/**
 * @author lius
 * @date 创建时间：2017/5/31 11:15
 * 描述:
 */

public class JsonUtils {
    public static String readJson(String jsonFile) {
        InputStream inputStream = FileUtils.openAssetFile(AppUtils.getAppContext(), jsonFile);
        String jsonStr = IOUtils.streamToString(inputStream);
        return jsonStr;
    }
}
