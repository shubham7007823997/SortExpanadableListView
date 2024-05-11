package com.example.sortarrayjava;

import android.content.Context;
import android.content.res.Resources;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class JSONFileReader {

    public JSONObject loadJSONFromRaw(Context context, int resourceId) {
        JSONObject jsonObject = null;
        Resources resources = context.getResources();

        try {
            // Open the raw resource file
            InputStream inputStream = resources.openRawResource(resourceId);
            String jsonString = convertStreamToString(inputStream);

            // Parse the JSON string into a JSONObject
            jsonObject = new JSONObject(jsonString);

            // Close the input stream
            inputStream.close();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
