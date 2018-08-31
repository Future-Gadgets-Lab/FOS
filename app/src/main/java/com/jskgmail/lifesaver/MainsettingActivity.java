package com.jskgmail.lifesaver;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

public class MainsettingActivity extends AppCompatActivity {
    static String name="unknown";
    RelativeLayout rl;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        setContentView(R.layout.activity_mainsetting);
        actionBar.setDisplayShowHomeEnabled(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final ImageView imgProfilePicture=findViewById(R.id.imageView3);
        rl=findViewById(R.id.rl);
        SharedPreferences prefs = getSharedPreferences("acckeys",MODE_PRIVATE);
String imgstring=prefs.getString("uri", "");
        Log.e("dddd",imgstring);
        Uri mypic=Uri.parse(imgstring);
        Glide.with(getApplicationContext()).load(mypic).asBitmap()
                .error(R.drawable.ic_person_outline_black_24dp)
                .into(new BitmapImageViewTarget(imgProfilePicture) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(),
                                Bitmap.createScaledBitmap(resource, 50, 50, false));
                        drawable.setCircular(true);
                        imgProfilePicture.setImageDrawable(drawable);
                    }
                });
       username = prefs.getString("username", "");
        String email = prefs.getString("email", "");

        final TextView nam=findViewById(R.id.textView2);
        TextView id=findViewById(R.id.textView4);
        if (username.equals("null"))
        {
            SharedPreferences.Editor editor= getSharedPreferences("acckeys",MODE_PRIVATE).edit();
            editor.putString("username","");
            editor.apply();
        }
         username = prefs.getString("username", "");
        nam.setText(username);
        id.setText(email);
        ImageView edit=findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layoutchange, null);
                final EditText namee=(EditText)alertLayout.findViewById(R.id.editText);
namee.setText(username);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainsettingActivity.this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setTitle("My Account ");
                alert.setIcon(R.drawable.ic_person_outline_rblack_24dp);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor= getSharedPreferences("acckeys",MODE_PRIVATE).edit();
                        editor.putString("username",""+namee.getText().toString());
                        editor.apply();
                        nam.setText(namee.getText().toString());


                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });
        Button signout=(Button)findViewById(R.id.signout);
        if (EmailPasswordActivity.sout==1)
            signout.setText("   Log in to the app");
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                signOut();



            }
        });




        Spinner currsemin = (Spinner) findViewById(R.id.spinner);

        List<String> category1 = new ArrayList<String>();
        category1.add("My Emergency Contacts");
        category1.add("Nearest Hospital and Fire Brigade");
        category1.add("Both");



        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currsemin.setAdapter(dataAdapter1);

        currsemin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        final Switch temp=(Switch)findViewById(R.id.switch4);


        SharedPreferences preference=getSharedPreferences("sensor",MODE_PRIVATE);
        String acce=preference.getString("accn","1");

        SharedPreferences preference1=getSharedPreferences("sensor",MODE_PRIVATE);
        String  tempp=preference1.getString("temp","1");

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(temp.isChecked())
                {
                    SharedPreferences.Editor editor=getSharedPreferences("sensor",MODE_PRIVATE).edit();
                    editor.putString("temp","1");

                    editor.apply();
                }
                else {

                    SharedPreferences.Editor editor=getSharedPreferences("sensor",MODE_PRIVATE).edit();
                    editor.putString("temp","0");

                    editor.apply();
                }




            }
        });





Button but=(Button)findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Snackbar.make(rl,"Long click on emergency contact which you want to delete ",Snackbar.LENGTH_LONG).show();
            }
        });







    }







    private void signOut() {
        EmailPasswordActivity.sout=1;


        Intent i=new Intent(MainsettingActivity.this,EmailPasswordActivity.class);
        startActivity(i);
    }
}
