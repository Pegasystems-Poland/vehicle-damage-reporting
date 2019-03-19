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

import java.util.ArrayList;

public class Parser {

    public PartRoot parseToPartRoot(JsonObject obj){
        JsonArray partsArray = new JsonArray();
        if(obj.isJsonNull())
            partsArray= obj.getAsJsonArray("selection");
        ArrayList<String> parts = new ArrayList<>();

        for(int i = 0; i < partsArray.size(); i++){
            String part = partsArray.get(i)
                    .getAsJsonObject()
                    .get("id")
                    .getAsString();
            String selection = part;
            parts.add(selection);
        }
        return new PartRoot(parts,"nothing");
    }

    public JsonObject parseToJson(PartRoot parts) {
        JsonObject parsed = new JsonObject();
        JsonArray parsedArray = new JsonArray();

        for(int i = 0; i < parts.getParts().size(); i++)
        {
            JsonObject jsonProperty = new JsonObject();
            jsonProperty.addProperty("id",parts.getParts().get(i));
            parsedArray.add(jsonProperty);
        }
        parsed.add("selection",parsedArray);
        return parsed;
    }
}
