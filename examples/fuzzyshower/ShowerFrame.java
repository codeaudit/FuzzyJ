/*
	This simple extension of the java.awt.Frame class
	contains all the elements necessary to act as the
	main window of an application.
 */


/** 
 * This is a very simple example of a 'simulated' shower with a hot and a cold
 * valve to control the flow and temperature in a shower. The simulation
 * is extremely crude and does not take into account delays in mixing
 * and travel of the hot and cold water or delays required to move the
 * valves. So we have perfect mixing and perfect and immediate results
 * to changes in the valve positions.The intent is to show a fuzzy system that
 * with a few rules that do not 'cover' the space well, can still do a reasonable
 * job of controlling the shower. Ideally we would have more terms for the 
 * shower temperature and pressure, therefore more rules, and we would
 * monitor the behaviour of the system so that it could tune the rules
 * based on performance of the system. For example, if the pressures of the
 * hot and cold rise, the adjustments will affect bigger system responses
 * so the rules should be tempered to account for the new sensitivity.
 *
 */
package examples.fuzzyshower;

import java.awt.*;

import symantec.itools.awt.HorizontalSlider;
import symantec.itools.awt.Label3D;
import symantec.itools.awt.ImagePanel;
import nrc.fuzzy.*;
import symantec.itools.awt.util.spinner.NumericSpinner;
public class ShowerFrame extends Frame
{
    InferenceCycleFrame inferenceCycleResults;
    ShowerSimulate showerSimulator;
    
    String applicationDirectory = ""; // directory where shower.gif file is
    
	public ShowerFrame(String title, String appDir)
	{
		applicationDirectory = appDir;
		
		// This code is automatically generated by Visual Cafe when you add
		// components to the visual environment. It instantiates and initializes
		// the components. To modify the code, only use code syntax that matches
		// what Visual Cafe can generate, or Visual Cafe may be unable to back
		// parse your Java file into its visual environment.
		
		//{{INIT_CONTROLS
		setLayout(null);
		setBackground(java.awt.Color.lightGray);
		setForeground(java.awt.Color.black);
//		setSize(681,538);
		setSize(710,560);
		setVisible(false);
		try {
			hotPressureSlider.setMaxValue(100);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotPressureSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotPressureSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotPressureSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(hotPressureSlider);
		hotPressureSlider.setBounds(48,96,130,48);
		try {
			hotValveSlider.setMaxValue(99);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotValveSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotValveSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotValveSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(hotValveSlider);
		hotValveSlider.setBounds(48,216,130,48);
		try {
			hotTempSlider.setMaxValue(100);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotTempSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotTempSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotTempSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(hotTempSlider);
		hotTempSlider.setBounds(48,324,130,48);
		try {
			coldPressureSlider.setMaxValue(99);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldPressureSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldPressureSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldPressureSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(coldPressureSlider);
		coldPressureSlider.setBounds(540,96,130,48);
		try {
			coldValveSlider.setMaxValue(99);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldValveSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldValveSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldValveSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(coldValveSlider);
		coldValveSlider.setBounds(540,216,130,48);
		try {
			coldTempSlider.setMaxValue(99);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldTempSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldTempSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldTempSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(coldTempSlider);
		coldTempSlider.setBounds(540,324,130,48);
		try {
			showerTempSlider.setMaxValue(72);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerTempSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerTempSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerTempSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		showerTempSlider.setEnabled(false);
		add(showerTempSlider);
		showerTempSlider.setBounds(216,12,108,48);
		try {
			showerFlowSlider.setMaxValue(24);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerFlowSlider.setMinValue(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerFlowSlider.setShowBorder(false);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerFlowSlider.setTickStyle(symantec.itools.awt.HorizontalSlider.TICK_NONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		showerFlowSlider.setEnabled(false);
		add(showerFlowSlider);
		showerFlowSlider.setBounds(396,12,108,48);
		try {
			hotPressureLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotPressureLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(hotPressureLabel3D);
		hotPressureLabel3D.setBounds(12,108,32,20);
		try {
			hotValveLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotValveLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(hotValveLabel3D);
		hotValveLabel3D.setBounds(12,228,32,20);
		try {
			hotTempLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			hotTempLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(hotTempLabel3D);
		hotTempLabel3D.setBounds(12,336,32,20);
		try {
			coldPressureLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldPressureLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(coldPressureLabel3D);
		coldPressureLabel3D.setBounds(504,108,32,20);
		try {
			coldValveLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldValveLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(coldValveLabel3D);
		coldValveLabel3D.setBounds(504,228,32,20);
		try {
			coldTempLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			coldTempLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(coldTempLabel3D);
		coldTempLabel3D.setBounds(504,336,32,20);
		try {
			showerTempLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerTempLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(showerTempLabel3D);
		showerTempLabel3D.setBounds(180,24,32,20);
		try {
			showerFlowLabel3D.setBevelStyle(symantec.itools.awt.Label3D.BEVEL_LOWERED);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			showerFlowLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(showerFlowLabel3D);
		showerFlowLabel3D.setBounds(360,24,32,20);
		label1.setText("Hot Pressure (kPa)");
		label1.setAlignment(java.awt.Label.CENTER);
		add(label1);
		label1.setForeground(java.awt.Color.red);
		label1.setBounds(50,144,118,22);
		label2.setText("Hot Valve (%)");
		label2.setAlignment(java.awt.Label.CENTER);
		add(label2);
		label2.setForeground(java.awt.Color.red);
		label2.setBounds(50,264,108,22);
		label3.setText("Hot Temperature (C)");
		label3.setAlignment(java.awt.Label.CENTER);
		add(label3);
		label3.setForeground(java.awt.Color.red);
		label3.setBounds(40,372,128,22);
		label4.setText("Cold Pressure (kPa)");
		label4.setAlignment(java.awt.Label.CENTER);
		add(label4);
		label4.setForeground(java.awt.Color.blue);
		label4.setBounds(530,144,120,22);
		label5.setText("Cold Temperature (C)");
		label5.setAlignment(java.awt.Label.CENTER);
		add(label5);
		label5.setForeground(java.awt.Color.blue);
		label5.setBounds(530,372,140,22);
		label6.setText("Cold Valve (%)");
		label6.setAlignment(java.awt.Label.CENTER);
		add(label6);
		label6.setForeground(java.awt.Color.blue);
		label6.setBounds(552,264,108,22);
		label7.setText(" Shower Temperature (C)");
		label7.setAlignment(java.awt.Label.RIGHT);
		add(label7);
		label7.setFont(new Font("Dialog", Font.BOLD, 12));
		label7.setBounds(156,60,168,22);
		label8.setText("Shower Flow (litres/min)");
		label8.setAlignment(java.awt.Label.RIGHT);
		add(label8);
		label8.setFont(new Font("Dialog", Font.BOLD, 12));
		label8.setBounds(360,60,144,22);
		try {
			noticeLabel3D.setText("Manual Mode: Set Temperatures and Pressures of Hot and Cold feeds and select auto for fuzzy control");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			noticeLabel3D.setBorderIndent(symantec.itools.awt.Label3D.INDENT_ONE);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			noticeLabel3D.setAlignStyle(symantec.itools.awt.Label3D.ALIGN_LEFT);
		}
		catch(java.beans.PropertyVetoException e) { }
		add(noticeLabel3D);
		noticeLabel3D.setFont(new Font("Dialog", Font.BOLD, 12));
		noticeLabel3D.setBounds(20,504,670,25);
		autoCheckbox.setLabel("auto Fuzzy Control");
		add(autoCheckbox);
		autoCheckbox.setBounds(320,456,160,24);
		try {
			java.net.URL url;
			if (applicationDirectory.equals(new String("")))
			{    url = symantec.itools.net.RelativeURL.getURL("shower.gif");
			 	 if (!url.toString().contains("examples/fuzzyshower"))
			 		url = symantec.itools.net.RelativeURL.getURL("examples/fuzzyshower/shower.gif");
			}
			else
			    url = new java.net.URL("file:/" + applicationDirectory + "shower.gif");
			imagePanel1.setImageURL(url);
		}
		catch (java.net.MalformedURLException error) 
		{ System.out.println("*** ERROR *** " + error); }
		catch(java.beans.PropertyVetoException e) { }
		try {
			imagePanel1.setStyle(symantec.itools.awt.ImagePanel.IMAGE_SCALED_TO_FIT);
		}
		catch(java.beans.PropertyVetoException e) { }
		imagePanel1.setLayout(null);
		add(imagePanel1);
		imagePanel1.setBounds(192,108,288,300);
		label9.setText("Millisecs between inference cycles");
		add(label9);
		label9.setBounds(15,456,220,22);
		msSleepTextField.setText("0");
		add(msSleepTextField);
		msSleepTextField.setBounds(245,450,45,29);
		showFiringsCheckbox.setLabel("show rule firings");
		add(showFiringsCheckbox);
		showFiringsCheckbox.setBounds(515,456,140,22);
		setTitle("Fuzzy Shower Demo");
		//}}
		
		//{{INIT_MENUS
		menu1.setLabel("File");
		menu1.add(miExit);
		miExit.setLabel("Exit");
		mainMenuBar.add(menu1);
		menu3.setLabel("Help");
		menu3.add(miAbout);
		miAbout.setLabel("About..");
		mainMenuBar.add(menu3);
		mainMenuBar.setHelpMenu(menu3);
		//$$ mainMenuBar.move(0,420);
		setMenuBar(mainMenuBar);
		//}}
		
		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		SymAction lSymAction = new SymAction();
		miAbout.addActionListener(lSymAction);
		miExit.addActionListener(lSymAction);
		SymPropertyChange lSymPropertyChange = new SymPropertyChange();
		hotPressureSlider.addPropertyChangeListener(lSymPropertyChange);
		hotPressureSlider.addActionListener(lSymAction);
		hotValveSlider.addPropertyChangeListener(lSymPropertyChange);
		hotValveSlider.addActionListener(lSymAction);
		hotTempSlider.addActionListener(lSymAction);
		hotTempSlider.addPropertyChangeListener(lSymPropertyChange);
		coldPressureSlider.addActionListener(lSymAction);
		coldPressureSlider.addPropertyChangeListener(lSymPropertyChange);
		coldValveSlider.addActionListener(lSymAction);
		coldValveSlider.addPropertyChangeListener(lSymPropertyChange);
		coldTempSlider.addPropertyChangeListener(lSymPropertyChange);
		coldTempSlider.addActionListener(lSymAction);
		showerTempSlider.addPropertyChangeListener(lSymPropertyChange);
		showerTempSlider.addActionListener(lSymAction);
		showerFlowSlider.addPropertyChangeListener(lSymPropertyChange);
		showerFlowSlider.addActionListener(lSymAction);
		SymItem lSymItem = new SymItem();
		autoCheckbox.addItemListener(lSymItem);
		showFiringsCheckbox.addItemListener(lSymItem);
		showerTempLabel3D.addPropertyChangeListener(lSymPropertyChange);
		showerFlowLabel3D.addPropertyChangeListener(lSymPropertyChange);
		//}}
		
		setTitle(title);
	}
	
	public ShowerFrame(String title)
	{
		this(title,"");
	}
	
    /**
     * Shows or hides the component depending on the boolean flag b.
     * @param b  if true, show the component; otherwise, hide the component.
     * @see java.awt.Component#isVisible
     */
    public void setVisible(boolean b)
	{
		if(b)
		{
			setLocation(50, 50);
		}	
		super.setVisible(b);
	}
	
	static public void main(String args[])
	{   // args is either empty or it has a string identifiying
		// where the directory is for the shower.gif file
		String appDir = "";
		if (args.length > 0)
		{	appDir = args[0];
		}
	    ShowerFrame showerFrame = new ShowerFrame("", appDir);
	    showerFrame.setVisible(true);

	    showerFrame.inferenceCycleResults = new InferenceCycleFrame();
	    showerFrame.showerSimulator = new ShowerSimulate(showerFrame);
	    showerFrame.showerSimulator.initSliders();
		showerFrame.repaint();
	}
	
	public void setNoticeLabel(String text)
	{
	    try
	    {   noticeLabel3D.setText(text);
	    }
	    catch (Exception e)
	    {   System.err.println("Error setting notice label.\n" +e);
	    }
	}
	
			
	public void addNotify()
	{
		// Record the size of the window prior to calling parents addNotify.
		Dimension d = getSize();
		
		super.addNotify();
	
		if (fComponentsAdjusted)
			return;
	
		// Adjust components according to the insets
		setSize(insets().left + insets().right + d.width, insets().top + insets().bottom + d.height);
		Component components[] = getComponents();
		for (int i = 0; i < components.length; i++)
		{
			Point p = components[i].getLocation();
			p.translate(insets().left, insets().top);
			components[i].setLocation(p);
		}
		fComponentsAdjusted = true;
	}
	
	// Used for addNotify check.
	boolean fComponentsAdjusted = false;
	
	//{{DECLARE_CONTROLS
	symantec.itools.awt.HorizontalSlider hotPressureSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.HorizontalSlider hotValveSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.HorizontalSlider hotTempSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.HorizontalSlider coldPressureSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.HorizontalSlider coldValveSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.HorizontalSlider coldTempSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.HorizontalSlider showerTempSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.HorizontalSlider showerFlowSlider = new symantec.itools.awt.HorizontalSlider();
	symantec.itools.awt.Label3D hotPressureLabel3D = new symantec.itools.awt.Label3D();
	symantec.itools.awt.Label3D hotValveLabel3D = new symantec.itools.awt.Label3D();
	symantec.itools.awt.Label3D hotTempLabel3D = new symantec.itools.awt.Label3D();
	symantec.itools.awt.Label3D coldPressureLabel3D = new symantec.itools.awt.Label3D();
	symantec.itools.awt.Label3D coldValveLabel3D = new symantec.itools.awt.Label3D();
	symantec.itools.awt.Label3D coldTempLabel3D = new symantec.itools.awt.Label3D();
	symantec.itools.awt.Label3D showerTempLabel3D = new symantec.itools.awt.Label3D();
	symantec.itools.awt.Label3D showerFlowLabel3D = new symantec.itools.awt.Label3D();
	java.awt.Label label1 = new java.awt.Label();
	java.awt.Label label2 = new java.awt.Label();
	java.awt.Label label3 = new java.awt.Label();
	java.awt.Label label4 = new java.awt.Label();
	java.awt.Label label5 = new java.awt.Label();
	java.awt.Label label6 = new java.awt.Label();
	java.awt.Label label7 = new java.awt.Label();
	java.awt.Label label8 = new java.awt.Label();
	symantec.itools.awt.Label3D noticeLabel3D = new symantec.itools.awt.Label3D();
	java.awt.Checkbox autoCheckbox = new java.awt.Checkbox();
	symantec.itools.awt.ImagePanel imagePanel1 = new symantec.itools.awt.ImagePanel();
	java.awt.Label label9 = new java.awt.Label();
	java.awt.TextField msSleepTextField = new java.awt.TextField();
	java.awt.Checkbox showFiringsCheckbox = new java.awt.Checkbox();
	//}}
	
	//{{DECLARE_MENUS
	java.awt.MenuBar mainMenuBar = new java.awt.MenuBar();
	java.awt.Menu menu1 = new java.awt.Menu();
	java.awt.MenuItem miExit = new java.awt.MenuItem();
	java.awt.Menu menu3 = new java.awt.Menu();
	java.awt.MenuItem miAbout = new java.awt.MenuItem();
	//}}
	
	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == ShowerFrame.this)
				Frame1_WindowClosing(event);
		}
	}
	
	void Frame1_WindowClosing(java.awt.event.WindowEvent event)
	{
		setVisible(false);	// hide the Frame
		dispose();			// free the system resources
		System.exit(0);		// close the application
	}
	
	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
            if (object == miAbout)
				miAbout_Action(event);
			else if (object == miExit)
				miExit_Action(event);
			else if (object == hotPressureSlider)
				hotPressureSlider_actionPerformed(event);
			else if (object == hotValveSlider)
				hotValveSlider_actionPerformed(event);
			else if (object == hotTempSlider)
				hotTempSlider_actionPerformed(event);
			else if (object == coldPressureSlider)
				coldPressureSlider_actionPerformed(event);
			else if (object == coldValveSlider)
				coldValveSlider_actionPerformed(event);
			else if (object == coldTempSlider)
				coldTempSlider_actionPerformed(event);
			else if (object == showerTempSlider)
				showerTempSlider_actionPerformed(event);
			else if (object == showerFlowSlider)
				showerFlowSlider_actionPerformed(event);
		}
	}
	
	void miAbout_Action(java.awt.event.ActionEvent event)
	{
		//{{CONNECTION
		// Action from About Create and show as modal
		(new AboutDialog(this, true)).setVisible(true);
		//}}
	}
	
	void miExit_Action(java.awt.event.ActionEvent event)
	{
		//{{CONNECTION
		// Action from Exit Create and show as modal
		(new QuitDialog(this, true)).setVisible(true);
		//}}
	}
	

	class SymPropertyChange implements java.beans.PropertyChangeListener
	{
		public void propertyChange(java.beans.PropertyChangeEvent event)
		{
			Object object = event.getSource();
			if (object == hotPressureSlider)
				hotPressureSlider_propertyChange(event);
			else if (object == hotValveSlider)
				hotValveSlider_propertyChange(event);
			else if (object == hotTempSlider)
				hotTempSlider_propertyChange(event);
			else if (object == coldPressureSlider)
				coldPressureSlider_propertyChange(event);
			else if (object == coldValveSlider)
				coldValveSlider_propertyChange(event);
			else if (object == coldTempSlider)
				coldTempSlider_propertyChange(event);
			else if (object == showerTempSlider)
				showerTempSlider_propertyChange(event);
			else if (object == showerFlowSlider)
				showerFlowSlider_propertyChange(event);
		}
	}

	void hotPressureSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ hotPressureLabel3D.setText(String.valueOf(hotPressureSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	void hotPressureSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ 
    		// keep pressure between 35 and 90
    		double hotPressure = (double)hotPressureSlider.getValue();
    		if (hotPressure < 35.0)
    		{ hotPressure = 35.0;
    		  hotPressureSlider.setValue((int)Math.round(hotPressure));
    		}
    		if (hotPressure > 90.0)
    		{ hotPressure = 90.0;
    		  hotPressureSlider.setValue((int)Math.round(hotPressure));
    		}
		    hotPressureLabel3D.setText(String.valueOf(hotPressureSlider.getValue()));
    		if (showerSimulator != null) 
    			showerSimulator.setHotPressure(hotPressure);
		}
		catch (Exception e)
		{ }		
		if (showerSimulator != null && autoCheckbox.getState() == false)
		    showerSimulator.setOutFlowAndOutTemp();
		else
		    showerSimulator.simulateShower();
		repaint();
	}

	void hotValveSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ 
		    hotValveLabel3D.setText(String.valueOf(hotValveSlider.getValue()));
		}
		catch (Exception e)
		{ }
	}


	void hotValveSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ 
		    if (autoCheckbox.getState() == false)
		    {   // manual mode ... set outFlow and outTemp
		        double hotValve = hotValveSlider.getValue()/100.0;
    		    if (showerSimulator != null) 
    		    { showerSimulator.setHotValve(hotValve);
    		      showerSimulator.setOutFlowAndOutTemp();
    		    }
    		}
		    else
		        // auto mode ... can't set the valve position manually
		        hotValveSlider.setValue((int)Math.round(showerSimulator.getHotValve()*100.0));
		    
		    hotValveLabel3D.setText(String.valueOf(hotValveSlider.getValue()));
		}
		catch (Exception e)
		{ }
		repaint();
	}

	void hotTempSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ 		
    		// keep temp between 5 and 100
    		double hotTemp = (double)hotTempSlider.getValue();
    		if (hotTemp < 5.0)
    		{ hotTemp = 5.0;
    		  hotTempSlider.setValue((int)Math.round(hotTemp));
    		}
    		if (hotTemp > 100.0)
    		{ hotTemp = 100.0;
    		  hotTempSlider.setValue((int)Math.round(hotTemp));
    		}
    		if (showerSimulator != null) 
    			showerSimulator.setHotTemp(hotTemp);
		    hotTempLabel3D.setText(String.valueOf(hotTempSlider.getValue()));
		}
		catch (Exception e)
		{ }
		
		if (showerSimulator != null && autoCheckbox.getState() == false)
		    showerSimulator.setOutFlowAndOutTemp();
		else
		    showerSimulator.simulateShower();
		repaint();
	}

	void hotTempSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ hotTempLabel3D.setText(String.valueOf(hotTempSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	void coldPressureSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ 
    		// keep pressure between 30 and 90
    		double coldPressure = (double)coldPressureSlider.getValue();
    		if (coldPressure < 35.0)
    		{ coldPressure = 35.0;
    		  coldPressureSlider.setValue((int)Math.round(coldPressure));
    		}
    		if (coldPressure > 90.0)
    		{ coldPressure = 90.0;
    		  coldPressureSlider.setValue((int)Math.round(coldPressure));
    		}
    		if (showerSimulator != null)
    			showerSimulator.setColdPressure(coldPressure);
		    coldPressureLabel3D.setText(String.valueOf(coldPressureSlider.getValue()));
		}
		catch (Exception e)
		{ }

		if (showerSimulator != null && autoCheckbox.getState() == false)
		    showerSimulator.setOutFlowAndOutTemp();
		else
		    showerSimulator.simulateShower();
		repaint();
	}

	void coldPressureSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ coldPressureLabel3D.setText(String.valueOf(coldPressureSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	void coldValveSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ 
		    if (autoCheckbox.getState() == false)
		    {   // manual mode ... set outFlow and outTemp
		        double coldValve = coldValveSlider.getValue()/100.0;
    		    if (showerSimulator != null)
    		    { showerSimulator.setColdValve(coldValve);
    		      showerSimulator.setOutFlowAndOutTemp();
    		    }
    		    repaint();
    		}
		    else
		        // auto mode ... can't set the valve position manually
		        coldValveSlider.setValue((int)Math.round(showerSimulator.getColdValve()*100.0));
		    
		    coldValveLabel3D.setText(String.valueOf(coldValveSlider.getValue()));
		}
		catch (Exception e)
		{ }
		repaint();
	}

	void coldValveSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ coldValveLabel3D.setText(String.valueOf(coldValveSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	void coldTempSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ coldTempLabel3D.setText(String.valueOf(coldTempSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	void coldTempSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ 
    		// keep temp between 5 and 100
    		double coldTemp = (double)coldTempSlider.getValue();
    		if (coldTemp < 5.0)
    		{ coldTemp = 5.0;
    		  coldTempSlider.setValue((int)Math.round(coldTemp));
    		}
    		if (coldTemp > 65.0)
    		{ coldTemp = 65.0;
    		  coldTempSlider.setValue((int)Math.round(coldTemp));
    		}
    		if (showerSimulator != null)
    			showerSimulator.setColdTemp(coldTemp);
		    coldTempLabel3D.setText(String.valueOf(coldTempSlider.getValue()));
    	}
		catch (Exception e)
		{ }

		if (showerSimulator != null && autoCheckbox.getState() == false)
		    showerSimulator.setOutFlowAndOutTemp();
		else
		    showerSimulator.simulateShower();
		repaint();
	}

	void showerTempSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ showerTempLabel3D.setText(String.valueOf(showerTempSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	void showerTempSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ showerTempLabel3D.setText(String.valueOf(showerTempSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	void showerFlowSlider_propertyChange(java.beans.PropertyChangeEvent event)
	{
		// to do: code goes here.
		try
		{ showerFlowLabel3D.setText(String.valueOf(showerFlowSlider.getValue()));
		}
		catch (Exception e)
		{ }
	}

	void showerFlowSlider_actionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
		try
		{ showerFlowLabel3D.setText(String.valueOf(showerFlowSlider.getValue()));}
		catch (Exception e)
		{ }
	}

	class SymItem implements java.awt.event.ItemListener
	{
		public void itemStateChanged(java.awt.event.ItemEvent event)
		{
			Object object = event.getSource();
			if (object == autoCheckbox)
				autoCheckbox_ItemStateChanged(event);
			else if (object == showFiringsCheckbox)
				showFiringsCheckbox_ItemStateChanged(event);
		}
	}

	void autoCheckbox_ItemStateChanged(java.awt.event.ItemEvent event)
	{
		// to do: code goes here.
		boolean state = autoCheckbox.getState();
		
		try
		{
    		if (state)
    		{   // auto mode
                setNoticeLabel("Auto Mode:  Fuzzy Control in effect");
                if (showerSimulator != null) 
                	showerSimulator.simulateShower();
    		}
    		else
    		{   // manual mode
                setNoticeLabel("Manual Mode: Set Temperatures and Pressures of Hot and Cold feeds and select auto for fuzzy control");
            }
        }
        catch (Exception e)
        {}
		repaint();		
	}

	void showFiringsCheckbox_ItemStateChanged(java.awt.event.ItemEvent event)
	{
		// to do: code goes here.
		if (showFiringsCheckbox.getState())
		    inferenceCycleResults.setVisible(true);
		else
		    inferenceCycleResults.setVisible(false);
	}

}
