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

    private int players_join;
    private int number_players=4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Bundle code_receive = getIntent().getExtras();
        players_join = code_receive.getInt("players_join");

        if (players_join==number_players){
        Intent intent_game = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent_game);}

    }

}
