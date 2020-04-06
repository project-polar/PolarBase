package cc.sfclub.polar.commands.operator.permission;

import cc.sfclub.polar.CommandBase;
import cc.sfclub.polar.events.messages.TextMessage;
import cc.sfclub.polar.user.User;
import cc.sfclub.polar.utils.UserUtil;

public class PermissionExecuteUserAdd extends CommandBase {
    @Override
    public boolean onCommand(User user, TextMessage textMessage) {
        String[] args = textMessage.getMessage().split(" ");
        if (args.length != 4) {
            return false;
        }

        long userId = 0;
        try {
            userId = Long.parseLong(args[2]);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            textMessage.reply("Bad user id!");
            return true;
        }

        User u = UserUtil.getUser(userId, "QQ");
        if (u == null) {
            textMessage.reply("User " + args[2] + " is not exists!");
            return true;
        }

        u.addPermission(args[3]);
        textMessage.reply(
                "User " + args[2] +
                        " now has permission " + args[3] + " !");
        return true;
    }
}
