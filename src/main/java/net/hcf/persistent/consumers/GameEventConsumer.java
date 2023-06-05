package net.hcf.persistent.consumers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import net.hcf.minecraft.game.GameEvent;
import net.hcf.persistent.models.player.Player;
import net.hcf.persistent.models.player.PlayerConnectionHistory;

@ApplicationScoped
public class GameEventConsumer {

  private static final Logger LOG = Logger.getLogger(GameEventConsumer.class);

  @Incoming("game-events")
  @Transactional
  public void consume(GameEvent event)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Boolean foundMethod = false;

    for (Method method : this.getClass().getMethods()) {
      if (method.getName().equals("on" + event.getType().name())) {
        foundMethod = true;
        method.invoke(this, event);
      }
    }

    if (!foundMethod) {
      LOG.warn("No method found for event type: " + event.getType().name());
    }
  }

  @Transactional
  public void onPlayerJoin(GameEvent event) {
    if (event.getUrn() == null) {
      throw new IllegalArgumentException("Player join event must have a URN");
    }

    String[] urnSplit = event.getUrn().split("::");

    if (urnSplit.length != 3) {
      throw new IllegalArgumentException("Player join event must have a URN with 3 parts");
    }

    if (!urnSplit[1].equals("player")) {
      throw new IllegalArgumentException("Player join event must have a URN with type 'player'");
    }

    UUID minecraftId = UUID.fromString(urnSplit[2]);
    Player player = Player.findByMinecraftId(minecraftId);
    if (player == null) {
      player = new Player(event.getData().getUsername(), minecraftId);
      player.persist();
    }

    // Save connection history
    PlayerConnectionHistory history = new PlayerConnectionHistory(player, event.getData().getRemoteAddress(),
        new Date());
    history.persist();

    LOG.debug("Player " + player.getUsername() + " joined the game");
  }

  @Transactional
  public void onPlayerQuit(GameEvent event) {
    if (event.getUrn() == null) {
      throw new IllegalArgumentException("Player join event must have a URN");
    }

    String[] urnSplit = event.getUrn().split("::");

    if (urnSplit.length != 3) {
      throw new IllegalArgumentException("Player join event must have a URN with 3 parts");
    }

    if (!urnSplit[1].equals("player")) {
      throw new IllegalArgumentException("Player join event must have a URN with type 'player'");
    }

    UUID minecraftId = UUID.fromString(urnSplit[2]);
    System.out.println(minecraftId);
    Player player = Player.findByMinecraftId(minecraftId);

    if (player == null) {
      throw new IllegalArgumentException("Player quit event must have a player");
    }

    // Save connection history
    PlayerConnectionHistory history = PlayerConnectionHistory.findLatestByPlayer(player);
    history.setLeaveAt(new Date());
    history.persist();

    LOG.debug("Player " + player.getUsername() + " quit the game");
  }

}
