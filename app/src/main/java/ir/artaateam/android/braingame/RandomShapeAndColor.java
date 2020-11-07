package ir.artaateam.android.braingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ir.artaateam.android.braingame.enums.Color;
import ir.artaateam.android.braingame.enums.GameDifficulty;
import ir.artaateam.android.braingame.enums.Shape;

import static ir.artaateam.android.braingame.enums.Color.*;
import static ir.artaateam.android.braingame.enums.Shape.*;
import static ir.artaateam.android.braingame.enums.GameDifficulty.*;

public class RandomShapeAndColor {
    private Random random;
    private ShapeAndColor oldShapeAndColor;
    private List<ShapeAndColor> shapeAndColorList;

    public RandomShapeAndColor(GameDifficulty gameDifficulty) {
        random = new Random();
        oldShapeAndColor = new ShapeAndColor(NULLShape, NULLColor, 0);
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
        shapeAndColorList.add(new ShapeAndColor(oval, indigo, R.drawable.purple_oval));
        shapeAndColorList.add(new ShapeAndColor(oval, orange, R.drawable.purple_oval));
        
        //// STOPSHIP: 11/8/20  
    }

    public int getRandom() {
        int randomInt;
        int id;
        Color color;
        Shape shape;
        if ((oldShapeAndColor.getColor() == NULLColor) && (oldShapeAndColor.getShape() == NULLShape)) {
            randomInt = random.nextInt(shapeAndColorList.size());
            id = shapeAndColorList.get(randomInt).getId();
            color = shapeAndColorList.get(randomInt).getColor();
            shape = shapeAndColorList.get(randomInt).getShape();
            oldShapeAndColor = new ShapeAndColor(shape, color, id);
        } else {
            int whichOneIsTrue = random.nextInt(4);//4 input button

            if (whichOneIsTrue == 0) {//shape true
                id = getRandomShapeEqualOldShape
                        (oldShapeAndColor.getShape());

            } else if (whichOneIsTrue == 1) {//color true
                id = getRandomColorEqualOldColor
                        (oldShapeAndColor.getColor());

            } else if (whichOneIsTrue == 2) {//both true
                id = oldShapeAndColor.getId();

            } else if (whichOneIsTrue == 3) {//non true
                id = getRandomShapeAndColorNotEqualOldShapeAndColor
                        (oldShapeAndColor.getShape(),
                                oldShapeAndColor.getColor());

            } else {//for ignore <id> not have been initialized but never reached
                id = oldShapeAndColor.getId();
            }
        }
        return id;
    }

    private int getRandomShapeEqualOldShape(Shape oldShape) {
        List<ShapeAndColor> subShapeAndColorList = new ArrayList<>();

        for (int i = 0; i < shapeAndColorList.size(); i++) {
            if (shapeAndColorList.get(i).getShape() == oldShape) {
                subShapeAndColorList.add(shapeAndColorList.get(i));
            }
        }

        int randomInt = random.nextInt(subShapeAndColorList.size());
        return subShapeAndColorList.get(randomInt).getId();
    }

    private int getRandomColorEqualOldColor(Color oldColor) {
        List<ShapeAndColor> subShapeAndColorList = new ArrayList<>();

        for (int i = 0; i < shapeAndColorList.size(); i++) {
            if (shapeAndColorList.get(i).getColor() == oldColor) {
                subShapeAndColorList.add(shapeAndColorList.get(i));
            }
        }

        int randomInt = random.nextInt(subShapeAndColorList.size());
        return subShapeAndColorList.get(randomInt).getId();
    }

    private int getRandomShapeAndColorNotEqualOldShapeAndColor(Shape oldShape, Color oldColor) {
        List<ShapeAndColor> subShapeAndColorList = new ArrayList<>();

        for (int i = 0; i < shapeAndColorList.size(); i++) {
            if ((shapeAndColorList.get(i).getShape() != oldShape) &&
                    (shapeAndColorList.get(i).getColor() != oldColor)) {
                subShapeAndColorList.add(shapeAndColorList.get(i));
            }
        }

        int randomInt = random.nextInt(subShapeAndColorList.size());
        return subShapeAndColorList.get(randomInt).getId();
    }
}
