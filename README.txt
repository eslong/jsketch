README file for eslong (20503353) - CS 349, Assignment 2

Files
	- JSketch.java: main file, creates custom frame and layout panel to house all functional panels
	- JSketchFrem.java: custom frame that acts as a view to create and handle the menubar
	- LayoutPanel.java: view that handles all panels that hold the canvas, tool buttons, color palette, and line thickness picker.
	- ToolPanel.java: view with a GridLayout housing the toggle buttons that specify what tool is currently selected
	- Tool.java: enum to identify the tool currenty in use without resorting to a string name
	- PalettePanel.java: view that holds both a GridLayout Jpanel filled with Jlabels that have colored backgrounds and click handlers to create a custom color palette chooser, and a Jbutton that opens a JColorChooser dialog from which more colors can be chosen.
	- LinePanel.java: a view that holds a JSlider that allows the user to pick the desired line thickness between 1 and 3 (in truth, the line thickness will be double the value the user picks, but the sample border drawn will be accurate as to what will be drawn on the canvas); a sample of the line thickness is drawn above the slider
	- CanvasPanel.java: view that handles the drawing of all shapes drawn by the user, as well as all mouse interactions
	- Shapes.java: abstract model for the various shapes that can be drawn by the user
	- Line.java: model for a Line shape drawn by the user, storing all necessary information to draw a line on the canvas
	- Rectangle.java: model for a Rectangle shape drawn by the user, storing all necessary information to draw a Rectangle on the canvas
	- Oval.java: model for an Oval shape drawn by the user, storing all necessary information to draw a Rectangle on the canvas (Note: the name Oval is leftover from when I mistakenly thought we could draw Ellipse and not just perfect circles, and the fields in the class reflect that, but the functionality is there)
	- Model.java: model that handles all interactions with the data to draw the canvas; stores an array of Shapes to know the current shapes on the canvas, which the CanvasPanel view itterates through in order to paint the canvas

Tools
	- Selection tool: allows the user to select a shape on the canvas; selected shapes will have a dashed border instead of a whole border; clicking and dragging a selected shape will allow the user to move it around the canvas; while selected, line thickness and line color can be changed by changing them in the corresponding panels on the toolbar.
	- Erase tool: clicking a shape on the canvas will remove that shape from the canvas
	- Line tool: clicking and dragging will create a line with the currently selected color as the line color and the current line thickness as the thickness of the drawn line; preview line is displayed as the mouse is dragged
	- Rectangle tool: clicking and dragging will draw a rectangle, and this rectangle can be drawn between the original click location and wherever the mouse is currently located on the canvas
	- Oval tool: clicking and dragging will draw a circle with a diameter that is equal to the x-coordinate diference between the initial click point and the current mouse pointer location within the canvas
	- Fill tool: clicking a shape on the canvas (that is not a line) will fill the shape with whatever the currently selected color is.

Missing functionality
	- File new: for some reason, resetting all the panels and the model don't seem to change the state of the application
	- File save/load: issues with use of JSON for saving data impeded development (code is present but commented out since it won't compile)
	- Fit-to-Window/Full Size resizing: I would have used a CardLayout on CanvasPanel to have both a regular JPanel and a ScrollPane to draw the canvas, switching between the two when necessary (both would be functionally identical). Scaling the shapes would be done by storing a constant scaling value by which the canvas could draw the shapes without changing their stored values, merely the way they are drawn. However, poor planning on my part meant I could not implement this in time.
	- ESC to cancel selection: adding a keyListener to every panel doesn't seem to want to trigger the event

Enhancements
	- customisable color palette: right click on any of the 6 palette blocks to open a color chooser dialog and pick the new color for that palette block (if the block was the currently selected color, then it will not longer be selected seeing as the color has changed)

Icon image links:
	- selection.png: https://www.iconfinder.com/icons/37538/cursor_hand_point_pointer_icon
	- eraser.png: http://plainicon.com/download-icon/44043/eraser
	- line.png: http://www.clipartpanda.com/categories/line-20clipart
	- square.png: http://alphaomega-theoretics.wikia.com/wiki/File:Square-outline-256.png
	- circle.png: https://www.roblox.com/Full-Circle-Outline-item?id=41657767
	- fill.png: https://www.iconfinder.com/icons/174644/bucket_paint_icon

Notes:
	- I elected to not disable any controls since I felt my application logic handled any potential issues well anough already