package dungeonmania.mvp.Task2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class Task2aTest {

    // this test has 1 spider and 1 spawner and tests whether both were killed in order to meet our goal
    @Test
    public void testEnemyGoalWithOneSpiderAndSpawner() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicGoalsTest_spiderAndSpawner", "c_basicGoalsTest_spiderAndSpawner");

        // check that there is 1 spider and 1 spawner
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "spider"));
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast_spawner"));

        // assert goal not met yet
        assertTrue(TestUtils.getGoals(res).contains(":enemy"));

        // kill spider
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(res.getEntities(), "spider"));

        // now we have killed the spider but the spawner still remains so goal not met
        assertEquals(1, TestUtils.countType(res, "zombie_toast_spawner"));
        assertTrue(TestUtils.getGoals(res).contains(":enemy"));

        // pick up sword
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // kill spawner
        String idOfSpawner = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();

        // cardinally adjacent: true, has sword: true, but invalid_id
        assertThrows(IllegalArgumentException.class, () -> dmc.interact("random_invalid_id"));

        // cardinally adjacent: true, has sword: true
        res = assertDoesNotThrow(() -> dmc.interact(idOfSpawner));
        assertEquals(0, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast_spawner"));

        // now we have killed all enemies so we pass our enemy goal
        assertEquals("", TestUtils.getGoals(res));

    }

    // this test is the same as the one on top but with an OR goal aswell.
    // this test can also be passed if the player goes to the exit straighaway.
    @Test
    public void testEnemyGoalORExitGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_complexGoalsTest_enemyORExit", "c_complexGoalsTest_enemyORExit");

        // check that there is 1 spider and 1 spawner
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "spider"));
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast_spawner"));

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":exit"));
        assertTrue(TestUtils.getGoals(res).contains(":enemy"));

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);

        // now we have reached the exit so we completed the goal
        assertEquals("", TestUtils.getGoals(res));
    }

    // this test is the same as the first enemy goal but with an AND goal aswell.
    // this test can also be passed if the player goes to the exit and passes the enemy goal.
    @Test
    public void testEnemyGoalANDExitGoal() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_complexGoalsTest_enemyANDExit", "c_complexGoalsTest_enemyORExit");

        // check that there is 1 spider and 1 spawner
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "spider"));
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast_spawner"));

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemy"));
        assertTrue(TestUtils.getGoals(res).contains(":exit"));

        // kill spider
        res = dmc.tick(Direction.RIGHT);
        assertEquals(0, TestUtils.countEntityOfType(res.getEntities(), "spider"));

        // now we have killed the spider but the spawner still remains so goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemy"));
        assertTrue(TestUtils.getGoals(res).contains(":exit"));

        // pick up sword
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        String idOfSpawner = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();

        // cardinally adjacent: true, has sword: true, but invalid_id
        assertThrows(IllegalArgumentException.class, () -> dmc.interact("random_invalid_id"));

        // cardinally adjacent: true, has sword: true, so now we kill spawner
        res = assertDoesNotThrow(() -> dmc.interact(idOfSpawner));
        assertEquals(0, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast_spawner"));

        // now we reach exit so all goals met
        res = dmc.tick(Direction.DOWN);
        assertEquals("", TestUtils.getGoals(res));
    }
}
