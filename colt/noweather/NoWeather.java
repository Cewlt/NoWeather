package colt.noweather;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoWeather extends JavaPlugin implements Listener {
	private Collection<String> worlds;

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		this.worlds = getConfig().getStringList("worlds");
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void WeatherChangeEvent(WeatherChangeEvent event) {
		if (!event.toWeatherState()) return;
		if (this.worlds.contains(event.getWorld().getName())) {
			event.setCancelled(true);
			event.getWorld().setWeatherDuration(0);
			event.getWorld().setThundering(false);
		}
	}
}
