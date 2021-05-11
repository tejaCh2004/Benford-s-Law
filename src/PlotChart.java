

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class PlotChart extends JFrame {

	private JPanel setTitlePanel () {
	       JPanel titlePanel = new JPanel();
	        titlePanel.setPreferredSize(new Dimension(this.getWidth(), 30));
	        titlePanel.setBackground(Color.blue);
	        JLabel titleLabel = new JLabel("Figure 1 -- Benford's Law Distribution Leading Digit");
	        titleLabel.setForeground(Color.WHITE);
	        titlePanel.add(titleLabel, BorderLayout.CENTER);
	        return titlePanel;
	}
	
    private CategoryDataset createBenFordDataset(double[] benfordArray) {

    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	for (int i = 1; i < benfordArray.length; i++) {
    		dataset.setValue(benfordArray[i], "", String.valueOf(i));
    	}

        return dataset;
    }
	
	
	private ChartPanel setChartPanel(double[] benfordArray) {
	        CategoryDataset benfordDataSet = createBenFordDataset(benfordArray);

	        JFreeChart chart = ChartFactory.createBarChart(
	                "",
	                "Digit",
	                "Percent",
	                benfordDataSet,
	                PlotOrientation.VERTICAL,
	                false, true, false);
	        
	        chart.getCategoryPlot().setDomainGridlinePaint(Color.black);
	        chart.getCategoryPlot().setRangeGridlinePaint(Color.black);
	        chart.getCategoryPlot().setDomainGridlinesVisible(true);
	        chart.getCategoryPlot().setRangeGridlinesVisible(true);
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        chartPanel.setBackground(Color.white);
	        
	        return chartPanel;
	}
	
	private JPanel setLegendPanel(double[] benfordArray) {
        JPanel legendPanel = new JPanel(new GridLayout(0,1));
        legendPanel.setBackground(Color.white);
        legendPanel.setBorder(BorderFactory.createTitledBorder("Percentage"));
   
    
        for (int i = 1; i < benfordArray.length; i++) {
        	String msg = String.format("%d = %.1f", i, benfordArray[i]);
        	JLabel label = new JLabel(msg + "%");
        	label.setFont(new Font("Serif", Font.BOLD, 20));
        	legendPanel.add(label, BorderLayout.CENTER);
        }
        
        return legendPanel;
	}
	
	private JPanel setGraphNLegandPanel(double[] benfordArray) {
		ChartPanel chartPanel = setChartPanel(benfordArray);
		JPanel digitPanel = setLegendPanel(benfordArray);
        JPanel glpanel = new JPanel();
        glpanel.setBackground(Color.white);
        glpanel.add(chartPanel);
        glpanel.add(digitPanel);
		
		return glpanel;
	}
	
	private JPanel setMainPanel(double[] benfordArray) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white);
        
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0; 

        mainPanel.add(setTitlePanel(), c);
   
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1; 
        mainPanel.add(setGraphNLegandPanel(benfordArray), c);
        return mainPanel;
        
	}
	
	
	
    public void plotGraph(double[] benfordArray) {
    
        add(setMainPanel(benfordArray));
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



}
