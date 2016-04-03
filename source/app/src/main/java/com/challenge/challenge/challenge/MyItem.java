package com.challenge.challenge.challenge;

/**
 * Created by sangeet on 4/3/2016.
 */
public class MyItem {
    String id,name,commit,message;

    public MyItem(String id, String name, String commit, String message) {
        this.id = id;
        this.name = name;
        this.commit = commit;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCommit() {
        return commit;
    }

    public String getMessage() {
        return message;
    }
}
