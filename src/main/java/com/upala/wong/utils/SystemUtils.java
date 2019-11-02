package com.upala.wong.utils;

/********************************
 *  @program image
 *  @author upala
 *  @version 0.0.1
 *  @since 2019-11-01 14:57
 *  @description 系统信息
 ********************************/
public class SystemUtils {

    /**
     * 获取系统类别
     * @return 返回系统信息
     */
    private String getSystemInfo() {
        return System.getProperties().getProperty("os.name").toLowerCase();
    }

    /**
     * 获取图片访问的路径
     * @return 返回路径
     */
    public String getImagePath() {
        // 存放图片的路径
        String imgPath = null;
        String systemInfo = getSystemInfo();
        boolean window7 = systemInfo.equals("window 7");
        boolean window8 = systemInfo.equals("window 8");
        boolean window10 = systemInfo.equals("window 10");
        boolean linux = systemInfo.equals("linux");
        if (window7|window8|window10) {
            imgPath = "D:/images/server";
        }
        if (linux) {
            imgPath = "/data01/images/server";
        }
        return imgPath;
    }

}
