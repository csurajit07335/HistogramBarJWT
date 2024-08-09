import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class HistogramPanel extends JPanel
{
    private int histogramHeight = 600;
    private int barWidth = 70;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private static List<Bar> bars = new ArrayList<Bar>();

    public HistogramPanel()
    {
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();
        Map<Integer,Bar> listBar = new HashMap<>();

        int maxValue = 0;
        String barsLabel = "";
        int barIndex = 0;
        int count = 1;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for(Bar bar: bars) {
            if(bar.getValue() == maxValue) {
                barsLabel = bar.getLabel();
                listBar.put(bars.indexOf(bar),new Bar(barsLabel,maxValue,Color.RED));
            }
        }

        for (Map.Entry<Integer,Bar> entry : listBar.entrySet()) {
            bars.set(entry.getKey(),entry.getValue());
        }



        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
        System.out.println(maxValue);
    }

    private class Bar
    {
        private String label;
        private int value;
        private Color color;

        public Bar(String label, int value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    private static void createAndShowGUI()
    {
         HistogramPanel panel = new HistogramPanel();
        panel.addHistogramColumn("Value1", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value2", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value3", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value4", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value5", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value6", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value7", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value8", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value9", new Random().nextInt(100)+1, Color.BLUE);
        panel.addHistogramColumn("Value10", new Random().nextInt(100)+1, Color.BLUE);
        panel.layoutHistogram();
        panel.setVisible(false);

        JButton quit = new JButton ("Random Array");

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("it worked");

                //panel.setVisible(true);
                bars.clear();
                panel.addHistogramColumn("Value1", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value2", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value3", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value4", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value5", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value6", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value7", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value8", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value9", new Random().nextInt(100)+1, Color.BLUE);
                panel.addHistogramColumn("Value10", new Random().nextInt(100)+1, Color.BLUE);
                panel.layoutHistogram();
                panel.setVisible(true);

            }
        });
        JButton quit1 = new JButton ("Input via Dialog Boxes");

        JPanel card1 = new JPanel();
        JFrame frame = new JFrame("Bar Chart Example");
        JLabel label = new JLabel();

        quit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("it workedss");
                bars.clear();
                for (int i = 1; i <= 10; i++) {
                    int value = Integer.parseInt(JOptionPane.showInputDialog("Enter the value for Value"+i));
                    panel.addHistogramColumn("Value"+i, value, Color.BLUE);


                }
                panel.layoutHistogram();
                panel.setVisible(true);

            }
        });

        card1.add(quit);
        card1.add(quit1);
        JPanel cards = new JPanel(new CardLayout());
        cards.add(card1);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel ,BorderLayout.PAGE_START);
        frame.add(cards,BorderLayout.PAGE_END);
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}