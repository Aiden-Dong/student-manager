package org.university.stm.start.common;

import com.google.common.io.BaseEncoding;

/**
 * <pre>
 *     Base 64 Util
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-03-03
 */
public class BaseUtil {
    /**
     * <pre>
     *     base64 编码工具
     * </pre>
     *
     * @param str 编码字符串
     */
    public static String base64Encode(String str){
        byte[] data = str.getBytes();
        return BaseEncoding.base64().encode(data, 0, data.length);
    }

    /**
     * <pre>
     *     base64 解码工具
     * </pre>
     *
     * @param
     */
    public static String base64Decode(String str){
        return new String(BaseEncoding.base64().decode(str));
    }

}
