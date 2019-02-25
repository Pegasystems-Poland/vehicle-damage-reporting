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

package com.pega.vehicledamagemodeling.API;

import com.badlogic.gdx.utils.Array;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Parser {

    public SelectionRoot parse(JsonObject obj){
        JsonArray arr = obj.getAsJsonArray("parts");

        Array<Selection> selections = new Array<>();

        for(int i =0; i < arr.size(); i++){
            Boolean selected = arr.get(i)
                    .getAsJsonObject()
                    .getAsJsonObject("damaged")
                    .getAsBoolean();

            if(selected){
                String part = arr.get(i)
                        .getAsJsonObject()
                        .getAsJsonObject("name")
                        .getAsString();

                Selection selection = new Selection(part);
                selections.add(selection);
            }
        }
        SelectionRoot root = new SelectionRoot(selections,"what?");
        return root;
    }
}
