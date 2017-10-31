import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class ToolPanel extends JPanel
{
	Model model;
	JToggleButton btnSelect;
	JToggleButton btnErase;
	JToggleButton btnLine;
	JToggleButton btnRect;
	JToggleButton btnOval;
	JToggleButton btnFill;

	ToolPanel(Model m)
	{
		model = m;

		setLayout(new GridLayout(3, 2));
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));

		// create button icons, then create toggle buttons with the proper icon

		ImageIcon selectIcon = new ImageIcon("selection.png");
		Image selectImg = selectIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		selectIcon = new ImageIcon(selectImg);
		btnSelect = new JToggleButton(selectIcon);

		ImageIcon eraseIcon = new ImageIcon("eraser.png");
		Image eraseImg = eraseIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		eraseIcon = new ImageIcon(eraseImg);
		btnErase = new JToggleButton(eraseIcon);

		ImageIcon lineIcon = new ImageIcon("line.png");
		Image lineImg = lineIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		lineIcon = new ImageIcon(lineImg);
		btnLine = new JToggleButton(lineIcon);

		ImageIcon squareIcon = new ImageIcon("square.png");
		Image squareImg = squareIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		squareIcon = new ImageIcon(squareImg);
		btnRect = new JToggleButton(squareIcon);

		ImageIcon ovalIcon = new ImageIcon("circle.png");
		Image ovalImg = ovalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ovalIcon = new ImageIcon(ovalImg);
		btnOval = new JToggleButton(ovalIcon);

		ImageIcon fillIcon = new ImageIcon("fill.png");
		Image fillImg = fillIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		fillIcon = new ImageIcon(fillImg);
		btnFill = new JToggleButton(fillIcon);

		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				model.currTool = Tool.SELECT;
				model.currShape = null;
				repaint();
			}
		});
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				model.currTool = Tool.ERASE;
				model.currShape = null;
				repaint();
			}
		});
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				model.currTool = Tool.LINE;
				model.currShape = null;
				repaint();
			}
		});
		btnRect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				model.currTool = Tool.RECT;
				model.currShape = null;
				repaint();
			}
		});
		btnOval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				model.currTool = Tool.OVAL;
				model.currShape = null;
				repaint();
			}
		});
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				model.currTool = Tool.FILL;
				model.currShape = null;
				repaint();
			}
		});

		add(btnSelect);
		add(btnErase);
		add(btnLine);
		add(btnRect);
		add(btnOval);
		add(btnFill);

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
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Tool tool = model.currTool;

		if (tool == Tool.SELECT)
		{
			btnSelect.setSelected(true);
		}
		else
		{
			btnSelect.setSelected(false);
		}

		if (tool == Tool.ERASE)
		{
			btnErase.setSelected(true);
		}
		else
		{
			btnErase.setSelected(false);
		}

		if (tool == Tool.LINE)
		{
			btnLine.setSelected(true);
		}
		else
		{
			btnLine.setSelected(false);
		}

		if (tool == Tool.RECT)
		{
			btnRect.setSelected(true);
		}
		else
		{
			btnRect.setSelected(false);
		}

		if (tool == Tool.OVAL)
		{
			btnOval.setSelected(true);
		}
		else
		{
			btnOval.setSelected(false);
		}

		if (tool == Tool.FILL)
		{
			btnFill.setSelected(true);
		}
		else
		{
			btnFill.setSelected(false);
		}
	}
}