import org.gjgr.github.GHRepository.Contributor;
import org.gjgr.github.GHUser;
import org.gjgr.github.GitHub;

/**
 * @author Kohsuke Kawaguchi
 */
public class Foo {
    public static void main(String[] args) throws Exception {
        GitHub gh = GitHub.connect();
        for (Contributor c : gh.getRepository("kohsuke/yo").listContributors()) {
            System.out.println(c);
        }
    }

    private static void testRateLimit() throws Exception {
        GitHub g = GitHub.connectAnonymously();
        for (GHUser u : g.getOrganization("jenkinsci").listMembers()) {
            u.getFollowersCount();
        }
    }
}
