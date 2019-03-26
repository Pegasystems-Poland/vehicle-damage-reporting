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

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SelectedPartsTest {


    @Test
    public void whenPartDoesntExistThenReturnNull(){  //removeNonexistentPart
        //initJson
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

        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(initJson);
        Material expected = null;

        //when
        Material result = selectedPartsRepository.remove("test");

        //then
        assertEquals(expected,result);
    }

    @Test
    public void whenSelectionIsNotNullThenReturnParts() {
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        selectedPartsRepository.add("roof",null);
        selectedPartsRepository.add("front bumper",null);

        HashSet<String> expected = new HashSet<>();
        expected.add("roof");
        expected.add("front bumper");

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void whenJsonEmptyThenReturnNull(){
        //given
        JsonObject initJson = new JsonObject();
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        SelectionService selectionService = new SelectionService();
        selectionService.attachJson(initJson);

        //when
        JsonObject result = selectedPartsRepository.getInitJson();

        //then
        assertNull(result);
    }

    @Test
    public void whenTextIsNotEmptyThenReturnCorrectText(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        selectedPartsRepository.setMainScreenText("The text");
        String expected = "The text";

        //when
        String result = selectedPartsRepository.getMainScreenText();

        //then
        assertEquals(expected, result);
    }
}
