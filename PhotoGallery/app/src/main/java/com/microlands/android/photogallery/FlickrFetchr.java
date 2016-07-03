package com.microlands.android.photogallery;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luisvivero on 7/2/16.
 */

public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";

    public static final String API_KEY = "baad491b469bb15c81c18c9c7f97c02d";

    private byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection =  (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                    ": with " + urlSpec);
            }

            int readBytes;
            byte[] buffer = new byte[1024];
            while ((readBytes = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, readBytes);
            }
            outputStream.close();
            return outputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String url) throws IOException {
        return new String(getUrlBytes(url));
    }

    public List<GalleryItem> fetchItems() {
        List<GalleryItem> items = new ArrayList<>();

        try {
            String url = Uri.parse("https://www.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON" + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (JSONException ex) {
            Log.e(TAG, "Failed to parse JSON", ex);
        } catch (IOException ex) {
            Log.e(TAG, "Failed to fetch items", ex);
        }

        return items;
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonBody)
        throws IOException, JSONException
    {
        JSONObject photosJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            // ignore photos that do not have an image url.
            if (!photoJsonObject.has("url_s")) {
                continue;
            }

            GalleryItem galleryItem = new GalleryItem();
            galleryItem.setId(photoJsonObject.getString("id"));
            galleryItem.setCaption(photoJsonObject.getString("title"));
            galleryItem.setUrl(photoJsonObject.getString("url_s"));
            items.add(galleryItem);
        }
    }

}
