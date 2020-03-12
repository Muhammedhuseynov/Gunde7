package tm.example.gunde7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Coming_Layout extends AppCompatActivity {
    private ArrayList <Item_money> items = new ArrayList<>();
    private GridView mGrid;
    private Button btn,btnremove,next;
    private ComingAdapter adapter;
    private EditText input,inputRemove;
    private TextView mtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming__layout);
        mGrid = findViewById(R.id.my_viewGrid);
        btn = findViewById(R.id.my_adder);
        next = findViewById(R.id.my_prev);
        input = findViewById(R.id.my_input);

        inputRemove = findViewById(R.id.my_removeInput);
        btnremove = findViewById(R.id.my_removeBtn);

        loadData();
        adapter = new ComingAdapter(this,R.layout.item_coming_layout,items);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!input.getText().toString().isEmpty()){

                    items.add(0,new Item_money(input.getText().toString()));

                    adapter.notifyDataSetChanged();
                    getData();
                    input.getText().clear();
                }


            }
        });
        mGrid.setAdapter(adapter);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Coming_Layout.this,activity_sec.class);
                startActivity(mIntent);
                finish();
            }
        });


        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max = items.size()+1;
                if(!inputRemove.getText().toString().isEmpty()){
                    int position = Integer.parseInt(inputRemove.getText().toString());
                    if(!items.isEmpty() && position < max && position != 0){
                        remover(position);
                        adapter.notifyDataSetChanged();
                        getData();

                    }

                }

            }
        });

    }


    public void remover(int pos){
        items.remove(pos-1);
    }

    public void  getData(){
        SharedPreferences sharedPreferences = getSharedPreferences("monitem",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("myTask",json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("monitem",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("myTask",null);
        Type type = new TypeToken<ArrayList<Item_money>>(){}.getType();
        items = gson.fromJson(json,type);

        if(items == null){
            items = new ArrayList<>();
        }

    }
}
