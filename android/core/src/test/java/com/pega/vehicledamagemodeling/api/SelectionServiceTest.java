package com.pega.vehicledamagemodeling.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelectionServiceTest {

    @Test
    public void TestGetInitJson() {
        //given
        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        expectedJson.addProperty("mainScreenText", "nothing");
        expectedJson.add("selection",partsArray);

        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(expectedJson);

        //when
        JsonObject result = selectionService.getInitJson();

        //then
        assertEquals(expectedJson,result);
    }

    @Test
    public void TestMainScreenText() {
        //given
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        initJson.addProperty("mainScreenText", "nothing");
        initJson.add("selection",partsArray);

        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(initJson);

        //when
        String result = selectionService.getMainScreenText();

        //then
        assertEquals("nothing",result);
    }
}
