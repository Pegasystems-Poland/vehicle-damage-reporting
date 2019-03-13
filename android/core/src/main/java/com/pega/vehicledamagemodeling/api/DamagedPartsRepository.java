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

import java.util.ArrayList;

public class DamagedPartsRepository implements DamagedPartsRepositoryProtocol {

    private ArrayList<Selection> selections;
    private JsonObject jsonObject;
    private Parser parser;

    public DamagedPartsRepository(JsonObject jsonObject, Parser parser){
        this.parser = parser;
        this.jsonObject = jsonObject;
        this.selections = this.parser.parse(this.jsonObject).returnArray();
    }

    @Override
    public void clear() {
        this.selections.clear();
    }

    @Override
    public void add(Selection selection) {
        if( !this.selections.contains(selection)){
            this.selections.add(selection);
        }
    }

    @Override
    public void remove(Selection selection) {
        this.selections.remove(selection);
    }

    @Override
    public void add(ArrayList<Selection> selections) {
        for(Selection selection : selections){
            add(selection);
        }
    }

    @Override
    public ArrayList<Selection> getAll() {
        return this.selections;
    }

    @Override
    public JsonObject getJsonObject(){
        return this.jsonObject;
    }
}
