package com.csci201.marketplace.pushnotif.model;

import com.csci201.marketplace.item.*;
import com.csci201.marketplace.item.model.Item;

public class Message {
    private String from;
    private String to;
    private Item item;
    private String msg;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}