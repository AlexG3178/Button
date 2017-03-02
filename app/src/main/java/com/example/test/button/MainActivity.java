package com.example.test.button;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_counter;
    private Button btn_button, btn_statistics;
    int count = 0;
    SharedPreferences sPref;
    final String SAVED_COUNT = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv_counter = (TextView) findViewById(R.id.tv_counter);
        btn_button = (Button) findViewById(R.id.btn_button);
        btn_statistics = (Button) findViewById(R.id.btn_statistics);

        btn_button.setOnClickListener(this);
        btn_statistics.setOnClickListener(this);

        try {loadCount();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_button:
                count += 1;
                String cnt = Integer.toString(count);
                tv_counter.setText(cnt);
                break;
            case R.id.btn_statistics:
                Intent int_statistics = new Intent(this, StatisticsActivity.class);
                startActivity(int_statistics);
                break;
        }
    }



    private void saveCount() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(SAVED_COUNT, tv_counter.getText().toString());
        editor.commit();
    }

    public void loadCount() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_COUNT, "");
        count = Integer.valueOf(savedText);
        tv_counter.setText(savedText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveCount();
    }
}