package com.example.android.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
/**
 * Created by ZHE on 2/20/2016.
 */
public class AddArticle extends Fragment
        implements HeadlinesFragment.OnHeadlineSelectedListener {
    final static String ARG_POSITION = "position";
    HeadlinesFragment.OnHeadlineSelectedListener mCallback;
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.ok_view, container, false);
        Button ok = (Button) rootView.findViewById(R.id.ok_button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onArticleSelected(mCurrentPosition);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    public void onArticleSelected(int position) {
        // The user selected the headline of an article from the HeadlinesFragment

        // Create fragment and give it an argument for the selected article
        HeadlinesFragment newFragment = new HeadlinesFragment();
        Bundle args = new Bundle();
//        args.putInt(HeadlinesFragment.ARG_POSITIONS, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}
