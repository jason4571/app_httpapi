package com.example.terry.fast_dev_final;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class authors_get_method extends AsyncTask<String, Void, String> {

    private CookieStore cookieStore = login_cookie.cookieStore;

    public static String authors_username,authors_name,authors_gender,authors_address;

    public static String result;
    @Override
    protected String doInBackground(String... urls) {
        return GET(urls[0]);
    }
    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            authors_username = new JSONObject(result).getString("username");
            authors_name = new JSONObject(result).getString("name");
            authors_gender = new JSONObject(result).getString("gender");
            authors_address = new JSONObject(result).getString("address");

            //arrli_id.add(new JSONArray(result).getJSONObject(json_count).getInt("id"));
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private  String GET(String url){
        InputStream inputStream = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            ((AbstractHttpClient) httpclient).setCookieStore(cookieStore);
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "找不到QQ";
        } catch (Exception e) {

        }
        return result;
    }

    private  String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }
}
