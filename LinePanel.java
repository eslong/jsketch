import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.event.*;

public class LinePanel extends JPanel
{
	JSlider slider;
	JPanel lineDrawing;
	Model model;

	LinePanel(Model m)
	{
		model = m;

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));

		slider = new JSlider(JSlider.HORIZONTAL, 1, 3, 2);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider)e.getSource();
				model.lineThickness = (int)source.getValue() * 2;
				lineDrawing.repaint();
				model.reDraw();
			}
		});

		lineDrawing = new JPanel() {
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g.create();

				g2.setStroke(new BasicStroke(slider.getValue() * 2));
				g2.draw(new Line2D.Double(20, 20, 80, 20));
			}
		};
		lineDrawing.setPreferredSize(new Dimension(100, 30));

		add(lineDrawing, BorderLayout.NORTH);
		add(slider, BorderLayout.CENTER);
		setPreferredSize(new Dimension(100, 100));
		setMaximumSize(getPreferredSize());

		addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent e)
    		{
    			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
    			{
    				model.currShape = null;
    				model.reDraw();
    			}
    		}
    	});

		lineDrawing.repaint();
	}
}