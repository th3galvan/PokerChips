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
        players_join = code_receive.getInt("players_join");


        database = FirebaseDatabase.getInstance();




        players_join_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.PLAYERS_JOIN_REFERENCE);
        /*players_join_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                players_join = dataSnapshot.getValue(Integer.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        if (inside) {
            players_join=1;
            players_join_ref.setValue(players_join);
        }

            game_ref = database.getReference(FirebaseReferences.GAME_REFERENCE);



                if(players_join==1 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE1).setValue(name_guest);
                inside = false;}


                else if (players_join==2 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE2).setValue(name_guest);
                inside = false;}


                else if (players_join==3 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE3).setValue(name_guest);
                inside = false;}


                else if (players_join==4 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE4).setValue(name_guest);
                inside = false;}


                else if (players_join==5 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE5).setValue(name_guest);
                inside = false;}


                else if (players_join==6 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE6).setValue(name_guest);
                inside = false;}


                else if (players_join==7 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE7).setValue(name_guest);
                inside = false;}

                else if (players_join==8 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE8).setValue(name_guest);
                inside = false;}


                else if (players_join==9 & inside){

                game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE9).setValue(name_guest);
                inside = false;}








        host_ready_ref = database.getReference().child(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.HOST_READY_REFERENCE);
        host_ready_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                host_ready = dataSnapshot.getValue(boolean.class);
                if (host_ready) {
                    Intent intent_game = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(intent_game);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        }
    }

