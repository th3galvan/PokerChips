package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class ModeGameActivity extends AppCompatActivity {

    private int code;
    private int players;
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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modegame);

        Bundle code_receive = getIntent().getExtras();
        code = code_receive.getInt("code");
        players = code_receive.getInt("players");

        final RadioButton rb_fast = (RadioButton)findViewById(R.id.rb_fast);
        final RadioButton rb_custom = (RadioButton)findViewById(R.id.rb_custom);
        Button btn_finish = (Button)findViewById(R.id.btn_finish2);

        final TextView txt_title_name = (TextView)findViewById(R.id.txt_title_name);
        final TextView txt_title_players = (TextView)findViewById(R.id.txt_title_players);
        edit_players_fast = (EditText)findViewById(R.id.edit_players_fast);
        edit_name_fast = (EditText)findViewById(R.id.edit_name_fast);

        txt_title_name.setVisibility(View.INVISIBLE);
        txt_title_players.setVisibility(View.INVISIBLE);
        edit_name_fast.setVisibility(View.INVISIBLE);
        edit_players_fast.setVisibility(View.INVISIBLE);

        rb_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_custom.setChecked(false);
                txt_title_name.setVisibility(View.VISIBLE);
                txt_title_players.setVisibility(View.VISIBLE);
                edit_name_fast.setVisibility(View.VISIBLE);
                edit_players_fast.setVisibility(View.VISIBLE);

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
                        builder.setMessage("Please enter your name");
                        builder.create().show();
                    }
                    else {empty_name=false;}

                    if(string_players_fast.equals("0")){empty_players=true;
                        if(!empty_name){
                            AlertDialog.Builder builder= new AlertDialog.Builder(ModeGameActivity.this);
                            builder.setTitle(R.string.players);
                            builder.setMessage("Please enter number of players");
                            builder.create().show();}
                    }
                    else{empty_players=false;}

                    boolean go =true;
                    //todo he puesto todo para poder pasar sin rellenar parametros
                    if (!empty_players & !empty_name || go){

                        initial_chips=1500;
                        big=10;
                        time_big_up=10;
                        change_value_big=10;

                    Intent fastgame = new Intent(getApplicationContext(), ShowCodeActivity.class);
                    fastgame.putExtra("code",code);
                    fastgame.putExtra("players",players);
                        fastgame.putExtra("name",name);
                        fastgame.putExtra("playersnumber",number_players);
                        fastgame.putExtra("initial_chips",initial_chips);
                        fastgame.putExtra("bigblind",big);
                        fastgame.putExtra("frecuency",time_big_up);
                        fastgame.putExtra("change",change_value_big);
                    startActivity(fastgame);

                }}

                if(rb_custom.isChecked()){

                    Intent customgame = new Intent(getApplicationContext(), SetGameActivity.class);
                    customgame.putExtra("code",code);
                    customgame.putExtra("players",players);
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

