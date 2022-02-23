import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class aa implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage("안녕하세요");
    }

}
