package org.gjgr.github;

import org.gjgr.github.issue.GHIssue;

import java.io.IOException;

/**
 * @author Kohsuke Kawaguchi
 * @see GHIssue#getLabels()
 * @see GHRepository#listLabels()
 */
public class GHLabel {
    private String url, name, color;
    private GHRepository repo;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    /**
     * Color code without leading '#', such as 'f29513'
     */
    public String getColor() {
        return color;
    }

    /*package*/ GHLabel wrapUp(GHRepository repo) {
        this.repo = repo;
        return this;
    }

    public void delete() throws IOException {
        repo.root.retrieve().method("DELETE").to(url);
    }
}
