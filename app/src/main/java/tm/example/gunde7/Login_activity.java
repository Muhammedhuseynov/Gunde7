package tm.example.gunde7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/// by:     MUHAMMET.....-- M.H --
public class Login_activity extends AppCompatActivity {
    private Button logBtn,back;
    private EditText input;
    private  String data;
    private String inpData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        logBtn = findViewById(R.id.buttonLogin);
        input = findViewById(R.id.login);
        back = findViewById(R.id.backsecond);

        data = "mmmb";
        inpData = input.getText().toString();
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(input.getText().toString().contains(data)) {
                    Intent cominPage = new Intent(Login_activity.this, Coming_Layout.class);
                    startActivity(cominPage);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Ooops,Yalnysh...",Toast.LENGTH_LONG).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bckLayout = new Intent(Login_activity.this,activity_sec.class);
                startActivity(bckLayout);
                finish();
            }
        });

    }
}
