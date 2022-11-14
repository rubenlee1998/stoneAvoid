package com.example.androidstudio2dgamedevelopment.graphics;

import android.graphics.Canvas;

import com.example.androidstudio2dgamedevelopment.GameDisplay;
import com.example.androidstudio2dgamedevelopment.gameobject.Enemy;
import com.example.androidstudio2dgamedevelopment.gameobject.Player;
import com.example.androidstudio2dgamedevelopment.gameobject.PlayerState;

public class Animator {
    private Sprite[] playerSpriteArray;
    private int idxMovingFrame = 1;
    public int gimoring = 0;
    public int cnt = 0;
    public PlayerState.State pre;
    public Animator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player, Enemy enemy) {

        if (enemy != null) {
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

        } else {

            if (player.getPlayerState().getState() == PlayerState.State.UP_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[0],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.DOWN_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[2],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.RIGHT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[1],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.LEFT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[3],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.UP_RIGHT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[5],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.DOWN_RIGHT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[6],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.DOWN_LEFT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[7],null);
            } else if (player.getPlayerState().getState() == PlayerState.State.UP_LEFT_MOVING) {
                pre = player.getPlayerState().getState();
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[4],null);
            }
            else{
                if (pre == PlayerState.State.UP_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[0],null);
                } else if (pre == PlayerState.State.DOWN_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[2],null);
                } else if (pre == PlayerState.State.RIGHT_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[1],null);
                } else if (pre == PlayerState.State.LEFT_MOVING) {

                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[3],null);
                } else if (pre == PlayerState.State.UP_RIGHT_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[5],null);
                } else if (pre == PlayerState.State.DOWN_RIGHT_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[6],null);
                } else if (pre == PlayerState.State.DOWN_LEFT_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[7],null);
                } else if (pre == PlayerState.State.UP_LEFT_MOVING) {
                    drawFrame(canvas, gameDisplay, player, playerSpriteArray[4],null);
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
}
