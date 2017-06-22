package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class one_article_list extends Activity {

    private String title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_article);

        TextView txtv_title = (TextView)findViewById(R.id.txtv_title);
        TextView txtv_content = (TextView)findViewById(R.id.txtv_content);

        title = article_all_list.title;
        txtv_title.setText("title:"+title);
        content = article_all_list.content;
        txtv_content.setText("content:"+content);
    }
}
