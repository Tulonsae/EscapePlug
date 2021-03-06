package en.tehbeard.reserve;

import net.escapecraft.component.AbstractComponent;
import net.escapecraft.component.ComponentDescriptor;
import net.escapecraft.escapePlug.EscapePlug;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.tulonsae.mc.util.Log;

@ComponentDescriptor(slug="reserve",name="Reserve List",version="1.00")
public class ReserveComponent extends AbstractComponent implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent event){
		if(event.getResult()!=Result.KICK_WHITELIST && event.getResult()!=Result.KICK_BANNED) {
			if((Bukkit.getOnlinePlayers().length >= Bukkit.getMaxPlayers())) {
				if (!event.getPlayer().hasPermission("escapeplug.reserve.allow")) {
					EscapePlug.printCon("ATTEMPTING DENIAL!");
					event.setKickMessage("server is fullup :(");
					event.setResult(PlayerLoginEvent.Result.KICK_FULL);
				} else {
					event.allow();
				}
			}
		}
	}

	@Override
	public boolean enable(Log log, EscapePlug plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		return true;
	}

	@Override
	public void disable() {
	}
}
