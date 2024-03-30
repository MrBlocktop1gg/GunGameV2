package mrblock.gungamev2.mongoDB;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerData {
  private final UUID uuid;
  private int money;
  private int kills;
  private int wins;
  private int rating = 1000;

  public PlayerData(UUID uuid) {
    this.uuid = uuid;
  }
  public void addKills() {
    this.kills++;
  }

  public void addWins() {
    this.wins++;
  }

  public void addMoney() {
    this.money += 10;
  }
  public void addRating() {
    this.rating += 10;
  }
}
