package com.vitah.islet.json;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class JsonMapperUtilTest {

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
}