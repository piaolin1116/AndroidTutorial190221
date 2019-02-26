package com.example.a.photogallery;

import android.net.Uri;
import android.util.Log;

import com.example.a.photogallery.model.GalleryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrFetchr {

    List<GalleryItem> items = new ArrayList<>();

    public byte[] getUrlByte(String urlSpec) throws Exception {

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InputStream is = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + " with " + urlSpec);
            }

            int byteRead = 0;
            byte[] buffer = new byte[1024];
            while((byteRead=is.read(buffer))>0){
                bos.write(buffer,0,byteRead);
            }
            is.close();
            bos.close();
            return bos.toByteArray();
        }finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws Exception {
        return new String(getUrlByte(urlSpec));
    }

    public List<GalleryItem> fetchItems(){
        String url = Uri.parse("https://api.flickr.com/services/rest/").buildUpon()
                .appendQueryParameter("method","flickr.photos.getRecent")
                .appendQueryParameter("api_key","ef1c9b05b86c87ec8418a78bc1e03b57")
                .appendQueryParameter("format","json")
                .appendQueryParameter("nojsoncallback","1")
                .appendQueryParameter("extras","url_s")
                .build().toString();
        try {
            String jsonString = getUrlString(url);
            JSONObject jsonBody = new JSONObject(jsonString);

            parseItems(items, jsonBody);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public void parseItems(List<GalleryItem> list, JSONObject jsonBody) throws JSONException {
        JSONObject photoJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photoJsonObject.getJSONArray("photo");
        for(int i=0; i<photoJsonArray.length(); i++){
            JSONObject photoJsonObejct = photoJsonArray.getJSONObject(i);
            GalleryItem item = new GalleryItem();
            item.setMid(photoJsonObejct.getString("id"));
            item.setCaption(photoJsonObejct.getString("title"));
            if(!photoJsonObejct.has("url_s")) continue;
            item.setUrl(photoJsonObejct.getString("url_s"));
            list.add(item);
        }
    }
}
