package com.innojane.lynli;

import java.util.List;

public class Home {

    private String homeName;
    private List<String> avatarURLs;

    public String getHomeName() {
        return homeName;
    }

    public List<String> getAvatarURL() {
        return avatarURLs;
    }

    public String getAvatarURL(int i) {
        if (i >= 0 && avatarURLs.size() > i) return avatarURLs.get(i);
        else return null;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public void setAvatarURL(List<String> avatarURLs) {
        this.avatarURLs = avatarURLs;
    }

    public void addAvatarURL(String avatarURL) {
        avatarURLs.add(avatarURL);
    }

}
