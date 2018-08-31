package com.jskgmail.lifesaver;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class Main22Activity extends AppCompatActivity {
    private static final String TAG = "sssssssss";
    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        ImageView imageView=findViewById(R.id.imageView);
        ImageView imageView1=findViewById(R.id.training);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final RelativeLayout rl=findViewById(R.id.rl);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layoutseverity, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(Main22Activity.this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setTitle("Fire Info ");
                alert.setIcon(R.drawable.ic_add_alert_black_24dp);
              //  alertLayout.findViewById(R.id.listname);
            //    FloatingTextButton fab11=alertLayout.findViewById(R.id.floatingActionButton);
              //  final TextView textView=alertLayout.findViewById(R.id.text);

             SeekBar   criteria = (SeekBar) alertLayout.findViewById(R.id.seekBar);
                SeekBar   criteria1 = (SeekBar) alertLayout.findViewById(R.id.seekBar1);

                final String androidId = Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID);

              //  criteria.setProgress(Integer.parseInt(recrit));
                final TextView critsh=(TextView)alertLayout.findViewById(R.id.textView38);
                //critsh.setText(recrit+"%");
                final String[] spread = new String[1];
                final String[] severity = new String[1];
                criteria.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //    progress=progress-progress%1;
                        critsh.setText(progress+" ");
                        severity[0] = String.valueOf(progress);
                        //Log.e("thecriteria",crit);
                        //recrit=crit;
                        //critt = 100 - Integer.parseInt(crit);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });



                final TextView critsh1=(TextView)alertLayout.findViewById(R.id.textView381);
                //critsh.setText(recrit+"%");
                criteria1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress=progress-progress%30;
                        critsh1.setText(progress+" degrees");
                        spread[0] = String.valueOf(progress);
                        //Log.e("thecriteria",crit);
                        //recrit=crit;
                        //critt = 100 - Integer.parseInt(crit);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });


                alert.setPositiveButton("GET HELP", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        final DatabaseReference myRef = database.getReference("user");

                        DatabaseReference myRef1 = myRef.child(androidId);
                        DatabaseReference myRef2 = myRef1.child(androidId);

                        DatabaseReference myRef11 = myRef2.child("lat");

                        myRef11.setValue(MainActivity.lat);
                        DatabaseReference myRef12 = myRef2.child("long");

                        myRef12.setValue(MainActivity.longi);
                        DatabaseReference myRef13 = myRef2.child("severity");

                        myRef13.setValue(severity[0]);
                        DatabaseReference myRef14 = myRef2.child("spread");

                        myRef14.setValue(spread[0]);


                       // myRef.setValue("Hello!");
                        Toast.makeText(getApplicationContext(), androidId, Toast.LENGTH_SHORT).show();


                        final int[] ch = {1};


// Read from the database
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                String spot = dataSnapshot.getValue(String.class);
                                double lat = Double.parseDouble(null);
                                double longi,distbtw2latlong;
                                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                                {
                                    if (dataSnapshot1.getKey().equals("lat"))
                                    {
                                        lat= Double.parseDouble(String.valueOf(dataSnapshot1.getValue()));
                                    }
                                    else if (dataSnapshot1.getKey().equals("long"))
                                    { longi= Double.parseDouble(String.valueOf(dataSnapshot1.getValue()));
                                      distbtw2latlong=  getDistanceFromLatLonInKm(MainActivity.lat,MainActivity.longi,lat,longi);
                                      Toast.makeText(getApplicationContext(),distbtw2latlong+"",Toast.LENGTH_LONG).show();
                                     /* if (distbtw2latlong<=1)
                                      {

                                          DatabaseReference myRef1 = myRef.child(spot);
                                          DatabaseReference myRef2 = myRef1.child(androidId);

                                          DatabaseReference myRef11 = myRef2.child("lat");

                                          myRef11.setValue(MainActivity.lat);
                                          DatabaseReference myRef12 = myRef2.child("long");

                                          myRef12.setValue(MainActivity.longi);
                                          DatabaseReference myRef13 = myRef2.child("severity");

                                          myRef13.setValue(severity[0]);
                                          DatabaseReference myRef14 = myRef2.child("spread");

                                          myRef14.setValue(spread[0]);


                                          ch[0] =0;

                                      }*/
                                    }


                                }



                                Log.d(TAG, "Value of spot: " + spot);

/*if (ch[0] ==1)
{


    DatabaseReference myRef1 = myRef.child(androidId);
    DatabaseReference myRef2 = myRef1.child(androidId);

    DatabaseReference myRef11 = myRef2.child("lat");

    myRef11.setValue(MainActivity.lat);
    DatabaseReference myRef12 = myRef2.child("long");

    myRef12.setValue(MainActivity.longi);
    DatabaseReference myRef13 = myRef2.child("severity");

    myRef13.setValue(severity[0]);
    DatabaseReference myRef14 = myRef2.child("spread");

    myRef14.setValue(spread[0]);


}
*/

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.w(TAG, "Failed to read value.", error.toException());
                            }
                        });







                    }});

                        AlertDialog dialog = alert.create();
                        dialog.show();












              //  Snackbar.make(rl,"Fire Brigades are reaching",Snackbar.LENGTH_LONG).show();

            }
        });


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main22Activity.this,MainActivity.class));
            }
        });




        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_2);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_2);
        Log.e("ppp", ""+bmb.getPiecePlaceEnum().pieceNumber());

        final Uri[] call = new Uri[1];
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            if(index==0)
                                call[0] =Uri.parse("tel:"+"102");
                            else if (index==1)call[0] =Uri.parse("tel:"+"100");
                            else if (index==2)call[0] =Uri.parse("tel:"+"101");
                            else if (index==3)call[0] =Uri.parse("tel:"+"181");

                            Intent surf=new Intent(Intent.ACTION_DIAL, call[0]);
                            startActivity(surf);

                        }
                    })

                    .normalImageRes(BuilderManager.getImageResource())
                    .normalText(BuilderManager.getImageText())
                    .pieceColor(Color.WHITE).normalColor(Color.WHITE);


            bmb.addBuilder(builder);
        }




    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }





    double getDistanceFromLatLonInKm(double lat1,double lon1,double lat2,double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }









}
