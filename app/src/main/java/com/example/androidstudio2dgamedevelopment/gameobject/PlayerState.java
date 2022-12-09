package com.example.androidstudio2dgamedevelopment.gameobject;

public class PlayerState {


    public enum State {
        UP_MOVING,
        UP_RIGHT_MOVING,
        DOWN_MOVING,
        RIGHT_MOVING,
        DOWN_RIGHT_MOVING,
        LEFT_MOVING,
        DOWN_LEFT_MOVING,
        UP_LEFT_MOVING,
        ENEMY
    }

    private Player player;
    public State state;

    public PlayerState(Player player) {
        this.player = player;
        this.state = State.UP_MOVING;
    }

    public State getState() {
        return state;
    }

    public void update() {
                if(player.joystick.getActuatorY() == 0 && player.joystick.getActuatorX() == 0){

                }
                else if(player.joystick.getActuatorY() - player.joystick.getActuatorX()*2.25 >= 0 && player.joystick.getActuatorX()*2.25 + player.joystick.getActuatorY() > 0) {
                    state = State.DOWN_MOVING;
                }
                else if(player.joystick.getActuatorY()-player.joystick.getActuatorX()*2.25 <= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX()*0.45 >= 0) {
                    state = State.DOWN_RIGHT_MOVING;
                }
                else if(player.joystick.getActuatorX()*0.45 + player.joystick.getActuatorY() >= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX()*0.45 <= 0) {
                    state = State.RIGHT_MOVING;
                }
                else if(player.joystick.getActuatorX()*0.45 + player.joystick.getActuatorY() <= 0 && player.joystick.getActuatorY() + player.joystick.getActuatorX()*2.25 >= 0) {
                    state = State.UP_RIGHT_MOVING;
                }
                else if(player.joystick.getActuatorX()*2.25 + player.joystick.getActuatorY() <= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX()*2.25 <= 0) {
                    state = State.UP_MOVING;
                }
                else if( player.joystick.getActuatorY() - player.joystick.getActuatorX()*0.45 <= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX()*2.25 >= 0) {
                    state = State.UP_LEFT_MOVING;
                }
                else if(player.joystick.getActuatorX()*0.45 + player.joystick.getActuatorY() <= 0 && player.joystick.getActuatorY() - player.joystick.getActuatorX()*0.45 >= 0) {
                    state = State.LEFT_MOVING;
                }
                else if(player.joystick.getActuatorX()*0.45 + player.joystick.getActuatorY() >= 0 && player.joystick.getActuatorY() + player.joystick.getActuatorX()*2.25 <= 0) {
                    state = State.DOWN_LEFT_MOVING;
                }
    }
}
