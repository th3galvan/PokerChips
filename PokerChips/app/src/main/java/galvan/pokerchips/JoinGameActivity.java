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

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class JoinGameActivity extends AppCompatActivity {

    private EditText name;
    private EditText code;

    private String string_name;
    private String string_code;
    private String string_code_bundle;

    private boolean empty=false;
    private boolean empty_code=false;
    private boolean stop;

    private int code_bundle;
    private int players;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joingame);

        Bundle code_receive = getIntent().getExtras();
        code_bundle = code_receive.getInt("code");
        players = code_receive.getInt("players");


        name = (EditText)findViewById(R.id.editText_nombre);
        code = (EditText)findViewById(R.id.editText_code);

        string_code_bundle = Integer.toString(code_bundle);

        start();

    }
    public void start() {

        Button btn_scanQR = (Button) findViewById(R.id.btn_scanQR);
        Button btn_scan = (Button) findViewById(R.id.btn_scan);


        btn_scanQR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                string_name = name.getText().toString();
                string_code = code.getText().toString();
                Log.i("Xavi",String.format("code %s // bundle %s",string_code,string_code_bundle));

                if(string_name.equals("")){empty=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(JoinGameActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(R.string.enterName);
                    builder.create().show();
                }
                else {empty=false;}
                if(string_code.equals("")){empty_code=true;
                    if(!empty){
                    AlertDialog.Builder builder= new AlertDialog.Builder(JoinGameActivity.this);
                    builder.setTitle(R.string.Code);
                    builder.setMessage(R.string.enterCode);
                    builder.create().show();}
                }
                else {empty_code=false;}

                Log.i("Xavi2",String.format("code %s // bundle %s",string_code,string_code_bundle));
                if (!empty & !empty_code & string_code.equals(string_code_bundle)){
                Intent intent_wait = new Intent(getApplicationContext(), GameActivity.class);
                    intent_wait.putExtra("players",players);
                startActivity(intent_wait);}

                else if (!empty & !empty_code & !string_code.equals(string_code_bundle)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinGameActivity.this);
                    builder.setTitle(R.string.Code);
                    builder.setMessage(R.string.codeIncorrect);
                    builder.setPositiveButton(R.string.confirmation,null);
                    builder.create().show();
                }

            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                string_name = name.getText().toString();

                if(string_name.equals("")){
                    AlertDialog.Builder builder= new AlertDialog.Builder(JoinGameActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(R.string.enterName);
                    builder.create().show();
                }
                else {
                    Intent intent_scanqr = new Intent(getApplicationContext(), ScanQRActivity.class);
                    startActivity(intent_scanqr);
                }


            }
        });
    }
}
