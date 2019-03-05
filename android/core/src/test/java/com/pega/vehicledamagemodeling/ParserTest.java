package com.pega.vehicledamagemodeling;

import com.google.gson.JsonObject;
import com.pega.vehicledamagemodeling.api.Parser;

import org.junit.Assert;
import org.junit.Test;


public class ParserTest {
    @Test
    public void testCreate(){
        Parser parser = new Parser();
        Assert.assertNotNull(parser);
    }

    @Test
    public void testParse(){
        JsonObject jsonObject = new JsonObject().getAsJsonObject("");
        //to do
    }
}


