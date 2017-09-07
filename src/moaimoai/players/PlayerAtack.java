package moaimoai.players;

import bases.Vector2D;
import bases.physics.Physics;
import bases.platforms.Platform;
import moaimoai.enemies.Enemy;

public class PlayerAtack {
    public void doAttack(Player owner) {
        if(owner.isRight()){
            owner.setRangeAttack(10);
        }
        else
            owner.setRangeAttack(-10);
        Vector2D checkPosition = owner.getScreenPosition().add(owner.getRangeAttack(), 0);
        Platform platform = Physics.collideWith(checkPosition, owner.getBoxCollider().getWidth(), 0, Platform.class);
        if(platform != null){
            if(platform.isBreakable()){
                platform.getHit();

            }
            if(platform.isMoveable()){
                platform.getVelocity().x =  owner.getRangeAttack() / 3;
                platform.setMoving(true);
//                AudioUtils.play(owner.getHitRock());
            }
        }
        Enemy enemy = Physics.collideWith(checkPosition, owner.getBoxCollider().getWidth(), 0, Enemy.class);
        if (enemy != null) {
            enemy.getHit();
        }
    }
}
