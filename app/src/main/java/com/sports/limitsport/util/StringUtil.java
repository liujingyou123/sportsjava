package com.sports.limitsport.util;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输入数据check
 */
public class StringUtil {

    private static char[] numbersAndLetters = null;
    private static Object initLock = new Object();
    private static Random randGen = null;

    /**
     * 判断email格式
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        String regularExpression = "^([a-z0-9A-Z]+[-|//.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?//.)+[a-zA-Z]{2,}$";

        if (TextUtils.isEmpty(str)) {
            return false;
        } else if (!str.matches(regularExpression)) {
            return false;
        }
        return true;
    }

    //判断是否为中文
    public static boolean isChinese(String s, int minLength, int maxLength) {
        String regex = "[\u4E00-\u9FA5]+";
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        return s.matches(regex) && (s.length() >= minLength && s.length() <= maxLength);
    }

    //判断是否为中文
    public static boolean isChinese(String s) {
        String regex = "[\u4E00-\u9FA5]+";
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        return s.matches(regex);
    }

    /**
     * 判断是否是URL
     *
     * @param str
     * @return
     */
    public static boolean isURL(String str) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(str);
        boolean isMatch = matcher.matches();
        if (!isMatch) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否是手机号码
     *
     * @param phone
     * @return
     */
    public static boolean isCellPhone(String phone) {
        if (Pattern.matches("^[1][3,4,5,7,8]+\\d{9}$", phone == null ? "" : phone)) {
            return true;
        }
        return false;
    }

    public static boolean checkPassword(String password) {
        if (Pattern.matches("^[0-9a-zA-Z]{6,18}$", password == null ? "" : password)) {
            return true;
        }
        return false;
    }


    /**
     * 产生不重复随机数
     *
     * @param length
     * @return String类型随机数
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Init of pseudo random number generator.
        if (randGen == null) {
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    // Also initialize the numbersAndLetters array
                    numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
                            + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .toCharArray();
                }
            }
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    public static String getMD5(String s) {
        if ((s != null) && (s.length() > 0)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(s.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                return buf.toString().substring(0, 32);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return "md5o";
    }

}
