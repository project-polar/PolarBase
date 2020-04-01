package cc.sfclub.polar;

import org.mve.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PolarBase extends JavaPlugin {
    @Override
    public void onEnable() {
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
        Reflections ref = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("cc.sfclub.polar.commands", this.getClass().getClassLoader())).setScanners(new SubTypesScanner()).addClassLoader(this.getClass().getClassLoader()));
        ref.getSubTypesOf(CommandBase.class).forEach(clazz -> {
            try {
                Core.getInstance().getCommandManager().register(clazz.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}