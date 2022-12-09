package com.example.androidstudio2dgamedevelopment.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;

import com.example.androidstudio2dgamedevelopment.GameDisplay;
import com.example.androidstudio2dgamedevelopment.GameLoop;
import com.example.androidstudio2dgamedevelopment.R;
import com.example.androidstudio2dgamedevelopment.graphics.Animator;

import java.math.MathContext;

/**
 * Enemy is a character which always moves in the direction of the player.
 * The Enemy class is an extension of a Circle, which is an extension of a GameObject
 */
public class Enemy extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 1000;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    public Player player;
    public Animator animator;
    public double ver;

    /**
     * Enemy is an overload constructor used for spawning enemies in random locations
     * @param context
     * @param player
     */
    public Enemy(Context context, Player player,Animator animator) {
        super(
            context,
            ContextCompat.getColor(context, R.color.enemy),
   Math.random()*9000,
   Math.random()*100,
     30
        );
        this.player = player;
        this.player.playerState.state = PlayerState.State.ENEMY;
        this.animator = animator;
        this.ver = Math.random()*20;
        double dir = Math.random()*2;
        if(dir < 1.0) {
            velocityX = Math.random()*6;
        }
        else{
            velocityX = -1*Math.random()*6;
        }
        velocityY = Math.random()*5+5;
    }
    /**
     * readyToSpawn checks if a new enemy should spawn, according to the decided number of spawns
     * per minute (see SPAWNS_PER_MINUTE at top)
     * @return
     */
    public static boolean readyToSpawn() {
        if (updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn --;
            return false;
        }
    }

    public void update() {
        if(this.ver <= 1.0) {
            double distanceToPlayerX = player.getPositionX() - positionX;
            double distanceToPlayerY = player.getPositionY() - positionY;

            double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

            double directionX = distanceToPlayerX / distanceToPlayer;
            double directionY = distanceToPlayerY / distanceToPlayer;

            if (distanceToPlayer > 0) {
                velocityX = directionX * MAX_SPEED;
                velocityY = directionY * MAX_SPEED;
            } else {
                velocityX = 0;
                velocityY = 0;
            }

        }
        positionX += velocityX;
        positionY += velocityY;
    }
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this.player,this,null);
    }
}

