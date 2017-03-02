package com.example.test.button;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "#######";
    String[] data = {""};

    RecyclerView rv_statistics;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    Button btn_back;
    TextView tv_statstics, tv_statstics2;

    String URL = "https://button-ed47.restdb.io/";

    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        tv_statstics = (TextView) findViewById(R.id.tv_statstics);
        tv_statstics2 = (TextView) findViewById(R.id.tv_statstics2);

        RestdbApi restdbApi = retrofit.create(RestdbApi.class);
        Call<List<User>> call = restdbApi.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> e = response.body();
                System.out.println(TAG + (e.get(0)).name);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        rv_statistics = (RecyclerView) findViewById(R.id.rv_statistics);
        rv_statistics.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_statistics.setLayoutManager(layoutManager);
        adapter = new AdapterStaristics(data);
        rv_statistics.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Intent int_back = new Intent(this, MainActivity.class);
                startActivity(int_back);
                break;
        }
    }

}