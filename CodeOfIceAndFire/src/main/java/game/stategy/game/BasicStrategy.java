package game.stategy.game;

import game.entities.HeadQuarters;
import game.entities.HeadQuartersManager;
import game.entities.Unit;
import game.stategy.train.SoloQuestStrategy;

/**
 * Treina apenas uma unidade e ataca o HQ inimigo
 *
 */
public class BasicStrategy implements GameStrategy {

	public final static BasicStrategy STRATEGY = new BasicStrategy();

	@Override
	public String run() {
		final StringBuilder commands = new StringBuilder();
		final HeadQuarters ownHeadQuarters = HeadQuartersManager.MANAGER.getOwnHeadQuarters();
		ownHeadQuarters.setStrategy(SoloQuestStrategy.STRATEGY);
		commands.append(ownHeadQuarters.runStrategy(1));
		for (final Unit unit : ownHeadQuarters.getArmy()) {
			commands.append(unit.runStrategy());
		}
		return commands.toString();
	}

}
