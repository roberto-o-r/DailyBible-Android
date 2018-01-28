package com.isscroberto.dailybibleandroid.data.models;

import com.isscroberto.dailybibleandroid.data.models.Channel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class RssResponse {

    @Element
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
