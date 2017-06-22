package com.example.terry.fast_dev_final;

import android.app.Activity;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class authors_patch extends Activity {

    CookieStore cookieStore = login_cookie.cookieStore;
    EditText etxt_patch_username,etxt_patch_name,etxt_patch_gender,etxt_patch_address;
    Button btn_patch_certain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors_patch);

        etxt_patch_username = (EditText)findViewById(R.id.etxt_patch_username);
        etxt_patch_username.setText(authors_get_method.authors_username);
        etxt_patch_name = (EditText)findViewById(R.id.etxt_patch_name);
        etxt_patch_name.setText(authors_get_method.authors_name);
        etxt_patch_gender = (EditText)findViewById(R.id.etxt_patch_gender);
        etxt_patch_gender.setText(authors_get_method.authors_gender);
        etxt_patch_address = (EditText)findViewById(R.id.etxt_patch_address);
        etxt_patch_address.setText(authors_get_method.authors_address);

        btn_patch_certain = (Button)findViewById(R.id.btn_patch_certain);
        btn_patch_certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authors_patch(
                        etxt_patch_username.getText().toString(),
                        etxt_patch_name.getText().toString(),
                        etxt_patch_gender.getText().toString(),
                        etxt_patch_address.getText().toString());
            }
        });
    }

    protected void authors_patch(final String username, final String name,final String gender,final String address) {
        Thread t = new Thread() {

            public void run() {
                Looper.prepare();
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
                HttpResponse response;
                JSONObject json = new JSONObject();

                try {
                    ((AbstractHttpClient) client).setCookieStore(cookieStore);
                    HttpPatch patch = new HttpPatch("https://richegg.top/authors/"+authors_get_method.authors_username);
                    json.put("username",etxt_patch_username.getText().toString());
                    json.put("name", etxt_patch_name.getText().toString());
                    json.put("gender", etxt_patch_gender.getText().toString());
                    json.put("address", etxt_patch_address.getText().toString());

                    StringEntity se = new StringEntity(json.toString(),"UTF-8");
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
