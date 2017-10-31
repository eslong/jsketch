import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Rectangle extends Shapes
{
	public Color fillColor;

	Rectangle(Point2D.Double p1, Point2D.Double p2, Color color, int thickness)
	{
		this.origin = p1;
		this.endPoint = p2;
		this.lineColor = color;
		this.lineThickness = thickness;
		shapeToDraw = new Rectangle2D.Double(origin.getX(), origin.getY(), getWidth(), getHeight());
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
		shapeToDraw = new Rectangle2D.Double(origin.getX() + tx, origin.getY() + ty, getWidth(), getHeight());
	}
}