package fundamentals.analysis_of_algorithms;

import edu.princeton.cs.algs4.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Created by patriknygren on 2017-04-16.
 */
public class Ex_1_4_3 extends ApplicationFrame {

    public Ex_1_4_3(String title, XYSeries series) {
        super(title);
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Running time for ThreeSum",
                "N",
                "Time",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) { // Print table of running times.
        final XYSeries series = new XYSeries("plot");
        for (int N = 250; N < 2000; N += N) {
            double time = timeTrial(N);
            series.add(N, time);
        }
        ApplicationFrame demo = new Ex_1_4_3("Running time", series);
        demo.pack();
        demo.setVisible(true);
        RefineryUtilities.centerFrameOnScreen(demo);
    }

    // Time ThreeSum.count() for N random 6-digit ints.
    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }


}
