package com.example.terry.fast_dev_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class article_patch_list extends Activity {

    private ArrayAdapter<String> listAdapter;
    private ArrayList<Integer> arrli_id = new ArrayList<>();
    private ArrayList<String> arrli_title = new ArrayList<>();
    private ArrayList<String> arrli_content = new ArrayList<>();
    public static String title,content;
    public static Integer arti_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        arrli_id = article_get_jsonarray.arrli_id;
        arrli_title = article_get_jsonarray.arrli_title;
        arrli_content = article_get_jsonarray.arrli_content;

        ListView listView = (ListView) findViewById(R.id.listview_art);
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrli_title);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(article_patch_list.this,article_patchPatch.class);
                arti_id = arrli_id.get(position);
                title = arrli_title.get(position);
                content = arrli_content.get(position);
                startActivity(intent);
            }
        });
    }
}
