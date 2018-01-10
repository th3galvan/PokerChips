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

public class SetGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setgame);

        gotoqr();


    }
    public void gotoqr() {

        Button btn_done_gotoqr = (Button) findViewById(R.id.btn_done_gotoqr);

        //hago clic y se abre el layout ShowCode
        btn_done_gotoqr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent generateqr = new Intent(getApplicationContext(), ShowCodeActivity.class);
                startActivity(generateqr);
            }
        });
    }


}
