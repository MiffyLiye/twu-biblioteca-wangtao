package com.twu.biblioteca.service;

import com.twu.biblioteca.item.IHaveID;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MiffyLiye on 06/08/2015.
 */
public class ItemService<Item> {
    private String NewLine;
    private List<Item> available_items;
    private List<Item> checkouted_items;

    public ItemService() {
        this.NewLine = System.getProperty("line.separator");
        this.available_items = new LinkedList<Item>();
        this.checkouted_items = new LinkedList<Item>();
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
                return true;
            }
            else {
                return false;
            }
        }
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
                return true;
            }
            else {
                return false;
            }
        }
    }
}
