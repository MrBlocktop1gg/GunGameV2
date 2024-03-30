package mrblock.gungamev2;

import lombok.Getter;
import mrblock.gungamev2.game.GunGameState;
import mrblock.gungamev2.game.State;
import mrblock.gungamev2.game.classes.listener.PlayerSelectSpecies;
import mrblock.gungamev2.game.classes.listener.UseSkills;
import mrblock.gungamev2.lobby.gui.listener.PlayerListener;
import mrblock.gungamev2.lobby.gui.listener.PlayerSelectGame;
import mrblock.gungamev2.game.playersGame.listener.KillerListener;
import mrblock.gungamev2.globalListener.CancelledListener;
import mrblock.gungamev2.globalListener.FastPvp;
import mrblock.gungamev2.mongoDB.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GunGameV2 extends JavaPlugin {
  public GunGameState gunGameState;
  //private MongoDBManager mongoDBManager;

  @Getter
  private PlayerStorage playerStorage;

  @Override
  public void onEnable() {

    playerStorage = new PlayerStorage();
 //   mongoDBManager = new MongoDBManager();
    gunGameState = new GunGameState();
    gunGameState.setState(State.LOBBY);

    System.out.printf("state" + gunGameState.getState());

  //  mongoDBManager.connect();

    registerListeners(
        new CancelledListener(),
        new FastPvp(),
        new KillerListener(),
        new PlayerSelectSpecies(this),
        new UseSkills(this),
        new PlayerListener(this),
        new PlayerSelectGame(this)
    );
  }

  public void registerListeners(Listener... listeners) {
    PluginManager pluginManager = Bukkit.getServer().getPluginManager();

    for (Listener listener : listeners) {
      pluginManager.registerEvents(listener, this);
    }
  }
}
