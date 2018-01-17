package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import galvan.pokerchips.Datos.FirebaseReferences;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class HomeActivity extends AppCompatActivity {

    private int code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //subo valor de player_join_a firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference code_reference = database.getReference(FirebaseReferences.CODE_REFERENCE);
        code_reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                code = dataSnapshot.getValue(Integer.class);
                code++;
                if (code ==9999){
                    code=1000;

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                    Log.e("ERROR", databaseError.getMessage());
            }
        });

        code_reference.setValue(code);
        Button btn_new_game =(Button)findViewById(R.id.btn_new_game);
        Button btn_join_game=(Button)findViewById(R.id.btn_join_game);
        Button btn_offline = (Button)findViewById(R.id.btn_offline);

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
        /*
        btn_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_join_game2 = new Intent (HomeActivity.this, JoinGameActivity2.class);
                startActivity(intent_join_game2);
            }
        });*/



    }
}
