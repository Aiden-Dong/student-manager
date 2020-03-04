package org.university.stm.start.common;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
public class JsonUtil {
    /**
     * <pre>
     *     string 转 jsonNode
     * </pre>
     *
     * @param json 待转换字符串
     */
    public static JsonNode str2JsonNode(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        return node;
    }

    /**
     * <pre>
     *     string-buffer 转 jsonNode
     * </pre>
     *
     * @param json 待转换字符串
     */
    public static JsonNode str2JsonNode(StringBuffer json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json.toString());
        return node;
    }

    /**
     * <pre>
     *     string 转 jsonNode
     * </pre>
     *
     * @param file 待转换文件
     */
    public static JsonNode str2JsonNode(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(file);
        return node;
    }

    /**
     * <pre>
     *     string 转 jsonNode
     * </pre>
     *
     * @param stream 转换流
     */
    public static JsonNode str2JsonNode(InputStream stream) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(stream);
        return node;
    }

    /**
     * <pre>
     *     Object 类转 json string
     * </pre>
     *
     * @param obj 待转 json string 的object
     */
    public static String object2JsonString(Object obj) throws IOException {
        return object2JsonString(obj, false);
    }

    /**
     * <pre>
     *     Object 类转 json string
     * </pre>
     *
     * @param obj 待转json string 的 object
     * @param prettyPrint 优雅打印
     */
    public static String object2JsonString(Object obj, boolean prettyPrint) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (prettyPrint) {
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                return writer.writeValueAsString(obj);
            }
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new IOException("Object to Json String parse error : " + e);
        }
    }

    /**
     * <pre>
     *     将json内容转class bean
     * </pre>
     *
     * @param jsonStr json 字符串
     * @param valueType 转义class
     */
    public static <T>T json2Bean(String jsonStr, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * <pre>
     *     将json内容转 class bean
     * </pre>
     *
     * @param jsonStr 序列号内容
     * @param valueType 序列号类型
     * @param jsonDeserializer 反序列化器
     */
    public static <T>T json2Bean(String jsonStr, Class<T> valueType, JsonDeserializer<T> jsonDeserializer) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        try {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(valueType, jsonDeserializer);
            mapper.registerModule(module);

            return mapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * <pre>
     *     将json内容转bean class
     * </pre>
     *
     * @param jsonNode jsonNode 节点
     * @param valueType 转义 class
     */
    public static <T>T json2Bean(JsonNode jsonNode, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String strValue = jsonNode.toString();
            return mapper.readValue(strValue, valueType);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * <pre>
     *     将json内容转bean class
     * </pre>
     *
     * @param file 文件 Json
     * @param valueType 转义 class
     */
    public static <T>T json2Bean(File file, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, valueType);

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * <pre>
     *     jsonNode 转object 工具类
     * </pre>
     *
     * @param node 待转换的jsonNode
     */
    public static Object json2Object(JsonNode node) throws IOException {

        String strValue = node.toString();
        ObjectMapper objectMapper = new ObjectMapper();

        if(node.isObject()){
            return objectMapper.readValue(strValue, Map.class);
        }else if(node.isArray()){
            return objectMapper.readValue(strValue, List.class);
        }else{
            if (node.isInt()) {
                return node.asInt();
            } else if (node.isLong()) {
                return node.asLong();
            } else if (node.isDouble()) {
                return node.asDouble();
            } else if(node.isTextual()) {
                return node.asText();
            } else if (node.isNull()){
                return null;
            }
            else {
                throw new IOException("Json node exception type :  " + node.numberType());
            }
        }
    }
}
