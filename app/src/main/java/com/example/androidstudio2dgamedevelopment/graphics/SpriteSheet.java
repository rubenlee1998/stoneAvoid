package com.example.androidstudio2dgamedevelopment.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.androidstudio2dgamedevelopment.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 128;
    private static final int SPRITE_HEIGHT_PIXELS = 128;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[8];
        spriteArray[0] = new Sprite(this, new Rect(0*128, 0, 1*128, 128)); // left
        spriteArray[1] = new Sprite(this, new Rect(0*128, 0, 1*128, 128)); // right
        spriteArray[2] = new Sprite(this, new Rect(0*128, 0, 1*128, 128)); // right
        spriteArray[3] = new Sprite(this, new Rect(0*128, 0, 1*128, 128)); // right
        spriteArray[4] = new Sprite(this, new Rect(1*128, 0, 2*128, 128)); // right
        spriteArray[5] = new Sprite(this, new Rect(1*128, 0, 2*128, 128)); // right
        spriteArray[6] = new Sprite(this, new Rect(1*128, 0, 2*128, 128)); // right
        spriteArray[7] = new Sprite(this, new Rect(1*128, 0, 2*128, 128)); // right

        return spriteArray;
    }
    public Sprite[] getEnemySpriteArray() {
        Sprite[] spriteArray = new Sprite[8];
        spriteArray[0] = new Sprite(this, new Rect(0*64, 256, 1*64, 320));
        spriteArray[1] = new Sprite(this, new Rect(0*64, 256, 1*64, 320));
        spriteArray[2] = new Sprite(this, new Rect(0*64, 256, 1*64, 320));
        spriteArray[3] = new Sprite(this, new Rect(0*64, 256, 1*64, 320));
        spriteArray[4] = new Sprite(this, new Rect(1*64, 256, 2*64, 320));
        spriteArray[5] = new Sprite(this, new Rect(1*64, 256, 2*64, 320));
        spriteArray[6] = new Sprite(this, new Rect(1*64, 256, 2*64, 320));
        spriteArray[7] = new Sprite(this, new Rect(1*64, 256, 2*64, 320));

        return spriteArray;
    }
    public Sprite[] getSpellSpriteArray() {
        Sprite[] spriteArray = new Sprite[9];
        spriteArray[0] = new Sprite(this, new Rect(2*64, 256, 3*64, 320));
        spriteArray[1] = new Sprite(this, new Rect(2*64, 256, 3*64, 320));
        spriteArray[2] = new Sprite(this, new Rect(2*64, 256, 3*64, 320));
        spriteArray[3] = new Sprite(this, new Rect(2*64, 256, 3*64, 320));
        spriteArray[4] = new Sprite(this, new Rect(3*64, 256, 4*64, 320));
        spriteArray[5] = new Sprite(this, new Rect(3*64, 256, 4*64, 320));
        spriteArray[6] = new Sprite(this, new Rect(3*64, 256, 4*64, 320));
        spriteArray[7] = new Sprite(this, new Rect(3*64, 256, 4*64, 320));
        spriteArray[8] = new Sprite(this, new Rect(3*64, 256, 4*64, 320));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getWaterSprite() {
        return getSpriteByIndex(1, 0);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(1, 1);
    }

    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }
}
