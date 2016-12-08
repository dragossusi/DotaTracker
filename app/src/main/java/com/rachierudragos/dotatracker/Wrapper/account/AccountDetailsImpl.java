package com.rachierudragos.dotatracker.Wrapper.account;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rachierudragos.dotatracker.Wrapper.exceptions.Dota2StatsAccessException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dragos on 05.12.2016.
 */

public class AccountDetailsImpl implements AccountDetails {

    private static final Object API_ODOTA_PLAYER = "https://api.opendota.com/api/players/";
    private static final Object SOLO = "solo_competitive_rank";
    private static final Object PARTY = "competitive_rank";
    private static final Object NAME = "personaname";
    private static final Object PHOTO = "avatarfull";
    private static final Object COUNTRY = "loccountrycode";
    private String name;
    private String smmr;
    private String pmmr;
    private String photoUrl;
    private String country;
    public AccountDetailsImpl(String id) {
        try {
            Map<String, Object> jsonmap = doHttpGetAndConvertJsonToObject(API_ODOTA_PLAYER+id);
            smmr = (String) jsonmap.get(SOLO);
            pmmr = (String) jsonmap.get(PARTY);
            Map<String, Object> profile = (Map<String,Object>)jsonmap.get("profile");
            name = (String) profile.get(NAME);
            photoUrl = (String) profile.get(PHOTO);
            country = (String) profile.get(COUNTRY);
        } catch (Dota2StatsAccessException e) {
            e.printStackTrace();
        }

    }

    HttpHost proxy;

    @Override
    public String getSoloMMR() {
        return smmr;
    }

    @Override
    public String getPartyMMR() {
        return pmmr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhotoURL() {
        return photoUrl;
    }

    @Override
    public String getCountry() {
        return country;
    }

    protected Map<String, Object> doHttpGetAndConvertJsonToObject(
            String queryString) throws Dota2StatsAccessException {

        String jsonString = doHttpGet(queryString);

        Gson gson = new Gson();
        try {
            Map<String, Object> map = gson.fromJson(jsonString,
                    new TypeToken<HashMap<String, Object>>() {
                    }.getType());

            return map;
        } catch (JsonSyntaxException e) {

            throw new Dota2StatsAccessException(
                    "Something went wrong after accessing the Dota2 WebAPI. Check accountid and/or API key!");

        }

    }

    protected String doHttpGet(String queryString)
            throws Dota2StatsAccessException {

        System.out.println("Requesting: " + queryString);

        DefaultHttpClient http = new DefaultHttpClient();

        if (proxy != null)
            http.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        HttpGet getDetails = new HttpGet(queryString);

        HttpResponse resp = null;
        try {
            resp = http.execute(getDetails);
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (IOException e) {

            throw new Dota2StatsAccessException(
                    "Cannot access host. Please check if "
                            + "your network is working and if you are "
                            + "connecting through a proxy (Configure proxy with parameters -pPort "
                            + "and -pUrl)");
        }

        String jsonString = null;
        try {
            jsonString = EntityUtils.toString(resp.getEntity());
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return jsonString;
    }
}
