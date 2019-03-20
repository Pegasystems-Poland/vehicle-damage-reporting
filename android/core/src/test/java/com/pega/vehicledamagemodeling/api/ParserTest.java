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
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void whenJsonIsNotNullThenReturnPartRoot(){
        //given
        JsonObject initJson = new JsonObject();
        initJson.addProperty("mainScreenText", "test");
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        initJson.add("selection",partsArray);

        ArrayList<String> list = new ArrayList<>();
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
        ArrayList<String> list = new ArrayList<>();
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
        expectedJson.addProperty("mainScreenText", "nothing");
        expectedJson.add("selection",partsArray);

        Parser parser = new Parser();

        //when
        JsonObject result = parser.parseToJson(parts);

        //then
        assertEquals(expectedJson,result);
    }
}


