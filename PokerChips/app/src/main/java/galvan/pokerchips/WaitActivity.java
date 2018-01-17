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

public class WaitActivity extends AppCompatActivity {

    private String game_id;
    private DatabaseReference host_ready_ref;
    private Boolean host_ready;
    private DatabaseReference game_id_ref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
/*
        Bundle code_receive = getIntent().getExtras();
        game_id = code_receive.getString("game_id");*/

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


       /*
        game_id_ref = database.getReference(FirebaseReferences.GAME_ID_REFERENCE);
        game_id_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                game_id = dataSnapshot.getValue(String.class);
                Log.i("KK",String.format("%s",game_id));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        host_ready_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.HOST_READY_REFERENCE);
        host_ready_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                host_ready = dataSnapshot.getValue(boolean.class);
                if (host_ready){
                    Intent intent_game = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(intent_game);}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/


    }

}
