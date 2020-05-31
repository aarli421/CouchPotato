package com.example.couchpotato.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.couchpotato.Classes.Ingredient;
import com.example.couchpotato.MainActivity;
import com.example.couchpotato.R;
import java.util.*;

public class BuyActivity extends AppCompatActivity {
    Button button;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        button = findViewById(R.id.back);

        listView = (ListView) findViewById(R.id.buy);



        ArrayList<Ingredient> list = new ArrayList<>();
        boolean inList = false;
        for (Ingredient ing : ViewFoodActivity.cart){
            for (int i = 0; i<list.size(); i++){
                if (list.get(i).getName().equals(ing.getName())){
                    list.get(i).add(ing.getAmount());
                    inList = true;
                }
            }
            if (inList == false){
                list.add(ing);
            }
        }


        ArrayAdapter<Ingredient> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);


        BuyActivity.MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });

    }


    class MyAdapter extends ArrayAdapter<Ingredient> {
        Context context;
        ArrayList<Ingredient> names;


        MyAdapter (Context c, ArrayList<Ingredient> names){
            super(c, R.layout.buy_layout, R.id.item, names);
            this.context = c;
            this.names = names;
        }
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.buy_layout, parent, false);
            TextView items;
            TextView amount;
            TextView price;
            items = row.findViewById(R.id.item);
            amount = row.findViewById(R.id.amount);
            price = row.findViewById(R.id.price);

            items.setText(names.get(position).getName());
            amount.setText(String.valueOf(names.get(position).getAmount()));
            price.setText(String.valueOf(names.get(position).getPrice()));
            return row;
        }
    }
}






