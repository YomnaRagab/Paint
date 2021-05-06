package finalApplet;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.awt.event.MouseEvent;

public class FinalApplet extends Applet {
	Color c = Color.BLACK;
	int dott = 0;
	int fill = 0;

	static class LineData {
		static int x_l[] = { 0 };
		static int y_l[] = { 0 };
		static Color line_color[] = { Color.BLACK };
		static int line_dott[] = { 0 };
	}

	static class OvalData {
		static int x_o[] = { 0 };
		static int y_o[] = { 0 };
		static Color oval_color[] = { Color.BLACK };
		static int oval_filled[] = { 0 };
	}

	static class RectData {
		static int x_r[] = { 0 };
		static int y_r[] = { 0 };
		static Color rect_color[] = { Color.BLACK };
		static int rect_filled[] = { 0 };
	}

	static class EraserData {
		static int x_e[] = { 0 };
		static int y_e[] = { 0 };

	}

	static class FreeHandData {
		static int x_f[] = { 0 };
		static int y_f[] = { 0 };
		static Color freehand_color[] = { Color.black };

	}

	int counter_l = 0;
	int counter_o = 0;
	int counter_r = 0;
	int counter_e = 0;
	int counter_f = 0;
	char option = 'z';
	int x_start, y_start, x_end, y_end;
	ColorButtons button = new ColorButtons();
	ShapesButtons shapes = new ShapesButtons();
	Features features = new Features();

	public void init() {
		add(button.red);
		add(button.green);
		add(button.blue);
		add(shapes.line);
		add(shapes.oval);
		add(shapes.rect);
		add(features.dotted);
		add(features.filled);
		add(features.clearAll);
		add(features.eraser);
		add(features.freehand);
		this.addMouseListener(new AppletListener());
		this.addMouseMotionListener(new AppletMotionListener());
	}

	class AppletMotionListener implements MouseMotionListener {
		Graphics g = getGraphics();
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

			g.setColor(c);
			x_end = e.getX();
			y_end = e.getY();
			switch (option) {
			case 'l':
				//g.fillOval(x_end, y_end, 1, 1);
				g.drawLine(x_start, y_start, x_end, y_end);
				break;
			case 'r':
				g.drawRect(x_start, y_start, x_end - x_start, y_end - y_start);
				break;
			case 'o':
				g.drawOval(x_start, y_start, x_end - x_start, y_end - y_start);
				break;
			case 'e':
				g.setColor(Color.white);
				g.fillOval(e.getX(), e.getY(), 20, 20);
				EraserData.x_e = Arrays.copyOf(EraserData.x_e, EraserData.x_e.length + 1);
				EraserData.y_e = Arrays.copyOf(EraserData.y_e, EraserData.y_e.length + 1);
				EraserData.x_e[counter_e] = e.getX();
				EraserData.y_e[counter_e] = e.getY();
				counter_e++;
				break;
			case 'f':
				g.fillOval(e.getX(), e.getY(), 20, 20);
				FreeHandData.x_f = Arrays.copyOf(FreeHandData.x_f, FreeHandData.x_f.length + 1);
				FreeHandData.y_f = Arrays.copyOf(FreeHandData.y_f, FreeHandData.y_f.length + 1);
				FreeHandData.freehand_color = (Color[]) Arrays.copyOf(FreeHandData.freehand_color,
						FreeHandData.freehand_color.length + 1);
				FreeHandData.x_f[counter_f] = e.getX();
				FreeHandData.y_f[counter_f] = e.getY();
				FreeHandData.freehand_color[counter_f] = c;
				counter_f++;
				break;

			}

		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub


	}
	}

	class AppletListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent e) {
			x_start = e.getX();
			y_start = e.getY();
			switch (option) {
			case 'l':
				LineData.x_l = Arrays.copyOf(LineData.x_l, LineData.x_l.length + 1);
				LineData.y_l = Arrays.copyOf(LineData.y_l, LineData.y_l.length + 1);
				LineData.x_l[counter_l] = e.getX();
				LineData.y_l[counter_l] = e.getY();
				counter_l++;
				break;
			case 'o':
				OvalData.x_o = Arrays.copyOf(OvalData.x_o, OvalData.x_o.length + 1);
				OvalData.y_o = Arrays.copyOf(OvalData.y_o, OvalData.y_o.length + 1);
				OvalData.x_o[counter_o] = e.getX();
				OvalData.y_o[counter_o] = e.getY();
				counter_o++;
				break;
			case 'r':
				RectData.x_r = Arrays.copyOf(RectData.x_r, RectData.x_r.length + 1);
				RectData.y_r = Arrays.copyOf(RectData.y_r, RectData.y_r.length + 1);
				RectData.x_r[counter_r] = e.getX();
				RectData.y_r[counter_r] = e.getY();
				counter_r++;
				break;
			}
		}

		public void mouseReleased(MouseEvent e) {
			switch (option) {
			case 'l':
				LineData.x_l = Arrays.copyOf(LineData.x_l, LineData.x_l.length + 1);
				LineData.y_l = Arrays.copyOf(LineData.y_l, LineData.y_l.length + 1);
				LineData.x_l[counter_l] = e.getX();
				LineData.y_l[counter_l] = e.getY();
				LineData.line_color = (Color[]) Arrays.copyOf(LineData.line_color, LineData.line_color.length + 1);
				LineData.line_color[counter_l / 2] = c;
				LineData.line_dott = (int[]) Arrays.copyOf(LineData.line_dott, LineData.line_dott.length + 1);
				LineData.line_dott[counter_l / 2] = dott;
				counter_l++;
				break;
			case 'o':
				OvalData.x_o = Arrays.copyOf(OvalData.x_o, OvalData.x_o.length + 1);
				OvalData.y_o = Arrays.copyOf(OvalData.y_o, OvalData.y_o.length + 1);
				OvalData.x_o[counter_o] = e.getX();
				OvalData.y_o[counter_o] = e.getY();
				OvalData.oval_color = (Color[]) Arrays.copyOf(OvalData.oval_color, OvalData.oval_color.length + 1);
				OvalData.oval_color[counter_o / 2] = c;
				OvalData.oval_filled = Arrays.copyOf(OvalData.oval_filled, OvalData.oval_filled.length + 1);
				OvalData.oval_filled[counter_o / 2] = fill;
				counter_o++;
				break;
			case 'r':
				RectData.x_r = Arrays.copyOf(RectData.x_r, RectData.x_r.length + 1);
				RectData.y_r = Arrays.copyOf(RectData.y_r, RectData.y_r.length + 1);
				RectData.x_r[counter_r] = e.getX();
				RectData.y_r[counter_r] = e.getY();
				RectData.rect_color = (Color[]) Arrays.copyOf(RectData.rect_color, RectData.rect_color.length + 1);
				RectData.rect_color[counter_r / 2] = c;
				RectData.rect_filled = Arrays.copyOf(RectData.rect_filled, RectData.rect_filled.length + 1);
				RectData.rect_filled[counter_r / 2] = fill;

				counter_r++;
				break;
			}
			repaint();

		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class ColorButtons {
		public Button red, green, blue;

		public ColorButtons() {
			red = new Button("Red");
			green = new Button("Green");
			blue = new Button("Blue");
			red.addActionListener(new RedButton());
			green.addActionListener(new GreenButton());
			blue.addActionListener(new BlueButton());
		}

		class RedButton implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				c = Color.RED;
			}
		}

		class GreenButton implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				c = Color.GREEN;
			}
		}

		class BlueButton implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				c = Color.BLUE;
			}
		}
	}

	class ShapesButtons {
		public Button rect, oval, line;

		public ShapesButtons() {
			rect = new Button("Rectangle");
			oval = new Button("Oval");
			line = new Button("Line");
			rect.addActionListener(new RectListener());
			oval.addActionListener(new OvalListener());
			line.addActionListener(new LineListener());
		}

		class LineListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				option = 'l';
			}
		}

		class OvalListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				option = 'o';
			}
		}

		class RectListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				option = 'r';
			}
		}

	}
	
	class Features {
		public Checkbox dotted;
		public Checkbox filled;
		public Button clearAll;
		public Button eraser;
		public Button freehand;

		public Features() {
			dotted = new Checkbox("Dotted");
			dotted.addItemListener(new DottedFeature());
			filled = new Checkbox("Filled");
			filled.addItemListener(new FilledFeature());
			clearAll = new Button("Clear All");
			clearAll.addActionListener(new ClearAll());
			eraser = new Button("Eraser");
			eraser.addActionListener(new Eraser());
			freehand = new Button("Free Hand");
			freehand.addActionListener(new FreeHand());

		}

		class FreeHand implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				option = 'f';
			}

		}

		class Eraser implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				option = 'e';
			}

		}

		class ClearAll implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				Graphics g = getGraphics();

				Dimension d = getSize();

				Color c = getBackground();

				g.setColor(c);

				g.fillRect(0, 0, d.width, d.height);

//				 Arrays.fill(LineData.x_l, 0);
//				 Arrays.fill(LineData.y_l , 0);
//				 Arrays.fill(LineData.line_color ,Color.BLACK);
//				 Arrays.fill(LineData.line_dott ,0);
//				 Arrays.fill(OvalData.x_o , 0);
//				 Arrays.fill(OvalData.y_o , 0);
//				 Arrays.fill(OvalData.oval_color , Color.BLACK);
//				 Arrays.fill(OvalData.oval_filled , 0);
//				 Arrays.fill(RectData.x_r , 0);
//				 Arrays.fill(RectData.y_r , 0);
//				 Arrays.fill(RectData.rect_color , Color.BLACK);
//				 Arrays.fill(RectData.rect_filled , 0);

//				repaint();

				LineData.x_l = Arrays.copyOf(LineData.x_l, 0);
				LineData.y_l = Arrays.copyOf(LineData.y_l, 0);
				LineData.line_color = (Color[]) Arrays.copyOf(LineData.line_color, 0);
				LineData.line_dott = (int[]) Arrays.copyOf(LineData.line_dott, 0);
				OvalData.x_o = Arrays.copyOf(OvalData.x_o, 0);
				OvalData.y_o = Arrays.copyOf(OvalData.y_o, 0);
				OvalData.oval_color = (Color[]) Arrays.copyOf(OvalData.oval_color, 0);
				OvalData.oval_filled = Arrays.copyOf(OvalData.oval_filled, 0);
				RectData.x_r = Arrays.copyOf(RectData.x_r, 0);
				RectData.y_r = Arrays.copyOf(RectData.y_r, 0);
				RectData.rect_color = (Color[]) Arrays.copyOf(RectData.rect_color, 0);
				RectData.rect_filled = Arrays.copyOf(RectData.rect_filled, 0);
				EraserData.x_e = Arrays.copyOf(EraserData.x_e, 0);
				EraserData.y_e = Arrays.copyOf(EraserData.y_e, 0);

				FreeHandData.x_f = Arrays.copyOf(FreeHandData.x_f, 0);
				FreeHandData.y_f = Arrays.copyOf(FreeHandData.y_f, 0);
				FreeHandData.freehand_color = (Color[]) Arrays.copyOf(FreeHandData.freehand_color, 0);
				counter_r = 0;
				counter_l = 0;
				counter_o = 0;
				counter_e = 0;
				counter_f = 0;

				repaint();

			}

		}

		class FilledFeature implements ItemListener {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					fill = 1;
				} else {
					fill = 0;
				}

			}

		}

		class DottedFeature implements ItemListener {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					dott = 1;
				} else {
					dott = 0;
				}

			}

		}
	}

	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2) {

		// creates a copy of the Graphics instance
		Graphics2D g2d = (Graphics2D) g.create();

		// set the stroke of the copy, not the original
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
		g2d.setStroke(dashed);
		g2d.drawLine(x1, y1, x2, y2);

		// gets rid of the copy
		g2d.dispose();
	}

	public void paint(Graphics g) {
		// g.setColor(c);

		for (int i = 0; i < OvalData.x_o.length - 1; i += 2) {
			g.setColor(OvalData.oval_color[i / 2]);
			if (OvalData.oval_filled[i / 2] == 0)
				g.drawOval(OvalData.x_o[i], OvalData.y_o[i], OvalData.x_o[i + 1] - OvalData.x_o[i],
						OvalData.y_o[i + 1] - OvalData.y_o[i]);
			else
				g.fillOval(OvalData.x_o[i], OvalData.y_o[i], OvalData.x_o[i + 1] - OvalData.x_o[i],
						OvalData.y_o[i + 1] - OvalData.y_o[i]);

		}

		for (int i = 0; i < RectData.x_r.length - 1; i += 2) {
			g.setColor(RectData.rect_color[i / 2]);
			if (RectData.rect_filled[i / 2] == 0)
				g.drawRect(RectData.x_r[i], RectData.y_r[i], RectData.x_r[i + 1] - RectData.x_r[i],
						RectData.y_r[i + 1] - RectData.y_r[i]);
			else
				g.fillRect(RectData.x_r[i], RectData.y_r[i], RectData.x_r[i + 1] - RectData.x_r[i],
						RectData.y_r[i + 1] - RectData.y_r[i]);

		}
		for (int i = 0; i < LineData.x_l.length - 1; i += 2) {
			g.setColor(LineData.line_color[i / 2]);
			if (LineData.line_dott[i / 2] == 0)
				g.drawLine(LineData.x_l[i], LineData.y_l[i], LineData.x_l[i + 1], LineData.y_l[i + 1]);
			else {
				drawDashedLine(g, LineData.x_l[i], LineData.y_l[i], LineData.x_l[i + 1], LineData.y_l[i + 1]);
			}
		}

		for (int i = 0; i < FreeHandData.x_f.length - 1; i++) {
			g.setColor(FreeHandData.freehand_color[i]);
			g.fillOval(FreeHandData.x_f[i], FreeHandData.y_f[i], 20, 20);
		}
		for (int i = 0; i < EraserData.x_e.length - 1; i++) {
			g.setColor(Color.WHITE);
			g.fillOval(EraserData.x_e[i], EraserData.y_e[i], 20, 20);
		}
	}
}
