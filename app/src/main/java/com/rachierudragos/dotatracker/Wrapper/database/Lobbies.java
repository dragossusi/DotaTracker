package com.rachierudragos.dotatracker.Wrapper.database;

/**
 * Created by Dragos on 03-Nov-17.
 */

public class Lobbies {
    private static final String[] lobbies = new String[] {"Normal","Practice","Tournament","Tutorial","Bots","Team match","Solo Queue","Ranked","Solo Mid 1vs1"};

    public static String getLobby(int id) {
        return lobbies[id];
    }
}
