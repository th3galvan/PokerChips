package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import galvan.pokerchips.Datos.FirebaseReferences;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Xavi on 15/01/2018.
 */

public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private int players_join;

    private ZXingScannerView scannerView;
    private FirebaseDatabase database;
    private DatabaseReference players_join_ref;
    private int code;
    private String name_guest;
    private DatabaseReference game_id_ref;
    private String game_id;
    private DatabaseReference game_ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle code_receive = getIntent().getExtras();
        players_join = code_receive.getInt("players_join");
        code = code_receive.getInt("code");
        name_guest = code_receive.getString("name_guest");


        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();

        database = FirebaseDatabase.getInstance();
        game_ref = database.getReference(FirebaseReferences.GAME_REFERENCE);
        players_join_ref = database.getReference(FirebaseReferences.PLAYERS_JOIN_REFERENCE);

        //Cojo id de la partida
        game_id_ref = database.getReference(FirebaseReferences.GAME_ID_REFERENCE);
        game_id_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                game_id = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        players_join_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                players_join = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
/*
    @Override
    protected void onPause() {
        super.onPause();

        scannerView.stopCamera();
    }*/

    @Override
    public void handleResult(Result result) {

        Log.i("Result",result.getText());
        if (result.getText().equals(Integer.toString(code))){

            players_join_ref.child(game_id).setValue(players_join);

            //segun el nombre de jugadores guardo en una referencia o en otra

            switch (players_join){

                case 1:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE1).setValue(name_guest);
                    break;

                case 2:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE2).setValue(name_guest);
                    break;

                case 3:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE3).setValue(name_guest);
                    break;

                case 4:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE4).setValue(name_guest);
                    break;

                case 5:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE5).setValue(name_guest);
                    break;

                case 6:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE6).setValue(name_guest);
                    break;

                case 7:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE7).setValue(name_guest);
                    break;

                case 8:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE8).setValue(name_guest);
                    break;

                case 9:

                    game_ref.child(game_id).child(FirebaseReferences.NAME_GUEST_REFERENCE9).setValue(name_guest);
                    break;

            }

            players_join++;


        Intent intent_wait = new Intent(getApplicationContext(), WaitActivity.class);
            intent_wait.putExtra("players_join",players_join);
        startActivity(intent_wait);}
        else{
            scannerView.startCamera();
        }
    }
}
