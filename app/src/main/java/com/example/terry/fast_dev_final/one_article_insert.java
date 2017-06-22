package com.example.terry.fast_dev_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class one_article_insert extends AppCompatActivity {

    private String title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_article);

        TextView txtv_title = (TextView)findViewById(R.id.txtv_title);
        TextView txtv_content = (TextView)findViewById(R.id.txtv_content);

        title = article_insertPosts.insert_title;
        txtv_title.setText("title:"+title);
        content = article_insertPosts.insert_content;
        txtv_content.setText("content:"+content);
    }
}
