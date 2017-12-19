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
import java.util.Locale;
//todo me da error import java.util.function.ToDoubleBiFunction;

public class GameActivity extends AppCompatActivity {

    // Añado un comentario para probar el push + update...

    private int pos;
    private int bet;
    private int ownChips;
    private int current_individual_bet;
    private int current_total_bet;
    private int total_bet;
    private int winner=0;
    private boolean all_in;
    private TextView txt_current_individual_bet;
    private TextView txt_ownChips;
    private TextView txt_bet;
    private TextView txt_current_total_bet;
    private TextView txt_total_bet;

//VARIABLES DE PRUEBA
    private TextView fichasmalaquito ;
    private TextView fichasmalaquitodatabase ;
    private TextView txt_playernumber;
//// TODO: 17/12/2017 hay que hacer algo para asignar un mobil con un jugador

    private int playernumber;

    private ArrayList<PlayerItems> players;
    private PlayerItemAdapter adapter;
    private ListView list;
    //PlayerItems( int i,
    //              String name, int chips,      boolean dealer,
    //              boolean big, boolean small,  boolean out,
    //              int bet,      boolean turn    boolean allin)
    private PlayerItems Fulanito= new PlayerItems(1,
            "Fulanito", 200,    true,
            false,      false,  true,
            0,          true,   false);
    private PlayerItems Menganito= new PlayerItems(2,
            "Menganito", 400,   false,
            true,        false, true,
            0,           false, false);
    private PlayerItems Malaquito= new PlayerItems(3,
            "Malaquito", 800,   false,
            false,       true,  true,
            0,           false, false);

    private PlayerItems Estalactito = new PlayerItems(4,
            "Estalactito", 1000,    false,
            false,       false,      true,
            0,           false,     false);
    private PlayerItems PlayerDataBase[]={Fulanito,Menganito,Malaquito,Estalactito};

// Variables cambiadas del Intger
    //todo revisar
    private String malaquito_toString = String.format(Locale.ENGLISH,"%d",(Malaquito.getChips()));
    private String playerdatabase_toString = String.format(Locale.ENGLISH,"%d",(PlayerDataBase[2].getChips()));
    private String playernumber_toString = String.format(Locale.ENGLISH,"%d",playernumber);
    private String ownChips_toString = String.format(Locale.ENGLISH,"%d",ownChips);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // TODO: 17/12/2017  hay que hacer algo para asignar un mobil con un jugador
        //Todo: hay que hacer que playernuber se obtenga de PlayerDataBase
        playernumber=0;

    //parte de añadir lista de jugadores y adapter
        list = (ListView)findViewById(R.id.players_list);

        players = new ArrayList<>();
                    //PlayerItems( int i,
                                // String name, int chips,      boolean dealer,
                                // boolean big, boolean small,  boolean out,
                                // int bet,      boolean turn    boolean allin)
        players.add(Fulanito);

        players.add(Menganito);

        players.add(Malaquito);

        players.add(Estalactito);

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
    //TOTAL BET
        total_bet=0;
        txt_total_bet = (TextView) findViewById(R.id.txt_total_bet_number);
        //TODO: de momento usamos total bet para hacer display de la gente que esta in
        checkOut();
    //declaramos el boton de igualar y una variable apuesta individual actual (arriba) y aqui la inicializamos
        final Button btn_equalize=(Button)findViewById(R.id.btn_equalize);
        txt_current_individual_bet = (TextView) findViewById(R.id.txt_current_individual_bet_number);
        current_individual_bet=0;

        txt_current_individual_bet.setText(Integer.toString(current_individual_bet));

    //declaramos e inicializamos el contador de tus fichas
        txt_ownChips = (TextView) findViewById(R.id.txt_own_chips_number);

        ownChips=PlayerDataBase[playernumber].getChips();
        txt_ownChips.setText(Integer.toString(ownChips));

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
        current_total_bet= 0;
        String Scurrent_total_bet= Integer.toString(current_total_bet);
        txt_current_total_bet.setText(Scurrent_total_bet);


    //VISUALIZACION VARIABLES DE PRUEBA
//VISUALIZACION VARIABLES DE PRUEBA

        // todo He variado el integer.toString porque me daba problemas al implementar key de Firebase y
        //todo Android avisaba de posibles bugs

        fichasmalaquito = (TextView) findViewById(R.id.fichasmalaquito);
        fichasmalaquitodatabase = (TextView) findViewById(R.id.fichasmalaquitodatabase);
        txt_playernumber = (TextView) findViewById(R.id.txt_playernumber);

        fichasmalaquito.setText(malaquito_toString);
        fichasmalaquitodatabase.setText(playerdatabase_toString);
        txt_playernumber.setText(playernumber_toString);

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
                Log.i("Galvan","CHECK PULSADO");
                toCheck();
            }
        });
    //BTN BET
        btn_bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            toBet(bet);

            }
        });




    }

    private void toCheck() {
        //DATABASE
            if(current_individual_bet==0){
                Log.i("Galvan","CHECK");
                nextTurn();
            }
            else{
                Log.i("Galvan","FOLD");
                PlayerDataBase[playernumber].setOut(false);
                nextTurn();
            }

    }

    //este metodo se ejecuta cuando pulsamos pasar o apostar, si pulsamos bet el valor de la variable
    // bet se restará a tus fichas y se actualizará el valor de current_individual_bet si este es menor
    //a la nueva apuesta
    // TODO: 11/12/2017 cuando apostamos un valor superior a ownChips sale un numero negativo hay que hacer algo en plan que te eche
    // TODO: 12/12/2017 hay que hacer que cuando entras en all in (NewOwnChips<0) te ponga en modo all in
    private void toBet(final int toBetBet) {
    //CALCULAMOS CON CUANTAS FICHAS NOS QUEDAMOS(en teoria es imposible tener un valor negativo en newownChips)
        final int newownChips=ownChips-toBetBet;

    //SI VAMOS ALL IN
        if(newownChips<=0){
            Log.i("Galvan","(newownChips<=0)");
         //CREAMOS DIALOGO
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle(R.string.confirmation);
            builder.setMessage("Are you sure you want to bet all your chips");
        //PULSAMOS YES
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        all_in=true;
                        ownChips=newownChips;
                     //REFRESCAR TXT_OWNCHIPS EN PANTALLA
                        String SownChips= Integer.toString(ownChips);
                        txt_ownChips.setText(SownChips);

                     //CAMBIO APUESTA ACTUAL INDIVIDUAL
                        if(toBetBet>current_individual_bet){
                            //se sustituye por la apuesta mas grande
                            current_individual_bet = toBetBet;
                            String Scurrent_individual_bet = Integer.toString(current_individual_bet);
                            txt_current_individual_bet.setText(Scurrent_individual_bet);
                            Log.i("Galvan","(current_individual_bet = toBetBet;)");
                        }
                    //ACTUALIZACION CURRENT TOTAL BET
                       current_total_bet=current_total_bet+toBetBet;                               //actualiza el valor de current total bet
                        String Scurrent_total_bet= Integer.toString(current_total_bet);
                        txt_current_total_bet.setText(Scurrent_total_bet);
                    //ACTUALIZACION BET
                    bet=0;
                    txt_bet.setText(Integer.toString(bet));
                    //DATABASE
                        PlayerDataBase[playernumber].setBet(toBetBet);
                        PlayerDataBase[playernumber].setChips(newownChips);
                        PlayerDataBase[playernumber].setAllin(true);
                        txt_playernumber.setText(Integer.toString(playernumber));

                    //todo aqui tambien he variado el integer
                        txt_playernumber.setText(playernumber_toString);
                    //REFRESH
                    list.setAdapter(adapter);
                    //todo y aqui
                    fichasmalaquito.setText(malaquito_toString);
                    fichasmalaquitodatabase.setText(playerdatabase_toString);
                    Log.i("Galvan","apuesta confirmada");
                    //CAMBIO DE TURNO
                    nextTurn();
                    //REFRESH ownChips
                    refresh();
                    txt_playernumber.setText(Integer.toString(playernumber));
                    ownChips= PlayerDataBase[playernumber].getChips();

                    txt_ownChips.setText(ownChips_toString);
                    txt_playernumber.setText(playernumber_toString);

                    Log.i("Galvan","siguiente");
                    Log.i("Galvan",String.format("Player %s",Integer.toString(playernumber)));
                    }

                });
        //PULSAMOS CANCEL
            builder.setNegativeButton(android.R.string.cancel, null);
            builder.create().show();
        }
    //SI HACEMOS UNA APUESTA NORMAL
        else{
            Log.i("Galvan","(newownChips<=0)");
            if(toBetBet<current_individual_bet){}
            else  {
                //CREAMOS DIALOGO
                    AlertDialog.Builder builder2= new AlertDialog.Builder(this);
                    builder2.setMessage(String.format("Are you sure you want to bet %s chips?",Integer.toString(toBetBet)));
                //PULSAMOS YES
                    builder2.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.i("Galvan","apuesta confirmada");
                            //EL VALOS newownChips ES VALIDO
                            ownChips=newownChips;
                        //REFRESCAMOS ownChips EN PANTALLA
                            String SownChips= Integer.toString(ownChips);
                            txt_ownChips.setText(SownChips);
                        //CAMBIO APUESTA ACTUAL INDIVIDUAL
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

                            //DATABASE
                            PlayerDataBase[playernumber].setBet(toBetBet);
                            PlayerDataBase[playernumber].setChips(newownChips);

                            //ACTUALIZACION BET
                            bet=0;
                            txt_bet.setText(Integer.toString(bet));

                            //CAMBIO DE TURNO
                            Log.i("Galvan","Inicio Cambio de Turno");
                            nextTurn();
                            refresh();


                            Log.i("Galvan",String.format("Player %s",Integer.toString(playernumber)));
                        }
                    });
                    builder2.setNegativeButton(android.R.string.cancel,null);
                    builder2.create().show();
                }}
                String SownChips= Integer.toString(ownChips);
                txt_ownChips.setText(SownChips);
                    }
                    else{
                        Log.i("Galvan","vuelta al principio");
                        playernumber=0;
                        Log.i("Galvan",String.format("Player %s",Integer.toString(playernumber)));
                        PlayerDataBase[playernumber].setTurn(true);
                        //REFRESH ownChips
                        ownChips= PlayerDataBase[playernumber].getChips();
                        //todo also here
                        txt_ownChips.setText(ownChips_toString);
                        }


                    //REFRESH ownChips
                    ownChips= PlayerDataBase[playernumber].getChips();
                    //todo also here
                    txt_ownChips.setText(ownChips_toString);
                    Log.i("Galvan",String.format("Player %s",Integer.toString(playernumber)));
                }
            });
            builder2.setNegativeButton(android.R.string.cancel,null);
            builder2.create().show();
        }
        String SownChips= Integer.toString(ownChips);
        txt_ownChips.setText(SownChips);

    }

    private void refresh() {
        //REFRESH ownChips
        Button btn_check = (Button)findViewById(R.id.btn_check);
        ownChips= PlayerDataBase[playernumber].getChips();
        txt_ownChips.setText(Integer.toString(ownChips));
        txt_current_individual_bet.setText(Integer.toString(current_individual_bet));
        txt_current_total_bet.setText(Integer.toString(current_total_bet));
        list.setAdapter(adapter);
        if(current_total_bet!=0){btn_check.setText("FOLD");}
        else{btn_check.setText("CHECK");}

    }

    private void nextTurn() {
    //CAMBIO DE TURNO
        Log.i("Galvan","CAMBIO DE TURNO");
        PlayerDataBase[playernumber].setTurn(false);

        if((playernumber==PlayerDataBase.length-1)){playernumber=0;}
        else{playernumber++;}

        while(!PlayerDataBase[playernumber].isOut()){

            if((playernumber==PlayerDataBase.length-1)){playernumber=0;}
            else{playernumber++;}

        }
    //SE ANALIZA CUANTA gente ESTA IN
        checkOut();


        PlayerDataBase[playernumber].setTurn(true);
        list.setAdapter(adapter);
        refresh();
    }

    private void checkOut() {
        //SE ANALIZA CUANTA ESTA IN
        boolean checkout;
        int playersin=0;
        //contamos cuantas personas isOut
        for(int i = 0; i<PlayerDataBase.length; i++){
            checkout = PlayerDataBase[i].isOut();
            if(checkout==true){playersin++;}
            txt_total_bet.setText(Integer.toString(playersin));

        }

        //si solo va uno es que ha ganado, buscamos en PlayerDataBase quien es el que ha ganado
        if(playersin==1){
            //buscamos el indice de jugador del ganador
            for(int i=0; i<PlayerDataBase.length;i++){
                 if(PlayerDataBase[i].isOut()){
                    winner= i;
                 }
            }
            //sumamos la cantidad de current total bet a las fichas del ganador
            int loot;
            loot= PlayerDataBase[winner].getChips()+current_total_bet;
            PlayerDataBase[winner].setChips(loot);
            AlertDialog.Builder builder3= new AlertDialog.Builder(this);
            builder3.setMessage(String.format("Player %s won %s chips",PlayerDataBase[winner].getName(),Integer.toString(loot)));
            builder3.create().show();
            winner=0;
            loot=0;
            current_total_bet=0;
            current_individual_bet=0;
            playersin=0;
            rotarCiega(playersin);


        }
    }

    private void rotarCiega(int playersin) {
        boolean checkout;//DESPLAZAMIENTO CIEGAS
        if( !PlayerDataBase[PlayerDataBase.length-1].isBig() &
            !PlayerDataBase[PlayerDataBase.length-1].isDealer() &
            !PlayerDataBase[PlayerDataBase.length-1].isSmall()) {
            boolean aux= false;
            for(int i=0; i<PlayerDataBase.length;i++){
                PlayerDataBase[i].setOut(true);
                PlayerDataBase[i].setAllin(false);



            //DEALER->nA
                if(PlayerDataBase[i].isDealer()){
                    PlayerDataBase[i].setDealer(false);
                    Log.i("GALVAN","Dealer-na");
                }
             //SMALL->DEALER
                if(PlayerDataBase[i].isSmall()){
                    PlayerDataBase[i].setDealer(true);
                    PlayerDataBase[i].setSmall(false);
                    Log.i("GALVAN","Small-Big");
                }
            //BIG->SMALL
                if(PlayerDataBase[i].isBig()){
                    PlayerDataBase[i].setSmall(true);
                    PlayerDataBase[i].setBig(false);
                    Log.i("GALVAN","Big-Small");
                    aux=true;
                    Log.i("GALVAN","aux true");
                    }
            //nA->BIG
                if( aux==true&&
                    !PlayerDataBase[i].isBig() &
                    !PlayerDataBase[i].isDealer() &
                    !PlayerDataBase[i].isSmall()) {

                    PlayerDataBase[i].setBig(true);
                    Log.i("GALVAN",String.format("Player %s is Big",Integer.toString(i)));
                    aux=false;

                }
                Log.i("GALVAN",String.format("i= %s",Integer.toString(i)));
                list.setAdapter(adapter);
            }
        }
        else if(PlayerDataBase[PlayerDataBase.length-1].isBig()) {
            PlayerDataBase[0].setBig(true);

            for (int i = 1; i < PlayerDataBase.length; i++) {
                PlayerDataBase[i].setOut(true);
                PlayerDataBase[i].setAllin(false);


                //DEALER->nA
                if (PlayerDataBase[i].isDealer()) {
                    PlayerDataBase[i].setDealer(false);
                    Log.i("GALVAN", "Dealer-na");
                }
                //SMALL->DEALER
                if (PlayerDataBase[i].isSmall()) {
                    PlayerDataBase[i].setDealer(true);
                    PlayerDataBase[i].setSmall(false);
                    Log.i("GALVAN", "Small-Big");
                }
                //BIG->SMALL
                if (PlayerDataBase[i].isBig()) {
                    PlayerDataBase[i].setSmall(true);
                    PlayerDataBase[i].setBig(false);

                    list.setAdapter(adapter);
                }
            }
        }
        else if(PlayerDataBase[PlayerDataBase.length-1].isSmall()){
            PlayerDataBase[0].setBig(false);
            PlayerDataBase[0].setSmall(true);
            PlayerDataBase[1].setBig(true);

            for(int i=1; i<PlayerDataBase.length;i++){
                PlayerDataBase[i].setOut(true);
                PlayerDataBase[i].setAllin(false);



                //DEALER->nA
                if(PlayerDataBase[i].isDealer()){
                    PlayerDataBase[i].setDealer(false);
                    Log.i("GALVAN","Dealer-na");
                }
                //SMALL->DEALER
                if(PlayerDataBase[i].isSmall()){
                    PlayerDataBase[i].setDealer(true);
                    PlayerDataBase[i].setSmall(false);
                    Log.i("GALVAN","Small-Big");
                }

                if( PlayerDataBase[i-1].isSmall()) {

                    PlayerDataBase[i].setBig(true);
                    Log.i("GALVAN",String.format("Player %s is Big",Integer.toString(i)));


                }
                list.setAdapter(adapter);
            }
        }
        else if(PlayerDataBase[PlayerDataBase.length-1].isDealer()){
                PlayerDataBase[0].setSmall(false);
                PlayerDataBase[0].setDealer(true);
                PlayerDataBase[1].setBig(false);
                PlayerDataBase[1].setSmall(true);
                PlayerDataBase[2].setBig(true);
            //DEALER->nA
            for(int i=3; i<PlayerDataBase.length;i++){
            if(PlayerDataBase[i].isDealer()){
                    PlayerDataBase[i].setDealer(false);
                    Log.i("GALVAN","Dealer-na");
                }
        }


    //RESTAURAR OUT
        for(int i = 0; i<PlayerDataBase.length; i++){
            checkout = PlayerDataBase[i].isOut();
            if(checkout==true){playersin++;}
            txt_total_bet.setText(Integer.toString(playersin));
        }
    refresh();

    }
    }

}




