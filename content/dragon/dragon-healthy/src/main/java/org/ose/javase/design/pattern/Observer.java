// publisher + subscriber

package org.ose.javase.design.pattern;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    public static void main(String[] args) {
        HeadHunter hh = new HeadHunter();
        hh.registerObserver(new JobSeeker("A"));
        hh.registerObserver(new JobSeeker("B"));
        hh.registerObserver(new JobSeeker("C"));
        hh.addJob("Job1");
        hh.addJob("Job2");
        hh.addJob("Job3");
        hh.removeJob("Job2");
    }
}

// publisher

class HeadHunter {
    private List<TheObserver> userList = new ArrayList<TheObserver>();
    private List<String>      jobs     = new ArrayList<String>();

    public void registerObserver(TheObserver o) {
        userList.add(o);
    }

    public void removeObserver(TheObserver o) {
        userList.remove(o);
    }

    public void addJob(String job) {
        this.jobs.add(job);
        notifyAllObservers();
    }

    public void removeJob(String job) {
        this.jobs.remove(job);
        notifyAllObservers();
    }

    public String jobsInfo() {
        return jobs.toString();
    }

    private void notifyAllObservers() {
        // push updates to subscribers
        for (TheObserver ob : userList) {
            ob.update(this);
        }
    }
}

//subscriber

interface TheObserver {
    public void update(HeadHunter s);
}

class JobSeeker implements TheObserver {
    private String id;

    public JobSeeker(String id) {
        this.id = id;
    }

    @Override
    public void update(HeadHunter s) {
        System.out.println(this.id + " get notified!");
        System.out.println(s.jobsInfo());
    }
}
