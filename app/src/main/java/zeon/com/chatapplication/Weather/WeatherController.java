package zeon.com.chatapplication.Weather;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import zeon.com.chatapplication.R;
import zeon.com.chatapplication.back1;

public class WeatherController extends AppCompatActivity {

    // Constants:
    final int REQUEST_CODE = 123;
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";

    // App ID to use OpenWeather data
    //final String APP_ID = "e72ca729af228beabd5d20e3b7749713";
    final String APP_ID = "d1f2d24005e7ac2614e5bad1a8d963d4";
    // Time between location updates (5000 milliseconds or 5 seconds)
    final long MIN_TIME = 5000;
    // Distance between location updates (1000m or 1km)
    final float MIN_DISTANCE = 1000;

    // TODO: Set LOCATION_PROVIDER here:
    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    // Member Variables:
    TextView mCityLabel;
    ImageView mWeatherImage;
    TextView mTemperatureLabel;
    ImageButton mButton;

    // TODO: Declare a LocationManager and a LocationListener here:
    //start or stop location updates
    LocationManager mLocationManager;
    //listen if device move
    LocationListener mLocationListener;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_controller);

        // Linking the elements in the layout to Java code
        mTemperatureLabel =(TextView)findViewById(R.id.tempTV);
        mCityLabel = (TextView)findViewById(R.id.locationTV);
        mButton =(ImageButton)findViewById(R.id.changeCityButton);


        // TODO: Add an OnClickListener to the changeCityButton here:

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherController.this, ChangeCity.class);
                startActivity(intent);
            }
        });


    }


    // TODO: Add onResume() here:
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("Clima","onResume() was called");

        Intent myIntent =getIntent();
        String mycity = myIntent.getStringExtra("City");
        if(mycity != null)
        {
            getWeatherForNewCity(mycity);

        }else {
            Log.d("Clima", "getWeatherForCurrentLocation() was called");
           // getWeatherForCurrentLocation();
        }
    }

    // TODO: Add getWeatherForNewCity(String city) here:
    private void getWeatherForNewCity(String city)
    {
        RequestParams params = new RequestParams();
        params.add("City",city);
        params.add("idapp",APP_ID);
        letsDoSomeNetworking(params);

    }

    // TODO: Add getWeatherForCurrentLocation() here:
    public void getWeatherForCurrentLocation()
    {
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Clima","onLocationChanged() was called");
                String latitude = String.valueOf(location.getLatitude());
                String longtitude = String.valueOf(location.getLongitude());
                RequestParams params = new RequestParams();
                params.add("latitude",latitude);
                params.add("longtitude",longtitude);
                params.add("appid",APP_ID);
                System.out.println("Lat"+latitude);
                System.out.println("long"+longtitude);
                letsDoSomeNetworking(params);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Clima","onProviderDisabled() was called");
                Toast.makeText(WeatherController.this,"Your GPS is Disabled",Toast.LENGTH_SHORT).show();


            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);

    }


    // TODO: Add letsDoSomeNetworking(RequestParams params) here:
    public void letsDoSomeNetworking(RequestParams params)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        final WeatherDataModel dataModel = new WeatherDataModel();
        client.get(WEATHER_URL,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int StatusCode, Header[] header, JSONObject object){
                Log.d("Clima","Success !");
                dataModel.WeatherDataModel(object);
            }
            @Override
            public void onFailure(int StatusCode,Header[] headers,Throwable e ,JSONObject object){
                Log.d("Clima","Failed !");
                Log.d("ClimaObject",object.toString());
                Log.d("ClimaThrowable",e.toString());
                Toast.makeText(WeatherController.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TODO: Add updateUI() here:
    public void updataUI(WeatherDataModel dataModel){
        String Temp , city;
        Temp = dataModel.getTemperature();
        city = dataModel.getCity();
        mCityLabel.setText(city);
        mTemperatureLabel.setText(Temp);
        int resourseid=getResources().getIdentifier(dataModel.getIconName(),"drawable",getPackageName());
        mWeatherImage.setImageResource(resourseid);


    }


    // TODO: Add onPause() here:


    @Override
    protected void onPause() {
        super.onPause();
        if(mLocationManager!=null)
            mLocationManager.removeUpdates(mLocationListener);
    }

}
