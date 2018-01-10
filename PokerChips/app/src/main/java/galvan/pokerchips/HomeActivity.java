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

public class HomeActivity extends AppCompatActivity {

    private int code = 5687;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_new_game =(Button)findViewById(R.id.btn_new_game);
        Button btn_join_game=(Button)findViewById(R.id.btn_join_game);

        btn_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_mode_game = new Intent(HomeActivity.this, ModeGameActivity.class);
                intent_mode_game.putExtra("code",code);
                startActivity(intent_mode_game);
            }
        });

        btn_join_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_join_game = new Intent (HomeActivity.this, JoinGameActivity.class);
                intent_join_game.putExtra("code",code);
                startActivity(intent_join_game);
            }
        });


    }
}
