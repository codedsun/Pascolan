package com.suneetsrivastava.pascolan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.suneetsrivastava.pascolan.APIData.ApiData;
import com.suneetsrivastava.pascolan.Model.SampleUser;
import com.suneetsrivastava.pascolan.Model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String BASE_URL = "http://pascolan.com/";
    private static int PAGE_NO = 0;
    private Retrofit retrofit;
    ApiData apiData;
    private SampleUser sampleUser = null;
    private static String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ImageView backButton;
    private ImageView nextButton;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
        retrofit = getRetrofitInstance();
        apiData = retrofit.create(ApiData.class);
        fetchData(PAGE_NO);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        nextButton.setOnClickListener(this);
        backButton.setOnClickListener(this);



    }

    private Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

   private void fetchData(int pageNo){
       Call<SampleUser> call = apiData.getSampleUsers(pageNo);
       call.enqueue(new Callback<SampleUser>() {
           @Override
           public void onResponse(Call<SampleUser> call, Response<SampleUser> response) {
               sampleUser = response.body();
               setData();
           }

           @Override
           public void onFailure(Call<SampleUser> call, Throwable t) {

           }
       });

   }

   private void setData(){
        if(sampleUser!=null) {
            recyclerViewAdapter = new RecyclerViewAdapter(this, sampleUser);
            recyclerView.swapAdapter(recyclerViewAdapter, true);
        }else
        {
            Toast.makeText(getBaseContext(), "No Data Available on"+PAGE_NO, Toast.LENGTH_SHORT).show();
        }

   }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton : if(PAGE_NO == 0) {
                Toast.makeText(this, "First Page", Toast.LENGTH_SHORT).show();
                  }
            else{
                PAGE_NO = PAGE_NO-1;
                fetchData(PAGE_NO);
            }
                break;

            case R.id.nextButton :

                PAGE_NO = PAGE_NO+1;

                fetchData(PAGE_NO);
                break;

            }

        }
    }

