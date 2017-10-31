import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class LayoutPanel extends JPanel
{
	Model model;
	PalettePanel palette;
    ToolPanel tools;
    LinePanel lines;
    CanvasPanel canvas;
    JPanel toolbars;
	
	LayoutPanel()
	{
		setLayout(new BorderLayout());

		newFile();
	}

	public void newFile()
	{
		// create model
		model = new Model();

    	// create content panels
    	canvas = new CanvasPanel(model);
    	palette =  new PalettePanel(model);
    	tools = new ToolPanel(model);
    	lines = new LinePanel(model);

    	model.canvas = canvas;

    	// create left toolbar container
    	toolbars = new JPanel();
    	toolbars.setLayout(new BoxLayout(toolbars, BoxLayout.Y_AXIS));
    	toolbars.add(tools);
    	toolbars.add(palette);
    	toolbars.add(lines);
    	add(toolbars, BorderLayout.WEST);
    	add(canvas, BorderLayout.CENTER);

    	setFocusable(true);
    	requestFocusInWindow();
	}
}