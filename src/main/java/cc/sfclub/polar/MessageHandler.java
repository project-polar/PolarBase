package cc.sfclub.polar;

import cc.sfclub.polar.events.messages.TextMessage;
import org.greenrobot.eventbus.Subscribe;

public class MessageHandler {
    private PolarBase polarBase;

    public MessageHandler(PolarBase polarBase) {
        this.polarBase = polarBase;
    }

    @Subscribe
    public void onMessage(TextMessage tm) {
        polarBase.stats.msgCount++;
    }
}
