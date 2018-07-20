package dk.getonboard.android.bakingtime;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dk.getonboard.android.bakingtime.Util.RecipeHelper;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class BakeActivityBasicTest {

    @Rule public ActivityTestRule<BakeActivity> mActivityTestRule = new ActivityTestRule<>(BakeActivity.class);

    @Before
    public void init(){
        StepListFragment stepListFragment = new StepListFragment();
        stepListFragment.setSteps(RecipeHelper.loadRecipes(mActivityTestRule.getActivity()).get(0).getSteps());

        mActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction()
                .add(R.id.bake_frame_layout, stepListFragment)
                .commit();
    }


    /*@Test
    public void stepListClick_OpensSingleStep() {
        onData()
    }*/

    @Test
    public void clickNextStepButton_ChangesCurrentStep() {
        //onView((withId(R.id.button_next))).perform(click());

        init();

        onView(withId(R.id.step_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.step_description)).check(matches(isDisplayed()));

    }
}
