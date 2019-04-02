// Copyright 2018 Flying Vehicle Monster team
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.pega.vehicledamagemodeling.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;

public class ParserTest {

    private static String Selection = "selection";
    private static String Id = "id";
    private static String MainScreenText = "mainScreenText";

    @Test
    public void whenJsonContainsTwoPartsThenReturnSelectedParts(){
        //given
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(Id,"roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(Id,"front bumper");
        partsArray.add(jsonProperty2);
        initJson.add(Selection,partsArray);
        Parser parser = new Parser();
        HashSet<String> list = new HashSet<>();
        list.add("roof");
        list.add("front bumper");

        //when
        HashSet<String> result = parser.parseToSelectedParts(initJson);

        //then
        assertEquals(list,result);
    }

    @Test
    public void whenJsonIsNullThenReturnEmptyHashSet() {
        //given
        JsonObject initJson = new JsonObject();
        HashSet<String> expected = new HashSet<>();
        Parser parser = new Parser();

        //when
        HashSet<String> result = parser.parseToSelectedParts(initJson);

        //then
        assertEquals(expected,result);
    }

    @Test
    public void whenHashSetContainsTwoPartsThenReturnJson(){
        //given
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("roof");
        hashSet.add("front bumper");
        Parser parser = new Parser();
        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(Id,"roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(Id,"front bumper");
        partsArray.add(jsonProperty2);
        expectedJson.addProperty(MainScreenText, "nothing");
        expectedJson.add(Selection,partsArray);

        //when
        JsonObject result = parser.parseToJson("nothing",hashSet);

        //then
        assertEquals(expectedJson,result);
    }

    @Test
    public void whenHashSetIsNullThenReturnJsonWithoutParts(){
        //given
        HashSet<String> parts = new HashSet<>();
        Parser parser = new Parser();
        JsonObject expectedJson = new JsonObject();
        expectedJson.addProperty(MainScreenText, "nothing");
        JsonArray partsArray = new JsonArray();
        expectedJson.add(Selection,partsArray);

        //when
        JsonObject result = parser.parseToJson("nothing",parts);

        //then
        assertEquals(expectedJson,result);
    }

    @Test
    public void whenMainScreenTextInJsonIsNotNullThenReturnCorrectText() {
        //given
        String expectedText = "nothing";
        JsonObject initJson = new JsonObject();
        initJson.addProperty(MainScreenText,"nothing");
        Parser parser = new Parser();

        //when
        String result = parser.parseToMainScreenText(initJson);

        //then
        assertEquals(expectedText,result);
    }

    @Test
    public void whenJsonDoesNotContainMainScreenTextThenReturnEmptyText(){
        //given
        JsonObject initJson = new JsonObject();
        String expectedText = "";
        Parser parser = new Parser();

        //when
        String result = parser.parseToMainScreenText(initJson);

        //then
        assertEquals(expectedText,result);
    }
}


