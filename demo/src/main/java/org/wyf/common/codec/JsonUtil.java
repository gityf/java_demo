package org.wyf.common.codec;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

/**
 * json编解码工具类
 *
 * @author kyo.ou 2013-7-16
 *
 */
@SuppressWarnings("deprecation")
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        // 为保持对象版本兼容性,忽略未知的属性
        MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 序列化的时候，跳过null值
        MAPPER.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
    }

    /**
     * 将一个对象编码为json字符串
     *
     * @param obj
     *            ,if null return "null" 要编码的字符串
     * @return json字符串
     * @throws RuntimeException
     *             若对象不能被编码为json串
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("error encode json for " + obj, e);
        }
    }

    /**
     * 将一个对象编码成字节
     *
     * @param obj
     * @return
     */
    public static byte[] toBytes(Object obj) {

        try {
            return MAPPER.writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException("error encode json for " + obj, e);
        }
    }

    /**
     * 将一个json字符串解码为java对象
     *
     * 注意：如果传入的字符串为null，那么返回的对象也为null
     *
     * @param json
     *            json字符串
     * @param cls
     *            对象类型
     * @return 解析后的java对象
     * @throws RuntimeException
     *             若解析json过程中发生了异常
     */
    public static <T> T toObject(String json, Class<T> cls) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, cls);
        } catch (Exception e) {
            throw new RuntimeException("error decode json to " + cls, e);
        }
    }

    /**
     * 将json字节解码为java对象
     *
     * @param jsonBytes
     *            json字节
     * @param cls
     *            对象类型
     * @return 解码后的对象
     */
    public static <T> T toObject(byte[] jsonBytes, Class<T> cls) {
        try {
            return MAPPER.readValue(jsonBytes, cls);
        } catch (Exception e) {
            throw new RuntimeException("error decode json to " + cls);
        }
    }

    /**
     * 将json字节解码为java对象
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> T toObject(String json, TypeReference typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("error decode json to " + typeReference);
        }
    }

}
