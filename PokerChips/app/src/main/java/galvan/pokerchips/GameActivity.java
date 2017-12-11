package galvan.pokerchips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    // Añado un comentario para probar el push + update...

    private int pos;
    private int bet;
    private int ownChips;
    private int current_individual_bet;
    private int current_total_bet;
    private TextView txt_current_individual_bet;
    private TextView txt_ownChips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final int chips[] ={5,10,25,50,100};


        //declaramos botones y views de las fichas
       final Button btn_plus_chip = (Button) findViewById(R.id.btn_plus_chip);
        final Button btn_minus_chip = (Button) findViewById(R.id.btn_minus_chip);
        final TextView txt_chip =(TextView) findViewById(R.id.txt_chip);

        //declaramos botones y views de la apuesta
        final Button btn_plus_bet = (Button)findViewById(R.id.btn_plus_bet);
        final Button btn_minus_bet = (Button) findViewById(R.id.btn_minus_bet);
        final TextView txt_bet =(TextView) findViewById(R.id.txt_bet);

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
        ownChips=9000;
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
        final TextView txt_current_total_bet= (TextView) findViewById(R.id.txt_current_total_bet_number);
        final Button btn_check= (Button) findViewById(R.id.btn_check);
        final Button btn_bet= (Button) findViewById(R.id.btn_bet);
        current_total_bet= 800;
        String Scurrent_total_bet= Integer.toString(current_total_bet);
        txt_current_total_bet.setText(Scurrent_total_bet);

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
        btn_plus_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bet+chips[pos]<=ownChips)
                {bet=bet+chips[pos];
                String textBetBtn = Integer.toString(bet);
                txt_bet.setText(textBetBtn);}
                else{}}
        });
        btn_minus_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bet<chips[pos]){}
                else{bet=bet-chips[pos];
                String textBetBtn = Integer.toString(bet);
                txt_bet.setText(textBetBtn);}
            }
        });
        btn_all_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bet=ownChips;
                String textAllBtn = Integer.toString(bet);
                txt_bet.setText(textAllBtn);
            }
        });
        btn_equalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bet=current_individual_bet;
                String textCurrentIBtn= Integer.toString(bet);
                txt_bet.setText(textCurrentIBtn);
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            toBet(bet);
            }
        });














    }
    //este metodo se ejecuta cuando pulsamos pasar o apostar, si pulsamos bet el valor de la variable
    // bet se restará a tus fichas y se actualizará el valor de current_individual_bet si este es menor
    //a la nueva apuesta
    // TODO: 11/12/2017 cuando apostamos un valor superior a ownChips sale un numero negativo hay que hacer algo en plan que te eche
    private void toBet(int bet) {
        ownChips=ownChips-bet;
        String SownChips= Integer.toString(ownChips);
        txt_ownChips.setText(SownChips);
        if(bet>current_individual_bet){
            //se sustituye por la apuesta mas grande
            current_individual_bet=bet;

            String Scurrent_individual_bet= Integer.toString(current_individual_bet);
            txt_current_individual_bet.setText(Scurrent_individual_bet);
        }
    }
}
