package ir.artaateam.android.braingame;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import ir.artaateam.android.braingame.game1.Game1GameFragment;

public class SingUpFragment extends Fragment {
    EditText usernameEditText;
    EditText nicknameEditText;
    ImageButton singUpImageButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sing_in_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
    }
    private void findViews(View view){
        usernameEditText=view.findViewById(R.id.user_name_edit_text);
        nicknameEditText=view.findViewById(R.id.nick_name_edit_text);
        singUpImageButton=view.findViewById(R.id.sing_up_button);
    }
    private void configure(){
        singUpImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singUpImageButtonOnClick();
            }
        });
    }
    private void singUpImageButtonOnClick(){
        String usernameString=usernameEditText.getText().toString();
        if(usernameString.isEmpty()||
                usernameString.length()<4||
                usernameString.contains("  ")){
            WrongAnimation(usernameEditText);
            return;
        }

        String nicknameString=nicknameEditText.getText().toString();
        if(nicknameString.isEmpty()||
                nicknameString.length()<4||
                nicknameString.contains("  ")){
            WrongAnimation(nicknameEditText);
            return;
        }

        Game1GameFragment game1GameFragment=new Game1GameFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.app_main_frame,game1GameFragment)
                .commit();
    }

    private void WrongAnimation(EditText editText){
        editText.setText("");
        ObjectAnimator editTextTranslationX=ObjectAnimator.ofFloat(
                editText,
                "TranslationX",
                0f,20f,0f,-20f,0f,20f,0f,-20f,0f
        );
        editTextTranslationX.setDuration(MainUtilValues.DELAY_WRONG_INPUT);

        ObjectAnimator editTextScaleX=ObjectAnimator.ofFloat(
                editText,
                "scaleX",
                1f,1.5f,1f
        );
        editTextScaleX.setDuration(MainUtilValues.DELAY_WRONG_INPUT);

        ObjectAnimator editTextScaleY=ObjectAnimator.ofFloat(
                editText,
                "scaleY",
                1f,1.5f,1f
        );
        editTextScaleY.setDuration(MainUtilValues.DELAY_WRONG_INPUT);

        editTextTranslationX.start();
        editTextScaleX.start();
        editTextScaleY.start();
    }
}
