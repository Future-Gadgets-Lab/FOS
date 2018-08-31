package com.jskgmail.lifesaver;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class Main22Activity extends AppCompatActivity {
    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        ImageView imageView=findViewById(R.id.imageView);
        ImageView imageView1=findViewById(R.id.training);

        final RelativeLayout rl=findViewById(R.id.rl);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(rl,"Fire Brigades are reaching",Snackbar.LENGTH_LONG).show();

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
}