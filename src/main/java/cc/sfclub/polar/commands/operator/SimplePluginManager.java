package cc.sfclub.polar.commands.operator;

import cc.sfclub.polar.Command;
import cc.sfclub.polar.CommandBase;
import cc.sfclub.polar.Core;
import cc.sfclub.polar.events.messages.TextMessage;
import cc.sfclub.polar.user.User;

import java.util.StringJoiner;

@Command(perm = "member.op.plugin.manage", description = "Reload Plugins", name = "plugin")
public class SimplePluginManager extends CommandBase {
    @Override
    public boolean onCommand(User u, TextMessage command) {
        if (command.getMessage().isEmpty()) {
            sendHelp(command);
            return false;
        }
        String[] args = command.getMessage().split(" ");
        if (args[1].equalsIgnoreCase("reload")) { //here java.lang.ArrayIndexOutOfBoundsException: 1
            command.reply("Reloading..");
            Core.getInstance().init();
        } else if (args[1].equalsIgnoreCase("list")) {
            StringJoiner sj = new StringJoiner(",", "Plugins:\n", "");
            Core.getInstance().getPlugins().forEach(a -> sj.add(a.getPluginLoader().getPluginClassLoader().getDescription().getName()));
            command.reply(sj.toString());
        } else {
            sendHelp(command);
            return false;
        }
        return true;
    }

    public void sendHelp(TextMessage msg) {
        msg.reply(new String[]{
                "Plugin Manager.",
                "plugin list ~ list plugins",
                "plugin reload ~ reload plugins"
        });
    }
}
