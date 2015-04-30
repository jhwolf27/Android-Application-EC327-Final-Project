package com.example.ernie.ec327app;

public class AlienEntity extends Entity {
    /** The speed at which the alient moves horizontally */
    private double moveSpeed = 0;
    /** The game in which the entity exists */
    private Game game;


    public AlienEntity(Game game,String ref,int x,int y) {
        super(ref,x,y);

        this.game = game;
        dx = -moveSpeed;
    }

    /**
     * Request that this alien moved based on time elapsed
     *
     * @param delta The time that has elapsed since last move
     */
    public void move(long delta) {
        // if we have reached the left hand side of the screen and
        // are moving left then request a logic update
        if ((dx < 0) && (x < 10)) {
            game.updateLogic();
        }
        // and vice vesa, if we have reached the right hand side of
        // the screen and are moving right, request a logic update
        if ((dx > 0) && (x > 280)) {
            game.updateLogic();
        }

        // proceed with normal move
        super.move(delta);
    }

    /**
     * Update the game logic related to aliens
     */
    public void doLogic() {
        // swap over horizontal movement and move down the
        // screen a bit
        dx = -dx;
        y += 50;

        // if we've reached the bottom of the screen then the player
        // dies
        if (y > 460) {
            // remove the affected entities
            game.removeEntity(this);

            // notify the game that the alien has been killed
            game.notifyAlienKilled();
        }
    }

    /**
     * Notification that this alien has collided with another entity
     *
     * @param other The other entity
     */
    public void collidedWith(Entity other) {
        // collisions with aliens are handled elsewhere
    }
}
