import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class JSketch
{
    public static JSketchFrame frame;
    public static LayoutPanel layout;

    public static void main(String []args)
	{
        // create the layout panel (create first to pass reference to frame)
        layout = new LayoutPanel();
        layout.setPreferredSize(new Dimension(800, 600));

        // create the window
        frame = new JSketchFrame("JSketch", layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(layout);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
	}
}