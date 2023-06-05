package net.hcf.persistent.models.player;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "player_connection_history")
@RequiredArgsConstructor
public class PlayerConnectionHistory extends PanacheEntity {

  @ManyToOne
  private final Player player;

  @Getter
  private final String ip;

  @Getter
  @Column(name = "join_at")
  private final Date joinAt;

  @Getter
  @Setter
  @Column(name = "leave_at")
  private Date leaveAt;

  public PlayerConnectionHistory() {
    this.player = null;
    this.ip = null;
    this.joinAt = null;
    this.leaveAt = null;
  }

  public static PlayerConnectionHistory findLatestByPlayer(Player player) {
    return find("player = ?1 ORDER BY joinAt DESC", player)
        .firstResult();
  }

}
