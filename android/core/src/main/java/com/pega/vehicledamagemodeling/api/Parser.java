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

import java.util.HashSet;

public class Parser {

    private static final String mainScreenText = "mainScreenText";
    private static final String selection = "selection";
    private static final String id = "id";

    public static String parseToMainScreenText(JsonObject jsonObject){
        JsonElement jsonText = jsonObject.get(mainScreenText);
        String text = "";
        if(jsonText != null){
            text = jsonText.getAsString();
        }
        return text;
    }

    public static HashSet<String> parseToSelectedParts(JsonObject jsonObject){
        JsonArray partsArray = jsonObject.getAsJsonArray(selection);
        HashSet<String> selectedParts = new HashSet<>();
        if(partsArray != null) {
            for (int i = 0; i < partsArray.size(); i++) {
                String selection = partsArray.get(i)
                        .getAsJsonObject()
                        .get(id)
                        .getAsString();
                selectedParts.add(selection);
            }
        }
        return selectedParts;
    }

    public static JsonObject parseToJson(String text, HashSet<String> parts){
        JsonObject parsed = new JsonObject();
        JsonArray parsedArray = new JsonArray();

        for(String s : parts){
            JsonObject jsonProperty = new JsonObject();
            jsonProperty.addProperty(id, s);
            parsedArray.add(jsonProperty);
        }

        parsed.addProperty(mainScreenText, text);
        parsed.add(selection,parsedArray);
        return parsed;
    }
}
