package tm.example.gunde7;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList <ItemsClass> items = new ArrayList<>();
    private ListView mListView;
    private Button btn,btnremove,next;
    private CostumAdapter adapter;
    private EditText input,inputRemove;
    private TextView mtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.viewGrid);
        btn = findViewById(R.id.adder);
        next = findViewById(R.id.next);
        input = findViewById(R.id.input);

        inputRemove = findViewById(R.id.remove);
        btnremove = findViewById(R.id.removeBtn);

        // get data from Arraylist and put them into gson
        loadData();
        adapter = new CostumAdapter(this,R.layout.item_layout,items);

        // Button for adding Item
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if EditText not empty
                if(!input.getText().toString().isEmpty()){
                    // add item in Arraylist
                    items.add(0,new ItemsClass(input.getText().toString()));

                    // update interface
                    adapter.notifyDataSetChanged();
                    //get Data from user;
                    getData();
                    input.getText().clear();
                }


            }
        });

        mListView.setAdapter(adapter);


        // button for next page
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this,activity_sec.class);
                startActivity(mIntent);
                finish();
            }
        });


        // button for remove Items
        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // size of Arraylist
                int max = items.size()+1;

                // if EditText not empty
                if(!inputRemove.getText().toString().isEmpty()){
                    int position = Integer.parseInt(inputRemove.getText().toString());
                    if(!items.isEmpty() && position < max && position != 0){

                        // remove item
                        remover(position);

                        // update interface
                        adapter.notifyDataSetChanged();
                        // get item from user
                        getData();

                    }

                }

            }
        });

    }


    public void remover(int pos){
        items.remove(pos-1);
    }
 // Used gson for save datas
    public void  getData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared prefrences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("task",json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared prefrences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task",null);
        Type type = new TypeToken<ArrayList<ItemsClass>>(){}.getType();
        items = gson.fromJson(json,type);

        if(items == null){
            items = new ArrayList<>();
        }

    }
}
