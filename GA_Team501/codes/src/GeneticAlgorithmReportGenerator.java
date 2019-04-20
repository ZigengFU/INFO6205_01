import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;



public class GeneticAlgorithmReportGenerator {

    private static Map<Integer, GenerationDetail> detailMap = new TreeMap<Integer, GenerationDetail>();

    private static CategoryDataset createGeneticReportDataSet() {
        String average = "Average fitness";
        String maxfitness = "Maximum fitness";
        String minfitness = "Minimum fitness";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        Iterator keyIterator = detailMap.keySet().iterator();
        while(keyIterator.hasNext()){
            Integer generation = (Integer)keyIterator.next();
            GenerationDetail detail = detailMap.get(generation);
            String genStr = String.valueOf(generation);
            defaultcategorydataset.addValue(detail.getAverageFitness(),average ,genStr);
            defaultcategorydataset.addValue(detail.getMaxFitness(), maxfitness, genStr);
            defaultcategorydataset.addValue(detail.getMinFitness(), minfitness, genStr);
        }
            return defaultcategorydataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset) {
        JFreeChart jfreechart = ChartFactory.createLineChart("Genetic Algorithm report", "generation",
                        "fitness", categorydataset, PlotOrientation.VERTICAL, true, true,
                        false);
        jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setRangeGridlinePaint(Color.white);
        CategoryAxis domainAxis = (CategoryAxis)categoryplot.getDomainAxis();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setAutoRangeIncludesZero(false);
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
                        .getRenderer();
        lineandshaperenderer.setShapesVisible(true);
        return jfreechart;
    }

    public static JPanel createGeneticAlgorithmReportPanel() {
        JFreeChart jfreechart = createChart(createGeneticReportDataSet());
    return new ChartPanel(jfreechart);
    }

    public static void addDetail(int generation, GenerationDetail detail) {
        detailMap.put(generation, detail);
    }

    public static GenerationDetail getDetail(int generation) {
        return detailMap.get(generation);
    }
}