package com.example.androidstudio2dgamedevelopment.graphics;

import android.graphics.Canvas;

import com.example.androidstudio2dgamedevelopment.GameDisplay;
import com.example.androidstudio2dgamedevelopment.gameobject.Player;
import com.example.androidstudio2dgamedevelopment.gameobject.PlayerState;

public class Animator {
    private Sprite[] playerSpriteArray;
    private int idxMovingFrame = 1;
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5;

    public Animator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {

        if(player.getPlayerState().getState() == PlayerState.State.UP_MOVING){
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[0]);}
        else if(player.getPlayerState().getState() == PlayerState.State.DOWN_MOVING){
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[2]);}
        else if(player.getPlayerState().getState() == PlayerState.State.RIGHT_MOVING){
            drawFrame(canvas, gameDisplay, player, playerSpriteArray[1]);}
        else if(player.getPlayerState().getState() == PlayerState.State.LEFT_MOVING){
            drawFrame(canvas, gameDisplay, player, playerSpriteArray[3]);}
        else if(player.getPlayerState().getState() == PlayerState.State.UP_RIGHT_MOVING){
            drawFrame(canvas, gameDisplay, player, playerSpriteArray[5]);}
        else if(player.getPlayerState().getState() == PlayerState.State.DOWN_RIGHT_MOVING){
            drawFrame(canvas, gameDisplay, player, playerSpriteArray[6]);}
        else if(player.getPlayerState().getState() == PlayerState.State.DOWN_LEFT_MOVING){
            drawFrame(canvas, gameDisplay, player, playerSpriteArray[7]);}
        else if(player.getPlayerState().getState() == PlayerState.State.UP_LEFT_MOVING){
            drawFrame(canvas, gameDisplay, player, playerSpriteArray[4]);}
    }


    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite) {
        sprite.draw(
                canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()) - sprite.getWidth()/2,
                (int) gameDisplay.gameToDisplayCoordinatesY(player.getPositionY()) - sprite.getHeight()/2
        );
    }
}
