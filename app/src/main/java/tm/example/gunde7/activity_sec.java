package tm.example.gunde7;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class activity_sec extends AppCompatActivity {
    private Button prev,adderDate,remove,my_Btn;
    private ArrayList<ItemDateClass> listDate = new ArrayList<>();
    private GridView secGrid;
    private EditText input,remInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        prev = findViewById(R.id.prev);
        secGrid = findViewById(R.id.viewGridSec);
        adderDate = findViewById(R.id.adderSec);
        input = findViewById(R.id.inputSec);
        remove = findViewById(R.id.removeBtnSec);
        remInput = findViewById(R.id.removeSec);
        my_Btn = findViewById(R.id.my_btn);
        // for save data
        loadData();

        final NewDateAdapter adapter = new NewDateAdapter(this,R.layout.item_date_layout,listDate);


        // button for previous page
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prevInten = new Intent(activity_sec.this,MainActivity.class);
                startActivity(prevInten);
                finish();
            }
        });

        // Button next page
        my_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_page = new Intent(activity_sec.this,Login_activity.class);
                startActivity(my_page);
                finish();
            }
        });


        // Button adder
        adderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if EditText not empty //
                if(!input.getText().toString().isEmpty()){

                    //adding data to Arraylist
                    listDate.add(0,new ItemDateClass(input.getText().toString()));

                    // Updating Adapter // When added new item on Inteface
                    adapter.notifyDataSetChanged();

                    // getdata for gson
                    getData();

                    // clear  EditText
                    input.getText().clear();
                }

            }
        });

        // remove Method //
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // size Arraylist
                int maxPos = listDate.size()+1;

                // EditText not empty
                if(!remInput.getText().toString().isEmpty()){
                    int position = Integer.parseInt(remInput.getText().toString());
                    if(position < maxPos && position > 0){

                        remover(position);
                        // Update
                        adapter.notifyDataSetChanged();
                        getData();
                        input.getText().clear();
                    }
                }
            }
        });

        secGrid.setAdapter(adapter);
    }
    public void remover(int pos){
        listDate.remove(pos-1);
    }

    public void getData(){
        SharedPreferences sharedPreferences = getSharedPreferences("gunde data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson ggson = new Gson();
        String jjson = ggson.toJson(listDate);
        editor.putString("keydata",jjson);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("gunde data",MODE_PRIVATE);
        Gson ggson = new Gson();

        String jjson = sharedPreferences.getString("keydata",null);

        Type type = new TypeToken<ArrayList<ItemDateClass>>(){}.getType();

        listDate = ggson.fromJson(jjson,type);
        if(listDate == null){
            listDate = new ArrayList<>();
        }
    }
}
