package com.rachierudragos.dotatracker.Wrapper.item;

import java.util.Map;

/**
 * Created by Dragos on 08-Nov-17.
 */

public class ItemsResponse {
    Map<String, ItemDetail> items;

    public ItemDetail getItemById(int id) {
        for (Map.Entry<String, ItemDetail> entry : items.entrySet()) {
            if(entry.getValue().id==id)
                return entry.getValue();
        }
        return null;
    }
}
