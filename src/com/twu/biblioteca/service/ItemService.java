package com.twu.biblioteca.service;

import com.twu.biblioteca.controller.IHaveSession;
import com.twu.biblioteca.entity.IHaveID;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ItemService<Item> {
    private List<Item> available_items;
    private List<Item> checkouted_items;
    private String library_number;
    private Map<Integer, String> checkout_item_user_library_number;

    public ItemService() {
        this.available_items = new LinkedList<Item>();
        this.checkouted_items = new LinkedList<Item>();
        this.checkout_item_user_library_number = new TreeMap<Integer, String>();
    }

    public List<Item> getAvailableItems() {
        return available_items;
    }

    public void setAvailableItems(List<Item> items) {
        this.available_items = items;
    }

    protected List<Item> getCheckoutedItems() {
        return checkouted_items;
    }

    public void setCheckoutedItems(List<Item> items) {
        this.checkouted_items = items;
    }

    public Item findAvailableItemById(Integer id) {
        for (Item item : available_items) {
            if (((IHaveID)item).getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public Item findCheckoutedItemById(Integer id) {
        for (Item item : checkouted_items) {
            if (((IHaveID)item).getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public boolean checkout(Integer id) {
        Item item = findAvailableItemById(id);
        if (item == null) {
            return false;
        }
        else {
            Item checkouted = findCheckoutedItemById(id);
            if (checkouted == null) {
                available_items.remove(item);
                checkouted_items.add(item);
                checkout_item_user_library_number.put(id, this.library_number);
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean checkout(Integer id, String number) {
        this.library_number = number;
        boolean result = checkout(id);
        this.library_number = null;
        return result;
    }

    public boolean checkin(Integer id) {
        Item item = findCheckoutedItemById(id);
        if (item == null) {
            return false;
        }
        else {
            Item returned = findAvailableItemById(id);
            if (returned == null) {
                checkouted_items.remove(item);
                available_items.add(item);
                checkout_item_user_library_number.remove(id);
                return true;
            }
            else {
                return false;
            }
        }
    }
}
