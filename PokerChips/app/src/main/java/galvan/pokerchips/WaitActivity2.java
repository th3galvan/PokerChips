package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import galvan.pokerchips.Datos.FirebaseReferences;

/**
 * Created by Xavi on 10/01/2018.
 */

public class WaitActivity2 extends AppCompatActivity {

    private String game_id;
    private DatabaseReference host_ready_ref;
    private Boolean host_ready;
    private DatabaseReference game_id_ref;
    private String name_guest;
    private FirebaseDatabase database;
    private DatabaseReference players_join_ref;
    private Integer players_join;
    private DatabaseReference game_ref;
    private boolean inside = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Bundle code_receive = getIntent().getExtras();
        game_id = code_receive.getString("game_id");
        name_guest = code_receive.getString("name_guest");

        database = FirebaseDatabase.getInstance();

        players_join_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERS_JOIN_REFERENCE);
        players_join_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                players_join = dataSnapshot.getValue(Integer.class);

                Intent intent_wait2 = new Intent(getApplicationContext(), WaitActivity.class);
                intent_wait2.putExtra("game_id",game_id);
                intent_wait2.putExtra("name_guest",name_guest);
                intent_wait2.putExtra("players_join",players_join);
                startActivity(intent_wait2);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}

