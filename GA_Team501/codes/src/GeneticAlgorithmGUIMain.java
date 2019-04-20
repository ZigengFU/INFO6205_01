import org.jfree.ui.RefineryUtilities;
import util.DataGenerator;

public class GeneticAlgorithmGUIMain {
    public static void main(String[] args){
            DataGenerator da = new DataGenerator();
            GenericAlgorithm ga = new GenericAlgorithm(da.DataReader());
            ga.init_generation();
            ga.evolution();
            ga.printWork();
            GeneticAlgorithmGUI gui = new GeneticAlgorithmGUI(ga);
            gui.pack();
            RefineryUtilities.centerFrameOnScreen(gui);
            gui.setVisible(true);
    }
}
