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


    @Test
    public void whenJsonIsNotNullThenReturnSelectedParts(){
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

        HashSet<String> list = new HashSet<>();
        list.add("roof");
        list.add("front bumper");

        //when
        HashSet<String> result = Parser.parseToSelectedParts(initJson);

        //then
        assertEquals(list,result);
    }

    @Test
    public void whenPartRootIsNotNullThenReturnJson(){
        //given
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("roof");
        hashSet.add("front bumper");

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

        //when
        JsonObject result = Parser.parseToJson("nothing",hashSet);

        //then
        assertEquals(expectedJson,result);
    }

    @Test
    public void whenMainScreenTextIsNotNullThenReturnCorrectText() {
        //initJson
        JsonObject initJson = new JsonObject();
        initJson.addProperty("mainScreenText","nothing");
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        initJson.add("selection",partsArray);

        //given
        String expectedText = "nothing";

        //when
        String result = Parser.parseToMainScreenText(initJson);

        //then
        assertEquals(expectedText,result);
    }
}


