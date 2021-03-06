package moaimoai.players;

import bases.Vector2D;
import bases.physics.Physics;
import bases.platforms.Platform;
import moaimoai.enemies.EnemyMouse;
import moaimoai.enemies.EnemyRabit;

public class PlayerAtack {
    public void doAttack(Player owner) {
        if(owner.isRight()){
            owner.setRangeAttack(15);
        }
        else
            owner.setRangeAttack(-15);
        Vector2D checkPosition = owner.getScreenPosition().add(owner.getRangeAttack(), 0);
        Platform platform = Physics.collideWith(checkPosition, owner.getBoxCollider().getWidth(), 0, Platform.class);
        if(platform != null){
            if(platform.isBreakable()){
                platform.getHit();

            }
            if(platform.isMoveable()){
                platform.getVelocity().x =  owner.getRangeAttack() *2/5;
                platform.setMoving(true);
                platform.setKillPlayer(true);
                platform.setStopable(false);
//                AudioUtils.play(owner.getHitRock());
            }
        }
        EnemyRabit enemy = Physics.collideWith(checkPosition, owner.getBoxCollider().getWidth(), 0, EnemyRabit.class);
        if (enemy != null) {
            enemy.getHit();
        }

        EnemyMouse enemyMouse = Physics.collideWith(checkPosition, owner.getBoxCollider().getWidth(), 0, EnemyMouse.class);
        if (enemyMouse != null){
            enemyMouse.getHit();
        }
    }
}
