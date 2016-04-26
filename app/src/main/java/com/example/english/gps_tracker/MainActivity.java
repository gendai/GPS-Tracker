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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    Button track;
    Button mock;
    LocationManager locationManager;
    Boolean activated = false;
    TextView textt;
    LocationListener locationListener;
    String locationProvider = LocationManager.GPS_PROVIDER;
    Timer task = new Timer();
    ArrayList<Location> locations = new ArrayList<>();
    GraphView grpahv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grpahv = (GraphView)findViewById(R.id.graph);
        track = (Button)findViewById(R.id.buttont);
       // mock = (Button)findViewById(R.id.mock);
        textt = (TextView)findViewById(R.id.textrack);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                //Log.d("DEBUG",location.toString());
                if(locations.size() == 100) {
                    locations.remove(0);
                    Log.d("Debug","We hit 100");
                }
                locations.add(location);
                grpahv.setLocations(locations);
                //makeUseOfNewLocation(location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("DEBUG","changed");}

            public void onProviderEnabled(String provider) {
                Log.d("DEBUG",locationProvider.toString());
            }

            public void onProviderDisabled(String provider) {}


        };
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activated) {
                    activated = !activated;
                    textt.setText("GPS Inactive");
                    track.setText("Start Tracking");
                    locationManager.removeUpdates(locationListener);
                    task.cancel();
                } else {
                    activated = !activated;
                    textt.setText("GPS Active");
                    track.setText("Stop Tracking");
                    locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);
                    grpahv.invalidate();
                    //ExecLoc();
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

    private void ExecLoc(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                task.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
                    }
                }, 1, 1);
            }
        });
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
