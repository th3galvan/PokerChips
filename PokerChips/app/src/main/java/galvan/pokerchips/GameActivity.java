package galvan.pokerchips;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

public class GameActivity extends AppCompatActivity {

    // Añado un comentario para probar el push + update...

    private int pos;
    private int bet;
    private int ownChips;
    private int current_individual_bet;
    private int current_total_bet;
    private boolean all_in;
    private TextView txt_current_individual_bet;
    private TextView txt_ownChips;
    private TextView txt_bet;
    private TextView txt_current_total_bet;

    private ArrayList<PlayerItems> players;
    private PlayerItemAdapter adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //parte de añadir lista de jugadores y adapter
        list = (ListView)findViewById(R.id.players_list);

        players = new ArrayList<>();

        players.add(new PlayerItems(1, "Fulanito", 200, false, true, false, true));

        players.add(new PlayerItems(2, "Menganito", 400, false, true, false, true));

        players.add(new PlayerItems(3, "Menganito", 400, false, true, false, true));

        players.add(new PlayerItems(4, "Menganito", 400, false, true, false, true));

        adapter = new PlayerItemAdapter(
                this,
                R.layout.activity_playeritems,
                players
        );
        list.setAdapter(adapter);



        final int chips[] ={5,10,25,50,100};


        //declaramos botones y views de las fichas
       final Button btn_plus_chip = (Button) findViewById(R.id.btn_plus_chip);
        final Button btn_minus_chip = (Button) findViewById(R.id.btn_minus_chip);
        final TextView txt_chip =(TextView) findViewById(R.id.txt_chip);

        //declaramos botones y views de la apuesta
        final Button btn_plus_bet = (Button)findViewById(R.id.btn_plus_bet);
        final Button btn_minus_bet = (Button) findViewById(R.id.btn_minus_bet);
        txt_bet =(TextView) findViewById(R.id.txt_bet);

        //declaramos el boton de All-in
        final Button btn_all_in=(Button)findViewById(R.id.btn_all_in);

        //declaramos el boton de igualar y una variable apuesta individual actual (arriba) y aqui la inicializamos
        final Button btn_equalize=(Button)findViewById(R.id.btn_equalize);
        txt_current_individual_bet = (TextView) findViewById(R.id.txt_current_individual_bet_number);
        current_individual_bet=200;
        String Scurrent_individual_bet= Integer.toString(current_individual_bet);
        txt_current_individual_bet.setText(Scurrent_individual_bet);

        //declaramos e inicializamos el contador de tus fichas
        txt_ownChips = (TextView) findViewById(R.id.txt_own_chips_number);
        ownChips=1000;
        String SownChips= Integer.toString(ownChips);
        txt_ownChips.setText(SownChips);

        //inicializamos los views de la calculadora
        pos=0;
        String textChip = Integer.toString(chips[pos]);
        txt_chip.setText(textChip);

        bet=0;
        String textBet = Integer.toString(bet);
        txt_bet.setText(textBet);

        //declaramos el view de la apuesta actual total y el boton de apostar y pasar
        txt_current_total_bet= (TextView) findViewById(R.id.txt_current_total_bet_number);
        final Button btn_check= (Button) findViewById(R.id.btn_check);
        final Button btn_bet= (Button) findViewById(R.id.btn_bet);
        current_total_bet= 800;
        String Scurrent_total_bet= Integer.toString(current_total_bet);
        txt_current_total_bet.setText(Scurrent_total_bet);

        //BTN+ Chips
        btn_plus_chip.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
            if(pos<chips.length-1){
                pos++;
                String textChipBtn = Integer.toString(chips[pos]);
                txt_chip.setText(textChipBtn);}

        }
       }
       );
        //BTN- Chips
        btn_minus_chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(pos>0){
                pos--;
                String textChipBtn = Integer.toString(chips[pos]);
                txt_chip.setText(textChipBtn);
            }
            }
        });
       //BTN+ Bet
        btn_plus_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bet+chips[pos]<=ownChips)
                {bet=bet+chips[pos];
                String textBetBtn = Integer.toString(bet);
                txt_bet.setText(textBetBtn);}
                else{}}
        });
        //BTN- Bet
        btn_minus_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bet<chips[pos]){}
                else{bet=bet-chips[pos];
                String textBetBtn = Integer.toString(bet);
                txt_bet.setText(textBetBtn);}
            }
        });
        //BTN ALL IN
        btn_all_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bet=ownChips;
                String textAllBtn = Integer.toString(bet);
                txt_bet.setText(textAllBtn);
            }
        });
        //BTN EQUALIZE
        btn_equalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_individual_bet>ownChips){
                    bet=ownChips;
                    String textCurrentIBtn= Integer.toString(bet);
                    txt_bet.setText(textCurrentIBtn);
                    //Todo: aqui se entraria en all in
                }
                else{
                    bet=current_individual_bet;
                    String textCurrentIBtn= Integer.toString(bet);
                    txt_bet.setText(textCurrentIBtn);
                }
            }
        });
        //BTN CHECK
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //BTN BET
        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            toBet(bet);

                bet=0;
                txt_bet.setText(Integer.toString(bet));

            }
        });




    }
    //este metodo se ejecuta cuando pulsamos pasar o apostar, si pulsamos bet el valor de la variable
    // bet se restará a tus fichas y se actualizará el valor de current_individual_bet si este es menor
    //a la nueva apuesta
    // TODO: 11/12/2017 cuando apostamos un valor superior a ownChips sale un numero negativo hay que hacer algo en plan que te eche
    // TODO: 12/12/2017 hay que hacer que cuando entras en all in (NewOwnChips<0) te ponga en modo all in
    private void toBet(final int toBetBet) {
        final int newownChips=ownChips-toBetBet;


        if(newownChips<=0){
            Log.i("Galvan","(newownChips<=0)");
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle(R.string.confirmation);
            builder.setMessage(String.format("Are you sure you want to bet all your chips"));
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        all_in=true;
                        ownChips=newownChips;
                        String SownChips= Integer.toString(ownChips);
                        txt_ownChips.setText(SownChips);
                        if(toBetBet>current_individual_bet){
                            //se sustituye por la apuesta mas grande
                            current_individual_bet = toBetBet;
                            String Scurrent_individual_bet = Integer.toString(current_individual_bet);
                            txt_current_individual_bet.setText(Scurrent_individual_bet);
                            Log.i("Galvan","(current_individual_bet = toBetBet;)");
                        }
                        current_total_bet=current_total_bet+toBetBet;                               //actualiza el valor de current total bet
                        String Scurrent_total_bet= Integer.toString(current_total_bet);
                        txt_current_total_bet.setText(Scurrent_total_bet);

                    }

                });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }
        else{
            Log.i("Galvan","(newownChips<=0)");
            AlertDialog.Builder builder2= new AlertDialog.Builder(this);
            builder2.setMessage(String.format("Are you sure you want to bet %s chips?",Integer.toString(toBetBet)));
            builder2.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    ownChips=newownChips;
                    String SownChips= Integer.toString(ownChips);
                    txt_ownChips.setText(SownChips);

                    if(toBetBet>current_individual_bet){
                        //se sustituye por la apuesta mas grande
                        current_individual_bet = toBetBet;
                        String Scurrent_individual_bet = Integer.toString(current_individual_bet);
                        txt_current_individual_bet.setText(Scurrent_individual_bet);
                        Log.i("Galvan","(current_individual_bet = toBetBet;)");

                    }
                    current_total_bet=current_total_bet+toBetBet;                                   //actualiza el valor de current total bet
                    String Scurrent_total_bet= Integer.toString(current_total_bet);
                    txt_current_total_bet.setText(Scurrent_total_bet);
                }
            });
            builder2.setNegativeButton(android.R.string.cancel,null);
            builder2.create().show();
        }
        String SownChips= Integer.toString(ownChips);
        txt_ownChips.setText(SownChips);

    }
}


