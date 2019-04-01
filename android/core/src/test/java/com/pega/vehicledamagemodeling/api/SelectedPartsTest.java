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

    private static String Selection = "selection";
    private static String Id = "id";

    @Test
    public void whenPartDoesNotExistThenReturnNull(){
        //initJson
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(Id,"roof");
        partsArray.add(jsonProperty);
        JsonObject jsonProperty2 = new JsonObject();
        jsonProperty2.addProperty(Id,"front bumper");
        partsArray.add(jsonProperty2);
        initJson.add(Selection,partsArray);

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
    public void whenSelectionContainsTwoPartsThenReturnCorrectParts() {
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        selectedPartsRepository.add("roof",new Material());
        selectedPartsRepository.add("front bumper",new Material());

        HashSet<String> expected = new HashSet<>();
        expected.add("roof");
        expected.add("front bumper");

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void whenSelectionIsEmptyThenReturnEmptyHashSet(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        HashSet<String> expected = new HashSet<>();

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void whenJsonIsEmptyThenReturnNull(){
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
    public void whenJsonIsNotEmptyThenReturnCorrectJson(){
        //given
        JsonObject initJson = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject jsonProperty = new JsonObject();
        jsonProperty.addProperty(Id,"roof");
        partsArray.add(jsonProperty);
        initJson.add(Selection,partsArray);

        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        selectedPartsRepository.setInitJson(initJson);

        //when
        JsonObject result = selectedPartsRepository.getInitJson();

        //then
        assertEquals(initJson,result);
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

    @Test
    public void whenTextIsEmptyThenReturnEmptyText(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        selectedPartsRepository.setMainScreenText("");

        //when
        String result = selectedPartsRepository.getMainScreenText();

        //then
        assertEquals("",result);
    }

    @Test
    public void whenPartExistThenDoNotAdd(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        selectedPartsRepository.add("roof",new Material());
        selectedPartsRepository.add("roof",new Material());
        HashSet<String> expected = new HashSet<>();
        expected.add("roof");

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected,result);
    }

    @Test
    public void whenPartDoesNotExistThenAdd(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository();
        selectedPartsRepository.add("roof", new Material());
        HashSet<String> expected = new HashSet<>();
        expected.add("roof");

        //when
        HashSet<String> result = selectedPartsRepository.getSelectedParts();

        //then
        assertEquals(expected,result);
    }
}
