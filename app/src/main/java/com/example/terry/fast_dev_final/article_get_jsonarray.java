package com.example.terry.fast_dev_final;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class article_get_jsonarray extends AsyncTask<String, Void, String> {

    public static ArrayList<Integer> arrli_id = new ArrayList<>();
    public static ArrayList<String> arrli_title = new ArrayList<>();
    public static ArrayList<String> arrli_content = new ArrayList<>();
    public static String[][] arr_tags_json = new String[50][3];

    public static String result;
    @Override
    protected String doInBackground(String... urls) {
        return GET(urls[0]);
    }
    @Override
    protected void onPostExecute(String result) {
        try {
            JSONArray json_arr = new JSONArray(result);

            for(int json_count=0;json_count<json_arr.length();json_count++) {
                arrli_id.add(new JSONArray(result).getJSONObject(json_count).getInt("id"));
                arrli_title.add(new JSONArray(result).getJSONObject(json_count).getString("title"));
                arrli_content.add(new JSONArray(result).getJSONObject(json_count).getString("content"));
                /*for(int i=0;i<2;i++) {
                    arr_tags_json[json_count][i] = new JSONArray(result).getJSONObject(json_count).getJSONArray("tags").getString(i);
                }*/
            }
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
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "沒文章QQ";
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
