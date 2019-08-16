package edu.study.log;

import org.apache.log4j.Logger;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;

/**
 * Created by gwd on 2016/6/7.
 */
public class MucLogger extends Thread {

    private Logger logger= Logger.getLogger(MucLogger.class.getName());

    public double getCpuUsage() throws Exception {
        double cpuUsed = 0;

        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令


        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;

            while ((str = in.readLine()) != null) {
                int m = 0;

                if (str.indexOf(" R ") != -1) {// 只分析正在运行的进程，top进程本身除外 &&

                    strArray = str.split(" ");
                    for (String tmp : strArray) {
                        if (tmp.trim().length() == 0)
                            continue;
                        if (++m == 9) {// 第9列为CPU的使用百分比(RedHat

                            cpuUsed += Double.parseDouble(tmp);

                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return cpuUsed;
    }

    public double getMemUsage() throws Exception {

        double menUsed = 0;
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令


        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;

            while ((str = in.readLine()) != null) {
                int m = 0;

                if (str.indexOf(" R ") != -1) {// 只分析正在运行的进程，top进程本身除外 &&
                    //
                    // System.out.println("------------------3-----------------");
                    strArray = str.split(" ");
                    for (String tmp : strArray) {
                        if (tmp.trim().length() == 0)
                            continue;

                        if (++m == 10) {
                            // 9)--第10列为mem的使用百分比(RedHat 9)

                            menUsed += Double.parseDouble(tmp);

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return menUsed;
    }


    public double getDeskUsage() throws Exception {
        double totalHD = 0;
        double usedHD = 0;
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("df -hl");//df -hl 查看硬盘空间


        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;
            int flag = 0;
            while ((str = in.readLine()) != null) {
                int m = 0;
//               if (flag > 0) {
//                   flag++;
                strArray = str.split(" ");
                for (String tmp : strArray) {
                    if (tmp.trim().length() == 0)
                        continue;
                    ++m;
//                       System.out.println("----tmp----" + tmp);
                    if (tmp.indexOf("G") != -1) {
                        if (m == 2) {
//                               System.out.println("---G----" + tmp);
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp
                                        .substring(0, tmp.length() - 1)) * 1024;

                        }
                        if (m == 3) {
//                               System.out.println("---G----" + tmp);
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(
                                        0, tmp.length() - 1)) * 1024;

                        }
                    }
                    if (tmp.indexOf("M") != -1) {
                        if (m == 2) {
//                               System.out.println("---M---" + tmp);
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp
                                        .substring(0, tmp.length() - 1));

                        }
                        if (m == 3) {
//                               System.out.println("---M---" + tmp);
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(
                                        0, tmp.length() - 1));
                            // System.out.println("----3----" + usedHD);
                        }
                    }

                }

//               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return (usedHD / totalHD) * 100;
    }

    private static  Sigar sigar=new Sigar();

    public void run(){

        while (true){
            try{
               // Mem mem=sigar.getMem();
             //   CpuInfo infos[] = sigar.getCpuInfoList();
            //    CpuPerc cpuList[] = null;
            //    cpuList = sigar.getCpuPercList();
                logger.warn(getName()+"is running for a job.");
                logger.info("monitor"+getName()+"the mem and cpu disk usage info.");
                logger.info("Cpu Info:"+getCpuUsage()+" DiskUsage:"+getDeskUsage()+"Memory Usage:"+getMemUsage());
             //   for (int i=0;i<infos.length;i++){
             //       printCpuPerc(cpuList[i]);
             //   }
               // (new Random((new Date()).getSeconds())+1000)
                sleep((int)(Math.random()*9000)+10000);
            }catch (Exception e){
                logger.error("Exception. Logger has a fail transcation.......");
            }


        }
    }


}
