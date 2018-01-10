package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class SetGameActivity extends AppCompatActivity {

    private EditText edit_initial_chips;
    private EditText edit_name;
    private EditText edit_number_players;
    private EditText edit_big;

    private String initial_chips;
    private String name;
    private String number_players;
    private String big;

    private int code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setgame);

        Bundle code_receive = getIntent().getExtras();
        code = code_receive.getInt("code");

        edit_initial_chips = (EditText)findViewById(R.id.edit_initial_chips);
        edit_name = (EditText)findViewById(R.id.edit_host_name);
        edit_number_players = (EditText)findViewById(R.id.edit_players_number);
        edit_big = (EditText)findViewById(R.id.edit_first_big_value);

        gotoqr();


    }
    public void gotoqr() {

        Button btn_done_gotoqr = (Button) findViewById(R.id.btn_done_gotoqr);

        //hago clic y se abre el layout ShowCode
        btn_done_gotoqr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent generateqr = new Intent(getApplicationContext(), ShowCodeActivity.class);
                generateqr.putExtra("code",code);
                startActivity(generateqr);
            }
        });
    }


}
