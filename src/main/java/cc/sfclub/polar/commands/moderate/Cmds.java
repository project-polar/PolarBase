package cc.sfclub.polar.commands.moderate;

import cc.sfclub.polar.ChainCommand;
import cc.sfclub.polar.Core;

import java.util.StringJoiner;

public class Cmds {
    public Cmds() {
        Core.getInstance().getCommandManager().register(
                new ChainCommand("cmds", "member.mod.list").execute((user, textMessage) -> {
                    if (textMessage.getMessage().isEmpty()) {
                        StringJoiner str = new StringJoiner("\n", "List of Commands:\n", "");
                        Core.getInstance().getCommandManager().getCommandMap().keySet().forEach(s -> {
                            if ("cmds".equals(s) || "unknown".equals(s)) {
                                return;
                            }
                            str.add(s + " ~ " + Core.getInstance().getCommandManager().getCommandMap().get(s).getDescription());
                        });
                        textMessage.reply(str.toString());
                    }
                    return true;
                })
        );
    }
}
