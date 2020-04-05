package cc.sfclub.polar;

import cc.sfclub.polar.commands.basic.Status;
import cc.sfclub.polar.commands.moderate.Cmds;
import org.mve.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PolarBase extends JavaPlugin {
    public Stat stats;

    @Override
    public void onEnable() {
        // XXX: Register commands.
        new Cmds();

        try {
            Field field = Core.getInstance().getClass().getDeclaredField("CONFIG_VERSION");
            field.setAccessible(true);
            int i = field.getInt(null);
            if (i <= 6) {
                Core.getLogger().warn("WARNING: Core Version NOT Supported.");
                return;
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return;
        }
        stats = new Stat(this);
        File stat = new File(getDataFolder().getAbsolutePath() + "/stat.json");
        if (!stat.exists()) {
            stats.saveConfig();
        }
        stats = (Stat) stats.reloadConfig();
        Reflections ref = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("cc.sfclub.polar.commands", this.getClass().getClassLoader())).setScanners(new SubTypesScanner()).addClassLoader(this.getClass().getClassLoader()));
        ref.getSubTypesOf(CommandBase.class).forEach(clazz -> {
            try {
                switch (clazz.getSimpleName()) {
                    case "Status":
                        break;
                    default:
                        Core.getInstance().getCommandManager().register(clazz.getDeclaredConstructor().newInstance());
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        //classes that should be register manually.
        Core.getInstance().getCommandManager().register(new Status(this));
        //Message Listener.
        Core.getInstance().getMessage().register(new MessageHandler(this));
    }

    @Override
    public void onDisable() {
        stats.saveConfig();
    }
}