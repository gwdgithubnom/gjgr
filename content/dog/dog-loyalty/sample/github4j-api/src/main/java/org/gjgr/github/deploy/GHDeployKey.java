package org.gjgr.github.deploy;

import java.io.IOException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.gjgr.github.GHRepository;
import org.gjgr.github.Requester;

public class GHDeployKey {

    protected String url, key, title;
    protected boolean verified;
    protected int id;
    private GHRepository owner;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public boolean isVerified() {
        return verified;
    }

    public GHDeployKey wrap(GHRepository repo) {
        this.owner = repo;
        return this;
    }

    public String toString() {
        return new ToStringBuilder(this).append("title",title).append("id",id).append("key",key).toString();
    }
    
    public void delete() throws IOException {
        new Requester(owner.root).method("DELETE").to(String.format("/repos/%s/%s/keys/%d", owner.getOwnerName(), owner.getName(), id));
    }
}
