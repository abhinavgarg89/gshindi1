package com.gshindi.android.gshindi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuestionSetListActivity extends AppCompatActivity {

    private JSONArray jArray;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_set_list);

        InputStream inputStream = getResources().openRawResource(R.raw.question_set_list);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("Text Data", byteArrayOutputStream.toString());

        JSONObject jObject = null;
        ArrayList<String> questionSetList = new ArrayList<>();
        try {
            jObject = new JSONObject(byteArrayOutputStream.toString());
            jArray = jObject.getJSONArray("quesionSets");
            for(int index = 0; index < jArray.length(); index++){
                questionSetList.add(jArray.getString(index));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        QuestionSetArrayAdapter questionSetArrayAdapter = new QuestionSetArrayAdapter(this, questionSetList.toArray(new String[questionSetList.size()]));
        listView = (ListView) findViewById(R.id.question_set_list);
        listView.setAdapter(questionSetArrayAdapter);

        AdapterView.OnItemClickListener adapterView = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = (String) listView.getAdapter().getItem(position);
                Intent myIntent = new Intent(QuestionSetListActivity.this, HomeActivity.class);
                myIntent.putExtra("selectedQuestionPaper", selectedValue);
                QuestionSetListActivity.this.startActivity(myIntent);
                finish();
            }
        };
        listView.setOnItemClickListener(adapterView);
    }

}
