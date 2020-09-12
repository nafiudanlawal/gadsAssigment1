package com.ambrella.leaderboard;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){}

    public static final String SKILLIQLEADERAPI = "https://gadsapi.herokuapp.com/api/skilliq";
    public static final String LEARNINGLEADERAPI = "https://gadsapi.herokuapp.com/api/hours:";

    /**
    *  @params apiType
     *  0 is for learning Leaders
     *  1 is for Top Skilled Leaders
     **/
    public static URL buildSkillIQUrl(){

        URL url = null;
        Uri uri = Uri.parse(SKILLIQLEADERAPI).buildUpon()
                .build();
        try{
            url = new URL(uri.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildTopLearnerUrl(){

        URL url = null;
        Uri uri = Uri.parse(LEARNINGLEADERAPI).buildUpon()
                .build();
        try{
            url = new URL(uri.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getTopLearnerJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try{
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if(hasData ){
                return scanner.next();
            }
            else{
                return null;
            }
        }catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }

    public static String getTopSkillIQJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try{
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if(hasData ){
                return scanner.next();
            }
            else{
                return null;
            }
        }catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }

    public static ArrayList<TopLearner> getTopLearnerFromJson(String json){
        ArrayList topLearners = new ArrayList<TopLearner>();

        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";
        try{

            JSONArray jsonLearners = new JSONArray(json);
            int numberOfLeaders = jsonLearners.length();

            for(int i = 0; i < numberOfLeaders; i++){
                JSONObject learner = jsonLearners.getJSONObject(i);
                try{
                    String name = learner.getString(NAME);
                    int hours = learner.getInt(HOURS);
                    String country = learner.getString(COUNTRY);
                    String badgeUrl = learner.getString(BADGEURL);

                    TopLearner topLearner = new TopLearner(name, hours, country, badgeUrl);
                    topLearners.add(topLearner);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return topLearners;
    }

    public static ArrayList<TopSkilled> getTopSkilledFromJson(String json){
        ArrayList topSkilleds = new ArrayList<TopSkilled>();

        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final String BADGEURL = "badgeUrl";
        try{

            JSONArray jsonLearners = new JSONArray(json);
            int numberOfLeaders = jsonLearners.length();

            for(int i = 0; i < numberOfLeaders; i++){
                JSONObject learner = jsonLearners.getJSONObject(i);
                try{
                    String name = learner.getString(NAME);
                    int score = learner.getInt(SCORE);
                    String country = learner.getString(COUNTRY);
                    String badgeUrl = learner.getString(BADGEURL);

                    TopSkilled topSkilled = new TopSkilled(name, score, country, badgeUrl);
                    topSkilleds.add(topSkilled);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return topSkilleds;
    }
}