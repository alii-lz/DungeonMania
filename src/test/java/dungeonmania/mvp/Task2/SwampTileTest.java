package dungeonmania.mvp.Task2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SwampTileTest {

    // this test checks if a spider is affected by swamp tile with a movement_factor of 2
    @Test
    public void testSwampTileAffectsSpider() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_spider", "c_swampTileTest_spider");

        // check a spider exists
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "spider"));

        // check a swamp tile exists
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "swamp_tile"));

        // confirm spider's intial position
        Position initialPosition = TestUtils.getEntities(res, "spider").get(0).getPosition();
        assertEquals(new Position(3, 3), initialPosition);

        // now the spider is on the swamp tile
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(3, 2), TestUtils.getEntities(res, "spider").get(0).getPosition());

        // first tick in swamp tile, position shouldnt change
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, 2), TestUtils.getEntities(res, "spider").get(0).getPosition());

        // second tick in swamp tile, position shouldnt change
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, 2), TestUtils.getEntities(res, "spider").get(0).getPosition());

        // third tick, so spider can move now
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(4, 2), TestUtils.getEntities(res, "spider").get(0).getPosition());

    }

    // this test checks that player isnt affected by swamp tile
    @Test
    public void testSwampTileDoesntAffectPlayer() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_player", "c_swampTileTest_spider");

        // check a swamp tile exists
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "swamp_tile"));

        // confirm player's intial position
        Position initialPosition = TestUtils.getEntities(res, "player").get(0).getPosition();
        assertEquals(new Position(1, 1), initialPosition);

        // now the player is on the swamp tile
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(2, 1), TestUtils.getEntities(res, "player").get(0).getPosition());

        // first tick after entering swamp tile, player should be out of swamp tile
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(3, 1), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    // this test checks that a 0 movement factor swamp tile doesnt affect spider
    @Test
    public void testZeroMovementFactorSwampTile() throws IllegalArgumentException, InvalidActionException {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTileTest_zeroMovementFactor", "c_swampTileTest_zeroMovementFactor");

        // check a swamp tile and spider exists
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "swamp_tile"));
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "spider"));

        // confirm spiders's intial position
        Position initialPosition = TestUtils.getEntities(res, "spider").get(0).getPosition();
        assertEquals(new Position(3, 3), initialPosition);

        // now the spider is on the swamp tile
        res = dmc.tick(Direction.UP);
        assertEquals(new Position(3, 2), TestUtils.getEntities(res, "spider").get(0).getPosition());

        // first tick after entering swamp tile, spider should be out of swamp tile
        res = dmc.tick(Direction.RIGHT);
        assertEquals(new Position(4, 2), TestUtils.getEntities(res, "spider").get(0).getPosition());
    }
}
