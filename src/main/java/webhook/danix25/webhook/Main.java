package webhook.danix25.webhook;




import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class Main extends JavaPlugin {
    private URL url;
    private static Main instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        try {
            url = new URL(getConfig().getString("Webhook-ID"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        Bukkit.getPluginManager().registerEvents(new Listeners(), this);


    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public void sendToDiscord(String content) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", "[MC-SERVER] " + content);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "Java-DiscordWebhook");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");


            OutputStream stream = connection.getOutputStream();
            stream.write(jsonObject.toJSONString().getBytes());
            stream.flush();
            stream.close();


            connection.getInputStream().close();
            connection.disconnect();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
