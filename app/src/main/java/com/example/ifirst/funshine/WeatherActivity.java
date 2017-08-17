package com.example.ifirst.funshine;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, LocationListener{

    final String URL_BASE = "http://api.openweathermap.org/data/2.5/forecast";
    final String URL_COORD = "?lat=";//31.7214064&lon=120.315264";
    final String URL_UNITS = "&units=imperial";
    final String URL_API_KEY = "&APPID=ed437169ddfeea91c3c6c15d4de60434";
    //18.7894013,98.9575217 :: Japan 31.7214064,120.315264

    private GoogleApiClient mGoogleApiClient;

    String testGetJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    public void downloadWeatherData() {
        final String url = URL_BASE + URL_COORD + URL_API_KEY;

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Fun", "Res: " + response.toString());
                        testGetJSON = response.toString();
                        Toast.makeText(getApplicationContext(), testGetJSON, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Fun", "Err:" + error.getLocalizedMessage());
            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
