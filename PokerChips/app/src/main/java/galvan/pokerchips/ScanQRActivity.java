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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import galvan.pokerchips.Datos.FirebaseReferences;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Xavi on 15/01/2018.
 */

public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{


    private ZXingScannerView scannerView;
    private String name_guest;
    private String game_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle code_receive = getIntent().getExtras();
        name_guest = code_receive.getString("name_guest");


        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();

            }

    @Override
    public void handleResult(Result result) {

        game_id = result.getText();

        Intent intent_wait2 = new Intent(getApplicationContext(), WaitActivity2.class);
            intent_wait2.putExtra("game_id",game_id);
            intent_wait2.putExtra("name_guest",name_guest);
        startActivity(intent_wait2);}


}
