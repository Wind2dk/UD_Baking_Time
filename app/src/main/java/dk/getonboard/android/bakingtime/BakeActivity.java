package dk.getonboard.android.bakingtime;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import dk.getonboard.android.bakingtime.adapter.StepAdapter;
import dk.getonboard.android.bakingtime.model.Recipe;
import dk.getonboard.android.bakingtime.model.Step;

// **
// Activity for showing info about a single recipe
// Master-Detail flow of steps
// Two-Pane on tablet
// ** //
public class BakeActivity extends AppCompatActivity implements StepListFragment.OnStepClickListener {

    Recipe mRecipe;
    StepListFragment mStepListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake);

        mRecipe = getIntent().getParcelableExtra("recipe");

        mStepListFragment = new StepListFragment();
        mStepListFragment.setSteps(mRecipe.getSteps());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.bake_frame_layout, mStepListFragment)
                .commit();

    }

    @Override
    public void onStepClick(Step step) {
        Toast.makeText(this, "Clicked: " + step.getShortDescription(), Toast.LENGTH_SHORT).show();
    }
}
