main:
	javac JSketch.java
	javac JSketchFrame.java
	javac LayoutPanel.java
	javac PalettePanel.java
	javac ToolPanel.java
	javac LinePanel.java
	javac CanvasPanel.java
	javac Shapes.java
	javac Tool.java
	javac Model.java

run:
	java JSketch

clean:
	rm *.class