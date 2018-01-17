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
    private int code;
    private String name_guest;
    private DatabaseReference game_id_ref;
    private String game_id="";
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

        game_id_ref = database.getReference(FirebaseReferences.GAME_ID_REFERENCE);
        game_id_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                game_id = dataSnapshot.getValue(String.class);
                Log.i("Xavi",String.format("%s",game_id));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.i("Xavi",String.format("%s",game_id));



    }


    @Override
    public void handleResult(Result result) {


        Intent intent_wait = new Intent(getApplicationContext(), WaitActivity2.class);
            intent_wait.putExtra("game_id",game_id);
            intent_wait.putExtra("name_guest",name_guest);
        startActivity(intent_wait);}


}
