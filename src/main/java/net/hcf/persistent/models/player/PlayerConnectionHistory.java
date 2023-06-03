package net.hcf.persistent.models.player;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity(name = "player_connection_history")
public class PlayerConnectionHistory extends PanacheEntity {

  @ManyToOne
  private Player player;

  @Getter
  private String ip;

  @Getter
  @Column(name = "join_at")
  private Date joinAt;

  @Getter
  @Column(name = "leave_at")
  private Date leaveAt;

}
