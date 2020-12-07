package ir.artaateam.android.braingame.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ir.artaateam.android.braingame.Forms.ShapeAndColor;
import ir.artaateam.android.braingame.R;
import ir.artaateam.android.braingame.Enums.Color;
import ir.artaateam.android.braingame.Enums.GameDifficulty;
import ir.artaateam.android.braingame.Enums.GameInputButton;
import ir.artaateam.android.braingame.Enums.Shape;

import static ir.artaateam.android.braingame.Enums.Color.*;
import static ir.artaateam.android.braingame.Enums.GameInputButton.*;
import static ir.artaateam.android.braingame.Enums.Shape.*;
import static ir.artaateam.android.braingame.Enums.GameDifficulty.*;

public class ShapeAndColorController {
    private Random random;
    private ShapeAndColor whatIsShapeAndColor;
    private GameInputButton answerButton;
    private List<ShapeAndColor> shapeAndColorList;

    public ShapeAndColorController(GameDifficulty gameDifficulty) {
        random = new Random();
        whatIsShapeAndColor = new ShapeAndColor(NULLShape, NULLColor, -1);
        answerButton = NULLButton;
        shapeAndColorList = new ArrayList<>();

        if (gameDifficulty == instructions) {
            setGameDifficultyInstructions();
        } else if (gameDifficulty == easy) {
            setGameDifficultyEasy();
        } else if (gameDifficulty == normal) {
            setGameDifficultyNormal();
        } else if (gameDifficulty == hard) {
            setGameDifficultyHard();
        }
    }

    public boolean isInputButtonTrue(GameInputButton clickedButton) {
        return clickedButton == answerButton;
    }

    private void setGameDifficultyInstructions() {
        shapeAndColorList.clear();

        shapeAndColorList.add(new ShapeAndColor(circle, blue, R.drawable.blue_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, red, R.drawable.red_circle));

        shapeAndColorList.add(new ShapeAndColor(square, blue, R.drawable.blue_square));
        shapeAndColorList.add(new ShapeAndColor(square, red, R.drawable.red_square));
    }

    private void setGameDifficultyEasy() {
        shapeAndColorList.clear();

        shapeAndColorList.add(new ShapeAndColor(circle, blue, R.drawable.blue_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, red, R.drawable.red_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, green, R.drawable.green_circle));

        shapeAndColorList.add(new ShapeAndColor(square, blue, R.drawable.blue_square));
        shapeAndColorList.add(new ShapeAndColor(square, red, R.drawable.red_square));
        shapeAndColorList.add(new ShapeAndColor(square, green, R.drawable.green_square));

        shapeAndColorList.add(new ShapeAndColor(triangle, blue, R.drawable.blue_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, red, R.drawable.red_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, green, R.drawable.green_triangle));
    }

    private void setGameDifficultyNormal() {
        shapeAndColorList.clear();

        shapeAndColorList.add(new ShapeAndColor(circle, blue, R.drawable.blue_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, red, R.drawable.red_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, green, R.drawable.green_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, yellow, R.drawable.yellow_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, purple, R.drawable.purple_circle));

        shapeAndColorList.add(new ShapeAndColor(square, blue, R.drawable.blue_square));
        shapeAndColorList.add(new ShapeAndColor(square, red, R.drawable.red_square));
        shapeAndColorList.add(new ShapeAndColor(square, green, R.drawable.green_square));
        shapeAndColorList.add(new ShapeAndColor(square, yellow, R.drawable.yellow_square));
        shapeAndColorList.add(new ShapeAndColor(square, purple, R.drawable.purple_square));

        shapeAndColorList.add(new ShapeAndColor(triangle, blue, R.drawable.blue_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, red, R.drawable.red_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, green, R.drawable.green_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, yellow, R.drawable.yellow_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, purple, R.drawable.purple_triangle));

        shapeAndColorList.add(new ShapeAndColor(diamond, blue, R.drawable.blue_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, red, R.drawable.red_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, green, R.drawable.green_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, yellow, R.drawable.yellow_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, purple, R.drawable.purple_diamond));

        shapeAndColorList.add(new ShapeAndColor(oval, blue, R.drawable.blue_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, red, R.drawable.red_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, green, R.drawable.green_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, yellow, R.drawable.yellow_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, purple, R.drawable.purple_oval));
    }

    private void setGameDifficultyHard() {
        shapeAndColorList.clear();

        shapeAndColorList.add(new ShapeAndColor(circle, blue, R.drawable.blue_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, red, R.drawable.red_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, green, R.drawable.green_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, yellow, R.drawable.yellow_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, purple, R.drawable.purple_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, indigo, R.drawable.indigo_circle));
        shapeAndColorList.add(new ShapeAndColor(circle, orange, R.drawable.orange_circle));

        shapeAndColorList.add(new ShapeAndColor(square, blue, R.drawable.blue_square));
        shapeAndColorList.add(new ShapeAndColor(square, red, R.drawable.red_square));
        shapeAndColorList.add(new ShapeAndColor(square, green, R.drawable.green_square));
        shapeAndColorList.add(new ShapeAndColor(square, yellow, R.drawable.yellow_square));
        shapeAndColorList.add(new ShapeAndColor(square, purple, R.drawable.purple_square));
        shapeAndColorList.add(new ShapeAndColor(square, indigo, R.drawable.indigo_square));
        shapeAndColorList.add(new ShapeAndColor(square, orange, R.drawable.orange_square));

        shapeAndColorList.add(new ShapeAndColor(triangle, blue, R.drawable.blue_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, red, R.drawable.red_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, green, R.drawable.green_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, yellow, R.drawable.yellow_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, purple, R.drawable.purple_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, indigo, R.drawable.indigo_triangle));
        shapeAndColorList.add(new ShapeAndColor(triangle, orange, R.drawable.orange_triangle));

        shapeAndColorList.add(new ShapeAndColor(diamond, blue, R.drawable.blue_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, red, R.drawable.red_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, green, R.drawable.green_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, yellow, R.drawable.yellow_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, purple, R.drawable.purple_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, indigo, R.drawable.indigo_diamond));
        shapeAndColorList.add(new ShapeAndColor(diamond, orange, R.drawable.orange_diamond));

        shapeAndColorList.add(new ShapeAndColor(oval, blue, R.drawable.blue_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, red, R.drawable.red_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, green, R.drawable.green_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, yellow, R.drawable.yellow_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, purple, R.drawable.purple_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, indigo, R.drawable.indigo_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, orange, R.drawable.orange_oval));

        shapeAndColorList.add(new ShapeAndColor(pentagon, blue, R.drawable.blue_pentagon));
        shapeAndColorList.add(new ShapeAndColor(pentagon, red, R.drawable.red_pentagon));
        shapeAndColorList.add(new ShapeAndColor(pentagon, green, R.drawable.green_pentagon));
        shapeAndColorList.add(new ShapeAndColor(pentagon, yellow, R.drawable.yellow_pentagon));
        shapeAndColorList.add(new ShapeAndColor(pentagon, purple, R.drawable.purple_pentagon));
        shapeAndColorList.add(new ShapeAndColor(pentagon, indigo, R.drawable.indigo_pentagon));
        shapeAndColorList.add(new ShapeAndColor(pentagon, orange, R.drawable.orange_pentagon));

        shapeAndColorList.add(new ShapeAndColor(hexagon, blue, R.drawable.blue_hexagon));
        shapeAndColorList.add(new ShapeAndColor(hexagon, red, R.drawable.red_hexagon));
        shapeAndColorList.add(new ShapeAndColor(hexagon, green, R.drawable.green_hexagon));
        shapeAndColorList.add(new ShapeAndColor(hexagon, yellow, R.drawable.yellow_hexagon));
        shapeAndColorList.add(new ShapeAndColor(hexagon, purple, R.drawable.purple_hexagon));
        shapeAndColorList.add(new ShapeAndColor(hexagon, indigo, R.drawable.indigo_hexagon));
        shapeAndColorList.add(new ShapeAndColor(hexagon, orange, R.drawable.orange_hexagon));
    }

    public int getRandom() {
        int id;

        if ((whatIsShapeAndColor.getColor() == NULLColor) &&
                (whatIsShapeAndColor.getShape() == NULLShape) &&
                (whatIsShapeAndColor.getId() == -1)) {
            int randomInt;
            Color color;
            Shape shape;
            randomInt = random.nextInt(shapeAndColorList.size());
            id = shapeAndColorList.get(randomInt).getId();
            color = shapeAndColorList.get(randomInt).getColor();
            shape = shapeAndColorList.get(randomInt).getShape();
            whatIsShapeAndColor = new ShapeAndColor(shape, color, id);

        } else {
            int whichOneIsTrue = random.nextInt(4);//4 input button

            if (whichOneIsTrue == 0) {//shape Button true
                id = getRandomShapeEqualOldShape
                        (whatIsShapeAndColor.getShape(),
                                whatIsShapeAndColor.getColor());
                answerButton = shapeButton;

            } else if (whichOneIsTrue == 1) {//color Button true
                id = getRandomColorEqualOldColor
                        (whatIsShapeAndColor.getShape(),
                                whatIsShapeAndColor.getColor());
                answerButton = colorButton;

            } else if (whichOneIsTrue == 2) {//shapeAndColor Button true
                id = whatIsShapeAndColor.getId();
                answerButton = bothButton;

            } else if (whichOneIsTrue == 3) {//neither Button true
                id = getRandomShapeAndColorNotEqualOldShapeAndColor
                        (whatIsShapeAndColor.getShape(),
                                whatIsShapeAndColor.getColor());
                answerButton = nonButton;

            } else {//for ignore <id> not have been initialized but never reached
                id = whatIsShapeAndColor.getId();
                answerButton = NULLButton;
            }
        }
        return id;
    }

    private int getRandomShapeEqualOldShape(Shape oldShape, Color oldColor) {
        List<ShapeAndColor> subShapeAndColorList = new ArrayList<>();

        for (int i = 0; i < shapeAndColorList.size(); i++) {
            if ((shapeAndColorList.get(i).getShape() == oldShape)
                    &&
                    (shapeAndColorList.get(i).getColor() != oldColor)) {
                subShapeAndColorList.add(shapeAndColorList.get(i));
            }
        }

        int randomInt = random.nextInt(subShapeAndColorList.size());
        whatIsShapeAndColor = subShapeAndColorList.get(randomInt);
        return whatIsShapeAndColor.getId();
    }

    private int getRandomColorEqualOldColor(Shape oldShape, Color oldColor) {
        List<ShapeAndColor> subShapeAndColorList = new ArrayList<>();

        for (int i = 0; i < shapeAndColorList.size(); i++) {
            if ((shapeAndColorList.get(i).getColor() == oldColor)
                    &&
                    (shapeAndColorList.get(i).getShape() != oldShape)) {
                subShapeAndColorList.add(shapeAndColorList.get(i));
            }
        }

        int randomInt = random.nextInt(subShapeAndColorList.size());
        whatIsShapeAndColor = subShapeAndColorList.get(randomInt);
        return whatIsShapeAndColor.getId();
    }

    private int getRandomShapeAndColorNotEqualOldShapeAndColor
            (Shape oldShape, Color oldColor) {
        List<ShapeAndColor> subShapeAndColorList = new ArrayList<>();

        for (int i = 0; i < shapeAndColorList.size(); i++) {
            if ((shapeAndColorList.get(i).getShape() != oldShape)
                    &&
                    (shapeAndColorList.get(i).getColor() != oldColor)) {
                subShapeAndColorList.add(shapeAndColorList.get(i));
            }
        }

        int randomInt = random.nextInt(subShapeAndColorList.size());
        whatIsShapeAndColor = subShapeAndColorList.get(randomInt);
        return whatIsShapeAndColor.getId();
    }
}