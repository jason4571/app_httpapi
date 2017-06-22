package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.InputStream;

public class authors_get_layout extends Activity {

    private CookieStore cookieStore = login_cookie.cookieStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors_get);

        TextView txt_authors_username = (TextView)findViewById(R.id.txt_authors_username);
        txt_authors_username.setText("username:"+authors_get_method.authors_username);
        TextView txt_authors_user = (TextView)findViewById(R.id.txt_authors_name);
        txt_authors_user.setText("name:"+authors_get_method.authors_name);
        TextView txt_authors_gender = (TextView)findViewById(R.id.txt_authors_gender);
        txt_authors_gender.setText("gender:"+authors_get_method.authors_gender);
        TextView txt_authors_address = (TextView)findViewById(R.id.txt_authors_address);
        txt_authors_address.setText("address:"+authors_get_method.authors_address);

        Button btn_authors_patch = (Button)findViewById(R.id.btn_authors_patch);
        btn_authors_patch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(authors_get_layout.this,authors_patch.class);
                startActivity(intent);
            }
        });
    }
}
