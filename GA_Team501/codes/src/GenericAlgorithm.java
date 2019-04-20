import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 *
 * @author yitongliu,zigeng fu
 */

public class GenericAlgorithm {
    public final int sitesNum = 15;
    public final int population=1000;
    
    private GenerationDetail detail = null;
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;
    double sum;

    public int[] crnt_generation = new int[1000];
    public int[] location_list = new int[sitesNum];
    public double[] probability_list = new double[1000];
    
    public List<Chromosome> list=new ArrayList<>();
    
    //Initialize military bases
    public GenericAlgorithm(int[] sites){
        location_list = Arrays.copyOf(sites, sitesNum);
    }	

    public void init_generation(){//random 1000 initial individual
        //Generate initial values
        int[] values = new int [1<<20];
        
        //Use 8-digit integers to represent location, higher 4 digits as x axis, lower 4 digits as y axis
        for (int i = 0 ; i<1024 ; i ++)
            for (int j = 0; j<1024; j++)
                values[i*1024+j] = i*10000+j;

        Random x = new Random();
        
        //Suffling
        for (int i = (1<<20) - 1 ; i>0; i--){
            int tmp = x.nextInt(i+1);
            int tmp2 = values[i];
            values[i] = values[tmp];
            values[tmp] =tmp2;
        }
        
        for (int i = 0 ; i<population ; i++){
            Chromosome c = new Chromosome();
            crnt_generation[i] = values[i];
            c.setChromo(crnt_generation[i]);
            list.add(c);
        }
        
        System.out.println("");
        System.out.println("");
        System.out.println("******************************************************************");
        System.out.println("1000 initial individuals:");
        for (int i = 0 ; i < 1000; i++){
            System.out.println("No."+(i+1)+" "+"x="+crnt_generation[i]/10000+" "+"y="+crnt_generation[i]%10000+" "
                                +"fitness=" + GenericAlgorithm.this.fitnessCalc(crnt_generation[i],list.get(i)));
        }
    }
    
    /**Function of the signal propagation, is a combination of a sin function and a linear decreasing function
     * 
     * @param x: Euclidean distance between the signal station candidate and one of the military bases
     * @return: signal strength
     */
     public double waveFormFunction(double x){ 
        double pi = Math.PI;
        return 10 * Math.sin(0.01*(x+50*pi)) - 0.02*x +50;
    }

    /**
     * Calculate fitness of each candidate
     * @param val: the hash code of the candidate
     * @param c: chromosome of the candidate
     * @return: sum of the signal strength
     */
    public double fitnessCalc(int val, Chromosome c){
        double sum = 0;
        for (int i = 0; i <sitesNum; i++){
            double x_diff = val/10000 - location_list[i]/10000;
            double y_diff = val%10000 - location_list[i]%10000;
            sum += waveFormFunction(Math.sqrt(Math.pow(x_diff,2) + Math.pow(y_diff,2)));
        }
        c.setFitness(sum);
        return sum;
    }
    
    /**
     * Calculate fitness of each candidate (for test cases)
     * @param val: the hash code of the candidate
     * @return: sum of the signal strength
     */
    public double fitnessCalc(int val){
        double sum = 0;
        for (int i = 0; i <sitesNum; i++){
            double x_diff = val/10000 - location_list[i]/10000;
            double y_diff = val%10000 - location_list[i]%10000;
            sum += waveFormFunction(Math.sqrt(Math.pow(x_diff,2) + Math.pow(y_diff,2)));
        }
        return sum;
    }
    
    /**
     * Selection probability calculations
     */
    public double[] weightAssignFunc(double[] probs){
        double sumWeight=0;
        for (int i = 0 ; i<probs.length; i++){
            probs[i] = GenericAlgorithm.this.fitnessCalc(crnt_generation[i],list.get(i));
            sumWeight += probs[i];
        }
        
        for (int i = 0 ; i<probs.length; i++){
            probs[i] = probs[i]/sumWeight;
        }
        return probs;
    }

    public void selection()
    {
        weightAssignFunc(probability_list);
        int[] newGeneration = new int[population];
        double totalProb = 1.0;
        for (int i = 0;i<population; i++){
            double r = Math.random()  * totalProb;
            double sum = 0.0;
            int j = 0;
            while(j < probability_list.length) {
                sum += probability_list[j];
                if (sum > r)
                    break;
                j++;
            }
            newGeneration[i] = crnt_generation[j];
        }

        crnt_generation = Arrays.copyOf(newGeneration, population);
        
    }

    public void crossover(){
        Random x = new Random();
        for (int i = population-1; i>0; i--)//shuffle make i and i+1 Random pairing
        {
            int tmp = x.nextInt(i+1);
            int tmp2 = crnt_generation[i];
            crnt_generation[i] = crnt_generation[tmp];
            crnt_generation[tmp] = tmp2;
        }

        for (int i = 0 ; i<population; i+=2){//take the i and i+1 as values pair change one of their genus randomly
            int tmpx = x.nextInt(20);
            //Change the bit on the y-axis
            if (tmpx/10 == 1) {
                int tmpx1 = crnt_generation[i]%10000;
                int tmpx2 = crnt_generation[i+1]%10000;
                int[] p=exchange(tmpx1,tmpx2,tmpx-10);
                crnt_generation[i] = (crnt_generation[i]/10000)*10000 + p[1];
                crnt_generation[i+1] = (crnt_generation[i+1]/10000)*10000 + p[0];

            }
            if (tmpx/10 == 0) {
            //Change the bit on the x-axis
                int tmpx1 = crnt_generation[i]/10000;
                int tmpx2 = crnt_generation[i+1]/10000;
                int[] p=exchange(tmpx1,tmpx2,tmpx);
                crnt_generation[i] = (crnt_generation[i]%10000) + p[1] * 10000;
                crnt_generation[i+1] = (crnt_generation[i+1]%10000) + p[0] * 10000;

            }
        }
    }

    public int[] exchange(int a,int b,int bit){ //exchange the genes
        int tmpx11 = (b % (1<<bit)) + ((b / (1<<(bit+1))) * (1<<(bit+1))) + (a & (1<<bit));
        int tmpx22 = (a % (1<<bit)) + ((a / (1<<(bit+1))) * (1<<(bit+1))) + (b & (1<<bit));
        int[] re= new int[2];
        re[0] = tmpx11;
        re[1] = tmpx22;
        return re;
    }

    /**
     * We defined the probability of mutation is 2%
     */
    public void mutation(){
        Random x = new Random();
        for (int i = 0 ; i<population; i++){
            int tmploc = x.nextInt(20);
            int tmpvar = x.nextInt(100);
            if (tmpvar == 2){ //probability = 2%
                int tmpY = crnt_generation[i]%10000;
                int tmpX = crnt_generation[i]/10000;
                if (tmploc < 10){
                    tmpY = mutate(tmpY,tmploc);
                    crnt_generation[i] = tmpX * 10000 + tmpY;
                }
                else{
                    tmpX = mutate(tmpX,tmploc-10);
                    crnt_generation[i] = tmpX * 10000 + tmpY;
                }
            }
        }
    }
    
    /**
     * Mutate a bit
     */
    public int mutate(int val,int loc){
        return val^(1<<loc);
    }
    
    public void printWork(){
        System.out.println("");
        System.out.println("");
        System.out.println("******************************************************************");
        System.out.println("Last generation: "+(detail.getGeneration()+1));

        System.out.println("1000 final individuals:");
        for (int i = 0 ; i<1000; i++){
            System.out.println("No."+(i+1)+" "+"x="+crnt_generation[i]/10000 +  " " +"y="+ crnt_generation[i]%10000 + " " 
                               +"fitness=" + GenericAlgorithm.this.fitnessCalc(crnt_generation[i],list.get(i)));
        }
    }
	

    public GenerationDetail getDetail() {
            return detail;
    }	
    
    private void evaluate(int i){
        double max=Integer.MIN_VALUE;
        double min=Integer.MAX_VALUE;
        detail = new GenerationDetail();
        detail.setGeneration(i);
        double sum = 0.0;
        for(Chromosome c : list){
                double fitness = c.getFitness();
                if(fitness > max){
                        max = fitness;
                        detail.setMaxChromo(c.getChromo());

                }
                if(fitness < min){
                        min = fitness;
                        detail.setMinChromo(c.getChromo());

                }
                c.setFitness(fitness);
                sum += fitness;
        }
        detail.setMaxFitness(max);
        detail.setMinFitness(min);
        detail.setAverageFitness(sum/population);
        GeneticAlgorithmReportGenerator.addDetail(detail.getGeneration(), detail);

        if(i==4999){
            this.max=max;
            this.min=min;
            this.sum=sum;
        }
    }

    /**
     * Repeat evolution 5000 times.
     * Record the first and last generation data into .csv file
     */
    public void evolution(){
        try {
            FileOutputStream fis = new FileOutputStream("./src/result.csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            String c = "X" + "," + "Y"+","+"Z"+ "\n";
            bw.write(c);
            bw.flush();
            for (int ci = 0; ci<5000; ci++){
                evaluate(ci);
                selection();
                crossover();
                mutation();
                if(ci == 0 || ci == 4999){   
                    for (int i = 0 ; i<1000; i++){
                        String content = crnt_generation[i]/10000 + "," + crnt_generation[i]%10000 + "," + fitnessCalc(crnt_generation[i],list.get(i)) + "\n";
                        bw.write(content);
                        bw.flush();
                    }
                }
            }
            bw.close(); 
         } catch (IOException e) {
            e.printStackTrace();        
        }

        selection();
        System.out.println("**************************************************************");
        System.out.println("max="+max);
        System.out.println("min="+min);
        System.out.println("average="+detail.getAverageFitness());
        System.out.println("**************************************************************");
    }
    
}
