/**
 *
 * @author yitongliu
 */

public class Chromosome implements Cloneable{
    private double fitness = -1;
    private int chromo;
    
    public int getChromo() {
        return chromo;
    }

    public void setChromo(int chromo) {
        this.chromo = chromo;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
	
}
