import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainForm extends JFrame implements ActionListener
{
    SpringLayout layout = new SpringLayout();
    //<editor-fold desc="Component Variables">
    JLabel lblTitle;
    JLabel lblWallHeight, lblWallWidth, lblWallArea, lblWallCount;
    JLabel lblOpeningHeight, lblOpeningWidth,lblOpeningsArea,lblOpeningsCount;
    JLabel lblPaintCoats, lblTotalArea;
    JTextField txtWallHeight,txtWallWidth;
    JTextField txtOpeningHeight, txtOpeningWidth;
    JTextField txtPaintCoats;
    JButton btnCalculateWall;
    JButton btnDeductOpening;
    JButton btnCalculateTotal;
    //</editor-fold>
    double wallArea, openingsArea, totalArea;
    int wallCount,openingCount;

    public MainForm()
    {
        setSize(400,400);
        setLocation(600,200);
        getContentPane().setBackground(Color.CYAN);
        setLayout(layout);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });

        BuildWallAreaComponents();
        BuildOpeningsAreaComponents();
        BuiltTotalAreaComponents();
        setVisible(true);
    }

    private void BuiltTotalAreaComponents() {
        lblPaintCoats = UIBuilderLibrary.BuildJLabelWithNorthWestAnchor("Paint Coats",25,250,layout,this);
        add(lblPaintCoats);

        txtPaintCoats = UIBuilderLibrary.BuildJTextFieldInlineBelow(5,5,layout,lblPaintCoats);
        add(txtPaintCoats);

        btnCalculateTotal = UIBuilderLibrary.BuildJButtonInlineBelow(120,25,"Calculate Total",5,this,layout,txtPaintCoats);
        add(btnCalculateTotal);

        lblTotalArea = UIBuilderLibrary.BuildJLabelInlineToRight("Total Area:",10,layout,btnCalculateTotal);
        add(lblTotalArea);
    }

    private void BuildOpeningsAreaComponents() {
        lblOpeningHeight = UIBuilderLibrary.BuildJLabelWithNorthWestAnchor("Opening Height",220,50,layout,this);
        add(lblOpeningHeight);

        txtOpeningHeight = UIBuilderLibrary.BuildJTextFieldInlineBelow(10,5,layout,lblOpeningHeight);
        add(txtOpeningHeight);

        lblOpeningWidth = UIBuilderLibrary.BuildJLabelInlineBelow("Opening Width",5,layout,txtOpeningHeight);
        add(lblOpeningWidth);

        txtOpeningWidth = UIBuilderLibrary.BuildJTextFieldInlineBelow(10,5,layout,lblOpeningWidth);
        add(txtOpeningWidth);

        btnDeductOpening = UIBuilderLibrary.BuildJButtonInlineBelow(130,25,"Deduct Opening",5,this,layout,txtOpeningWidth);
        add(btnDeductOpening);

        lblOpeningsCount = UIBuilderLibrary.BuildJLabelInlineBelow("Openings Deducted:",5,layout,btnDeductOpening);
        add(lblOpeningsCount);

        lblOpeningsArea = UIBuilderLibrary.BuildJLabelInlineBelow("Openings Area:",5,layout,lblOpeningsCount);
        add(lblOpeningsArea);
    }

    private void BuildWallAreaComponents()
    {
        lblTitle = new JLabel("Paint Calculator");
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font("Comic Sans MS",Font.BOLD,30));
        layout.putConstraint(SpringLayout.WEST, lblTitle, 85, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lblTitle, 10, SpringLayout.NORTH, this);
        add(lblTitle);

        lblWallHeight = new JLabel("Wall Height");
        layout.putConstraint(SpringLayout.WEST,lblWallHeight,25,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, lblWallHeight, 50, SpringLayout.NORTH, this);
        add(lblWallHeight);

        txtWallHeight = new JTextField(10);
        layout.putConstraint(SpringLayout.WEST,txtWallHeight,0,SpringLayout.WEST,lblWallHeight);
        layout.putConstraint(SpringLayout.NORTH, txtWallHeight, 5, SpringLayout.SOUTH, lblWallHeight);
        add(txtWallHeight);

        lblWallWidth = new JLabel("Wall Width");
        layout.putConstraint(SpringLayout.WEST,lblWallWidth,0,SpringLayout.WEST,txtWallHeight);
        layout.putConstraint(SpringLayout.NORTH, lblWallWidth, 5, SpringLayout.SOUTH, txtWallHeight);
        add(lblWallWidth);

        txtWallWidth = new JTextField(10);
        layout.putConstraint(SpringLayout.WEST,txtWallWidth,0,SpringLayout.WEST,lblWallWidth);
        layout.putConstraint(SpringLayout.NORTH, txtWallWidth, 5, SpringLayout.SOUTH, lblWallWidth);
        add(txtWallWidth);

        btnCalculateWall = new JButton("Calculate Area");
        btnCalculateWall.addActionListener(this);
        btnCalculateWall.setPreferredSize(new Dimension(120,25));
        layout.putConstraint(SpringLayout.WEST,btnCalculateWall,0,SpringLayout.WEST,txtWallWidth);
        layout.putConstraint(SpringLayout.NORTH, btnCalculateWall, 5, SpringLayout.SOUTH, txtWallWidth);
        add(btnCalculateWall);

        lblWallCount = UIBuilderLibrary.BuildJLabelInlineBelow("Total Walls:",5,layout,btnCalculateWall);
        add(lblWallCount);

        lblWallArea = new JLabel("Total Wall Area: ");
        layout.putConstraint(SpringLayout.WEST,lblWallArea,0,SpringLayout.WEST,lblWallCount);
        layout.putConstraint(SpringLayout.NORTH, lblWallArea, 5, SpringLayout.SOUTH, lblWallCount);
        add(lblWallArea);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnCalculateWall)
        {
            double width = Double.parseDouble(txtWallWidth.getText());
            double height = Double.parseDouble(txtWallHeight.getText());
            wallCount++;
            wallArea += width * height;
            lblWallCount.setText("Walls Added: " + wallCount);
            lblWallArea.setText("Total Wall Area: " + wallArea);
        }
        if (e.getSource() == btnDeductOpening)
        {
            double width = Double.parseDouble(txtOpeningWidth.getText());
            double height = Double.parseDouble(txtOpeningHeight.getText());
            openingCount++;
            openingsArea += width * height;
            lblOpeningsCount.setText("Openings Deducted: " + openingCount);
            lblOpeningsArea.setText("Openings Area: " + openingsArea);
        }
        if (e.getSource() == btnCalculateTotal)
        {
            int coats = Integer.parseInt(txtPaintCoats.getText());
            totalArea = (wallArea - openingsArea) * coats;
            lblTotalArea.setText("Total Area: " + totalArea + "m²");
        }

    }
}
