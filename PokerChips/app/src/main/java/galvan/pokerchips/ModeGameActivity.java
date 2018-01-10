package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class ModeGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modegame);

        custom();
        fast();
    }

        public void custom() {

            Button btn_custom = (Button) findViewById(R.id.btn_custom);

            //hago clic y se abre el layout SetGame
            btn_custom.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent customgame = new Intent(getApplicationContext(), SetGameActivity.class);
                    startActivity(customgame);
                }
            });
        }


    public void fast() {

        Button btn_fast = (Button) findViewById(R.id.btn_fast);

        //hago clic y se abre el layout ShowCode
        btn_fast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent fastgame = new Intent(getApplicationContext(), ShowCodeActivity.class);
                startActivity(fastgame);
            }
        });
    }


}

