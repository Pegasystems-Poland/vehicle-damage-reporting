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

import com.google.gson.JsonObject;
import org.junit.Test;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;

public class SelectedPartsTest {

    @Test
    public void testRemove(){  //removeNonexistentPart
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(new JsonObject(), new Parser());
        boolean expected = false;

        //when
        boolean result = selectedPartsRepository.remove("test");

        //then
        assertEquals(expected,result);
    }

    @Test
    public void testGetAll(){
        //given
        SelectedPartsRepository selectedPartsRepository = new SelectedPartsRepository(new JsonObject(), new Parser());
        ArrayList<String> expected = new ArrayList<>();

        //when
        ArrayList<String> result = selectedPartsRepository.getAll();

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
}
