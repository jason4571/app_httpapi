package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import org.json.JSONObject;
import java.io.InputStream;

public class login_cookie extends Activity {

    EditText etxt_id,etxt_pwd;
    TextView txtv_cookie;
    public static CookieStore cookieStore;
    public static HttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cookie);

        etxt_id = (EditText)findViewById(R.id.etxt_id);
        etxt_pwd = (EditText)findViewById(R.id.etxt_pwd);
        txtv_cookie = (TextView)findViewById(R.id.txt_cookie_test);
        Button btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendJson(etxt_id.getText().toString(),etxt_pwd.getText().toString());
                Intent intent = new Intent(login_cookie.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void sendJson(final String username, final String password) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare();
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    HttpPost post = new HttpPost("https://richegg.top/login");
                    json.put("username", etxt_id.getText().toString());
                    json.put("password", etxt_pwd.getText().toString());
                    StringEntity se = new StringEntity( json.toString());
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    post.setEntity(se);
                    response = client.execute(post);
                    String test = response.toString();
                    if(response!=null){
                        InputStream in = response.getEntity().getContent();
                    }

                    //CookieStore cookies=((AbstractHttpClient)client).getCookieStore();
                    cookieStore =((AbstractHttpClient)client).getCookieStore();

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
