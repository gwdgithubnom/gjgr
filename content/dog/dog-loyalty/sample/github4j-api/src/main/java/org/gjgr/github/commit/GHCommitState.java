package org.gjgr.github.commit;

/**
 * Represents the state of commit
 *
 * @author Kohsuke Kawaguchi
 * @see GHCommitStatus
 */
public enum GHCommitState {
    PENDING, SUCCESS, ERROR, FAILURE
}
