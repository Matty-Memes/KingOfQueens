package javalampstudos.kingofqueens.kingOfQueens.world;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.GameViewFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleScreen extends Fragment {


    public TitleScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title_screen, container, false);


    }



}
