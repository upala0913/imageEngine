package com.upala.wong.utils;

import lombok.extern.log4j.Log4j2;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/********************************
 *  @program imageEngine
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-05 15:47
 *  @description
 ********************************/

@Log4j2
public class WeatherUtils {

    /**
     * 获取城市
     * @return 返回值
     */
    public static String get(String cityID) {
        String key = FinalVarUtil.key;
        String url = null;
        if (StringUtils.isEmpty(cityID)) {
            url = "http://v.juhe.cn/weather/citys?key=" + key;
        } else {
            url = "http://v.juhe.cn/weather/index?ID="+cityID+"&key="+key;
        }
        return post(url, null);
    }

    private static String post(String path, Map<String, String> param) {
        HttpURLConnection conn = null;
        try {
            URL u = new URL(path);
            conn = (HttpURLConnection) u.openConnection();
            StringBuffer sb = null;
            if (param != null) {// 如果请求参数不为空
                sb = new StringBuffer();
                conn.setDoOutput(true);
                // 设定post方法,默认get
                conn.setRequestMethod("POST");
                // 获得输出流
                OutputStream out = conn.getOutputStream();
                // 对输出流封装成高级输出流
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                // 将参数封装成键值对的形式
                for (Map.Entry<String, String> s : param.entrySet()) {
                    sb.append(s.getKey()).append("=").append(s.getValue()).append("&");
                }
                // 将参数通过输出流写入
                writer.write(sb.deleteCharAt(sb.toString().length() - 1).toString());
                writer.close();// 一定要关闭,不然可能出现参数不全的错误
                sb = null;
            }
            conn.connect();// 建立连接
            sb = new StringBuffer();
            // 获取连接状态码
            int recode = conn.getResponseCode();
            BufferedReader reader = null;
            if (recode == 200) {
                // Returns an input stream that reads from this open connection
                // 从连接中获取输入流
                InputStream in = conn.getInputStream();
                // 对输入流进行封装
                reader = new BufferedReader(new InputStreamReader(in));
                String str = null;
                sb = new StringBuffer();
                // 从输入流中读取数据
                while ((str = reader.readLine()) != null) {
                    sb.append(str).append(System.getProperty("line.separator"));
                }
                // 关闭输入流
                reader.close();
                if (sb.toString().length() == 0) {
                    return null;
                }
                return sb.toString().substring(0,
                        sb.toString().length() - System.getProperty("line.separator").length());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null)// 关闭连接
                conn.disconnect();
        }
        return null;
    }
}
