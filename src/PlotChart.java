

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

/**
 * @author michelle chan
 *
 */
public class PlotChart extends JFrame {

	/**
	 * @return
	 * 
	 * Creates title panel and format
	 */
	private JPanel setTitlePanel () {
	       JPanel titlePanel = new JPanel();
	        titlePanel.setPreferredSize(new Dimension(this.getWidth(), 30));
	        titlePanel.setBackground(Color.blue);
	        // creates a title label to put into panel
	        JLabel titleLabel = new JLabel("Figure 1 -- Benford's Law Distribution Leading Digit");
	        titleLabel.setForeground(Color.WHITE);
	        titlePanel.add(titleLabel, BorderLayout.CENTER);
	        return titlePanel;
	}
	
    /**
     * @param benfordArray
     * @return
     * 
     * Creates a data set
     */
    private CategoryDataset createBenFordDataset(double[] benfordArray) {

    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	// loops through the double array and sets into the data set
    	for (int i = 1; i < benfordArray.length; i++) {
    		dataset.setValue(benfordArray[i], "", String.valueOf(i));
    	}

        return dataset;
    }
	
	
	/**
	 * @param benfordArray
	 * @return
	 * 
	 * Creates graph panel (bar graph)
	 */
	private ChartPanel setChartPanel(double[] benfordArray) {
		// creates a data set 
	        CategoryDataset benfordDataSet = createBenFordDataset(benfordArray);
	        
	        // creates a bar graph based on the data set (x,y)
	        JFreeChart chart = ChartFactory.createBarChart(
	                "",
	                "Digit",
	                "Percent",
	                benfordDataSet,
	                PlotOrientation.VERTICAL,
	                false, true, false);
	        
	        // creates grid in the graph
	        chart.getCategoryPlot().setDomainGridlinePaint(Color.black);
	        chart.getCategoryPlot().setRangeGridlinePaint(Color.black);
	        chart.getCategoryPlot().setDomainGridlinesVisible(true);
	        chart.getCategoryPlot().setRangeGridlinesVisible(true);
	        // creates a chart panel to include the graph
	        ChartPanel chartPanel = new ChartPanel(chart);
	        // sets the area of the graph
	        chartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        chartPanel.setBackground(Color.white);
	        
	        return chartPanel;
	}
	
	/**
	 * @param benfordArray
	 * @return
	 * 
	 * Creates legend panel (percentages)
	 */
	private JPanel setLegendPanel(double[] benfordArray) {
		// creates a single column (grid) legend panel to store the percentages
        JPanel legendPanel = new JPanel(new GridLayout(0,1));
        legendPanel.setBackground(Color.white);
        legendPanel.setBorder(BorderFactory.createTitledBorder("Percentage"));
   
        // creates a set of labels to put into the legend panel
        for (int i = 1; i < benfordArray.length; i++) {
        	String msg = String.format("%d = %.1f", i, benfordArray[i]);
        	JLabel label = new JLabel(msg + "%");
        	label.setFont(new Font("Serif", Font.BOLD, 20));
        	legendPanel.add(label, BorderLayout.CENTER);
        }
        
        return legendPanel;
	}
	
	/**
	 * @param benfordArray
	 * @return
	 * 
	 * Creates a panel containing the graph + legend panels
	 */
	private JPanel setGraphNLegandPanel(double[] benfordArray) {
		ChartPanel chartPanel = setChartPanel(benfordArray);
		JPanel digitPanel = setLegendPanel(benfordArray);
		// creates a panel to store the graph and legend panels 
        JPanel glpanel = new JPanel();
        glpanel.setBackground(Color.white);
        glpanel.add(chartPanel);
        glpanel.add(digitPanel);
		
		return glpanel;
	}
	
	/**
	 * @param benfordArray
	 * @return
	 * 
	 * Creates main panel to house the title + graph + legend panels
	 */
	private JPanel setMainPanel(double[] benfordArray) {
		// creates the main panel and sets it to white 
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white);
        
        // separates the title and graph panels into 2 boxes
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
	
	
	
    /**
     * @param benfordArray
     * 
     * Plots the graph
     */
    public void plotGraph(double[] benfordArray) {
    
        add(setMainPanel(benfordArray));
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



}
