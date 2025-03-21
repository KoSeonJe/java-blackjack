package domain;

import java.util.Map;
import java.util.Set;

public class GameResults {

    private final Map<Player, GameResultStatus> gameResults;

    public GameResults(Map<Player, GameResultStatus> gameResults) {
        this.gameResults = gameResults;
    }

    public int calculateStatusCount(GameResultStatus status) {
        return (int) getAllPlayers().stream()
                .filter(player -> status.isEqualTo(gameResults.get(player)))
                .count();
    }

    public Set<Player> getAllPlayers() {
        return gameResults.keySet();
    }

    public GameResultStatus getGameResultstatus(Player key) {
        return gameResults.get(key);
    }
}
