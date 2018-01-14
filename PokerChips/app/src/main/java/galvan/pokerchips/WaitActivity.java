package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Xavi on 10/01/2018.
 */

public class WaitActivity extends AppCompatActivity {

    public static int milisegungos_espera = 5000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        esperar(milisegungos_espera);
    }
    public void esperar(int milisegundos){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_game= new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent_game);
            }
        },milisegundos);
    }
}
