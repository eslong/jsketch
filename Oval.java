import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Oval extends Shapes
{
	public Color fillColor;

	Oval(Point2D.Double p1, double diameter, Color color, int thickness)
	{
		this.origin = p1;
		// endPoint is not the best implementation method for this, but remains due to last minute fixes on my part
		// ideally, diameter would be a field for Oval (and Oval would be called Circle)
		this.endPoint = new Point2D.Double(p1.getX() + diameter, p1.getY() + diameter);
		this.lineColor = color;
		this.lineThickness = thickness;
		shapeToDraw = new Ellipse2D.Double(origin.getX(), origin.getY(), getWidth(), getHeight());
	}

	public void setFillColor(Color color)
	{
		fillColor = color;
	}

	public double getWidth()
	{
		return endPoint.getX() - origin.getX();
	}

	public double getHeight()
	{
		return endPoint.getY() - origin.getY();
	}

	public void translate(double tx, double ty)
	{
		shapeToDraw = new Ellipse2D.Double(origin.getX() + tx, origin.getY() + ty, getWidth(), getHeight());
	}
}