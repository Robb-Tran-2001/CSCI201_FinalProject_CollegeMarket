package com.csci201.marketplace.Store;

import com.csci201.marketplace.user.model.User;

import java.util.*;

public class Store { //replace integer with Item
    private static Map<User, Set<Integer>> sellers = new HashMap();
    private static Map<Integer, Queue<User>> buyers = new HashMap();
    private List<User> allUser = new ArrayList<>();
    public static void main(String[] args) {

    }

    public static Map<User, Set<Integer>> getSellers() {
        return sellers;
    }

    public static Map<Integer, Queue<User>> getBuyers() {
        return buyers;
    }
}
