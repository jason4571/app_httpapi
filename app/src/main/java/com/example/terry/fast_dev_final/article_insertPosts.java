package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class article_insertPosts extends Activity {

    EditText etxt_title,etxt_content,etxt_tags;
    Button btn_insert;
    private CookieStore cookieStore;
    final String [] arr_tags = new String[5];
    public static String insert_title,insert_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_insert);

        etxt_title = (EditText)findViewById(R.id.etxt_title_ins);
        etxt_content = (EditText)findViewById(R.id.etxt_content_ins);
        etxt_tags = (EditText)findViewById(R.id.etxt_tags_ins);
        btn_insert = (Button)findViewById(R.id.btn_insert);

        cookieStore = login_cookie.cookieStore;

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr_tags[0] = etxt_tags.getText().toString();
                sendJson(etxt_title.getText().toString(),etxt_content.getText().toString(), arr_tags);
                Intent intent = new Intent(article_insertPosts.this,one_article_insert.class);
                startActivity(intent);
            }
        });
    }

    protected void sendJson(final String username, final String password,final String tags[]) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare();
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    ((AbstractHttpClient) client).setCookieStore(cookieStore);
                    HttpPost post = new HttpPost("https://richegg.top/posts");
                    json.put("title", etxt_title.getText().toString());
                    insert_title = etxt_title.getText().toString();
                    json.put("content", etxt_content.getText().toString());
                    insert_content = etxt_content.getText().toString();
                    JSONArray jsonArray = new JSONArray();
                    for(int i=0;i<arr_tags.length;i++) {
                        if(arr_tags[i]!=null) {
                            jsonArray.put(arr_tags[i]);
                        }
                    }
                    json.put("tags", jsonArray);

                    StringEntity se = new StringEntity( json.toString(), "UTF-8");
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    post.setEntity(se);
                    response = client.execute(post);
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
