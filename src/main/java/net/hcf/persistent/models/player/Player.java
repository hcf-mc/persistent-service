package net.hcf.persistent.models.player;

import java.util.Set;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "player")
public class Player extends PanacheEntityBase {

  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Getter
  @Setter
  private String username;

  @Getter
  @Setter
  @Column(name = "minecraft_id")
  private UUID minecraftId;

  @Getter
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
  private Set<PlayerConnectionHistory> connectionHistory;
}
