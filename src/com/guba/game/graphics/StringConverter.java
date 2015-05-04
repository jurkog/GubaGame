package com.guba.game.graphics;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jurkoguba on 2013-12-26.
 */
public class StringConverter {

    public static Sprite[] letters = new Sprite[26];

    public StringConverter() {
        letters[0] = new Sprite(5, 5, 0, 0, SpriteSheet.text_sheet,'a');
        letters[1] = new Sprite(4, 5, 6, 0, SpriteSheet.text_sheet,'b');
        letters[2] = new Sprite(3, 5, 11, 0, SpriteSheet.text_sheet,'c');
        letters[3] = new Sprite(4, 5, 15, 0, SpriteSheet.text_sheet,'d');
        letters[4] = new Sprite(4, 5, 20, 0, SpriteSheet.text_sheet,'e');
        letters[5] = new Sprite(3, 5, 25, 0, SpriteSheet.text_sheet,'f');
        letters[6] = new Sprite(4, 5, 29, 0, SpriteSheet.text_sheet,'g');
        letters[7] = new Sprite(4, 5, 34, 0, SpriteSheet.text_sheet,'h');
        letters[8] = new Sprite(1, 5, 39, 0, SpriteSheet.text_sheet,'i');
        letters[9] = new Sprite(3, 5, 41, 0, SpriteSheet.text_sheet,'j');
        letters[10] = new Sprite(4, 5, 45, 0, SpriteSheet.text_sheet,'k');
        letters[11] = new Sprite(3, 5, 50, 0, SpriteSheet.text_sheet,'l');
        letters[12] = new Sprite(5, 5, 54, 0, SpriteSheet.text_sheet,'m');
        letters[13] = new Sprite(4, 5, 60, 0, SpriteSheet.text_sheet,'n');
        letters[14] = new Sprite(4, 5, 65, 0, SpriteSheet.text_sheet,'o');
        letters[15] = new Sprite(4, 5, 70, 0, SpriteSheet.text_sheet,'p');
        letters[16] = new Sprite(4, 5, 75, 0, SpriteSheet.text_sheet,'q');
        letters[17] = new Sprite(4, 5, 80, 0, SpriteSheet.text_sheet,'r');
        letters[18] = new Sprite(4, 5, 85, 0, SpriteSheet.text_sheet,'s');
        letters[19] = new Sprite(3, 5, 90, 0, SpriteSheet.text_sheet,'t');
        letters[20] = new Sprite(4, 5, 94, 0, SpriteSheet.text_sheet,'u');
        letters[21] = new Sprite(5, 5, 99, 0, SpriteSheet.text_sheet,'v');
        letters[22] = new Sprite(5, 5, 105, 0, SpriteSheet.text_sheet,'w');
        letters[23] = new Sprite(5, 5, 111, 0, SpriteSheet.text_sheet,'x');
        letters[24] = new Sprite(5, 5, 117, 0, SpriteSheet.text_sheet,'y');
        letters[25] = new Sprite(4, 5, 123, 0, SpriteSheet.text_sheet,'z');
    }
    public Sprite getSprite(int index) {

        return letters[index];
    }

    public int[] createPixelTextArray(String text) {



        char[] textCharArray = text.trim().toLowerCase().toCharArray();


            int arrayWidth = 0;

            for (int i = 0; i < textCharArray.length; i++) {
                for (int j = 0; j < 26; j++) {
                    if (textCharArray[i] == letters[j].getId()) {
                        arrayWidth += letters[j].getWidth() + 1;
                        System.out.println(arrayWidth);
                    }
                }
            }
        System.out.println(arrayWidth);
            arrayWidth--;
            int returnPixelArray[] = new int[arrayWidth * 5];
            for (int i = 0; i < returnPixelArray.length;i++) {
                returnPixelArray[i] = 0xffff00ff;
            }

            //
            // Loop to add it to our array
            //
            int xOffset = 0;
            int yOffset = 0;
            for (int i = 0; i < textCharArray.length; i++) {
                for (int j = 0; j < 26; j++) {
                    if (textCharArray[i] == letters[j].getId()) {


                        for (int y = 0; y < letters[j].getHeight(); y++) {
                            for (int x = 0; x < letters[j].getWidth(); x++) {
                                returnPixelArray[(x + y * arrayWidth) + xOffset] = letters[j].pixels[x + y * letters[j].getWidth()];

                            }
                        }
                        xOffset += letters[j].getWidth() + 1;

                    }
                }



            }



            return returnPixelArray;
        }

    }


