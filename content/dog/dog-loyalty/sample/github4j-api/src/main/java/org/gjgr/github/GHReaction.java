package org.gjgr.github;

import java.io.IOException;
import java.net.URL;

/**
 * Reaction to issue, comment, PR, and so on.
 *
 * @author Kohsuke Kawaguchi
 * @see Reactable
 */
@Preview @Deprecated
public class GHReaction extends GHObject {
    private GitHub root;

    private GHUser user;
    private ReactionContent content;

    /*package*/ GHReaction wrap(GitHub root) {
        this.root = root;
        user.wrapUp(root);
        return this;
    }

    /**
     * The kind of reaction left.
     */
    public ReactionContent getContent() {
        return content;
    }

    /**
     * User who left the reaction.
     */
    public GHUser getUser() {
        return user;
    }

    /**
     * Reaction has no HTML URL. Don't call this method.
     */
    @Deprecated
    public URL getHtmlUrl() {
        return null;
    }

    /**
     * Removes this reaction.
     */
    public void delete() throws IOException {
        new Requester(root).method("DELETE").withPreview(Previews.SQUIRREL_GIRL).to("/reactions/"+id);
    }
}
