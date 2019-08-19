#!/usr/bin/env bash
ps aux |grep 'org.gjgr.demo.run.App' |grep -v grep |awk '{print $2}'