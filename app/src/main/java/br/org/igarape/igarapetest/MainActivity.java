package br.org.igarape.igarapetest;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }

        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
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

    public void onLocationChanged(Location location) {
        Location mCurrentLocation = location;
        TextView txtLatitude = (TextView)findViewById(R.id.txtLatitudeValue);
        TextView txtLongitude = (TextView)findViewById(R.id.txtLongitudeValue);

        txtLatitude.setText(String.valueOf(mCurrentLocation.getLatitude()));
        txtLongitude.setText(String.valueOf(mCurrentLocation.getLongitude()));
    }


    //@Override
    public void onProviderEnabled(String provider) {}
    public void onProviderDisabled(String provider) {}
    public void onStatusChanged(String a, int b, Bundle c) {}
    protected void stopLocationUpdates() {}
    public void onResume() {super.onResume();}

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

}
