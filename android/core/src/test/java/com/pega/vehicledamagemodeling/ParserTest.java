package com.pega.vehicledamagemodeling;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pega.vehicledamagemodeling.api.Parser;
import com.pega.vehicledamagemodeling.api.PartRoot;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ParserTest {

    @Test
    public void whenJsonIsNotNullThenReturnPartRoot(){
        //given
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        initJson.add("selection",partsArray);

        ArrayList<String> list = new ArrayList<String>();
        list.add("roof");
        list.add("front bumper");

        Parser parser = new Parser();

        //when
        PartRoot result = parser.parseToPartRoot(initJson);

        //then
        assertEquals(list,result.getParts());
    }

    @Test
    public void whenPartRootIsNotNullThenReturnJson(){
        //given
        ArrayList<String> list = new ArrayList<String>();
        list.add("roof");
        list.add("front bumper");
        PartRoot parts = new PartRoot(list,"nothing");

        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        expectedJson.add("selection",partsArray);

        Parser parser = new Parser();

        //when
        JsonObject result = parser.parseToJson(parts);

        //then
        assertEquals(expectedJson,result);
    }
}


