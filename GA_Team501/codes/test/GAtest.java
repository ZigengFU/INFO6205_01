import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;
import util.DataGenerator;

/**
 *
 * @author fengqiu
 */

public class GAtest {
    /**
     * Test the select method
     * Initialize the first generation and calculate the fitness of each one
     * After using the selection method, check the probability of each one
     * If the fitness value is bigger, the possibility it will be chosen is bigger
     */
    @Test
    public void selectionTest(){
        DataGenerator dg = new DataGenerator();
        GenericAlgorithm ga = new GenericAlgorithm(dg.DataReader());
        ga.init_generation();
        double [] a=new double[1000];
        for(int i=0;i<1000;i++){
            a[i]=ga.fitnessCalc(ga.crnt_generation[i]);
        }
        ga.selection();
        boolean flag=true;
        for(int i=1;i<1000;i++){
            if(a[i-1] > a[i] ){
                if(ga.probability_list[i-1]>ga.probability_list[i]) continue;
                else { flag=false;
                    break; }
            }
            else if(a[i-1]<a[i] ){
                if(ga.probability_list[i-1]<ga.probability_list[i]) continue;
                else {flag=false;
                    break;}
            }
        }
        assertEquals(true,flag);
    }
    
    @Test
    /**
    * Test the crossover function
    * The first case: binary code of a is 00011111 and binary code of b is 10001111
    * crossover the first bit of these two codes and the crossover result should be 10011111 and 00001111
    * Decimal code of 10011111 is 159 and Decimal code of 00001111 is 15
    
    * The second case: binary code of c is 00001001 and binary code of d is 01110111
    * crossover the third bit of these two codes and the crossover result should be  and 01010111 and 00101001
    * Decimal code of 01010111 is 87 and Decimal code of 00101001 is 41
    */
    public void crossOverTest(){
        DataGenerator dg = new DataGenerator();
        GenericAlgorithm ga = new GenericAlgorithm(dg.DataReader());
        //The first test case
        int a=31,b=143;
        int[] result1=ga.exchange(a,b,7);
        int r11=result1[0]; 
        int r12=result1[1]; 
        assertEquals(15,r11);
        assertEquals(159,r12);
        //The second test case
        int c=9,d=119; 
        int[] result2=ga.exchange(c,d,5);
        int r21=result2[0]; 
        int r22=result2[1]; 
        assertEquals(87,r21);
        assertEquals(41,r22);


    }
    
    /**
     * Test the mutation function
     * The first case: the binary code of a is 00101001 and mutate the first bit of it
     * Result is 10101001 and the decimal code of it is 169
     */
    @Test
    public void mutationTest(){
        DataGenerator dg = new DataGenerator();
        GenericAlgorithm ga = new GenericAlgorithm(dg.DataReader());
        //The first test case
        int a=41;
        int result1=ga.mutate(a,7);
        assertEquals(169,result1);
        //The second test case
        int b=119;
        int result2=ga.mutate(b,0);
        assertEquals(118,result2);
        //The third test case
        int c=31;
        int result3=ga.mutate(c,3);
        assertEquals(23,result3);
    }

    /**
     * Check the GA process
     * Initialize the first generation and calculate the sum1 of their fitness value
     * After 5000 times of selection, crossover and mutation, calculate the sum2 of their fitness value
     * Compare these two value, if sum1 is smaller than sum2, GA has a positive performance
     */
    @Test
    public void GAEvaluation() {
        DataGenerator dg = new DataGenerator();
        GenericAlgorithm ga = new GenericAlgorithm(dg.DataReader());
        ga.init_generation();
        double[] origin = new double[1000];
        double sum1 = 0, sum2 = 0;
        boolean flag=true;
        for (int i = 0; i < 1000; i++) {
            origin[i] = ga.fitnessCalc(ga.crnt_generation[i]);
            sum1=sum1+origin[i];
        }
        ga.evolution();
        for (int i = 0; i < 1000; i++) {
            sum2 = sum2 + ga.fitnessCalc(ga.crnt_generation[i]);
        }
        if (sum2 < sum1 ) 
            flag = false;
        assertEquals(true, flag);
    }

}