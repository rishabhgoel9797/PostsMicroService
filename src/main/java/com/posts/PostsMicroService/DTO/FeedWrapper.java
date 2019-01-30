package com.posts.PostsMicroService.DTO;

import com.posts.PostsMicroService.Entity.Feeds;

import java.util.List;

public class FeedWrapper {
    private List<Feeds> feedsList;

    public List<Feeds> getFeedsList() {
        return feedsList;
    }

    public void setFeedsList(List<Feeds> feedsList) {
        this.feedsList = feedsList;
    }

    @Override
    public String toString() {
        return "FeedWrapper{" +
                "feedsList=" + feedsList +
                '}';
    }
}
