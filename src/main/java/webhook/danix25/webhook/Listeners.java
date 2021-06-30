package webhook.danix25.webhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {


        Main.getInstance().sendToDiscord(e.getPlayer().getName() + " join to server");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

        Main.getInstance().sendToDiscord(e.getPlayer().getName() + " leave the server ");
    }


    @EventHandler
    public void onAdvancementComplete(PlayerAdvancementDoneEvent e) {
        Main.getInstance().sendToDiscord(e.getPlayer().getName() + "get advancement" + e.getAdvancement());
    }



}
