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
    private static final String SELECTION = "selection";
    private static final String ID = "id";
    private static final String MAIN_SCREEN_TEXT = "mainScreenText";
    private static final String NOTHING = "nothing";
    private static final String ROOF = "roof";
    private static final String FRONT_BUMPER = "front bumper";

    @Test
    public void whenJsonIsNullThenHashSetIsEmpty() {
        //given
        JsonObject initJson = null;
        Parser parser = new Parser();

        //when
        HashSet<String> hashSet = parser.parseToSelectedParts(initJson);

        //then
        assertEquals(hashSet, new HashSet<>());
    }

    @Test
    public void whenJsonContainsTwoPartsThenReturnSelectedParts() {
        //given
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(ID, ROOF);
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(ID, FRONT_BUMPER);
        partsArray.add(jsonProperty2);
        initJson.add(SELECTION, partsArray);
        Parser parser = new Parser();
        HashSet<String> list = new HashSet<>();
        list.add(ROOF);
        list.add(FRONT_BUMPER);

        //when
        HashSet<String> result = parser.parseToSelectedParts(initJson);

        //then
        assertEquals(list, result);
    }

    @Test
    public void whenJsonIsEmptyThenReturnEmptyHashSet() {
        //given
        JsonObject initJson = new JsonObject();
        HashSet<String> expected = new HashSet<>();
        Parser parser = new Parser();

        //when
        HashSet<String> result = parser.parseToSelectedParts(initJson);

        //then
        assertEquals(expected, result);
    }

    @Test
    public void whenHashSetContainsTwoPartsThenReturnJson() {
        //given
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(ROOF);
        hashSet.add(FRONT_BUMPER);
        Parser parser = new Parser();
        JsonObject expectedJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(ID, ROOF);
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(ID, FRONT_BUMPER);
        partsArray.add(jsonProperty2);
        expectedJson.addProperty(MAIN_SCREEN_TEXT, NOTHING);
        expectedJson.add(SELECTION, partsArray);

        //when
        JsonObject result = parser.parseToJson(NOTHING, hashSet);

        //then
        assertEquals(expectedJson, result);
    }

    @Test
    public void whenHashSetIsEmptyThenReturnJsonWithoutParts() {
        //given
        HashSet<String> parts = new HashSet<>();
        Parser parser = new Parser();
        JsonObject expectedJson = new JsonObject();
        expectedJson.addProperty(MAIN_SCREEN_TEXT, NOTHING);
        JsonArray partsArray = new JsonArray();
        expectedJson.add(SELECTION, partsArray);

        //when
        JsonObject result = parser.parseToJson(NOTHING, parts);

        //then
        assertEquals(expectedJson, result);
    }

    @Test
    public void whenMainScreenTextInJsonIsNotNullThenReturnCorrectText() {
        //given
        String expectedText = NOTHING;
        JsonObject initJson = new JsonObject();
        initJson.addProperty(MAIN_SCREEN_TEXT, NOTHING);
        Parser parser = new Parser();

        //when
        String result = parser.parseToMainScreenText(initJson);

        //then
        assertEquals(expectedText, result);
    }

    @Test
    public void whenJsonDoesNotContainMainScreenTextThenReturnEmptyText() {
        //given
        JsonObject initJson = new JsonObject();
        String expectedText = "";
        Parser parser = new Parser();

        //when
        String result = parser.parseToMainScreenText(initJson);

        //then
        assertEquals(expectedText, result);
    }
}
