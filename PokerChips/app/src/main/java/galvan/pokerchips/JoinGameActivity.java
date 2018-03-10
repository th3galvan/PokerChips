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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);

        edit_name = (EditText)findViewById(R.id.editText_nombre);

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
                    intent_wait.putExtra("name_guest",name_guest);
                    startActivity(intent_wait);}

            }
        });

    }
}