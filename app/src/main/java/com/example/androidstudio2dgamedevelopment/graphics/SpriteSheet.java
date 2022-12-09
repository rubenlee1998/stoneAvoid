package com.example.androidstudio2dgamedevelopment.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.androidstudio2dgamedevelopment.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[8];
        spriteArray[0] = new Sprite(this, new Rect(0*64, 0, 1*64, 64));
        spriteArray[1] = new Sprite(this, new Rect(1*64, 0, 2*64, 64));
        spriteArray[2] = new Sprite(this, new Rect(2*64, 0, 3*64, 64));
        spriteArray[3] = new Sprite(this, new Rect(3*64, 0, 4*64, 64));
        spriteArray[4] = new Sprite(this, new Rect(0*64, 128, 1*64, 192)); // 북서쪽
        spriteArray[5] = new Sprite(this, new Rect(1*64, 128, 2*64, 192)); // 북동쪽
        spriteArray[6] = new Sprite(this, new Rect(2*64, 128, 3*64, 192)); // 남동쪽
        spriteArray[7] = new Sprite(this, new Rect(3*64, 128, 4*64, 192)); // 남서쪽
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
    public Sprite[] getShellSpriteArray() {
        Sprite[] spriteArray = new Sprite[9];
        spriteArray[0] = new Sprite(this, new Rect(0*64, 192, 1*64, 256));
        spriteArray[1] = new Sprite(this, new Rect(0*64, 192, 1*64, 256));
        spriteArray[2] = new Sprite(this, new Rect(0*64, 192, 1*64, 256));
        spriteArray[3] = new Sprite(this, new Rect(1*64, 192, 2*64, 256));
        spriteArray[4] = new Sprite(this, new Rect(1*64, 192, 2*64, 256));
        spriteArray[5] = new Sprite(this, new Rect(1*64, 192, 2*64, 256));
        spriteArray[6] = new Sprite(this, new Rect(2*64, 192, 3*64, 256));
        spriteArray[7] = new Sprite(this, new Rect(2*64, 192, 3*64, 256));
        spriteArray[8] = new Sprite(this, new Rect(2*64, 192, 3*64, 256));

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

    public Sprite getGroundSprite() {
        return getSpriteByIndex(1, 2);
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 3);
    }

    public Sprite getTreeSprite() {
        return getSpriteByIndex(1, 4);
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
