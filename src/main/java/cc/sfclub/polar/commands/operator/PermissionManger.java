package cc.sfclub.polar.commands.operator;

import cc.sfclub.polar.*;
import cc.sfclub.polar.events.messages.TextMessage;
import cc.sfclub.polar.user.Group;
import cc.sfclub.polar.user.User;
import cc.sfclub.polar.utils.UserUtil;

import java.util.Iterator;

public class PermissionManger {
    public PermissionManger() {
        Core.getInstance().getCommandManager().register(
                new ChainCommand("perm", "member.op.perm")
                        .branch(new ChainCommand("group", "member.op.perm.group")
                                .branch(new ChainCommand("add", "member.op.group.add")
                                        .execute((user, textMessage) -> {
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

                                            //Todo
                                            return true;
                                        }))
                                .branch(new ChainCommand("del", "member.op.group.del")
                                        .execute((user, textMessage) -> {

                                            return false;
                                        }))
                                .branch(new ChainCommand("check", "member.op.group.check")
                                        .execute((user, textMessage) -> {

                                            return false;
                                        }))
                        )
                        .branch(new ChainCommand("user", "member.op.perm.user")
                                .branch(new ChainCommand("add", "member.op.user.add")
                                        .execute((user, textMessage) -> {
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
                                        }))
                                .branch(new ChainCommand("del", "member.op.user.del")
                                        .execute((user, textMessage) -> {
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

                                            if (u.hasPermission(args[3])) {
                                                // Todo: Delete user permission.
                                                textMessage.reply("Not impl");
                                            } else {
                                                textMessage.reply(
                                                        "User " + args[2] +
                                                                " doesn't have permission " + args[3] + " !");
                                            }
                                            return true;
                                        }))
                                .branch(new ChainCommand("check", "member.op.user.check")
                                        .execute((user, textMessage) -> {
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

                                            if (u.hasPermission(args[3])) {
                                                textMessage.reply(
                                                        "User " + args[2] +
                                                                " has permission " + args[3] + " !");
                                            } else {
                                                textMessage.reply(
                                                        "User " + args[2] +
                                                                " doesn't have permission " + args[3] + " !");
                                            }
                                            return true;
                                        }))
                        )
                        .fallback((user, textMessage) -> {
                            textMessage.reply("/p perm <group|user> <add|del|check> <groupId|userId> <permission>");
                            return true;
                        })
        );
    }
}
