import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.event.MouseInputAdapter;


public class CanvasPanel extends JPanel
{
	Model model;

	CanvasPanel(Model m)
	{
		model = m;
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
		addMouseListener(new CanvasController());
		addMouseMotionListener(new CanvasController());

		addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent e)
    		{
    			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
    			{
    				model.currShape = null;
    				repaint();
    			}
    		}
    	});

    	repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g.create();

		// paint all shapes stored in the array
		for (int i = 0; i < model.shapes.size(); i++)
		{
			Shapes shape = model.shapes.get(i);
			g2.setStroke(new BasicStroke(shape.lineThickness * 3)); // set line thickness
			g2.setColor(shape.lineColor); // set line color

			draw(g2, shape);
		}

		if (model.currShape != null)
		{
			g2.setStroke(new BasicStroke(model.currShape.lineThickness * 3)); // set line thickness
			g2.setColor(model.currShape.lineColor); // set line color
			draw(g2, model.currShape);
		}
	}

	public void draw(Graphics2D g2, Shapes shape)
	{
		if (shape instanceof Line) // Line
		{
			Line line = (Line)shape;

			if (model.currShape == line && !model.isDrawing)
			{
				g2.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{9}, 0.0f));
			}

			g2.draw(line.shapeToDraw);
		}
		else if (shape instanceof Rectangle) // Rectangle
		{
			Rectangle rect = (Rectangle)shape;

			if (rect.fillColor != null)
			{
				g2.setColor(rect.fillColor);
				g2.fill(rect.shapeToDraw);
				g2.setColor(shape.lineColor); 
			}

			if (model.currShape == rect && !model.isDrawing)
			{
				g2.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{9}, 0.0f));
			}

			g2.draw(rect.shapeToDraw);
		}
		else if (shape instanceof Oval) // Oval
		{
			Oval oval = (Oval)shape;

			if (oval.fillColor != null)
			{
				g2.setColor(oval.fillColor);
				g2.fill(oval.shapeToDraw);
				g2.setColor(shape.lineColor); 
			}

			if (model.currShape == oval && !model.isDrawing)
			{
				g2.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{9}, 0.0f));
			}

			g2.draw(oval.shapeToDraw);
		}
	}

	class CanvasController extends MouseInputAdapter
	{
		// mouse events to handle drawing of shapes
		public void mousePressed(MouseEvent e)
		{
			Point2D.Double p =new Point2D.Double(e.getX(), e.getY());

			if (model.currTool == Tool.SELECT)
			{
				model.checkSelect(new Point2D.Double(e.getX(), e.getY()));
				if (model.currShape == null) return;

				// only set dragStart if the user clicks inside the shape
				if (model.currShape instanceof Line)
		        {
		            if (model.currShape.shapeToDraw.intersects(p.getX() - 10, p.getY() - 10, 20, 20))
		            {
		                model.dragStart = p;
		            }
		        }
		        else if (model.currShape.shapeToDraw.contains(p))
		        {
		            model.dragStart = p;
		        }
			}
			else if (model.currTool == Tool.LINE || model.currTool == Tool.RECT || model.currTool == Tool.OVAL)
			{
				model.dragStart = p;

				model.createShape(new Point2D.Double(e.getX(), e.getY()));
			}
			else if (model.currTool == Tool.ERASE)
			{
				model.checkErase(new Point2D.Double(e.getX(), e.getY()));
			}
			else if (model.currTool == Tool.FILL)
			{
				model.checkFill(new Point2D.Double(e.getX(), e.getY()));
			}

			repaint();
		}

		public void mouseDragged(MouseEvent e)
		{
			if (model.currTool == Tool.SELECT && model.currShape != null && model.dragStart != null)
			{
				model.moveShape(new Point2D.Double(e.getX(), e.getY()));
			}
			else if (model.currTool == Tool.LINE || model.currTool == Tool.RECT || model.currTool == Tool.OVAL)
			{
				model.dragShape(new Point2D.Double(e.getX(), e.getY()));
			}

			repaint();
		}

		public void mouseReleased(MouseEvent e)
		{
			if (model.currTool == Tool.SELECT && model.currShape != null && model.dragStart != null)
			{
				model.setTranslatedPoints();
			}
			else if (model.currTool == Tool.LINE || model.currTool == Tool.RECT || model.currTool == Tool.OVAL)
			{
				model.addDrawnShape();
			}

			model.dragStart = null;

			repaint();
		}
	}
}