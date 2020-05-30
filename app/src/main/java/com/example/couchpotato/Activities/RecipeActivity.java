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
import com.example.couchpotato.Classes.Recipe;
import com.example.couchpotato.MainActivity;
import com.example.couchpotato.R;

import org.w3c.dom.Text;

import java.security.cert.LDAPCertStoreParameters;
import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    Button button;
    ArrayList <Recipe> store;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        //hi


        listView = findViewById(R.id.listview);
        button = findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        ArrayList <Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Salt", 1.0 , 1, "pinch", -1));
        ingredients.add(new Ingredient("Dried Pasta", 1.0 , 1, "handful", -1));
        ingredients.add(new Ingredient("Butter", 1.0 , 2, "tablespoon" , 5));
        ingredients.add(new Ingredient("Grated Parmesan", 2.0 ,1, "tablespoon", 5));
        ingredients.add(new Ingredient("Black Pepper", 1.0 ,1,  "pinch", 5));

        store = new ArrayList<>();


        store.add(new Recipe(ingredients, "pasta", "Italian", 1));
        store.add(new Recipe(ingredients, "noodles", "Chinese", 1));
        store.add(new Recipe(ingredients, "curry", "India", 10));
        store.add(new Recipe(ingredients, "baguettes", "French", 1));
        store.add(new Recipe(ingredients, "rice", "Chinese", 20));
        store.add(new Recipe(ingredients, "pasta", "Italian", 1));
        store.add(new Recipe(ingredients, "noodles", "Chinese", 1));
        store.add(new Recipe(ingredients, "curry", "India", 10));
        store.add(new Recipe(ingredients, "baguettes", "French", 1));
        store.add(new Recipe(ingredients, "rice", "Chinese", 20));



        final ArrayList <String> recipeNames = new ArrayList<>();
        ArrayList <Double> recipePrice = new ArrayList<>();
        ArrayList <String> recipeAmount = new ArrayList<>();
        for (Recipe r : store){
            recipeNames.add(r.getName());
            recipePrice.add(r.getPrice());
            recipeAmount.add("2");
        }

        MyAdapter adapter = new MyAdapter(  this, recipeNames, recipePrice);
        listView.setAdapter(adapter);


    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList <String> recipeNames;
        ArrayList <Double> recipePrice;
        ArrayList <String> recipeAmount;
        Button up;
        Button down;
        Button toCart;
        TextView countIng;


        MyAdapter (Context c, ArrayList<String> names, ArrayList<Double> price){
            super(c, R.layout.row, R.id.main_title, names);
            this.context = c;
            this.recipeNames = names;
            this.recipePrice = price;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle;
            myTitle = row.findViewById(R.id.main_title);
            TextView myDescription = row.findViewById(R.id.sub_title);
            myTitle.setText(recipeNames.get(position));
            myDescription.setText("$" + recipePrice.get(position).toString());

            toCart = row.findViewById(R.id.to_cart);
            up = row.findViewById(R.id.button_up);
            down = row.findViewById(R.id.button_down);
            countIng = row.findViewById(R.id.count);


            countIng.setText("0");

            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countIng = listView.getChildAt(position).findViewById(R.id.count);
                    Integer a = Integer.parseInt(countIng.getText().toString());
                    a++;
                    countIng.setText(a.toString());
                    return;
                }
            });

            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countIng = listView.getChildAt(position).findViewById(R.id.count);
                    String s = countIng.getText().toString();
                    Integer a = Integer.parseInt(s) - 1;
                    if (a<0){
                        return;
                    }
                    countIng.setText(a.toString());
                    return;
                }
            });

            toCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecipeActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                    //add to users cart TODO:
                    return;
                }
            });

            return row;
        }
    }
}
