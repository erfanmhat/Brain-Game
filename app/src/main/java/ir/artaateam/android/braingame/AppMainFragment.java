package ir.artaateam.android.braingame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

public class AppMainFragment extends Fragment {
    Button startGame1Button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_main_fragment, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
    }

    private void findViews(View view){
        //startGame1Button=view.findViewById(R.id.start_game1_button);
    }

    private void configure(){
        startGame1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame1ButtonOnClick();
            }
        });
    }

    private void startGame1ButtonOnClick(){

    }

}
