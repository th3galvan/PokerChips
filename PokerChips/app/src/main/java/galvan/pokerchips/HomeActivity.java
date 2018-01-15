package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    private int code=5694;
    private int players_join;
    private DatabaseReference players_join_ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference code_reference = database.getReference(FirebaseReferences.CODE_REFERENCE);

        players_join_ref = database.getReference(FirebaseReferences.PLAYERS_JOIN_REFERENCE);
        players_join_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                players_join = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        code_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                code = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                    Log.e("ERROR", databaseError.getMessage());
            }
        });

        code_reference.setValue(code);

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
                intent_join_game.putExtra("players_join",players_join);
                startActivity(intent_join_game);
            }
        });


    }
}
