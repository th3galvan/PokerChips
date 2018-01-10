package galvan.pokerchips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by xvilaseca on 09/01/2018.
 */

public class ShowCodeActivity extends AppCompatActivity {

    private TextView txt_code;
    private int code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showqr);

        Bundle code_receive = getIntent().getExtras();
        code = code_receive.getInt("code");

        Button btn_code = (Button)findViewById(R.id.btn_generate_code);
        txt_code = (TextView)findViewById(R.id.txt_code);



        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String string_code = Integer.toString(code);
                txt_code.setText(string_code);

            }
        });


    }

}
