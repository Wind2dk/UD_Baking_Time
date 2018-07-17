package dk.getonboard.android.bakingtime.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dk.getonboard.android.bakingtime.R;
import dk.getonboard.android.bakingtime.model.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> mRecipeList;

    public RecipeAdapter(List<Recipe> recipes) {
        mRecipeList = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = mRecipeList.get(position);
        holder.textViewTitle.setText(recipe.getName());
        holder.textViewSteps.setText("Steps: " + String.valueOf(recipe.getSteps().size()));
        holder.textViewServings.setText("Servings: " + recipe.getServings());
        holder.textViewTime.setText("Time to make: " + recipe.getTimeInMinutes());
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle, textViewSteps, textViewServings, textViewTime;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // todo open details
                }
            });
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewSteps = itemView.findViewById(R.id.textViewSteps);
            textViewServings = itemView.findViewById(R.id.textViewServings);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }

}