package com.pega.vehicledamagemodeling;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pega.vehicledamagemodeling.api.DamagedPartsRepository;
import com.pega.vehicledamagemodeling.api.Parser;
import com.pega.vehicledamagemodeling.api.Selection;
import com.pega.vehicledamagemodeling.api.SelectionRoot;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class ParserTest {
    @Test
    public void testCreate(){
        Parser parser = new Parser();
        Assert.assertNotNull(parser);
    }

    @Test
    public void testParse(){
        JsonObject jsonObject = new JsonObject();
        JsonArray array = new JsonArray();
        JsonObject item = new JsonObject();
        JsonObject item2 = new JsonObject();
        item.addProperty("name","roof");
        item.addProperty("damaged",true);
        array.add(item);
        item2.addProperty("name","front bumper");
        item2.addProperty("damaged",false);
        array.add(item2);

        jsonObject.add("parts",array);

        Selection s1 = new Selection("roof");
        ArrayList<Selection> list = new ArrayList<Selection>();
        list.add(s1);
        SelectionRoot selectionRoot = new SelectionRoot(list,"nothing");
        Parser parser = new Parser();
        SelectionRoot parsed = parser.parse(jsonObject);
        for(int i = 0; i < parsed.returnArray().size(); i++)
            System.out.println(parsed.returnArray().get(i).getId());
        Assert.assertTrue(parsed.equals(selectionRoot));
    }
}


