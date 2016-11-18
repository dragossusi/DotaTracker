package com.rachierudragos.dotatracker.dummy;

import com.rachierudragos.dotatracker.Wrapper.domain.matchdetail.MatchDetailPlayer;

import java.util.ArrayList;
import java.util.List;

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

    public DummyContent(List<MatchDetailPlayer> players) {
        for(int i=0;i<10;++i)
            addItem(players.get(i));
    }

    private static void addItem(MatchDetailPlayer player) {
        ITEMS.add(player);
    }
}
