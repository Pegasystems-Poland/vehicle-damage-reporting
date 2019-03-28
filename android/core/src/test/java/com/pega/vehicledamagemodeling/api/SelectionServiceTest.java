package com.pega.vehicledamagemodeling.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelectionServiceTest {

    private static String Selection = "selection";
    private static String Id = "id";
    private static String MainScreenText = "mainScreenText";

    @Test
    public void whenJsonContainsTwoPartsThenReturnCorrectInitJson() {
        //given
        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(Id,"roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(Id,"front bumper");
        partsArray.add(jsonProperty2);
        expectedJson.add(Selection,partsArray);

        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(expectedJson);

        //when
        JsonObject result = selectionService.getInitJson();

        //then
        assertEquals(expectedJson,result);
    }

    @Test
    public void whenJsonIsEmptyThenReturnEmptyInitJson(){
        //given
        JsonObject initJson = new JsonObject();
        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(initJson);

        //when
        JsonObject result = selectionService.getInitJson();

        //then
        assertEquals(initJson,result);
    }

    @Test
    public void whenInitJsonContainTextThenReturnCorrectText() {
        //given
        JsonObject initJson = new JsonObject();
        initJson.addProperty(MainScreenText, "nothing");
        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(initJson);

        //when
        String result = selectionService.getMainScreenText();

        //then
        assertEquals("nothing",result);
    }

    @Test
    public void whenJsonIsEmptyAndNewPartIsAddedThenReturnModifiedJson(){
        //given
        JsonObject initJson = new JsonObject();
        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(Id,"roof");
        partsArray.add(jsonProperty);
        expectedJson.addProperty(MainScreenText,"");
        expectedJson.add(Selection,partsArray);

        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(initJson);
        selectionService.setSelectedPart("roof");

        //when
        JsonObject result = selectionService.getModifiedJson();

        //then
        assertEquals(expectedJson,result);
    }
}
