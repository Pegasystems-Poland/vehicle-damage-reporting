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

import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

public class PartRootTest {

    @Test
    public void whenArrayNullThenGetEmptyList(){
        //given
        ArrayList<String> arrayList = new ArrayList<>();
        String testString = "test";
        PartRoot partRoot = new PartRoot(arrayList, testString);
        ArrayList<String> expected = new ArrayList<>();

        //when
        ArrayList<String> result = partRoot.getParts();

        //then
        TestCase.assertEquals(expected,result);
    }

    @Test
    public void whenTestNotNullThenReturnCorrectText() {
        //given
        ArrayList<String> arrayList = new ArrayList<>();
        String testString = "test";
        PartRoot partRoot = new PartRoot(arrayList, testString);

        //when
        String result = partRoot.getMainScreenText();

        //then
        TestCase.assertEquals("test",result);

    }
}