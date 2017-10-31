import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Color;
//import java.io.*;
//import javax.json.*;

public class Model
{
    List<Shapes> shapes;
    Color currColor;
    Tool currTool;
    int lineThickness;
    Shapes currShape;
    Point2D.Double dragStart;
    boolean isDrawing = false;
    double translateX, translateY;
    CanvasPanel canvas;
    String filename;

    Model()
    {
        shapes = new ArrayList<Shapes>();
        currColor = Color.black;
        currTool = Tool.SELECT;
        lineThickness = 4;
    }

    // method that creates a shape once a click is detected and the proper tool is selected
    public void createShape(Point2D.Double p1)
    {
        // create new shape based on the current selected tool
        if (currTool == Tool.LINE)
        {
            currShape = (Shapes)(new Line(p1, p1, currColor, lineThickness));
        }
        else if (currTool == Tool.RECT)
        {
            currShape = (Shapes)(new Rectangle(p1, p1, currColor, lineThickness));
        }
        else if (currTool == Tool.OVAL)
        {
            currShape = (Shapes)(new Oval(p1, 1, currColor, lineThickness));
        }
         isDrawing = true;
    }

    // method that checks which shape, if any, was selected
    public void checkSelect(Point2D.Double p)
    {
        for (int i = 0; i < shapes.size(); i++)
        {
            if (shapes.get(i) instanceof Line)
            {
                if (shapes.get(i).shapeToDraw.intersects(p.getX() - 10, p.getY() - 10, 20, 20))
                {
                    currShape = shapes.get(i);
                }
            }
            else if (shapes.get(i).shapeToDraw.contains(p))
            {
                currShape = shapes.get(i);
            }
        }
    }

    // method that fills selected shape with current color
    public void checkFill(Point2D.Double p)
    {
        currShape = null;

        for (int i = 0; i < shapes.size(); i++)
        {
            if (shapes.get(i) instanceof Line)
            {
                if (shapes.get(i).shapeToDraw.intersects(p.getX() - 10, p.getY() - 10, 20, 20))
                {
                    currShape = shapes.get(i);
                }
            }
            else if (shapes.get(i).shapeToDraw.contains(p))
            {
                currShape = shapes.get(i);
            }
        }

        if (currShape instanceof Rectangle) 
        {
            Rectangle rect = (Rectangle)currShape;
            rect.setFillColor(currColor);
            currShape = rect;
        }
        else if (currShape instanceof Oval)
        {
            Oval oval = (Oval)currShape;
            oval.setFillColor(currColor);
            currShape = oval;
        }

        currShape = null;
    }

    // method that erases shape if it is clicked
    public void checkErase(Point2D.Double p)
    {
        currShape = null;

        for (int i = 0; i < shapes.size(); i++)
        {
            if (shapes.get(i) instanceof Line)
            {
                if (shapes.get(i).shapeToDraw.intersects(p.getX() - 10, p.getY() - 10, 20, 20))
                {
                    currShape = shapes.get(i);
                }
            }
            else if (shapes.get(i).shapeToDraw.contains(p))
            {
                currShape = shapes.get(i);
            }
        }

        shapes.remove(currShape);
        currShape = null;
    }

    // method that draws shape as mouse is dragged
    public void dragShape(Point2D.Double p2)
    {
        // as mouse is dragged, the endPoint is updated to the current mouse position
        if (currTool == Tool.LINE)
        {
            currShape = new Line(currShape.origin, p2, currColor, lineThickness);
        }
        else if (currTool == Tool.RECT)
        {
            currShape = new Rectangle(getNewOriginRect(p2), getNewEndPointRect(p2), currColor, lineThickness);
        }
        else if (currTool == Tool.OVAL)
        {
            currShape = new Oval(getNewOriginOval(p2), Math.abs(dragStart.getX() - p2.getX()), currColor, lineThickness);
        }
    }

    // get new origin for rectangle being drawn
    public Point2D.Double getNewOriginRect(Point2D.Double p2)
    {
        Point2D.Double newPoint;

        if (p2.getX() < dragStart.getX())
        {
            if (p2.getY() < dragStart.getY())
            {
                return p2;
            }
            else
            {
                newPoint = new Point2D.Double(p2.getX(), dragStart.getY());
                return newPoint;
            }
        }
        else if (p2.getY() < dragStart.getY())
        {
            newPoint = new Point2D.Double(dragStart.getX(), p2.getY());
            return newPoint;
        }
        else
        {
            return dragStart;
        }
    }

    // get endPoint for new rectangle being drawn
    public Point2D.Double getNewEndPointRect(Point2D.Double p2)
    {
        Point2D.Double newPoint;

        if (p2.getX() > dragStart.getX())
        {
            if (p2.getY() > dragStart.getY())
            {
                return p2;
            }
            else
            {
                newPoint = new Point2D.Double(p2.getX(), dragStart.getY());
                return newPoint;
            }
        }
        else if (p2.getY() > dragStart.getY())
        {
            newPoint = new Point2D.Double(dragStart.getX(), p2.getY());
            return newPoint;
        }
        else
        {
            return dragStart;
        }
    }

    // get origin for new circle being drawn
    public Point2D.Double getNewOriginOval(Point2D.Double p2)
    {
        Point2D.Double newPoint;

        if (p2.getX() < dragStart.getX())
        {
            if (p2.getY() < dragStart.getY())
            {
                newPoint = new Point2D.Double(p2.getX(), dragStart.getY() - (dragStart.getX() - p2.getX()));
                return newPoint;
            }
            else
            {
                newPoint = new Point2D.Double(p2.getX(), dragStart.getY());
                return newPoint;
            }
        }
        else if (p2.getY() < dragStart.getY())
        {
            newPoint = new Point2D.Double(dragStart.getX(), dragStart.getY() - (p2.getX() - dragStart.getX()));
            return newPoint;
        }
        else
        {
            return dragStart;
        }
    }

    // get translation amount and translate shape
    public void moveShape(Point2D.Double p2)
    {
        // when dragging a shape, change points by the value of the translation
        translateX = p2.getX() - dragStart.getX();
        translateY = p2.getY() - dragStart.getY();

        currShape.translate(translateX, translateY);
    }

    // add newly drawn shape to array of shapes
    public void addDrawnShape()
    {
        shapes.add(currShape);
        currShape = null;
        isDrawing = false;
    }

    // set origin and endpoints now that the translation is completed
    public void setTranslatedPoints()
    {
        currShape.origin = new Point2D.Double(currShape.origin.getY() + translateX, currShape.origin.getY() + translateY);
        currShape.endPoint = new Point2D.Double(currShape.endPoint.getY() + translateX, currShape.endPoint.getY() + translateY);
    }

    public void saveImage(String newFilename)
    {
        // save shapes array in JSON format for later use

        /*
        filename = newFilename;
        JsonObjectBuilder shapesJsonContainer = Json.createObjectBuilder();
        JsonArrayBuilder shapesJson = Json.createArrayBuilder();

        for (int i = 0; i < shapes.size(); i++)
        {
            JsonObjectBuilder shape = Json.createObjectBuilder();

            String type;
            if (shapes.get(i) instanceof Line)
            {
                type = "Line";
            }
            else if (shapes.get(i) instanceof Rectangle)
            {
                type = "Rectangle";
            }
            else if (shapes.get(i) instanceof Oval)
            {
                type = "Oval";
            }

            shape.add("type", type);
            shape.add("origin-x", new Double(shapes.get(i).origin.getX()));
            shape.add("origin-y", new Double(shapes.get(i).origin.getY()));
            shape.add("end-point-x", new Double(shapes.get(i).endPoint.getX()));
            shape.add("end-point-y", new Double(shapes.get(i).endPoint.getY()));
            shape.add("line-color", new String(shapes.get(i).lineColor.toString()));
            shape.add("line-thickness", new Integer(shapes.get(i).lineThickness));
            if (shapes.get(i).fillColor != null)
            {
                shape.add("fill-color", new String(shapes.get(i).fillColor.toString()));
            }
            shapeJson.add(shape);

            JsonObject obj = shapesJsonContainer.build();

            OutputStream os = new FileOutputStream(filename);
            JsonWrite jsonWriter = Json.createWriter(os);
            jsonWriter.writeObject(obj);
            jsonWriter.close();
        }

        shapesJsonContainer.put("Shapes", shapesJson);

        try 
        {
            FileWriter file = new FileWriter("test.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }*/
    }

    public void loadImage(String newFilename)
    {
        // fill shapes array using JSON data stored by previous images, then redraw new canvas
        /*
        filename = newFilename;
        InputStream input = new FileInputStream(filename);
        JsonReader reader = Json.createReader(filename);


        JsonObject jsonObject = reader.readObject();
        reader.close();
        input.close();

        JsonArray shapesJson = jsonObject.getJsonArray("Shapes");

        shapes = new ArrayLists<Shapes>();

        for (int i = 0; i < shapesJson.length(); i++)
        {
            JsonObject savedShape = shapesJson.getJsonObject(i);

            String type = (String)savedShape.get("type");
            double originX = (double)savedShape.get("origin-x");
            double originY = (double)savedShape.get("origin-y");
            double endPointX = (double)savedShape.get("end-point-x");   
            double endPointX = (double)savedShape.get("end-point-x");
            Color lineColor = Color.decode((String)savedShape.get("line-color"));
            int lineThickness = (int)savedShape.get("line-thickness");
            Color fillColor = Color.decode((String)savedShape.get("fill-color"));

            Point2D.Double origin = new Point2D.Double(originX, originY);
            Point2D.Double endPoint =  new Point2D.Double(endPointX, endPointY);

            switch(type) 
            {
                case("Line"):
                    Line shape = new Line(origin, endPoint, lineColor, lineThickness);
                    break;
                case("Rectangle"):
                    Rectangle shape = new Rectangle(origin, endPoint, lineColor, lineThickness);
                    if (fillColor != null) shape.setFillColor(fillColor);
                    break;
                case("Oval"):
                    Oval shape = new Oval(origin, endPoint, lienColor, lineThickness);
                    if (fillColor != null) shape.setFillColor(fillColor);
                    break;
                default:
                    break;
            }

            shapes.add(shape);
        }*/
    }

    // change current color (called from PalettePanel.java)
    public void changeColor(Color color)
    {
        currColor = color;
        if (currShape != null) currShape.lineColor = currColor;
    }

    // method used to redraw canvas from other panels
    public void reDraw()
    {
        canvas.repaint();
    }
}