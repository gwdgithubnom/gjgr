package org.gjgr.exam.contest.pdd;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.gjgr.exam.tools.InputOutObserver;

import java.io.InputStream;
import java.util.*;

/**
 * File Name : arithmetic4j - edu.gjgr.exam
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/12/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class BearAndSugar {
    public static void main(String[] args){
        InputStream inputStream=System.in;
        inputStream= InputOutObserver.getLocalInputFileStream(BearAndSugar.class);
        Scanner scanner=new Scanner(inputStream);
        Integer n=scanner.nextInt();
        Integer[] result=new Integer[n];
        Integer m=scanner.nextInt();
        List<Integer> list=new ArrayList<Integer>();
        Integer i=0;
        while (i<m){
            list.add(scanner.nextInt());
            i++;
        }
        Collections.sort(list);
        i=0;
        List<Model> models=new ArrayList<>();
        while (i<n){
            Model model=new Model();
            model.setFight(scanner.nextInt());
            model.setHungry(scanner.nextInt());
            model.setId(i);
            models.add(model);
            i++;
        }
        Collections.sort(models, new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o2.getFight()-o1.getFight();
            }
        });
        Iterator<Model> iterator=models.iterator();
        for(Model model:models){
            for(int j=list.size()-1;j>=0;j--){
                if(model.getHungry()>=list.get(j)){
                    model.setHungry(model.getHungry()-list.get(j));
                    list.remove(j);
                }
            }
            result[model.getId()]=model.getHungry();
        }
        for(int j=0;j<n;j++){
            System.out.println(result[j]);
        }

    }

}
class Model{
    Integer id;
    Integer hungry;
    Integer fight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHungry() {
        return hungry;
    }

    public void setHungry(Integer hungry) {
        this.hungry = hungry;
    }

    public Integer getFight() {
        return fight;
    }

    public void setFight(Integer fight) {
        this.fight = fight;
    }
}
