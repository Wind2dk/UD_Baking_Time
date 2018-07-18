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

import dk.getonboard.android.bakingtime.adapter.StepAdapter;
import dk.getonboard.android.bakingtime.model.Step;

public class StepListFragment extends Fragment implements StepAdapter.ListItemClickListener {

    private List<Step> mSteps;
    OnStepClickListener mCallback;

    public interface OnStepClickListener {
        void onStepClick(Step position);
    }

    public void setSteps(List<Step> steps) {
        mSteps = steps;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnStepClickLister");
        }
    }

    public StepListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.step_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StepAdapter stepAdapter = new StepAdapter(mSteps, this);
        recyclerView.setAdapter(stepAdapter);

        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        mCallback.onStepClick(mSteps.get(clickedItemIndex));
    }
}
