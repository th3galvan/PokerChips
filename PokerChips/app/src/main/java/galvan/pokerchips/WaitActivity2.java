package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import galvan.pokerchips.Datos.FirebaseReferences;

/**
 * Created by Xavi on 03/02/2018.
 */

public class WaitActivity2 extends AppCompatActivity {

    private String game_id;
    private String name_guest;
    private DatabaseReference inside_ref;
    private DatabaseReference players_join_ref;
    private int players_join;
    private boolean inside;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Bundle code_receive = getIntent().getExtras();
        game_id = code_receive.getString("game_id");
        name_guest = code_receive.getString("name_guest");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        players_join_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERS_JOIN_REFERENCE);
        players_join_ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                players_join = dataSnapshot.getValue(Integer.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        inside_ref = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.INSIDE_REFERENCE);
        if (players_join==0){inside_ref.setValue(false);}
        inside_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                inside = dataSnapshot.getValue(boolean.class);

                if (!inside){
                    Intent intent_wait = new Intent(getApplicationContext(), WaitActivity.class);
                    intent_wait.putExtra("game_id",game_id);
                    intent_wait.putExtra("name_guest",name_guest);
                    startActivity(intent_wait);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
