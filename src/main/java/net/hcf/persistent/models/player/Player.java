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
import lombok.RequiredArgsConstructor;

@Entity(name = "player")
@RequiredArgsConstructor
public class Player extends PanacheEntityBase {

  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Getter
  private final String username;

  @Getter
  @Column(name = "minecraft_id")
  private final UUID minecraftId;

  @Getter
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
  private Set<PlayerConnectionHistory> connectionHistory = Set.of();

  public Player() {
    this.username = null;
    this.minecraftId = null;
  }

  public static Player findByMinecraftId(UUID uuid) {
    return find("minecraftId", uuid).firstResult();
  }
}
