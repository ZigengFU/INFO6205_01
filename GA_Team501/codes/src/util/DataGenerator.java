package util;
/**
 *
 * @author yitongliu
 */
import java.util.*;
/**
 * randomly generate 15 sites
 * @author fuzigeng
 */
public class DataGenerator {
    private int[] sites = new int [15];
    
    public DataGenerator(){
        int[] values = new int [1<<20];
        for (int i = 0 ; i<1024 ; i ++)
            for (int j = 0 ;j<1024; j ++)
                values[i*1024+j] = i*10000+j; //initial hashcode value of each site

        Random x = new Random();
        for (int i = (1<<20) - 1 ; i>0; i--){ //shuffling
            int tmp = x.nextInt(i);
            int tmp2 = values[i];
            values[i] = values[tmp];
            values[tmp] =tmp2;
        }
        
        for (int i = 0 ; i<15 ; i ++){ //select the first 15th point
            sites[i] = values[i];
            System.out.println("No."+(i+1)+" military base: "+"x="+sites[i]/10000 +" y="+sites[i]%10000);
        }

        Arrays.sort(sites);
    }
    
    public int[] DataReader(){
        return sites;
    }
}