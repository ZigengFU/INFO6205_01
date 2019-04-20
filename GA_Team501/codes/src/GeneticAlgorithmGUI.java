import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;

public class GeneticAlgorithmGUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 5978534001656130751L;

    private JTextField generationText;

    private JTextField populationText;

    private JTextField mutationText;

    private JTextField crossText;

    private JLabel generationLabel;

    private JLabel averageFitnessLabel;

    private JLabel maxFitnessLabel;

    private JLabel minFitnessLabel;

    private JProgressBar progressBar;

    private GenericAlgorithm ga = null;

    public GeneticAlgorithmGUI(GenericAlgorithm sol) {
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("GA Algorithm Experiment");
        getContentPane().setLayout(null);
        setSize(500, 358);

        JPanel configPanel;
        configPanel = new JPanel();
        configPanel.setLayout(null);
        configPanel.setBounds(10, 10, 472, 148);
        getContentPane().add(configPanel);
        configPanel.setBorder(new TitledBorder(null, "configure information",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, null, null));

        final JLabel label_6 = new JLabel();
        label_6.setText("Ratio of mutation");
        label_6.setBounds(10, 53, 60, 20);
        configPanel.add(label_6);

        final JLabel label_7 = new JLabel();
        label_7.setText("Populate size");
        label_7.setBounds(10, 79, 60, 20);
        configPanel.add(label_7);

        final JLabel label_8 = new JLabel();
        label_8.setText("Generation");
        label_8.setBounds(10, 105, 60, 20);
        configPanel.add(label_8);

        mutationText = new JTextField();
        mutationText.setText("0.02");
        mutationText.setAutoscrolls(false);
        mutationText.setToolTipText("Ratio of mutation");
        mutationText.setBounds(100, 54, 186, 20);
        configPanel.add(mutationText);

        populationText = new JTextField();
        populationText.setText("1000");
        populationText.setAutoscrolls(false);
        populationText.setToolTipText("Population size");
        populationText.setBounds(100, 80, 186, 20);
        configPanel.add(populationText);

        generationText = new JTextField();
        generationText.setText("5000");
        generationText.setAutoscrolls(false);
        generationText.setToolTipText("generation");
        generationText.setBounds(100, 106, 186, 20);
        configPanel.add(generationText);

        final JButton startButton = new JButton();
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mutaionPossibility = mutationText.getText();
                String population = populationText.getText();
                final String generation = generationText.getText();
                try {
                    ga = sol;
                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(getContentPane(), "Invalid Numbers!","Error", JOptionPane.ERROR_MESSAGE);
                }
                GeneticAlgorithmThread thread = new GeneticAlgorithmThread(Integer.valueOf(generation));
                new Thread(thread).start();

            }
        });
        startButton.setText("Start");
        startButton.setBounds(331, 26, 99, 23);
        configPanel.add(startButton);

        final JButton closeButton = new JButton();
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(getContentPane(),
                                    GeneticAlgorithmReportGenerator.createGeneticAlgorithmReportPanel(), "Report",
                                    JOptionPane.PLAIN_MESSAGE);
            }
        });
        closeButton.setText("Print the report");
        closeButton.setBounds(331, 64, 99, 23);
        configPanel.add(closeButton);

        final JButton aboutButton = new JButton();
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(getContentPane(),
                                                  "Version:\t  v1.0\nAuthor:\t  Yitong\nMajor:\t  Information Systems\n",
                                                  "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        aboutButton.setText("About");
        aboutButton.setBounds(331, 104, 99, 23);
        configPanel.add(aboutButton);

        final JPanel detailPanel = new JPanel();
        detailPanel.setLayout(null);
        detailPanel.setBorder(new TitledBorder(null, "Details",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION, null, null));
        detailPanel.setBounds(10, 193, 472, 123);
        getContentPane().add(detailPanel);

        final JLabel label_2 = new JLabel();
        label_2.setText("current generation");
        label_2.setBounds(10, 25, 84, 15);
        detailPanel.add(label_2);

        final JLabel label = new JLabel();
        label.setText("average fitness");
        label.setBounds(10, 46, 84, 15);
        detailPanel.add(label);

        final JLabel label_1 = new JLabel();
        label_1.setText("maximum fitness");
        label_1.setBounds(10, 67, 84, 15);
        detailPanel.add(label_1);

        final JLabel label_3 = new JLabel();
        label_3.setText("minimum fitness");
        label_3.setBounds(10, 88, 84, 15);
        detailPanel.add(label_3);

        generationLabel = new JLabel();
        generationLabel.setText("0");
        generationLabel.setBounds(119, 25, 164, 15);
        detailPanel.add(generationLabel);

        averageFitnessLabel = new JLabel();
        averageFitnessLabel.setText("0.0");
        averageFitnessLabel.setBounds(119, 46, 164, 15);
        detailPanel.add(averageFitnessLabel);

        maxFitnessLabel = new JLabel();
        maxFitnessLabel.setText("0.0");
        maxFitnessLabel.setBounds(119, 67, 164, 15);
        detailPanel.add(maxFitnessLabel);

        minFitnessLabel = new JLabel();
        minFitnessLabel.setText("0.0");
        minFitnessLabel.setBounds(119, 88, 164, 15);
        detailPanel.add(minFitnessLabel);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setOpaque(true);
        progressBar.setBounds(10, 164, 472, 18);
        getContentPane().add(progressBar);
        getContentPane().setPreferredSize(new Dimension(500, 358));
    }

    public class GeneticAlgorithmThread extends Thread implements Runnable {
        private int times;

        public GeneticAlgorithmThread(int times) {
                this.times = times;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            for (int i = 0; i < times; i++) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                            // TODO Auto-generated method stub
                            GenerationDetail detail = ga.getDetail();
                            generationLabel.setText(String.valueOf(detail.getGeneration()+1));
                            averageFitnessLabel.setText(String.valueOf(detail.getAverageFitness()));
                            maxFitnessLabel.setText(String.valueOf(detail	.getMaxFitness()));
                            minFitnessLabel.setText(String.valueOf(detail.getMinFitness()));
                            progressBar.setValue(100 * (detail.getGeneration())/ Integer.valueOf(times-1));
                    }
                });
            }
        }
    }
}
