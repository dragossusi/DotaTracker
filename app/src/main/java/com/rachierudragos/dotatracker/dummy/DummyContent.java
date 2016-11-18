package com.rachierudragos.dotatracker.dummy;

import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetailPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<MatchDetailPlayer> ITEMS = new ArrayList<MatchDetailPlayer>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, MatchDetailPlayer> ITEM_MAP = new HashMap<String, MatchDetailPlayer>();

    private static final int COUNT = 10;

    public DummyContent(List<MatchDetailPlayer> players) {
        for(int i=1;i<=COUNT;++i)
            addItem(players.get(i));
    }

    private static void addItem(MatchDetailPlayer player) {
        ITEMS.add(player);
        ITEM_MAP.put(String.valueOf(player.getHeroId()), player);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        builder.append("\nMore details information here.");
        return builder.toString();
    }
}
