package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class ModeGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modegame);

        Button btn_fast = (Button)findViewById(R.id.btn_fast);
        Button btn_custom = (Button)findViewById(R.id.btn_custom);

        btn_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_code = new Intent(ModeGameActivity.this, ShowCodeActivity.class);
                startActivity(intent_code);
            }
        });

        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_set_game = new Intent(ModeGameActivity.this, SetGameActivity.class);
                startActivity(intent_set_game);
            }
        });
    }

}

