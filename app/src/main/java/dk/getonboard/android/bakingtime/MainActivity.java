package dk.getonboard.android.bakingtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import dk.getonboard.android.bakingtime.model.Recipe;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickListener {

    List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    @Override
    public void onRecipeClick(Recipe recipe) {
        Toast.makeText(this, "Clicked recipe: " + recipe.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BakeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }
}
