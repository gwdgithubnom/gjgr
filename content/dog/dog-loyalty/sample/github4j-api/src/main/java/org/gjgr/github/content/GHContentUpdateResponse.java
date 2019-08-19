package org.gjgr.github.content;

import org.gjgr.github.commit.GHCommit;

/**
 * The response that is returned when updating
 * repository content.
**/
public class GHContentUpdateResponse {
    private GHContent content;
    private GHCommit commit;

    public GHContent getContent() {
        return content;
    }

    public GHCommit getCommit() {
        return commit;
    }
}
