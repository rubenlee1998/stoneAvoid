package com.example.androidstudio2dgamedevelopment.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.androidstudio2dgamedevelopment.R;

public class GameClear {
    private Context context;

    public GameClear(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas) {
        String text = "Congratulation!!";


        float x = 800;
        float y = 200;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameClear);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
    }
}
