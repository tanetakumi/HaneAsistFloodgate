package net.serveron.hane;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.slf4j.Logger;

@Plugin(id = "haneasist", name = "HaneAsistFloodgate", version = "0.1.0-SNAPSHOT"
        , description = "This is a velocity plugin", authors = {"tanetakumi"})
public class HaneAsistFloodgate {
    private final ProxyServer server;
    private final Logger logger;
    @Inject
    public HaneAsistFloodgate(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        logger.info("This is a HaneAsis-Floodgate");
    }
    @Subscribe
    public void onJoin(LoginEvent e) {
        FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(e.getPlayer().getUniqueId());
        if(floodgatePlayer != null){
            String device = floodgatePlayer.getDeviceOs().toString();
            logger.info(
                    "PlayerName : "+floodgatePlayer.getUsername()
                    + ", Device : "+device
                    + ", Xuid : "+floodgatePlayer.getXuid()
                    + ", Version : "+floodgatePlayer.getVersion()
                    + ", Lang : "+floodgatePlayer.getLanguageCode()
            );
            if(device.toLowerCase().contains("windows")){
                e.getPlayer().disconnect(Component.text("[HaneAsist] ").color(TextColor.color(255,127,0)).append(
                        Component.text("PCもってるならJavaで入って!怒  あとルール読よく読んで!\n" +
                                "これ記録されてるから、次やったらBANになるよ。").color(TextColor.color(	0,255,127))
                        ));
            } else if(device.toLowerCase().contains("android")){
                if(!floodgatePlayer.getVersion().equals("1.19.60")){
                    e.getPlayer().disconnect(Component.text("[HaneAsist] ").color(TextColor.color(255,127,0)).append(
                            Component.text("最新バージョンではないか、または現在アンドロイドでの参加が制限されています。").color(TextColor.color(	0,255,127)))
                    );
                }
            }
        }
    }
}