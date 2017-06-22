package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class article_delete_list extends Activity {

    private ArrayAdapter<String> listAdapter;
    private ArrayList<Integer> arrli_id = new ArrayList<>();
    private ArrayList<String> arrli_title = new ArrayList<>();
    private CookieStore cookieStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_delete_list);

        cookieStore = login_cookie.cookieStore;
        arrli_id = article_get_jsonarray.arrli_id;
        arrli_title = article_get_jsonarray.arrli_title;

        ListView listView = (ListView) findViewById(R.id.listview_art);
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrli_title);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(article_delete_list.this,MainActivity.class);
                sendJson_del(arrli_id.get(position));
                Toast.makeText(article_delete_list.this,"成功刪除:"+arrli_title.get(position),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    protected void sendJson_del(final Integer id) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare();
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    ((AbstractHttpClient) client).setCookieStore(cookieStore);
                    HttpDelete delete = new HttpDelete("https://richegg.top/posts/"+id);

                    StringEntity se = new StringEntity( json.toString(), "UTF-8");
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    response = client.execute(delete);
                    String test = response.toString();
                    if(response!=null){
                        InputStream in = response.getEntity().getContent();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                Looper.loop();
            }
        };
        t.start();
    }
}
