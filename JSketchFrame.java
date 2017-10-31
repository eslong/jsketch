import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JSketchFrame extends JFrame
{
    public static LayoutPanel layoutRef;

    JSketchFrame(String name, LayoutPanel layoutRef)
	{
		setTitle(name);
        this.layoutRef = layoutRef;

				// add menu bar to frame
		MenuBar menuBar = new MenuBar();
    	Menu fileMenu = new Menu("File");
    	Menu viewMenu = new Menu("View");

        MenuItem newImage = new MenuItem("New");
        newImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                menuNew();
            }
        });
        MenuItem save = new MenuItem("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooser = new JFileChooser();
                int code = chooser.showOpenDialog(null);
                if (code == JFileChooser.APPROVE_OPTION)
                {
                    File file = chooser.getSelectedFile();
                    String fileName = file.getName();

                    menuSave(fileName);
                }
            }
        });
        MenuItem load = new MenuItem("Load");
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooser = new JFileChooser();
                int code = chooser.showOpenDialog(null);
                if (code == JFileChooser.APPROVE_OPTION)
                {
                    File file = chooser.getSelectedFile();
                    String fileName = file.getName();

                    menuLoad(fileName);
                }
            }
        });

        fileMenu.add(newImage);
        fileMenu.add(save);
        fileMenu.add(load);

    	viewMenu.add(new MenuItem("Full Size"));
    	viewMenu.add(new MenuItem("Fit To Window"));

    	menuBar.add(fileMenu);
    	menuBar.add(viewMenu);

    	if (this.getMenuBar() == null)
    	{
        	this.setMenuBar(menuBar);
    	}

        setMaximumSize(new Dimension(1600, 1200));
        setMinimumSize(new Dimension(500, 400));
	}

    public static void menuNew()
    {
        layoutRef.newFile();
    }

    public static void menuSave(String file)
    {
        layoutRef.model.saveImage(file);
    }

    public static void menuLoad(String file)
    {
        layoutRef.model.loadImage(file);
    }
}