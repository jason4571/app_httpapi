package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class article_patchPatch extends Activity {

    private CookieStore cookieStore;
    private String title;
    private Integer arti_id;
    EditText etxt_patch_title,etxt_patch_content,etxt_patch_tags;
    Button btn_patch_certain;
    final String [] arr_tags = new String[5];
    public static String patch_title,patch_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_patch_posts);

        cookieStore = login_cookie.cookieStore;

        etxt_patch_title = (EditText)findViewById(R.id.etxt_patch_title);
        etxt_patch_content = (EditText)findViewById(R.id.etxt_patch_content);
        etxt_patch_tags = (EditText)findViewById(R.id.etxt_patch_tags);

        btn_patch_certain = (Button)findViewById(R.id.btn_patch_certain);

        arti_id = article_patch_list.arti_id;
        title = article_patch_list.title;
        etxt_patch_title.setText(title);

        btn_patch_certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr_tags[0] = etxt_patch_tags.getText().toString();
                sendJson(etxt_patch_title.getText().toString(),etxt_patch_content.getText().toString(), arr_tags);
                Intent intent = new Intent(article_patchPatch.this,one_article_patch.class);
                startActivity(intent);
            }
        });
    }

    protected void sendJson(final String title, final String content,final String tags[]) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare();
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    ((AbstractHttpClient) client).setCookieStore(cookieStore);
                    //HttpPut patch = new HttpPut("https://richegg.top/posts/"+id);           //android沒有patch功能
                    //HttpPost post = new HttpPost("https://richegg.top/posts/"+id);
                    HttpPatch patch = new HttpPatch("https://richegg.top/posts/"+arti_id);
                    json.put("title", etxt_patch_title.getText().toString());
                    patch_title = etxt_patch_title.getText().toString();
                    json.put("content", etxt_patch_content.getText().toString());
                    patch_content = etxt_patch_content.getText().toString();
                    JSONArray jsonArray = new JSONArray();
                    for(int i=0;i<arr_tags.length;i++) {
                        if(arr_tags[i]!=null) {
                            jsonArray.put(arr_tags[i]);
                        }
                    }
                    json.put("tags", jsonArray);


                    StringEntity se = new StringEntity(json.toString());
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    patch.setEntity(se);
                    response = client.execute(patch);
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
