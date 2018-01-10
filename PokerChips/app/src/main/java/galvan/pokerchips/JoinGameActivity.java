package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class JoinGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);

        start();

    }
    public void start() {

        Button btn_scanQR = (Button) findViewById(R.id.btn_scanQR);

        //hago clic y se abre el layout GameActivity
        //TODO: de momento se abre siempre sin guardar nombre y pasando del codigo
        btn_scanQR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startgame = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(startgame);
            }
        });
    }
}
