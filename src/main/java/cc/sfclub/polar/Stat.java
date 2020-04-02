package cc.sfclub.polar;

import org.mve.plugin.java.JavaPlugin;
import org.mve.plugin.java.PluginConfig;

public class Stat extends PluginConfig {
    public int msgCount = 0;
    public long startTime = System.currentTimeMillis();

    public Stat(JavaPlugin p) {
        super(p);
    }

    @Override
    public String getConfigName() {
        return "stat.json";
    }
}
