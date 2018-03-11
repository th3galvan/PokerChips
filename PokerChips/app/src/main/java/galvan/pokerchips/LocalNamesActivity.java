package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Xavi on 10/03/2018.
 */

public class LocalNamesActivity extends AppCompatActivity {

    private int number_players;
    private int initial_chips;
    private int big;
    private int time_big_up;
    private int change_value_big;

    private String name;

    private EditText edit_second;
    private EditText edit_third;
    private EditText edit_fourth;
    private EditText edit_fifth;
    private EditText edit_sixth;
    private EditText edit_seventh;
    private EditText edit_eighth;
    private EditText edit_nineth;
    private EditText edit_tenth;

    private String name_second;
    private String name_third;
    private String name_fourth;
    private String name_fifth;
    private String name_sixth;
    private String name_seventh;
    private String name_eighth;
    private String name_nineth;
    private String name_tenth;

    private boolean empty_second=false;
    private boolean empty_third=false;
    private boolean empty_fourth=false;
    private boolean empty_fifth=false;
    private boolean empty_sixth=false;
    private boolean empty_seventh=false;
    private boolean empty_eighth=false;
    private boolean empty_nineth=false;
    private boolean empty_tenth=false;

    private TextView txt_second;
    private TextView txt_third;
    private TextView txt_fourth;
    private TextView txt_fifth;
    private TextView txt_sixth;
    private TextView txt_seventh;
    private TextView txt_eighth;
    private TextView txt_nineth;
    private TextView txt_tenth;

    private Button btn_play;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localnames);

        Bundle code_receive = getIntent().getExtras();
        number_players =code_receive.getInt("playersnumber");
        initial_chips =code_receive.getInt("initial_chips");
        big =code_receive.getInt("bigblind");
        time_big_up =code_receive.getInt("frecuency");
        change_value_big =code_receive.getInt("change");
        name =code_receive.getString("name");

        //campo donde se rellena el nombre de los jugadores
        edit_second = (EditText)findViewById(R.id.edit_second_player);
        edit_third = (EditText)findViewById(R.id.edit_third_player);
        edit_fourth = (EditText)findViewById(R.id.edit_fourth_player);
        edit_fifth = (EditText)findViewById(R.id.edit_fifth_player);
        edit_sixth = (EditText)findViewById(R.id.edit_sixth_player);
        edit_seventh = (EditText)findViewById(R.id.edit_seventh_player);
        edit_eighth = (EditText)findViewById(R.id.edit_eighth_player);
        edit_nineth = (EditText)findViewById(R.id.edit_nineth_player);
        edit_tenth = (EditText)findViewById(R.id.edit_tenth_player);

        //texto del plain text
        txt_second = (TextView)findViewById(R.id.txt_second_player);
        txt_third = (TextView)findViewById(R.id.txt_third_player);
        txt_fourth = (TextView)findViewById(R.id.txt_fourth_player);
        txt_fifth = (TextView)findViewById(R.id.txt_fifth_player);
        txt_sixth = (TextView)findViewById(R.id.txt_sixth_player);
        txt_seventh = (TextView)findViewById(R.id.txt_seventh_player);
        txt_eighth = (TextView)findViewById(R.id.txt_eighth_player);
        txt_nineth = (TextView)findViewById(R.id.txt_nineth_player);
        txt_tenth = (TextView)findViewById(R.id.txt_tenth_player);

        //boton

        btn_play = (Button)findViewById(R.id.btn_play);


        //Ponemos en invisible los jugadores que no esten en partida
        edit_second.setVisibility(View.INVISIBLE);
        edit_third.setVisibility(View.INVISIBLE);
        edit_fourth.setVisibility(View.INVISIBLE);
        edit_fifth.setVisibility(View.INVISIBLE);
        edit_sixth.setVisibility(View.INVISIBLE);
        edit_seventh.setVisibility(View.INVISIBLE);
        edit_eighth.setVisibility(View.INVISIBLE);
        edit_nineth.setVisibility(View.INVISIBLE);
        edit_tenth.setVisibility(View.INVISIBLE);

        //Ponemos invisible los txt tambn

        txt_second.setVisibility(View.INVISIBLE);
        txt_third.setVisibility(View.INVISIBLE);
        txt_fourth.setVisibility(View.INVISIBLE);
        txt_fifth.setVisibility(View.INVISIBLE);
        txt_sixth.setVisibility(View.INVISIBLE);
        txt_seventh.setVisibility(View.INVISIBLE);
        txt_eighth.setVisibility(View.INVISIBLE);
        txt_nineth.setVisibility(View.INVISIBLE);
        txt_tenth.setVisibility(View.INVISIBLE);



        EditText Array_edits[] = {edit_second, edit_third, edit_fourth, edit_fifth, edit_sixth,
                edit_seventh, edit_eighth, edit_nineth, edit_tenth};

        TextView Array_txts[] = {txt_second, txt_third, txt_fourth, txt_fifth, txt_sixth,
                txt_seventh, txt_eighth, txt_nineth, txt_tenth};


        for (int i = 0; i<number_players-1;i++){

            Array_edits[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i<number_players-1;i++){

            Array_txts[i].setVisibility(View.VISIBLE);
        }

        for (int i = 9; i>number_players-1;i--){
            Array_edits[i-1].setText("1");
        }

        //Revisamos si hay algun campo vacio

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Nombre de los jugadores
                name_second = edit_second.getText().toString();
                name_third = edit_third.getText().toString();
                name_fourth = edit_fourth.getText().toString();
                name_fifth = edit_fifth.getText().toString();
                name_sixth = edit_sixth.getText().toString();
                name_seventh = edit_seventh.getText().toString();
                name_eighth = edit_eighth.getText().toString();
                name_nineth = edit_nineth.getText().toString();
                name_tenth = edit_tenth.getText().toString();

                String Array_names[] = {name_second, name_third, name_fourth, name_fifth, name_sixth,
                        name_seventh, name_eighth, name_nineth, name_tenth};

                Log.i("Names4",String.format("%s",name_second));

                for (int i=0; i<Array_names.length; i++){
                    Log.i("Names3",String.format("%s",Array_names[i]));
                }

                empty_second=false;
                empty_third=false;
                empty_fourth=false;
                empty_fifth=false;
                empty_sixth=false;
                empty_seventh=false;
                empty_eighth=false;
                empty_nineth=false;
                empty_tenth=false;

                if (name_second.equals("")){
                    empty_second=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.second));      //añadido recurso
                    builder.create().show();
                }
                else{empty_second=false;}

                if (name_third.equals("")){
                    if(!empty_second){

                        empty_third=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.third));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_third=false;}

                if (name_fourth.equals("")){
                    if(!empty_second & !empty_third){
                        empty_fourth=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.fourth));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_fourth=false;}

                if (name_fifth.equals("")){
                    if(!empty_second & !empty_third & !empty_fourth){

                        empty_fifth=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.fifth));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_fifth=false;}

                if (name_sixth.equals("")){
                    Log.i("Names",String.format("%b %b %b %b %b %b %b", empty_fourth, empty_fifth, empty_sixth, name_seventh, name_eighth, name_nineth, name_tenth));
                    if(!empty_second & !empty_third & !empty_fourth & !empty_fifth){

                        empty_sixth=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.sixth));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_sixth=false;}

                if (name_seventh.equals("")){
                    if(!empty_second & !empty_third & !empty_fourth & !empty_fifth & !empty_sixth){

                        empty_seventh=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.seventh));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_seventh=false;}


                if (name_eighth.equals("")){
                    if(!empty_second & !empty_third & !empty_fourth & !empty_fifth & !empty_sixth & !empty_seventh){

                        empty_eighth=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.eighth));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_eighth=false;}

                if (name_nineth.equals("")){
                    if(!empty_second & !empty_third & !empty_fourth & !empty_fifth & !empty_sixth & !empty_seventh & !empty_eighth){

                        empty_nineth=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.nineth));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_nineth=false;}

                if (name_tenth.equals("")){
                    if(!empty_second & !empty_third & !empty_fourth & !empty_fifth & !empty_sixth & !empty_seventh & !empty_eighth & !empty_nineth){

                        empty_tenth=true;
                    AlertDialog.Builder builder= new AlertDialog.Builder(LocalNamesActivity.this);
                    builder.setTitle(R.string.Name);
                    builder.setMessage(getString(R.string.tenth));      //añadido recurso
                    builder.create().show();
                }}
                else{empty_tenth=false;}


                if(!empty_second & !empty_third & !empty_fourth & !empty_fifth & !empty_sixth & !empty_seventh & !empty_eighth & !empty_nineth & !empty_tenth){

                    for (int i=0; i<Array_names.length; i++){

                        Log.i("Names2",String.format("%s",Array_names[i]));
                    }

                    Intent singledevice = new Intent(getApplicationContext(), SingleDeviceGameActivity.class);
                    singledevice.putExtra("name",name);
                    singledevice.putExtra("playersnumber",number_players);
                    singledevice.putExtra("initial_chips",initial_chips);
                    singledevice.putExtra("bigblind",big);
                    singledevice.putExtra("frecuency",time_big_up);
                    singledevice.putExtra("change",change_value_big);
                    singledevice.putExtra("second",Array_names[0]);
                    singledevice.putExtra("third",Array_names[1]);
                    singledevice.putExtra("fourth",Array_names[2]);
                    singledevice.putExtra("fifth",Array_names[3]);
                    singledevice.putExtra("sixth",Array_names[4]);
                    singledevice.putExtra("seventh",Array_names[5]);
                    singledevice.putExtra("eighth",Array_names[6]);
                    singledevice.putExtra("nineth",Array_names[7]);
                    singledevice.putExtra("tenth",Array_names[8]);

                    startActivity(singledevice);}
            }
        });


    }
}
