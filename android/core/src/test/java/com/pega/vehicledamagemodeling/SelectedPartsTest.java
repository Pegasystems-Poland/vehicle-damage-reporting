package com.pega.vehicledamagemodeling;

import org.junit.Test;

import java.util.ArrayList;

public class SelectedPartsTest {

    @Test
    public void testCreate(){
        ArrayList<String> arrayList = new ArrayList<>();
        //SelectedPartsRepository damagedPartsRepository = new SelectedPartsRepository(arrayList);
        //Assert.assertNotNull(damagedPartsRepository);
    }

    @Test
    public void testAdd(){
        //todo
    }

    @Test
    public void testAddMultiple(){
        String s1 = "s1";
        String s2 = "s2";
        ArrayList<String> list = new ArrayList<String>();
        list.add(s1);
        list.add(s2);
        //SelectedPartsRepository damagedPartsRepository = new SelectedPartsRepository(new ArrayList<>());
        //toDo
    }

    @Test
    public void testRemove(){
        String s1 = "s1";
        ArrayList<String> list = new ArrayList<String>();
        list.add(s1);
        //SelectedPartsRepository damagedPartsRepository = new SelectedPartsRepository(list);
        //damagedPartsRepository.remove(s1);
        //Assert.assertTrue(damagedPartsRepository.getAll().isEmpty());
    }

    @Test
    public void testClear(){
        String s1 = "s1";
        ArrayList<String> list = new ArrayList<String>();
        list.add(s1);
        //SelectedPartsRepository damagedPartsRepository = new SelectedPartsRepository(list);
        //damagedPartsRepository.clear();
        //Assert.assertTrue(damagedPartsRepository.getAll().isEmpty());
    }

    @Test
    public void testGetAll(){
        String s1 = "s1";
        String s2 = "s2";
        String s3 = "s3";
        //SelectedPartsRepository damagedPartsRepository = new SelectedPartsRepository(new ArrayList<>());
        //damagedPartsRepository.add(s1);
        //damagedPartsRepository.add(s2);
        //damagedPartsRepository.add(s3);
        //ArrayList<Part> arrayList = damagedPartsRepository.getAll();
        //Assert.assertTrue(arrayList.contains(s1));
        //Assert.assertTrue(arrayList.contains(s2));
        //Assert.assertTrue(arrayList.contains(s2));
        //Assert.assertTrue(arrayList.size()==3);
    }
}
