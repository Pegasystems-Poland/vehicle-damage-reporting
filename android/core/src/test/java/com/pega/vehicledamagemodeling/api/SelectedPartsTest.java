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

import com.badlogic.gdx.graphics.g3d.Material;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class SelectedPartsTest {

    @Test
    public void testRemove(){  //removeNonexistentPart
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(new JsonObject(), new Parser());
        Material expected = null;

        //when
        Material result = selectedPartsRepository.remove("test");

        //then
        assertEquals(expected,result);
    }

    @Test
    public void testGetAll(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(new JsonObject(), new Parser());
        HashSet<String> expected = new HashSet<>();

        //when
        HashSet<String> result = selectedPartsRepository.getAll();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void testGetInitJson(){
        //given
        JsonObject jsonObject = new JsonObject();
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(jsonObject, new Parser());
        JsonObject expected = new JsonObject();

        //when
        JsonObject result = selectedPartsRepository.getInitJson();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void testGetMainScreenText(){
        //given
        JsonObject jsonText = new JsonObject();
        jsonText.addProperty("mainScreenText", "The text");
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(jsonText,new Parser());
        String expected = "The text";

        //when
        String result = selectedPartsRepository.getMainScreenText();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void testGetJson(){
        //given
        //init
        JsonObject initJson = new JsonObject();
        initJson.addProperty("mainScreenText","Można obracać autko");
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        initJson.add("selection",partsArray);
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(initJson,new Parser());

        //add
        selectedPartsRepository.add("trunk", null);

        //expected
        JsonObject expected = new JsonObject();
        expected.addProperty("mainScreenText","Można obracać autko");
        partsArray = new JsonArray();
        jsonProperty = new JsonObject();
        jsonProperty.addProperty("id","roof");
        partsArray.add(jsonProperty);
        jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty("id","front bumper");
        partsArray.add(jsonProperty2);
        JsonObject jsonProperty3 = new JsonObject();
        jsonProperty3.addProperty("id","trunk");
        partsArray.add(jsonProperty3);
        expected.add("selection",partsArray);

        //when
        JsonObject result = selectedPartsRepository.getJson();

        //then
        assertEquals(expected,result);
    }
}
