package com.vitah.islet.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class JsonMapperUtilTest {

    @Data
    public class User {
        private Long id;
        private String name;
        private Integer age;
        private Double price;
        private BigDecimal balance;
    }

    @Test
    @DisplayName("objToJsonString-String")
    void objToJsonString_string() {
        Object obj = "string";
        String result = JsonMapperUtil.objToJsonString(obj);
        Assert.assertEquals(obj.toString(), result);
    }

    @Test
    @DisplayName("objToJsonString-int")
    void objToJsonString_int() {
        Object obj = 1;
        String result = JsonMapperUtil.objToJsonString(obj);
        Assert.assertEquals(obj.toString(), result);
    }

    @Test
    @DisplayName("objToJsonString-null")
    void objToJsonString_null() {
        Object obj = null;
        String result = JsonMapperUtil.objToJsonString(obj);
        Assert.assertEquals(null, result);
    }


    @Test
    @DisplayName("objToJsonString-EmptyList")
    void objToJsonString_EmptyList() {
        Object obj = null;
        List<String> list = new ArrayList<>();
        String result = JsonMapperUtil.objToJsonString(list);
        Assert.assertEquals("[]", result);
    }

    @Test
    @DisplayName("objToJsonString-EmptyMap")
    void objToJsonString_EmptyMap() {
        HashMap map = new HashMap();
        String result = JsonMapperUtil.objToJsonString(map);
        Assert.assertEquals("{}", result);
    }

    @Test
    @DisplayName("objToJsonString-Map")
    void objToJsonString_Map() {
        HashMap map = new HashMap();
        map.put("a", 1);
        map.put("b", "test");
        String result = JsonMapperUtil.objToJsonString(map);
        Assert.assertEquals("{\"a\":1,\"b\":\"test\"}", result);
    }

    @Test
    @DisplayName("objToJsonString-List")
    void objToJsonString_List() {
        List<String> list = new ArrayList<>();
        list.add("test");
        String result = JsonMapperUtil.objToJsonString(list);
        Assert.assertEquals("[\"test\"]", result);

        List<Integer> intList = new ArrayList<>();
        intList.add(5);
        Assert.assertEquals("[5]", JsonMapperUtil.objToJsonString(intList));
    }

    @Test
    @DisplayName("objToJsonNode-String")
    void objToJsonNode_String() {
        String obj = "a";
        JsonNode jsonNode = JsonMapperUtil.objToJsonNode(obj);
        Assert.assertEquals("\"a\"", jsonNode.toString());
    }

    @Test
    @DisplayName("objToJsonNode-Null")
    void objToJsonNode_Null() {
        JsonNode jsonNode = JsonMapperUtil.objToJsonNode(null);
        Assert.assertEquals(NullNode.getInstance(), jsonNode);
    }

    @Test
    @DisplayName("objToObjectNode-String")
    void objToObjectNode_String() {
        String a = "a";
        ObjectNode objectNode = JsonMapperUtil.objToObjectNode(a);
        Assert.assertEquals(null, objectNode);
    }

    @Test
    @DisplayName("objToObjectNode-Null")
    void objToObjectNode_Null() {
        ObjectNode objectNode = JsonMapperUtil.objToObjectNode(null);
        Assert.assertEquals(null, objectNode);
    }

    @Test
    @DisplayName("objToObjectNode-Class")
    void objToObjectNode_Class() {
        User user = new User();
        user.setAge(10);
        user.setId(1L);
        user.setName("name");
        ObjectNode objectNode = JsonMapperUtil.objToObjectNode(user);
        Assert.assertNotNull(objectNode);
        Assert.assertEquals(10, objectNode.get("age").asInt());
        Assert.assertEquals(JsonMapperUtil.objToJsonString(user), objectNode.toString());
    }
}