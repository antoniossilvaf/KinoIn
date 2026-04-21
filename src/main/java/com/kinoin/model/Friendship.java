package com.kinoin.model;

import com.kinoin.enums.FriendshipStatus;

public class Friendship {
    private User requester;
    private User receiver;
    private FriendshipStatus status;

    public Friendship(User requester, User receiver) {
        this.requester = requester;
        this.receiver = receiver;
        this.status = FriendshipStatus.PENDING;
    }

    public void accept() {
        this.status = FriendshipStatus.ACCEPTED;
    }

    public void block() {
        this.status = FriendshipStatus.BLOCKED;
    }

    public boolean involves(User user) {
        return requester.equals(user) || receiver.equals(user);
    }

    public User getOther(User user) {
        return requester.equals(user) ? receiver : requester;
    }

    public User getRequester() { return requester; }
    public User getReceiver() { return receiver; }
    public FriendshipStatus getStatus() { return status; }
}
