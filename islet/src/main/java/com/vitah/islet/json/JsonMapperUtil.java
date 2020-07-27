package com.vitah.islet.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author vitah
 */
public class JsonMapperUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <T> String objToJsonString(T obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> JsonNode objToJsonNode(T obj) {
        return objectMapper.convertValue(obj, JsonNode.class);
    }

    public static <T> ObjectNode objToObjectNode(T obj) {
        try {
            return objectMapper.valueToTree(obj);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
