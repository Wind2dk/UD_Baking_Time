package dk.getonboard.android.bakingtime;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dk.getonboard.android.bakingtime.Util.RecipeHelper;
import dk.getonboard.android.bakingtime.adapter.RecipeAdapter;
import dk.getonboard.android.bakingtime.model.Recipe;

public class RecipeListFragment extends Fragment implements RecipeAdapter.ListItemClickListener {

    private List<Recipe> mRecipes;
    OnRecipeClickListener mCallback;

    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnRecipeClickLister");
        }
    }

    public RecipeListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mRecipes = RecipeHelper.loadRecipes(getContext());

        RecyclerView recyclerView = rootView.findViewById(R.id.recipe_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecipeAdapter recipeAdapter = new RecipeAdapter(mRecipes, this);
        recyclerView.setAdapter(recipeAdapter);

        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        mCallback.onRecipeClick(mRecipes.get(clickedItemIndex));
    }
}
