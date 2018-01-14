package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class SetGameActivity extends AppCompatActivity {

    private EditText edit_initial_chips;
    private EditText edit_name;
    private EditText edit_number_players;
    private EditText edit_big;
    private EditText edit_time_big_up;
    private EditText edit_change_value_big;

    private String string_initial_chips;
    private String name;
    private String string_number_players;
    private String string_big;
    private String string_time_big_up;
    private String string_change_value_big;

    private int code;
    private int players;
    private int number_players;
    private int initial_chips;
    private int big;
    private int time_big_up;
    private int change_value_big;

    private boolean empty_name;
    private boolean empty_players;
    private boolean empty_initial;
    private boolean empty_big;
    private boolean empty_time;
    private boolean empty_change;
    private boolean max_players;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setgame);


        Bundle code_receive = getIntent().getExtras();
        code = code_receive.getInt("code");
        players = code_receive.getInt("players");

        //Cogemos todos los edits
        edit_initial_chips = (EditText)findViewById(R.id.edit_initial_chips);
        edit_name = (EditText)findViewById(R.id.edit_host_name);
        edit_number_players = (EditText)findViewById(R.id.edit_players_number);
        edit_big = (EditText)findViewById(R.id.edit_first_big_value);
        edit_time_big_up =(EditText)findViewById(R.id.edit_big_freq);
        edit_change_value_big = (EditText)findViewById(R.id.edit_big_change_value);

        //Button
        Button btn_done_gotoqr = (Button) findViewById(R.id.btn_done_gotoqr);




        //hago clic y se abre el layout ShowCode
        btn_done_gotoqr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //actualizamos valor de los edits
                TakeTextEdits();
                //y miramos si algun campo esta vacio y avisamos uno por uno de cual
                if(name.equals("")){empty_name=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(SetGameActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.enterName));      //añadido recurso
                    builder.create().show();
                }
                else {empty_name=false;}

                if(string_number_players.equals("0")){empty_players=true;
                    if(!empty_name){
                        AlertDialog.Builder builder= new AlertDialog.Builder(SetGameActivity.this);
                        builder.setTitle(R.string.players);
                        builder.setMessage(getString(R.string.enterPlayers));   //añadido recurso
                        builder.create().show();}
                }
                else {empty_players=false;}

                if(string_initial_chips.equals("0")){empty_initial=true;
                    if(!empty_name & !empty_players){
                        AlertDialog.Builder builder= new AlertDialog.Builder(SetGameActivity.this);
                        builder.setTitle(R.string.initial_chips);
                        builder.setMessage(R.string.enterInitialChips);     //añadido recurso
                        builder.create().show();}
                }
                else {empty_initial=false;}

                if(string_big.equals("0")){empty_big=true;
                    if(!empty_initial & !empty_name & !empty_players){
                        AlertDialog.Builder builder= new AlertDialog.Builder(SetGameActivity.this);
                        builder.setTitle(R.string.bigblind);
                        builder.setMessage(R.string.enterBig);      //añadido recurso
                        builder.create().show();}
                }
                else {empty_big=false;}

                if(string_time_big_up.equals("0")){empty_time=true;
                    if(!empty_big & !empty_initial & !empty_name & !empty_players){
                        AlertDialog.Builder builder= new AlertDialog.Builder(SetGameActivity.this);
                        builder.setTitle(R.string.Frecuency);
                        builder.setMessage(R.string.enterFreqRiseBig);      //añadido recurso
                        builder.create().show();}
                }
                else {empty_time=false;}

                if(string_change_value_big.equals("0")){empty_change=true;
                    if(!empty_time & !empty_big & !empty_initial & !empty_name & !empty_players){
                        AlertDialog.Builder builder= new AlertDialog.Builder(SetGameActivity.this);
                        builder.setTitle(R.string.Rise_value);
                        builder.setMessage(R.string.enterBigRiseValue);     //añadido recurso
                        builder.create().show();}
                }
                else {empty_change=false;}

                if (number_players>10){
                    max_players=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(SetGameActivity.this);
                    builder.setTitle(R.string.players);
                    builder.setMessage(getString(R.string.maximPlayers));   //añadido recurso
                    builder.create().show();}

                else{max_players=false;}

            //hasta que todos los campos no esten llenos no podemos cambiar de pantalla
                if(!empty_time & !empty_big & !empty_initial & !empty_name & !empty_players & !empty_change & !max_players){
                Intent generateqr = new Intent(getApplicationContext(), ShowCodeActivity.class);
                generateqr.putExtra("code",code);
                generateqr.putExtra("players",players);
                    generateqr.putExtra("name",name);
                    generateqr.putExtra("playersnumber",number_players);
                    generateqr.putExtra("initial_chips",initial_chips);
                    generateqr.putExtra("bigblind",big);
                    generateqr.putExtra("frecuency",time_big_up);
                    generateqr.putExtra("change",change_value_big);
                startActivity(generateqr);}
            }
        });


    }

    private void TakeTextEdits() {

        //ParseInt dan problemas si no se rellena alguna casilla, para solucionarlo he escrito 0 en las casillas en blanco, asi no falla
        //NAME
        name = edit_name.getText().toString();

        //NUMERO DE JUGADORES
        string_number_players = edit_number_players.getText().toString();
        string_number_players.trim();
        if (string_number_players.equals("")){edit_number_players.setText("0");
            string_number_players = edit_number_players.getText().toString().trim();}
        number_players= Integer.parseInt(string_number_players);


        //FICHAS INICIALES
        string_initial_chips = edit_initial_chips.getText().toString();
        string_initial_chips.trim();
        if (string_initial_chips.equals("")){edit_initial_chips.setText("0");
            string_initial_chips = edit_initial_chips.getText().toString().trim();}
        initial_chips= Integer.parseInt(string_initial_chips);

        //BIG BLIND
        string_big = edit_big.getText().toString();
        string_big.trim();
        if (string_big.equals("")){edit_big.setText("0");
            string_big = edit_big.getText().toString().trim();}
        big= Integer.parseInt(string_big);

        //FRECUENCIA DE SUBIDA BIG BLIND
        string_time_big_up = edit_time_big_up.getText().toString();
        string_time_big_up.trim();
        if (string_time_big_up.equals("")){edit_time_big_up.setText("0");
            string_time_big_up = edit_time_big_up.getText().toString().trim();}
        time_big_up= Integer.parseInt(string_time_big_up);

        //VALOR DE SUBIDO BIG BLIND
        string_change_value_big = edit_change_value_big.getText().toString();
        string_change_value_big.trim();
        if (string_change_value_big.equals("")){edit_change_value_big.setText("0");
            string_change_value_big = edit_change_value_big.getText().toString().trim();}
        change_value_big= Integer.parseInt(string_change_value_big);
    }

}
