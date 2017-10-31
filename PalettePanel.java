import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JColorChooser;

public class PalettePanel extends JPanel
{
	Model model;
	JPanel colorPalette;
	JLabel lblColor0;
	JLabel lblColor1;
	JLabel lblColor2;
	JLabel lblColor3;
	JLabel lblColor4;
	JLabel lblColor5;
	JButton btnChooser;
	java.util.List<Color> paletteColors;

	PalettePanel(Model m)
	{
		model = m;
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));

		colorPalette = new JPanel(new GridLayout(3, 2));
		lblColor0 = new JLabel();
		lblColor1 = new JLabel();
		lblColor2 = new JLabel();
		lblColor3 = new JLabel();
		lblColor4 = new JLabel();
		lblColor5 = new JLabel();
		btnChooser = new JButton("Chooser");
		paletteColors = new ArrayList<Color>(6);
		paletteColors.add(Color.black);
		paletteColors.add(Color.red);
		paletteColors.add(Color.yellow);
		paletteColors.add(Color.blue);
		paletteColors.add(Color.green);
		paletteColors.add(Color.orange);

		lblColor0.setOpaque(true);
		lblColor1.setOpaque(true);
		lblColor2.setOpaque(true);
		lblColor3.setOpaque(true);
		lblColor4.setOpaque(true);
		lblColor5.setOpaque(true);

		lblColor0.setBackground(paletteColors.get(0));
		lblColor1.setBackground(paletteColors.get(1));
		lblColor2.setBackground(paletteColors.get(2));
		lblColor3.setBackground(paletteColors.get(3));
		lblColor4.setBackground(paletteColors.get(4));
		lblColor5.setBackground(paletteColors.get(5));

		lblColor0.setPreferredSize(new Dimension(45, 30));
		lblColor1.setPreferredSize(new Dimension(45, 30));
		lblColor2.setPreferredSize(new Dimension(45, 30));
		lblColor3.setPreferredSize(new Dimension(45, 30));
		lblColor4.setPreferredSize(new Dimension(45, 30));
		lblColor5.setPreferredSize(new Dimension(45, 30));

		lblColor0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					paletteColors.set(0, JColorChooser.showDialog(null, "Pick a color", paletteColors.get(0)));
					lblColor0.setBackground(paletteColors.get(0));
				}
				else
				{
					model.changeColor(paletteColors.get(0));
					model.reDraw();
				}
				repaint();
			}
		});
		lblColor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					paletteColors.set(1, JColorChooser.showDialog(null, "Pick a color", paletteColors.get(1)));
					lblColor1.setBackground(paletteColors.get(1));
				}
				else
				{
					model.changeColor(paletteColors.get(1));
					model.reDraw();
				}
				repaint();
			}
		});
		lblColor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					paletteColors.set(2, JColorChooser.showDialog(null, "Pick a color", paletteColors.get(2)));
					lblColor2.setBackground(paletteColors.get(2));
				}
				else
				{
					model.changeColor(paletteColors.get(2));
					model.reDraw();
				}
				repaint();
			}
		});
		lblColor3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					paletteColors.set(3, JColorChooser.showDialog(null, "Pick a color", paletteColors.get(3)));
					lblColor3.setBackground(paletteColors.get(3));
				}
				else
				{
					model.changeColor(paletteColors.get(3));
					model.reDraw();
				}
				repaint();
			}
		});
		lblColor4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					paletteColors.set(4, JColorChooser.showDialog(null, "Pick a color", paletteColors.get(4)));
					lblColor4.setBackground(paletteColors.get(4));
				}
				else
				{
					model.changeColor(paletteColors.get(4));
					model.reDraw();
				}
				repaint();
			}
		});
		lblColor5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					paletteColors.set(5, JColorChooser.showDialog(null, "Pick a color", paletteColors.get(5)));
					lblColor5.setBackground(paletteColors.get(5));
				}
				else
				{
					model.changeColor(paletteColors.get(5));
					model.reDraw();
				}
				repaint();
			}
		});
		
		colorPalette.add(lblColor0);
		colorPalette.add(lblColor1);
		colorPalette.add(lblColor2);
		colorPalette.add(lblColor3);
		colorPalette.add(lblColor4);
		colorPalette.add(lblColor5);

		btnChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				model.currColor = JColorChooser.showDialog(null, "Pick a color", model.currColor);
				repaint();
			}
		});

		add(colorPalette);
		add(btnChooser);

		setPreferredSize(new Dimension(100, 140));
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

		Color color = model.currColor;

		if (color == paletteColors.get(0))
		{
			lblColor0.setBorder(BorderFactory.createDashedBorder(Color.gray, 3, 3, 2, false));
		}
		else
		{
			lblColor0.setBorder(BorderFactory.createEmptyBorder());
		}

		if (color == paletteColors.get(1))
		{
			lblColor1.setBorder(BorderFactory.createDashedBorder(Color.gray, 3, 3, 2, false));
		}
		else
		{
			lblColor1.setBorder(BorderFactory.createEmptyBorder());
		}

		if (color == paletteColors.get(2))
		{
			lblColor2.setBorder(BorderFactory.createDashedBorder(Color.gray, 3, 3, 2, false));
		}
		else
		{
			lblColor2.setBorder(BorderFactory.createEmptyBorder());
		}

		if (color == paletteColors.get(3))
		{
			lblColor3.setBorder(BorderFactory.createDashedBorder(Color.gray, 3, 3, 2, false));
		}
		else
		{
			lblColor3.setBorder(BorderFactory.createEmptyBorder());
		}

		if (color == paletteColors.get(4))
		{
			lblColor4.setBorder(BorderFactory.createDashedBorder(Color.gray, 3, 3, 2, false));
		}
		else
		{
			lblColor4.setBorder(BorderFactory.createEmptyBorder());
		}

		if (color == paletteColors.get(5))
		{
			lblColor5.setBorder(BorderFactory.createDashedBorder(Color.gray, 3, 3, 2, false));
		}
		else
		{
			lblColor5.setBorder(BorderFactory.createEmptyBorder());
		}
	}
}