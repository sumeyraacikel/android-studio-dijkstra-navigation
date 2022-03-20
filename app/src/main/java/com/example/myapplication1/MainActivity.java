package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.graphics.Point;
import android.os.Bundle;
import java.util.ArrayList;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings({"ALL", "UnnecessaryParentheses"})

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ImageButton tool;
    private ListView lv;
    UiSettings mapSettings;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    ArrayList<Point> points = new ArrayList<Point>();
    MarkerOptions destination;
    int tagNumber;
    boolean places;
    String[] orientOptions;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pointCreator();
        setContentView(R.layout.activity_main);
        String[] products = pointTags();
        orientOptions = new String[13];
        lv = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        inputSearch = (EditText) findViewById(R.id.editText);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.adapter.getFilter().filter(cs);
                lv.setAdapter(adapter);
                lv.setVisibility(View.VISIBLE);
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            public void afterTextChanged(Editable arg0) {
            }

        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long id) {
                Object o = adapter.getItemAtPosition(position);
                String str_text = o.toString();
                inputSearch.setText(str_text, TextView.BufferType.EDITABLE);
            }
        });
        tool = (ImageButton) findViewById(R.id.tool);
        tool.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, tool);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == (R.id.ge100Mode)) {
                            Intent i = new Intent(getApplicationContext(), ge100.class);
                            startActivity(i);
                        }
                        if (item.getItemId() == (R.id.showPlaces)) {
                            mMap.clear();
                            if (places == false) {
                                mMap.addMarker(new MarkerOptions().title("Marmara Restarurant").position(new LatLng(39.87076202058893, 32.75043960660696)));
                                mMap.addMarker(new MarkerOptions().title("CafeInn").position(new LatLng(39.869907449323705, 32.750478498637676)));
                                mMap.addMarker(new MarkerOptions().title("Coffee Break").position(new LatLng(39.86943474152441, 32.75011774152517)));
                                mMap.addMarker(new MarkerOptions().title("Express Cafe").position(new LatLng(39.86883954193255, 32.74950485676527)));
                                mMap.addMarker(new MarkerOptions().title("Coffee Break").position(new LatLng(39.868212942748535, 32.74902977049351)));
                                mMap.addMarker(new MarkerOptions().title("Starbucks").position(new LatLng(39.86733106225063, 32.75035947561264)));
                                mMap.addMarker(new MarkerOptions().title("Cafe Alis").position(new LatLng(39.86710975401423, 32.75029040873051)));
                                mMap.addMarker(new MarkerOptions().title("Express Cafe").position(new LatLng(39.86632847876406, 32.74922322481871)));
                                mMap.addMarker(new MarkerOptions().title("Speed & Kıraç").position(new LatLng(39.8660330535252, 32.7485191449523)));
                                mMap.addMarker(new MarkerOptions().title("Bilka").position(new LatLng(39.86456800347043, 32.74780835956335)));
                                places = true;
                            } else if (places == true) {
                                places = false;
                            }
                        }
                        if (item.getItemId() == (R.id.options)) {
                        }
                        if (item.getItemId() == (R.id.item_about)) {
                            Intent i = new Intent(getApplicationContext(), about.class);
                            startActivity(i);
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });
    }
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng bilkent = new LatLng(39.869950, 32.7489783);
        Marker marker = mMap.addMarker(new MarkerOptions().position(bilkent));
        assert marker != null;
        marker.setVisible(true);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);
        }
        mapSettings = mMap.getUiSettings();
        mapSettings.setCompassEnabled(true);
        mapSettings.setMapToolbarEnabled(false);
        mapSettings.setRotateGesturesEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bilkent, 16));

        mMap.setOnCameraChangeListener(getCameraChangeListener());
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng arg0) {

                //Log.d("arg0", arg0.toString());
                //mMap.addMarker(new MarkerOptions().position(arg0));
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });

    }
    public void onSearch(View view) {

        String location = inputSearch.getText().toString().toLowerCase();

        if (location != null || !location.equals("")) {
            for (int i = 0; i < points.size(); i++) {
                for (int j = 0; j < points.get(i).getName().size(); j++) {
                    if (points.get(i).getName().get(j).equals(location)) {
                        mMap.clear();
                        destination = new MarkerOptions();
                        destination.position(points.get(i).getName);
                        destination.title("Destination" + location);
                        mMap.addMarker(destination);
                        if (destination != null) {
                            //float zoom = mMap.getCameraPosition().zoom;
                            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getNewZoom(), zoom));
                        }
                    }
                }
            }
        }
    }
    public GoogleMap.OnCameraChangeListener getCameraChangeListener() {
        return new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(@NonNull CameraPosition position) {

            }
        };
    }
    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
    public LatLng getLoc() {
        LatLng result = new LatLng(0, 0);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            result = new LatLng(latitude, longitude);
        }
        return result;

    }
    public LatLng getNewZoom() {
        LatLng a = getLoc();
        LatLng b = destination.getPosition();
        LatLng result = new LatLng(((a.latitude + b.latitude) / 2), ((a.longitude + b.longitude) / 2));
        return result;
    }
    public void pointCreator() {
        String text = "points.txt"; //your text file name in the assets folder
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(text);
            int size = is.available(); //size of the file in bytes
            buffer = new byte[size]; //declare the size of the byte array with size of the file
            is.read(buffer); //read file
            is.close(); //close file
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str_data = new String(buffer);

        tagNumber = 0;
        while (str_data.indexOf("?") > 0) {
            String name = str_data.substring(0, str_data.indexOf("?"));
            str_data = str_data.substring(str_data.indexOf("?") + 1);
            ArrayList<String> tags = new ArrayList<String>();
            while (name.indexOf(",") > 0) {
                name.replaceAll(" ", "");
                tags.add(name.substring(0, name.indexOf(",")));
                if(!name.substring(0, name.indexOf(",")).contains("point")) {
                    tagNumber++;
                }
                name = name.substring(name.indexOf(",") + 1);
            }
            Log.d("tagnumber",tagNumber+"");
            double lat = Double.parseDouble(str_data.substring(0, str_data.indexOf("?")));
            str_data = str_data.substring(str_data.indexOf("?") + 1);
            double lon = Double.parseDouble(str_data.substring(0, str_data.indexOf("?")));
            str_data = str_data.substring(str_data.indexOf("?") + 1);
            LatLng coordinate = new LatLng(lat, lon);
            Point point = new Point(tags, coordinate);
            points.add(point);
        }


    }
    public String[] pointTags() {
        String[] result = new String[tagNumber];
        int count = 0;
        for (int i = 0; i < points.size(); i++) {

            for (int j = 0; j < points.get(i).getName().size(); j++) {
                if(!points.get(i).getName().get(j).contains("point"))
                {result[count] = points.get(i).getName().get(j);
                    count++;
                    Log.d("Count", count + "  " + points.get(i).getName().get(j));}
            }
        }
        Log.d("COunt", (count - 1) + "");
        return result;
    }


}