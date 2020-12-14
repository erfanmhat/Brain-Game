package ir.artaateam.android.braingame.Fragments;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import ir.artaateam.android.braingame.App.Data;
import ir.artaateam.android.braingame.Controllers.FragmentController;
import ir.artaateam.android.braingame.R;

public class SingUpFragment extends Fragment {
    final static int DELAY_WRONG_INPUT_EDIT_TEXT = 400;
    EditText nameEditText;
    ImageView singUpButton;
    ImageView settingsImageView;

    public SingUpFragment() {
        super();
    }

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
        nameEditText.requestFocus();
    }

    @Override
    public void onPause() {
        FragmentController.removeFragment(getActivity(),this);
        super.onPause();
    }

    private void findViews(View view) {
        nameEditText = view.findViewById(R.id.nick_name_edit_text);
        singUpButton = view.findViewById(R.id.sing_up_button);
        settingsImageView = view.findViewById(R.id.sing_up_settings_image_view);
    }

    private void configure() {
        singUpButton.setOnClickListener(view -> singUpImageButtonOnClick());
        settingsImageView.setOnClickListener(view -> FragmentController.showSettingsFragment(getActivity()));
    }

    private void singUpImageButtonOnClick() {
        String nicknameString = nameEditText.getText().toString();
        if (nicknameString.isEmpty() ||
                nicknameString.length() < 3 ||
                nicknameString.contains("  ")) {
            WrongAnimation(nameEditText);
            return;
        }

        saveUser();
        FragmentController.removeFragment(getActivity(),this);
        FragmentController.showMainFragment(getActivity());
    }

    private void saveUser() {
        Data.get().setName(nameEditText.getText().toString());
    }

    private void WrongAnimation(EditText editText) {
        editText.setText("");
        ObjectAnimator editTextTranslationX = ObjectAnimator.ofFloat(
                editText,
                "TranslationX",
                0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f, 0f
        );
        editTextTranslationX.setDuration(DELAY_WRONG_INPUT_EDIT_TEXT);

        ObjectAnimator editTextScaleX = ObjectAnimator.ofFloat(
                editText,
                "scaleX",
                1f, 1.5f, 1f
        );
        editTextScaleX.setDuration(DELAY_WRONG_INPUT_EDIT_TEXT);

        ObjectAnimator editTextScaleY = ObjectAnimator.ofFloat(
                editText,
                "scaleY",
                1f, 1.5f, 1f
        );
        editTextScaleY.setDuration(DELAY_WRONG_INPUT_EDIT_TEXT);

        editTextTranslationX.start();
        editTextScaleX.start();
        editTextScaleY.start();
    }
}
