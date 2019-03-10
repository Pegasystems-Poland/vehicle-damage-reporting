package com.pega.vehicledamagemodeling;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pega.vehicledamagemodeling.api.Receiver;

import org.junit.Assert;
import org.junit.Test;

public class ReceiverTest {

    @Test
    public void testCreate() {
        JsonObject obj = new JsonObject();
        Receiver receiver = new Receiver(obj);
        Assert.assertNotNull(receiver);
    }

    @Test
    public void testRetrunJson() {
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

        Receiver receiver = new Receiver(jsonObject);
        JsonObject received = receiver.returnJson();
        System.out.println(received);
        Assert.assertTrue(received.equals(jsonObject));
    }
}
