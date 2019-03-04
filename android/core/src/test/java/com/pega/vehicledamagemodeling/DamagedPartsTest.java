package com.pega.vehicledamagemodeling;

import com.pega.vehicledamagemodeling.api.DamagedPartsRepository;
import com.pega.vehicledamagemodeling.api.Selection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DamagedPartsTest {

    @Test
    public void testCreate(){
        ArrayList<Selection> arrayList = new ArrayList<>();
        DamagedPartsRepository damagedPartsRepository = new DamagedPartsRepository(arrayList);
        Assert.assertNotNull(damagedPartsRepository);
    }

    @Test
    public void testAdd(Selection selection){
        Selection s1 = new Selection("s1");
        DamagedPartsRepository damagedPartsRepository = new DamagedPartsRepository(new ArrayList<>());
        if(damagedPartsRepository.getAll().isEmpty()) {
            damagedPartsRepository.add(s1);
            Assert.assertTrue(!damagedPartsRepository.getAll().isEmpty());
        } else Assert.assertFalse(false);
        //ok ?
    }

    @Test
    public void testRemove(Selection selection){
        //to do
    }

    @Test
    public void testGetAll(){
        Selection s1 = new Selection("s1");
        Selection s2 = new Selection("s2");
        Selection s3 = new Selection("s3");
        DamagedPartsRepository damagedPartsRepository = new DamagedPartsRepository(new ArrayList<>());
        damagedPartsRepository.add(s1);
        damagedPartsRepository.add(s2);
        damagedPartsRepository.add(s3);
        ArrayList<Selection> arrayList = damagedPartsRepository.getAll();
        Assert.assertTrue(arrayList.contains(s1));
        Assert.assertTrue(arrayList.contains(s2));
        Assert.assertTrue(arrayList.contains(s2));
        Assert.assertTrue(arrayList.size()==3);
    }
}
