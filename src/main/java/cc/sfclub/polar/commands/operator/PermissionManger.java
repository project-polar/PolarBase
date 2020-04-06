package cc.sfclub.polar.commands.operator;

import cc.sfclub.polar.*;
import cc.sfclub.polar.commands.operator.permission.*;
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
                                        .execute(new PermissionExecuteGroupAdd()))
                                .branch(new ChainCommand("del", "member.op.group.del")
                                        .execute(new PermissionExecuteGroupDel()))
                                .branch(new ChainCommand("check", "member.op.group.check")
                                        .execute(new PermissionExecuteGroupCheck()))
                        )
                        .branch(new ChainCommand("user", "member.op.perm.user")
                                .branch(new ChainCommand("add", "member.op.user.add")
                                        .execute(new PermissionExecuteUserAdd()))
                                .branch(new ChainCommand("del", "member.op.user.del")
                                        .execute(new PermissionExecuteUserDel()))
                                .branch(new ChainCommand("check", "member.op.user.check")
                                        .execute(new PermissionExecuteUserCheck()))
                        )
                        .fallback((user, textMessage) -> {
                            textMessage.reply("/p perm <group|user> <add|del|check> <groupId|userId> <permission>");
                            return true;
                        })
        );
    }
}
