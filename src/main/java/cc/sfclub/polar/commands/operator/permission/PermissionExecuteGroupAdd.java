package cc.sfclub.polar.commands.operator.permission;

import cc.sfclub.polar.CommandBase;
import cc.sfclub.polar.events.messages.TextMessage;
import cc.sfclub.polar.user.User;

public class PermissionExecuteGroupAdd extends CommandBase {
    @Override
    public boolean onCommand(User user, TextMessage textMessage) {
        String[] args = textMessage.getMessage().split(" ");
        if (args.length != 4) {
            return false;
        }

        long groupId = 0;
        try {
            groupId = Long.parseLong(args[2]);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            textMessage.reply("Bad group id!");
            return true;
        }

        // Todo

        return true;
    }
}
