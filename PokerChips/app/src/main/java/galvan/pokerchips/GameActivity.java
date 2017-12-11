package galvan.pokerchips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    // Añado un comentario para probar el push + update...

    //private Button btn_plus_chip = (Button) findViewById(R.id.btn_plus_chip);   va en el onCreate despues de crear el layout
    private int[] chips ={5,10,25,50,100};
    private int pos;
    private TextView txt_chip ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //lista predeterminada solo de nombres
        ListView player_list = (ListView)findViewById(R.id.players_list);
        String[] values = new String[]{"Menganito","Fulanito","Juanito","Estalactito","Teodoro","Pauek","Eustaquio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        player_list.setAdapter(adapter);

        Button btn_plus_chip = (Button) findViewById(R.id.btn_plus_chip);

        pos=0;
        txt_chip =(TextView) findViewById(R.id.txt_chip);

        String textChip = Integer.toString(chips[0]);


       txt_chip.setText(textChip);
        btn_plus_chip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            if(pos<chips.length)
                pos++;

                String textChipBtn = Integer.toString(chips[pos]);
                txt_chip.setText(textChipBtn);
                Log.i("Galvan","btn chip pulsado %i");
            }
        });



















    }
}
