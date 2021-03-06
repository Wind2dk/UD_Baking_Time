package dk.getonboard.android.bakingtime;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CircularProgressDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dk.getonboard.android.bakingtime.model.Step;

public class SingleStepFragment extends Fragment {

    @BindView(R.id.button_previous) Button btn_previous;
    @BindView(R.id.button_next) Button btn_next;
    @BindView(R.id.media_image_view) ImageView mediaImageView;
    @BindView(R.id.media_player_view) SimpleExoPlayerView mediaPlayerView;
    @BindView(R.id.step_description) TextView descriptionTxtView;

    List<Step> mSteps;
    int mStepIndex;
    SimpleExoPlayer mExoPlayer;

    public SingleStepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_step, container, false);

        ButterKnife.bind(this, view);

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStepIndex--;
                updateUI();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStepIndex++;
                updateUI();
            }
        });

        updateUI();

        return view;
    }

    private void updateUI() {
        showMedia();
        descriptionTxtView.setText(getCurrentStep().getDescription());
        updateButtons();
    }

    private void updateButtons() {
        if(mStepIndex == 0) {
            btn_previous.setEnabled(false);
        } else {
            btn_previous.setEnabled(true);
        }
        if(mStepIndex == mSteps.size() - 1) {
            btn_next.setEnabled(false);
        } else {
            btn_next.setEnabled(true);
        }
    }

    private void showMedia() {
        if(!TextUtils.isEmpty(getCurrentStep().getVideoURL())) {
            // Show video and hide image view
            playMedia();
            Toast.makeText(getContext(), "This step has video", Toast.LENGTH_SHORT).show();
            hideImageView();
        } else if(!TextUtils.isEmpty(getCurrentStep().getThumbnailUrl())) {
            // Show image and hide video
            showImage();
            Toast.makeText(getContext(), "This step has image", Toast.LENGTH_SHORT).show();
            hidePlayerView();
        } else {
            // No media, hide player and show default image
            hidePlayerView();
            Toast.makeText(getContext(), "No Media", Toast.LENGTH_SHORT).show();
        }
    }

    private void showImage() {
        Glide
                .with(this)
                .load(Uri.parse(getCurrentStep().getThumbnailUrl()))
                //.apply(new RequestOptions()
                //.placeholder() todo: add placeholder
                .into(mediaImageView);
    }

    private void playMedia() {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mediaPlayerView.setPlayer(mExoPlayer);
        }
        // Prepare the MediaSource
        String userAgent = Util.getUserAgent(getContext(), "BakingTime");
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(getCurrentStep().getVideoURL()), new DefaultDataSourceFactory(
                getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    private void hideImageView() {
        mediaImageView.setVisibility(View.GONE);
        mediaPlayerView.setVisibility(View.VISIBLE);
    }

    private void hidePlayerView() {
        mediaImageView.setVisibility(View.VISIBLE);
        mediaPlayerView.setVisibility(View.GONE);
    }

    public void setStep(int stepIndex, List<Step> steps) {
        mStepIndex = stepIndex;
        mSteps = steps;
    }

    private Step getCurrentStep() {
        return mSteps.get(mStepIndex);
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
}
