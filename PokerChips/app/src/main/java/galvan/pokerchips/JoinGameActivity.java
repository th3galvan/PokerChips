package galvan.pokerchips;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import galvan.pokerchips.Datos.FirebaseReferences;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class JoinGameActivity extends AppCompatActivity {

    private EditText name;


    private String string_name;

    private boolean empty=false;

    private int players_join;
    private DatabaseReference players_join_ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);

        Bundle code_receive = getIntent().getExtras();
        players_join = code_receive.getInt("players_join");

        name = (EditText)findViewById(R.id.editText_nombre);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
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

        start();

    }
    public void start() {

        Button btn_scanQR = (Button) findViewById(R.id.btn_scanQR);


        btn_scanQR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                string_name = name.getText().toString();

                if(string_name.equals("")){empty=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(JoinGameActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(R.string.enterName);
                    builder.create().show();
                }
                else {empty=false;}

                if (!empty){
                Intent intent_wait = new Intent(getApplicationContext(), ScanQRActivity.class);
                    intent_wait.putExtra("players_join",players_join);
                startActivity(intent_wait);}

            }
        });

    }
}
