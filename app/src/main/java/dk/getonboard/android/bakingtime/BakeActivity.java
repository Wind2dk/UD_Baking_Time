package dk.getonboard.android.bakingtime;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import dk.getonboard.android.bakingtime.model.Recipe;

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
    public void onStepClick(int step) {
        SingleStepFragment singleStepFragment = new SingleStepFragment();
        singleStepFragment.setStep(step, mRecipe.getSteps());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bake_frame_layout, singleStepFragment)
                .addToBackStack("singleStepFragment")
                .commit();
    }
}
