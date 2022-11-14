package com.example.androidstudio2dgamedevelopment.gameobject;

import com.example.androidstudio2dgamedevelopment.gamepanel.Joystick;

public class PlayerState {


    public enum State {
        NOT_MOVING,
        STARED_MOVING,
        IS_MOVING,
        UP_MOVING,
        DOWN_MOVING,
        RIGHT_MOVING,
        LEFT_MOVING

    }

    private Player player;
    private State state;

    public PlayerState(Player player) {
        this.player = player;
        this.state = State.NOT_MOVING;
    }

    public State getState() {
        return state;
    }

    public void update() {
//        switch (state) {
//            case NOT_MOVING:
//                if (player.velocityX != 0 || player.velocityY != 0)
//                    state = State.IS_MOVING;

                if(player.joystick.getActuatorY() - player.joystick.getActuatorX() >= 0 && player.joystick.getActuatorX() + player.joystick.getActuatorY() > 0) {
                    state = State.DOWN_MOVING;
                }
                else if(player.joystick.getActuatorX() + player.joystick.getActuatorY() <= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX() <= 0) {
                    state = State.UP_MOVING;
                }
                else if(player.joystick.getActuatorX() + player.joystick.getActuatorY() >= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX() <= 0) {
                    state = State.RIGHT_MOVING;
                }
                else if(player.joystick.getActuatorX() + player.joystick.getActuatorY() <= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX() > 0) {
                    state = State.LEFT_MOVING;
                }
//                break;
//            case STARED_MOVING:
//                if (player.velocityX != 0 || player.velocityY != 0)
//                    state = State.IS_MOVING;
//                break;
//            case IS_MOVING:

//                break;
//            default:
//                break;
//        }
//        if (player.velocityX == 0 && player.velocityY == 0)
//            state = State.NOT_MOVING;
    }
}
