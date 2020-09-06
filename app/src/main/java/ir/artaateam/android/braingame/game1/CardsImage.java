package ir.artaateam.android.braingame.game1;

import ir.artaateam.android.braingame.R;

public class CardsImage {
    private static CardsImage instance;
    private int[] cardsList;

    private CardsImage() {
        cardsList = new int[12];
        cardsList[0] = R.drawable.triangle_red;
        cardsList[1] = R.drawable.triangle_yellow;
        cardsList[2] = R.drawable.triangle_green;
        cardsList[3] = R.drawable.triangle_purple;

        cardsList[4] = R.drawable.square_red;
        cardsList[5] = R.drawable.square_yellow;
        cardsList[6] = R.drawable.square_green;
        cardsList[7] = R.drawable.square_purple;

        cardsList[8] = R.drawable.circle_red;
        cardsList[9] = R.drawable.circle_yellow;
        cardsList[10] = R.drawable.circle_green;
        cardsList[11] = R.drawable.circle_purple;
    }

    public static CardsImage getInstance() {
        if (instance == null) {
            instance = new CardsImage();
        }
        return instance;
    }

    public int getIdByIndex(int index){
        return cardsList[index];
    }
}
