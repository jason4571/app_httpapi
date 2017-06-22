package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private String result_posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new article_get_jsonarray().execute("https://richegg.top/posts");
        new authors_get_method().execute("https://richegg.top/login");

        Button btn_art = (Button)findViewById(R.id.btn_art);
        btn_art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result_posts = article_get_jsonarray.result;
                Intent intent = new Intent(MainActivity.this,article_all_list.class);
                startActivity(intent);
            }
        });

        Button btn_ins = (Button)findViewById(R.id.btn_ins);
        btn_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,article_insertPosts.class);
                startActivity(intent);
            }
        });

        Button btn_pat = (Button)findViewById(R.id.btn_pat);
        btn_pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,article_patch_list.class);
                startActivity(intent);
            }
        });

        Button btn_del = (Button)findViewById(R.id.btn_del);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,article_delete_list.class);
                startActivity(intent);
            }
        });

        Button btn_aut_get = (Button)findViewById(R.id.btn_authors_get);
        btn_aut_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,authors_get_layout.class);
                startActivity(intent);
            }
        });
    }
}
