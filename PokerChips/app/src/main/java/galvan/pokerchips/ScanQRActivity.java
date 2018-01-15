package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle code_receive = getIntent().getExtras();
        players_join = code_receive.getInt("players_join");


        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();

        database = FirebaseDatabase.getInstance();
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

    }

    @Override
    protected void onPause() {
        super.onPause();

        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        Log.i("Result",result.getText());
        if (result.getText().equals("5694")){
            players_join++;
            players_join_ref.setValue(players_join);

        Intent intent_wait = new Intent(getApplicationContext(), WaitActivity.class);
            intent_wait.putExtra("players_join",players_join);
        startActivity(intent_wait);}
        else{
            scannerView.startCamera();
        }
    }
}
