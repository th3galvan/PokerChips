package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import galvan.pokerchips.Datos.FirebaseReferences;

/**
 * Created by Xavi on 03/02/2018.
 */

public class WaitActivity3 extends AppCompatActivity {

    private DatabaseReference host_ready_ref;
    private FirebaseDatabase database;
    private DatabaseReference players_join_ref;
    private String game_id;
    private int players_join;
    private boolean host_ready;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        Bundle code_receive = getIntent().getExtras();
        game_id = code_receive.getString("game_id");
        players_join = code_receive.getInt("players_join");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        players_join_ref = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERS_JOIN_REFERENCE);
        players_join_ref.setValue(players_join);


        host_ready_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.HOST_READY_REFERENCE);
        host_ready_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                host_ready = dataSnapshot.getValue(boolean.class);
                if (host_ready) {
                    Intent intent_game = new Intent(getApplicationContext(), GameActivity.class);
                    intent_game.putExtra("game_id", game_id);
                    startActivity(intent_game);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
