package org.gjgr.github.deploy;

import org.gjgr.github.GHRepository;
import org.gjgr.github.Requester;

import java.io.IOException;

public class GHDeploymentStatusBuilder {
    private final Requester builder;
    private GHRepository repo;
    private int deploymentId;

    public GHDeploymentStatusBuilder(GHRepository repo, int deploymentId, GHDeploymentState state) {
        this.repo = repo;
        this.deploymentId = deploymentId;
        this.builder = new Requester(repo.root);
        this.builder.with("state",state);
    }

    public GHDeploymentStatusBuilder description(String description) {
      this.builder.with("description",description);
      return this;
    }

    public GHDeploymentStatusBuilder targetUrl(String targetUrl) {
        this.builder.with("target_url",targetUrl);
        return this;
    }

    public GHDeploymentStatus create() throws IOException {
        return builder.to(repo.getApiTailUrl("deployments")+"/"+deploymentId+"/statuses",GHDeploymentStatus.class).wrap(repo);
    }
}
