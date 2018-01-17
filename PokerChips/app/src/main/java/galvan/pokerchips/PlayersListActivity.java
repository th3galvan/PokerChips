package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import galvan.pokerchips.Datos.FirebaseReferences;

public class PlayersListActivity extends AppCompatActivity {

    private ArrayList<String> players_list;
    private ArrayAdapter<String> adapter;

    private int number_players;
    private int initial_chips;
    private int big;
    private int time_big_up;
    private int change_value_big;

    private String name;
    private String name_player1="Paco";
    private String name_player2="Pepe";
    private String name_player3="Yo";
    private String name_player4="Tu";
    private String name_player5="MechaHitler";
    private String name_player6="Sivir";
    private String name_player7="Pra";
    private String name_player8="Bru";
    private String name_player9="TT";
    private String game_id;

    private DatabaseReference name_guest_ref;
    private DatabaseReference name_guest_ref1;
    private DatabaseReference name_guest_ref2;
    private DatabaseReference name_guest_ref3;
    private DatabaseReference name_guest_ref4;
    private DatabaseReference name_guest_ref5;
    private DatabaseReference name_guest_ref6;
    private DatabaseReference name_guest_ref7;
    private DatabaseReference name_guest_ref8;
    private DatabaseReference name_guest_ref9;
    private DatabaseReference host_ready_ref;
    private DatabaseReference inside_ref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist);

        Bundle code_receive = getIntent().getExtras();
        number_players =code_receive.getInt("playersnumber");
        initial_chips =code_receive.getInt("initial_chips");
        big =code_receive.getInt("bigblind");
        time_big_up =code_receive.getInt("frecuency");
        change_value_big =code_receive.getInt("change");
        name =code_receive.getString("name");
        game_id = code_receive.getString("game_id");

        Button btn_finish = (Button) findViewById(R.id.btn_comenzar);

        //Cojo los valores que hay en firebase de los nombres de los jugadores y creo la lista

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        inside_ref = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.INSIDE_REFERENCE);
        inside_ref.setValue(true);

        name_guest_ref1 = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE1);
        name_guest_ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player1 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref2 =  database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE2);
        name_guest_ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player2 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref3 =  database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE3);
        name_guest_ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player3 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref4 =  database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE4);
        name_guest_ref4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player4 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref5 =  database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE5);
        name_guest_ref5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player5 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref6 =  database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE6);
        name_guest_ref6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player6 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref7 = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE7);
        name_guest_ref7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player7 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref8 = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE8);
        name_guest_ref8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player8 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name_guest_ref9 = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE9);
        name_guest_ref9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_player9 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ListView list = (ListView) findViewById(R.id.list_players);


        String[] values = {name, name_player1, name_player2, name_player3, name_player4,
                name_player5, name_player6, name_player7, name_player8, name_player9};

        players_list = new ArrayList<>();

        for (int i=0; i<number_players;i++){
        players_list.add(values[i]);}

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players_list);

        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View item, int pos, long id) {


                return false;
            }
        });

       btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean host_ready = true;

                host_ready_ref = database.getReference(FirebaseReferences.GAME_REFERENCE).child(game_id).child(FirebaseReferences.HOST_READY_REFERENCE);
                host_ready_ref.setValue(host_ready);


                Intent intent_game = new Intent(PlayersListActivity.this, GameActivity.class);
                /*intent_game.putExtra("name",name);
                intent_game.putExtra("playersnumber",number_players);
                intent_game.putExtra("initial_chips",initial_chips);
                intent_game.putExtra("bigblind",big);
                intent_game.putExtra("frecuency",time_big_up);
                intent_game.putExtra("change",change_value_big);*/
                intent_game.putExtra("game_id",game_id);
                startActivity(intent_game);
            }
        });


    }}
