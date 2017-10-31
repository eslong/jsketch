import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

abstract class Shapes
{
	public Point2D.Double origin;
	public Point2D.Double endPoint;
	public Color lineColor;
	public int lineThickness;
	public Shape shapeToDraw;

	abstract void translate(double tx, double ty);
}