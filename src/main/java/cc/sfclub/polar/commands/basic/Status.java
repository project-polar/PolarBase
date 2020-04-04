package cc.sfclub.polar.commands.basic;

import cc.sfclub.polar.Command;
import cc.sfclub.polar.CommandBase;
import cc.sfclub.polar.Core;
import cc.sfclub.polar.PolarBase;
import cc.sfclub.polar.events.messages.TextMessage;
import cc.sfclub.polar.modules.security.PolarSec;
import cc.sfclub.polar.user.User;

import java.util.concurrent.atomic.AtomicInteger;

@Command(name = "status", perm = "member.basic.status", description = "Get Bot Status.")
public class Status extends CommandBase {
    PolarBase polarBase;

    public Status(PolarBase pb) {
        polarBase = pb;
    }

    @Override
    public boolean onCommand(User u, TextMessage msg) {
        StringBuilder str = new StringBuilder();
        str.append(Core.getConf().name)
                .append(" ")
                .append(Core.getConf().version)
                .append("\n");
        boolean isPsOn = Core.getConf().usePolarSecurity;
        str.append("PolarSecurity: ")
                .append(isPsOn)
                .append("\n");
        if (isPsOn) {
            int busyLevel = Core.getInstance().getPSec().getBusyLevel();
            AtomicInteger ignored = new AtomicInteger();
            AtomicInteger banned = new AtomicInteger();
            PolarSec.getConf().getPriority().forEach((k, v) -> {
                if (v < busyLevel) {
                    if (v == -1) {
                        banned.getAndIncrement();
                    } else {
                        ignored.getAndIncrement();
                    }
                }
            });
            str.append("Banned Users: ")
                    .append(banned).append("\n");
            str.append("Ignored Users: ")
                    .append(ignored).append("\n");
            str.append("BusyLevel: ").append(busyLevel)
                    .append("\n");
        }
        str.append("Total Messages: ").append(polarBase.stats.msgCount).append("\n");
        //todo time convert
        //str.append("Running about ").append();
        msg.reply(str.toString());

        return true;
    }
}
