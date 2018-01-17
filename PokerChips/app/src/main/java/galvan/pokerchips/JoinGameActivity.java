package galvan.pokerchips;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import galvan.pokerchips.Datos.FirebaseReferences;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class JoinGameActivity extends AppCompatActivity {

    private EditText edit_name;
    private String name_guest;
    private boolean empty=false;
    private int code;
    private String game_id;
    private DatabaseReference game_id_ref;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);

        Bundle code_receive = getIntent().getExtras();
        code = code_receive.getInt("code");
        game_id = code_receive.getString("game_id");

        edit_name = (EditText)findViewById(R.id.editText_nombre);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        game_id_ref = database.getReference(FirebaseReferences.GAME_ID_REFERENCE);
        game_id_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                game_id = dataSnapshot.getValue(String.class);
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

                name_guest = edit_name.getText().toString();

                if(name_guest.equals("")){empty=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(JoinGameActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(R.string.enterName);
                    builder.create().show();
                }
                else {empty=false;}

                if (!empty){



                Intent intent_wait = new Intent(getApplicationContext(), ScanQRActivity.class);
                    intent_wait.putExtra("code",code);
                    intent_wait.putExtra("name_guest",name_guest);
                    intent_wait.putExtra("game_id",game_id);
                startActivity(intent_wait);}

            }
        });

    }
}
