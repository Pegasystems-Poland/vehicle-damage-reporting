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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Parser {

    public SelectionRoot parse(JsonObject obj){
        JsonArray partsArray = obj.getAsJsonArray("parts");
        ArrayList<Selection> selections = new ArrayList<>();

        for(int i =0; i < partsArray.size(); i++){
            if(isDamaged(partsArray.get(i))){
                String part = partsArray.get(i)
                        .getAsJsonObject()
                        .get("name")
                        .getAsString();

                Selection selection = new Selection(part);
                selections.add(selection);
            }
        }
        return new SelectionRoot(selections,"nothing");
    }

    private boolean isDamaged(JsonElement jsonElement) {
        return jsonElement.getAsJsonObject()
                .get("damaged")
                .getAsBoolean();
    }
}
