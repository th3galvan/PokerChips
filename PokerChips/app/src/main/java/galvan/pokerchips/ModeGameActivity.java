package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import galvan.pokerchips.Datos.FirebaseReferences;


public class ModeGameActivity extends AppCompatActivity {

    private int number_players;
    private int initial_chips;
    private int big;
    private int time_big_up;
    private int change_value_big;

    private String name;
    private String string_players_fast;

    private boolean empty_name=true;
    private boolean empty_players=true;

    private EditText edit_players_fast;
    private EditText edit_name_fast;
    private boolean max_players;
    private DatabaseReference game_reference;
    private String game_id;
    private DatabaseReference game_id_ref;
    private boolean min_players;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modegame);

        /* TODO DIRIA QUE NO HACE FALTA SUBIR ESTO A FIREBASE
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference number_players_ref = database.getReference(FirebaseReferences.NUMBER_PLAYERS_REFERENCE);
        DatabaseReference initial_chips_ref = database.getReference(FirebaseReferences.INITIAL_CHIPS_REFERENCE);
        DatabaseReference big_ref = database.getReference(FirebaseReferences.BIG_REFERENCE);
        DatabaseReference time_big_up_ref = database.getReference(FirebaseReferences.TIME_BIG_UP_REFERENCE);
        DatabaseReference change_value_ref = database.getReference(FirebaseReferences.CHANGE_VALUE_BIG_REFERENCE);
        DatabaseReference name_ref = database.getReference(FirebaseReferences.NAME_REFERENCE);*/


        final RadioButton rb_fast = (RadioButton)findViewById(R.id.rb_fast);
        final RadioButton rb_custom = (RadioButton)findViewById(R.id.rb_custom);
        final Button btn_finish = (Button)findViewById(R.id.btn_finish2);
        final Button btn_local =(Button)findViewById(R.id.btn_single);
        final TextView txt_title_name = (TextView)findViewById(R.id.txt_title_name);
        final TextView txt_title_players = (TextView)findViewById(R.id.txt_title_players);
        edit_players_fast = (EditText)findViewById(R.id.edit_players_fast);
        edit_name_fast = (EditText)findViewById(R.id.edit_name_fast);

        txt_title_name.setVisibility(View.INVISIBLE);
        txt_title_players.setVisibility(View.INVISIBLE);
        edit_name_fast.setVisibility(View.INVISIBLE);
        edit_players_fast.setVisibility(View.INVISIBLE);

        btn_finish.setVisibility(View.INVISIBLE);
        btn_local.setVisibility(View.INVISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        game_reference = database.getReference(FirebaseReferences.GAME_REFERENCE);
        //cojemos id aleatoria
        game_id = game_reference.push().getKey();

        rb_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_custom.setChecked(false);
                txt_title_name.setVisibility(View.VISIBLE);
                txt_title_players.setVisibility(View.VISIBLE);
                edit_name_fast.setVisibility(View.VISIBLE);
                edit_players_fast.setVisibility(View.VISIBLE);

                btn_finish.setVisibility(View.VISIBLE);
                btn_finish.setText(R.string.ONLINE);
                btn_local.setVisibility(View.VISIBLE);

            }
        });
        rb_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_fast.setChecked(false);

                txt_title_name.setVisibility(View.INVISIBLE);
                txt_title_players.setVisibility(View.INVISIBLE);
                edit_name_fast.setVisibility(View.INVISIBLE);
                edit_players_fast.setVisibility(View.INVISIBLE);

                btn_finish.setVisibility(View.VISIBLE);
                btn_finish.setText(R.string.next);
                btn_local.setVisibility(View.INVISIBLE);

            }
        });
        btn_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_fast.isChecked()){

                    GetEdit();
                    if(name.equals("")){empty_name=true;
                        AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                        builder.setTitle(R.string.Name);
                        builder.setMessage(getString(R.string.enterName));  //añadido recurso
                        builder.create().show();
                    }
                    else {empty_name=false;}

                    if(string_players_fast.equals("0")){empty_players=true;
                        if(!empty_name){
                            AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                            builder.setTitle(R.string.players);
                            builder.setMessage(R.string.enterPlayers);  //añadido recurso
                            builder.create().show();}
                    }
                    else{empty_players=false;}

                    //si se selecciona mas de 10 jugadores no entra en partida y sale un mensaje de alerta
                    if (number_players>10){
                        max_players=true;
                        AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                        builder.setTitle(R.string.players);
                        builder.setMessage(R.string.maximPlayers);  //añadido recurso
                        builder.create().show();}

                    else{max_players=false;}

                    //si se selecciona menos de 2 jugadores no entra en partida y sale un mensaje de alerta
                    if (number_players<=1){
                        min_players=true;
                        AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                        builder.setTitle(R.string.players);
                        builder.setMessage(R.string.minPlayers);  //añadido recurso
                        builder.create().show();}

                    else{min_players=false;}


                    if (!empty_players & !empty_name  & !max_players & !min_players){

                        initial_chips=1500;
                        big=10;
                        time_big_up=10;
                        change_value_big=10;


                        Intent fastgame = new Intent(getApplicationContext(), LocalNamesActivity.class);
                        fastgame.putExtra("name",name);
                        fastgame.putExtra("playersnumber",number_players);
                        fastgame.putExtra("initial_chips",initial_chips);
                        fastgame.putExtra("bigblind",big);
                        fastgame.putExtra("frecuency",time_big_up);
                        fastgame.putExtra("change",change_value_big);
                        startActivity(fastgame);

                    }}

                if(rb_custom.isChecked()){

                    Intent localgame = new Intent(getApplicationContext(), SetGameActivity.class);
                    localgame.putExtra("game_id",game_id);
                    startActivity(localgame);
                }
            }
        });
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


                if(rb_fast.isChecked()){

                    GetEdit();
                    if(name.equals("")){empty_name=true;
                        AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                        builder.setTitle(R.string.Name);
                        builder.setMessage(getString(R.string.enterName));  //añadido recurso
                        builder.create().show();
                    }
                    else {empty_name=false;}

                    if(string_players_fast.equals("0")){empty_players=true;
                        if(!empty_name){
                            AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                            builder.setTitle(R.string.players);
                            builder.setMessage(R.string.enterPlayers);  //añadido recurso
                            builder.create().show();}
                    }
                    else{empty_players=false;}

                    //si se selecciona mas de 10 jugadores no entra en partida y sale un mensaje de alerta
                    if (number_players>10){
                        max_players=true;
                        AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                        builder.setTitle(R.string.players);
                        builder.setMessage(R.string.maximPlayers);  //añadido recurso
                        builder.create().show();}

                    else{max_players=false;}

                    //si se selecciona menos de 2 jugadores no entra en partida y sale un mensaje de alerta
                    if (number_players<=1){
                        min_players=true;
                        AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                        builder.setTitle(R.string.players);
                        builder.setMessage(R.string.minPlayers);  //añadido recurso
                        builder.create().show();}

                    else{min_players=false;}

                    if (!empty_players & !empty_name  & !max_players & !min_players){

                        initial_chips=1500;
                        big=10;
                        time_big_up=10;
                        change_value_big=10;


                        Intent fastgame = new Intent(getApplicationContext(), ShowCodeActivity.class);
                        fastgame.putExtra("name",name);
                        fastgame.putExtra("playersnumber",number_players);
                        fastgame.putExtra("initial_chips",initial_chips);
                        fastgame.putExtra("bigblind",big);
                        fastgame.putExtra("frecuency",time_big_up);
                        fastgame.putExtra("change",change_value_big);
                        fastgame.putExtra("game_id",game_id);
                        startActivity(fastgame);

                    }}

                if(rb_custom.isChecked()){

                    Intent customgame = new Intent(getApplicationContext(), SetGameActivity.class);
                    customgame.putExtra("game_id",game_id);
                    startActivity(customgame);
                }
            }
        });


    }

    private void GetEdit() {
        //ParseInt dan problemas si no se rellena alguna casilla, para solucionarlo he escrito 0 en las casillas en blanco, asi no falla

        name = edit_name_fast.getText().toString();
        string_players_fast = edit_players_fast.getText().toString().trim();
        if (string_players_fast.equals("")){edit_players_fast.setText("0");
            string_players_fast = edit_players_fast.getText().toString().trim();}
        number_players = Integer.parseInt(string_players_fast);
    }


}