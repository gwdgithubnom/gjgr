package org.gjgr.github.manager;

import static org.gjgr.github.Previews.LOKI;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.gjgr.github.*;
import org.gjgr.github.manager.BranchProtection.RequiredStatusChecks;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.gjgr.github.commit.GHCommitStatus;

/**
 * A branch in a repository.
 * 
 * @author Yusuke Kokubo
 */
@SuppressFBWarnings(value = {"UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_FIELD", 
    "NP_UNWRITTEN_FIELD", "URF_UNREAD_FIELD"}, justification = "JSON API")
public class GHBranch {
    private GitHub root;
    private GHRepository owner;

    private String name;
    private Commit commit;
    @JsonProperty("protected")
    private boolean protection;
    private String protection_url;


    public static class Commit {
        String sha;
        
        @SuppressFBWarnings(value = "UUF_UNUSED_FIELD", justification = "We don't provide it in API now")
        String url;
    }

    public GitHub getRoot() {
        return root;
    }

    /**
     * Repository that this branch is in.
     */
    public GHRepository getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns true if the push to this branch is restricted via branch protection.
     */
    @Preview
    @Deprecated
    public boolean isProtected() {
        return protection;
    }

    /**
     * Returns API URL that deals with the protection of this branch.
     */
    @Preview @Deprecated
    public URL getProtectionUrl() {
        return GitHub.parseURL(protection_url);
    }


    /**
     * The commit that this branch currently points to.
     */
    public String getSHA1() {
        return commit.sha;
    }

    /**
     * Disables branch protection and allows anyone with push access to push changes.
     */
    @Preview @Deprecated
    public void disableProtection() throws IOException {
        BranchProtection bp = new BranchProtection();
        bp.enabled = false;
        setProtection(bp);
    }

    /**
     * Enables branch protection to control what commit statuses are required to push.
     *
     * @see GHCommitStatus#getContext()
     */
    @Preview @Deprecated
    public void enableProtection(EnforcementLevel level, Collection<String> contexts) throws IOException {
        BranchProtection bp = new BranchProtection();
        bp.enabled = true;
        bp.requiredStatusChecks = new RequiredStatusChecks();
        bp.requiredStatusChecks.enforcement_level = level;
        bp.requiredStatusChecks.contexts.addAll(contexts);
        setProtection(bp);
    }

    @Preview @Deprecated
    public void enableProtection(EnforcementLevel level, String... contexts) throws IOException {
        enableProtection(level, Arrays.asList(contexts));
    }

    private void setProtection(BranchProtection bp) throws IOException {
        new Requester(root).method("PATCH").withPreview(LOKI)._with("protection",bp).to(getApiRoute());
    }

    String getApiRoute() {
        return owner.getApiTailUrl("/branches/"+name);
    }
    
    @Override
    public String toString() {
        final String url = owner != null ? owner.getUrl().toString() : "unknown";
        return "Branch:" + name + " in " + url;
    }

    /*package*/ GHBranch wrap(GHRepository repo) {
        this.owner = repo;
        this.root = repo.root;
        return this;
    }
}
