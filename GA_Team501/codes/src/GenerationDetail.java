/**
 *
 * @author yitongliu
 */

public class GenerationDetail {
    private int generation = 1;

    private double averageFitness = 0.0;

    private double maxFitness = 0.0;

    private int maxChromo;

    private double minFitness = 0.0;

    private int minChromo;

    public double getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(double averageFitness) {
        this.averageFitness = averageFitness;
    }

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public double getMinFitness() {
        return minFitness;
    }

    public void setMinFitness(double minFitness) {
        this.minFitness = minFitness;
    }

    public int getMaxChromo() {
        return maxChromo;
    }

    public void setMaxChromo(int maxChromo) {
        this.maxChromo = maxChromo;
    }

    public int getMinChromo() {
        return minChromo;
    }

    public void setMinChromo(int minChromo) {
        this.minChromo = minChromo;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
    
}
