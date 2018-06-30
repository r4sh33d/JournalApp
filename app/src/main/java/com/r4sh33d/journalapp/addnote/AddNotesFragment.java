package com.r4sh33d.journalapp.addnote;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r4sh33d.journalapp.R;
import com.r4sh33d.journalapp.activities.MainActivity;
import com.r4sh33d.journalapp.base.BaseFragment;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNotesFragment extends BaseFragment implements AddNotesContract.View{
    private static final String TAG = AddNotesFragment.class.getSimpleName();


    public AddNotesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarTitle("Active Medications");
        ((MainActivity) getActivity()).setDrawerIconToBack();

    }


    void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void moveToNextStep() {
    }
}
