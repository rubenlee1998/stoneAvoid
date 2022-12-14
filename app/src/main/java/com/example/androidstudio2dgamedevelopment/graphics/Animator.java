package com.example.androidstudio2dgamedevelopment.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.example.androidstudio2dgamedevelopment.GameDisplay;
import com.example.androidstudio2dgamedevelopment.R;
import com.example.androidstudio2dgamedevelopment.gameobject.Enemy;
import com.example.androidstudio2dgamedevelopment.gameobject.Player;
import com.example.androidstudio2dgamedevelopment.gameobject.PlayerState;
import com.example.androidstudio2dgamedevelopment.gameobject.Spell;

public class Animator {
    private Sprite[] playerSpriteArray;
    private int idxMovingFrame = 1;
    public int gimoring = 0;
    public int gimoring2 = 0;
    public int cnt = 0;
    public PlayerState.State pre;
    private Context context;


    public Animator(Sprite[] playerSpriteArray) {

        this.playerSpriteArray = playerSpriteArray;

    }



    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player, Enemy enemy,Spell spell) {
        if(spell != null){
            if (gimoring2 == 0) {
                gimoring2 = 1;
            } else if(gimoring2 == 1){
                gimoring2 = 2;
            }
            else if(gimoring2 == 2){
                gimoring2 =3;
            }
            else if(gimoring2 == 3){
                gimoring2 =4;
            }
            else if(gimoring2 == 4){
                gimoring2 =5;
            }
            else if(gimoring2 == 5){
                gimoring2 =6;
            }
            else if(gimoring2 == 6){
                gimoring2 =7;
            }
            else if(gimoring2 == 7){
                gimoring2 =8;
            }
            else if(gimoring2 == 8){
                gimoring2 =0;
            }


            drawSpellFrame(canvas, gameDisplay,playerSpriteArray[gimoring2],spell);
        }
        else if (enemy != null) {
            if (gimoring == 0) {
                gimoring = 1;
            } else if(gimoring == 1){
                gimoring = 2;
            }
            else if(gimoring == 2){
                gimoring =3;
            }
            else if(gimoring == 3){
                gimoring =4;
            }
            else if(gimoring == 4){
                gimoring =5;
            }
            else if(gimoring == 5){
                gimoring =6;
            }
            else if(gimoring == 6){
                gimoring =7;
            }
            else if(gimoring == 7){
                gimoring =0;
            }
            drawFrame(canvas, gameDisplay, enemy.player, playerSpriteArray[gimoring],enemy);

        } else if(player != null){

            if (player.getPlayerState().getState() == PlayerState.State.RIGHT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[6],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.LEFT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[1],null);
            }

            else{
                if (pre == PlayerState.State.RIGHT_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[6],null);
                } else if (pre == PlayerState.State.LEFT_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[1],null);
                }
            }
        }
    }


    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite,Enemy enemy) {

        if(enemy == null) {
            sprite.draw(
                    canvas,
                    (int) gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()) - sprite.getWidth() / 2,
                    (int) gameDisplay.gameToDisplayCoordinatesY(player.getPositionY()) - sprite.getHeight() / 2
            );
        }
        else{
            sprite.draw(
                    canvas,
                    (int) gameDisplay.gameToDisplayCoordinatesX(enemy.getPositionX()) - sprite.getWidth() / 2,
                    (int) gameDisplay.gameToDisplayCoordinatesY(enemy.getPositionY()) - sprite.getHeight() / 2
            );
        }
    }
    public void drawSpellFrame(Canvas canvas, GameDisplay gameDisplay, Sprite sprite,  Spell spell) {
        sprite.draw(
            canvas,
            (int) gameDisplay.gameToDisplayCoordinatesX(spell.getPositionX()) - sprite.getWidth() / 2,
            (int) gameDisplay.gameToDisplayCoordinatesY(spell.getPositionY()) - sprite.getHeight() / 2
        );
    }
}
