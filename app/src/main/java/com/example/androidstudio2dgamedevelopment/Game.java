package com.example.androidstudio2dgamedevelopment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.androidstudio2dgamedevelopment.gameobject.Circle;
import com.example.androidstudio2dgamedevelopment.gameobject.Enemy;
import com.example.androidstudio2dgamedevelopment.gameobject.Player;
import com.example.androidstudio2dgamedevelopment.gameobject.Spell;
import com.example.androidstudio2dgamedevelopment.gamepanel.GameClear;
import com.example.androidstudio2dgamedevelopment.gamepanel.GameOver;
import com.example.androidstudio2dgamedevelopment.gamepanel.Joystick;
import com.example.androidstudio2dgamedevelopment.graphics.Animator;
import com.example.androidstudio2dgamedevelopment.graphics.SpriteSheet;
import com.example.androidstudio2dgamedevelopment.map.Tilemap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Game manages all objects in the game and is responsible for updating all states and render all
 * objects to the screen
 */
class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Tilemap tilemap;
    private final int removeGal;
    private final int hitSound;
    private int joystickPointerId = 0;
    private final Joystick joystick;
    private final Player player;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private int numberOfShellsToShot = 0;
    private GameOver gameOver;
    private GameClear gameClear;

    private GameDisplay gameDisplay;
    public int bubbleShot;
    public SoundPool mSound;

    public Game(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);


        gameOver = new GameOver(context);
        gameClear = new GameClear(context);
        joystick = new Joystick(275, 700, 150, 70);

        SpriteSheet spriteSheet = new SpriteSheet(context);
        Animator animator = new Animator(spriteSheet.getPlayerSpriteArray());
        player = new Player(context, joystick, 4*800, 5200, 32, animator);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        tilemap = new Tilemap(spriteSheet);

        AudioAttributes attributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED).setUsage(AudioAttributes.USAGE_GAME).build();
        mSound = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(5).build();
        bubbleShot = mSound.load(context, R.raw.bubblepop,1);
        removeGal = mSound.load(context, R.raw.removegal,1);
        hitSound = mSound.load(context, R.raw.hitsound,1);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle user input touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.getIsPressed()) {
                    // Joystick was pressed before this event -> cast spell
                    numberOfShellsToShot ++;
                } else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store pointer id
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                    numberOfShellsToShot ++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    // Joystick was pressed previously and is now moved
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // joystick pointer was let go off -> setIsPressed(false) and resetActuator()
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // Draw Tilemap
        tilemap.draw(canvas, gameDisplay);

        // Draw game objects
        player.draw(canvas, gameDisplay);

        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);


        }

        for (Spell spell : spellList) {
            spell.draw(canvas, gameDisplay);
        }

        // Draw game panels
        joystick.draw(canvas);

        // Draw Game over if the player is dead
        if (player.getHealthPoint() <= 0) {
            gameOver.draw(canvas);
        }
        if(player.getPositionY() <=1140 && player.getPositionY() >= 500 && player.getPositionX() <= 6000 && player.getPositionX() >= 4600){
            gameClear.draw(canvas);
        }
    }

    public void update() {
        if (player.getHealthPoint() <= 0) {
            return;
        }
        if(player.getPositionY() <=1140 && player.getPositionY() >= 500 && player.getPositionX() <= 6000 && player.getPositionX() >= 4600){
            return;
        }

        joystick.update();
        player.update();

        if(Enemy.readyToSpawn()) {
            SpriteSheet spriteSheet = new SpriteSheet(getContext());
            Animator animator = new Animator(spriteSheet.getEnemySpriteArray());
            enemyList.add(new Enemy(getContext(), player,animator));
        }

        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        while (numberOfShellsToShot > 0) {
            SpriteSheet spriteSheet = new SpriteSheet(getContext());
            Animator animator = new Animator(spriteSheet.getSpellSpriteArray());
            spellList.add(new Spell(getContext(), player,animator));
            numberOfShellsToShot --;
            mSound.play(bubbleShot,0.1f,0.1f,1,0,1);
        }
        for (Spell spell : spellList) {
            spell.update();
        }

        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            Circle enemy = iteratorEnemy.next();
            if (Circle.isColliding(enemy, player)) {
                mSound.play(hitSound,0.1f,0.1f,1,0,1);
                iteratorEnemy.remove();
                player.setHealthPoint(player.getHealthPoint() - 1);
                continue;
            }

            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()) {
                Circle spell = iteratorSpell.next();
                if (Circle.isColliding(spell, enemy)) {
                    mSound.play(removeGal,0.1f,0.1f,1,0,1);
                    iteratorSpell.remove();
                    iteratorEnemy.remove();

                    break;
                }
            }
        }
        
        // Update gameDisplay so that it's center is set to the new center of the player's
        // game coordinates
        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
