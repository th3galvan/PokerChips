package galvan.pokerchips;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setgame);

        edit_initial_chips = (EditText)findViewById(R.id.edit_initial_chips);
        edit_name = (EditText)findViewById(R.id.edit_host_name);
        edit_number_players = (EditText)findViewById(R.id.edit_players_number);
        edit_big = (EditText)findViewById(R.id.edit_first_big_value);







    }
}
