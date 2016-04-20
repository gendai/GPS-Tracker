package com.example.english.gps_tracker;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button track;
    Button mock;
    LocationManager locationManager;
    Boolean activated = false;
    TextView textt;
    LocationListener locationListener;
    String locationProvider = LocationManager.GPS_PROVIDER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        track = (Button)findViewById(R.id.buttont);
       // mock = (Button)findViewById(R.id.mock);
        textt = (TextView)findViewById(R.id.textrack);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Log.d("DEBUG",location.toString());
                //makeUseOfNewLocation(location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("DEBUG","changed");}

            public void onProviderEnabled(String provider) {
                Log.d("DEBUG",locationProvider);
            }

            public void onProviderDisabled(String provider) {}
        };
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activated){
                    activated = !activated;
                    textt.setText("GPS Inactive");
                    track.setText("Start Tracking");
                    //locationManager.removeUpdates(locationListener);
                }else{
                    activated = !activated;
                    textt.setText("GPS Active");
                    track.setText("Stop Tracking");
                    //locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
                }
            }
        });
        /*mock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMockLocation();
            }

            });*/
    }

    private void getMockLocation()
    {
        Location l = new Location(locationProvider);
        l.setLatitude(1.2345d);
        l.setLongitude(1.2345d);
        l.setAccuracy(0.01f);
        l.setTime(System.currentTimeMillis());
        l.setElapsedRealtimeNanos(System.currentTimeMillis());
        locationManager.addTestProvider(locationProvider, false,false, false, false, false, true, true, 0, 5);
        locationManager.setTestProviderLocation(locationProvider,l);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
