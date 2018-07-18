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
import dk.getonboard.android.bakingtime.model.Step;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private List<Step> mStepList;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public StepAdapter(List<Step> steps, ListItemClickListener listener) {
        mStepList = steps;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_card_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step step = mStepList.get(position);
        holder.textViewTitle.setText(step.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle;

        public ViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }

}

