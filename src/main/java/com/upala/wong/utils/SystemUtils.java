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
    public static String getSystemInfo() {
        return System.getProperties().getProperty("os.name").toLowerCase();
    }

	/**
	 * 判断是否是window
	 * @return 返回值 true-是
	 * 				 false-否
	 */
	public static boolean isWindow() {
		String systemInfo = getSystemInfo();
		boolean window7 = systemInfo.equals("window 7");
		boolean window8 = systemInfo.equals("window 8");
		boolean window10 = systemInfo.equals("window 10");
		return window7|window8|window10;
	}

	/**
	 * 判断是否是linux
	 * @return 返回值 true-是
	 *    			 false-否
	 */
	public static boolean isLinux() {
		String systemInfo = getSystemInfo();
		return systemInfo.equals("linux");
	}

    /**
     * 获取图片访问的路径
     * @return 返回路径
     */
    public static String getImagePath() {
        // 存放图片的路径
        String imgPath = null;
        if (isWindow()) {
            imgPath = "D:/images/server";
        }
        if (isLinux()) {
            imgPath = "/data01/images/server";
        }
        return imgPath;
    }

}
