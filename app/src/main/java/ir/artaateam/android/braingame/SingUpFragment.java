package ir.artaateam.android.braingame;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class SingUpFragment extends Fragment {
    EditText usernameEditText;
    EditText nicknameEditText;
    ImageView sss;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sing_in_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        configure();
    }

    private void findViews(View view) {
        usernameEditText = view.findViewById(R.id.user_name_edit_text);
        nicknameEditText = view.findViewById(R.id.nick_name_edit_text);
        sss=view.findViewById(R.id.sss);
    }

    private void configure() {
        sss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singUpImageButtonOnClick();
            }
        });
    }

    private void singUpImageButtonOnClick() {
//        SettingBackgroundFragment settingBackgroundFragment=new SettingBackgroundFragment();
//        getFragmentManager()
//                .beginTransaction()
//                .add(R.id.setting_background_frame, settingBackgroundFragment)
//                .commit();
//        SettingsFragment settingsFragment =new SettingsFragment(settingBackgroundFragment);
//        getFragmentManager()
//                .beginTransaction()
//                .add(R.id.setting_frame, settingsFragment)
//                .addToBackStack(null)
//                .commit();

        String usernameString = usernameEditText.getText().toString();
        if (usernameString.isEmpty() ||
                usernameString.length() < 4 ||
                usernameString.contains("  ")) {
            WrongAnimation(usernameEditText);
            return;
        }

        String nicknameString = nicknameEditText.getText().toString();
        if (nicknameString.isEmpty() ||
                nicknameString.length() < 4 ||
                nicknameString.contains("  ")) {
            WrongAnimation(nicknameEditText);
            return;
        }

        saveUser();
        MainActivity.showAppMainFragment(getActivity());
    }

    private void saveUser() {
        String nicknameString = nicknameEditText.getText().toString();
        String usernameString = usernameEditText.getText().toString();
        User user=new User(usernameString,nicknameString,0);
        UserPreferences.getInstance(getActivity()).putUser(user);
    }

    private void WrongAnimation(EditText editText) {
        editText.setText("");
        ObjectAnimator editTextTranslationX = ObjectAnimator.ofFloat(
                editText,
                "TranslationX",
                0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f, 0f
        );
        editTextTranslationX.setDuration(MainUtilValues.DELAY_WRONG_INPUT_EDIT_TEXT);

        ObjectAnimator editTextScaleX = ObjectAnimator.ofFloat(
                editText,
                "scaleX",
                1f, 1.5f, 1f
        );
        editTextScaleX.setDuration(MainUtilValues.DELAY_WRONG_INPUT_EDIT_TEXT);

        ObjectAnimator editTextScaleY = ObjectAnimator.ofFloat(
                editText,
                "scaleY",
                1f, 1.5f, 1f
        );
        editTextScaleY.setDuration(MainUtilValues.DELAY_WRONG_INPUT_EDIT_TEXT);

        editTextTranslationX.start();
        editTextScaleX.start();
        editTextScaleY.start();
    }
}
