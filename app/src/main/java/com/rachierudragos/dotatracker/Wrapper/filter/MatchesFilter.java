package com.rachierudragos.dotatracker.Wrapper.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dragos on 02-Nov-17.
 */

public class MatchesFilter {
    private int limit;
    private int hero_id;

    public MatchesFilter setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public MatchesFilter setHero_id(int hero_id) {
        this.hero_id = hero_id;
        return this;
    }

    public Map<String,String> getFilter() {
        Map<String,String> filters = new HashMap<>();
        if(limit!=0)
            filters.put("limit", String.valueOf(limit));
        if(hero_id!=0) {
            filters.put("hero_id",String.valueOf(hero_id));
        }
        return filters;
    }
}
