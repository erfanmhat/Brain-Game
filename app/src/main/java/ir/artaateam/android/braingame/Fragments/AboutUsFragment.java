package ir.artaateam.android.braingame.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import ir.artaateam.android.braingame.R;
// TODO :)
public class AboutUsFragment extends Fragment {
    public AboutUsFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_us_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void closeFragment() {
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
