package dk.getonboard.android.bakingtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import dk.getonboard.android.bakingtime.Util.RecipeHelper;
import dk.getonboard.android.bakingtime.adapter.RecipeAdapter;
import dk.getonboard.android.bakingtime.model.Recipe;

public class MainActivity extends AppCompatActivity {

    List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipes = RecipeHelper.loadRecipes(this);

        RecyclerView recyclerView = findViewById(R.id.recipe_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecipeAdapter recipeAdapter = new RecipeAdapter(mRecipes);
        recyclerView.setAdapter(recipeAdapter);

    }
}
