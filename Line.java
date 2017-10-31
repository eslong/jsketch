import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Line extends Shapes
{
	Line(Point2D.Double p1, Point2D.Double p2, Color color, int thickness)
	{
		this.origin = p1;
		this.endPoint = p2;
		this.lineColor = color;
		this.lineThickness = thickness;
		shapeToDraw = new Line2D.Double(origin, endPoint);
	}

	public void translate(double tx, double ty)
	{
		Point2D.Double newOrigin = new Point2D.Double(origin.getX() + tx, origin.getY() + ty);
		Point2D.Double newEndPoint = new Point2D.Double(endPoint.getX() + tx, endPoint.getY() + ty);

		shapeToDraw = new Line2D.Double(newOrigin, newEndPoint);
	}
}