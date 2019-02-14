package vitcon.example.baucua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    ImageView imgBat;
    Button btna;
    private Animation goin;
    private Animation goout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgBat = findViewById(R.id.img_bat);
        goout = AnimationUtils.loadAnimation(this,R.anim.anim_go_out);
        btna = findViewById(R.id.btna);
        goout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("vitcona", "onAnimationStart: go out");
                btna.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btna.setVisibility(View.VISIBLE);
                Log.d("vitcona", "onAnimationEnd:  go out");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        goin = AnimationUtils.loadAnimation(this,R.anim.anim_go_in);
        goin.setAnimationListener(this);
    }

    public void open(View view) {
        imgBat.startAnimation(goout);
        imgBat.setVisibility(View.INVISIBLE);
    }

    public void close(View view) {
        imgBat.startAnimation(goin);
        imgBat.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (animation == goin){
            Log.d("vitcona", "onAnimationStart: go in");
            btna.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == goin){
            btna.setVisibility(View.VISIBLE);
            Log.d("vitcona", "onAnimationEnd: go in");

        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
