package com.example.admin.week4test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.week4test.model.SchoolModel;
import com.example.admin.week4test.retrofit.RetrofitArrayAPI;
import com.example.admin.week4test.retrofit.RetrofitObjectAPI;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView text_name_1;
    private TextView text_email_1;
    private TextView text_location_1;
    private TextView text_name_2;
    private TextView text_email_2;
    private TextView text_location_2;
    private static final String url = "https://data.cityofnewyork.us/resource/97mf-9njv.json/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_name_1 = findViewById(R.id.text_name_1);
        text_email_1 = findViewById(R.id.text_email_1);
        text_location_1 = findViewById(R.id.text_location_1);
        text_name_2 = findViewById(R.id.text_name_2);
        text_email_2 = findViewById(R.id.text_email_2);
        text_location_2 = findViewById(R.id.text_location_2);
        Button buttonArray = findViewById(R.id.RetrofitArray);
        Button buttonObject = findViewById(R.id.RetrofitObject);
        buttonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View VisibleArray = findViewById(R.id.RetrofitArray);
                VisibleArray.setVisibility(View.GONE);
                View VisibleObject = findViewById(R.id.RetrofitObject);
                VisibleObject.setVisibility(View.GONE);
                getRetrofitArray();
            }
        });

        buttonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View VisibleArray = findViewById(R.id.RetrofitArray);
                VisibleArray.setVisibility(View.GONE);
                View VisibleObject = findViewById(R.id.RetrofitObject);
                VisibleObject.setVisibility(View.GONE);
                getRetrofitObject();
            }
        });

    }

    void getRetrofitObject() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

        Call<SchoolModel> call = service.getStudentDetails();

        call.enqueue(new Callback<SchoolModel>() {
            @Override
            public void onResponse(Call<SchoolModel> call, Response<SchoolModel> response) {
                try {

                    text_name_1.setText("SchoolName  :  " + response.body().getSchoolName());
                    text_email_1.setText("SchoolEmail  :  " + response.body().getSchoolEmail());
                    text_location_1.setText("SchoolLocation  : " + response.body().getLocation());

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SchoolModel> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    void getRetrofitArray() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);

        Call<List<SchoolModel>> call = service.getStudentDetails();

        call.enqueue(new Callback<List<SchoolModel>>() {
            @Override
            public void onResponse(Call<List<SchoolModel>> call, Response<List<SchoolModel>> response) {
                try {

                    List<SchoolModel> SchoolData = response.body();

                    for (int i = 0; i < SchoolData.size(); i++) {

                        if (i == 0) {
                            text_name_1.setText("SchoolName  :  " + SchoolData.get(i).getSchoolName());
                            text_email_1.setText("SchoolEmail  :  " + SchoolData.get(i).getSchoolEmail());
                            text_location_1.setText("SchoolLocation  : " + SchoolData.get(i).getLocation());
                        } else if (i == 1) {
                            text_name_2.setText("StudentId  :  " + SchoolData.get(i).getSchoolName());
                            text_email_2.setText("StudentName  :  " + SchoolData.get(i).getSchoolEmail());
                            text_location_2.setText("StudentMarks  : " + SchoolData.get(i).getLocation());
                        }
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<SchoolModel>> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }

        });
    }
}
