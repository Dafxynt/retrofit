package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/3/") // Corrected base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create ApiService instance
        apiService = retrofit.create(ApiService.class);

        // Call the method to get the team data
        getAllTeams("English Premier League");
    }

    private void getAllTeams(String league) {
        Call<TeamResponse> call = apiService.getTeams(league);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    TeamResponse teamResponse = response.body();
                    if (teamResponse != null && teamResponse.getTeams() != null) {
                        for (Team team : teamResponse.getTeams()) {
                            // Access team data here
                            Log.d(TAG, "Team ID: " + team.getIdTeam());
                            Log.d(TAG, "Team Name: " + team.getStrTeam());
                            // Add more data access as needed
                        }
                    } else {
                        Log.e(TAG, "Empty response or null data");
                    }
                } else {
                    Log.e(TAG, "Unsuccessful response: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                // Handle network failure
                Log.e(TAG, "Network failure", t);
            }
        });
    }
}
