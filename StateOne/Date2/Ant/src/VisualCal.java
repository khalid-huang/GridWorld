import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class VisualCal extends JFrame {
	//声明相关变量
	private double firstValue, secondValue, result;
	private String symbol;
	private JPanel plExpress = new JPanel(new GridLayout(1, 5, 1, 2));
	private JPanel plSymbol = new JPanel(new GridLayout(1, 5, 1, 2));
	private Border border = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));
	//private DecimalFormat df = new DecimalFormat("#.000");
	//private Font font = new Font("宋体", Font.BOLD, 20);
	//定义按钮
	private JButton
		btWhichSymbol = new JButton(),
		btEqual = new JButton("="),
		btAdd = new JButton("+"),
		btSub = new JButton("-"),
		btDiv = new JButton("/"),
		btMul = new JButton("*"),
		btOk = new JButton("OK");
	//定义数字区域
	private JTextField 
		tfFirstValue = new JTextField(""),
		tfSecondValue = new JTextField(""),
		tfResultValue = new JTextField("");
	//tfResultValue.setFont(new Font("宋体", Font.BOLD, 16));

	public VisualCal(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//组装表达式面板
		createPlExpress();
		//组装运算符号面板并加上响应
		createPlSymbol();
		//加入JFrame中
		tfFirstValue.setHorizontalAlignment(JTextField.CENTER);
		tfSecondValue.setHorizontalAlignment(JTextField.CENTER);
		tfResultValue.setHorizontalAlignment(JTextField.CENTER);
		plExpress.setBorder(border);
		plSymbol.setBorder(border);
		Container container = getContentPane();
		container.setLayout(new GridLayout(2,1));
		container.add(plExpress);
		container.add(plSymbol);
		setSize(500, 200);
		setVisible(true);
	}

	public void createPlExpress() {
		//限制数字框只可以输入数字
		plExpress.add(tfFirstValue);
		plExpress.add(btWhichSymbol);
		plExpress.add(tfSecondValue);
		plExpress.add(btEqual);
		plExpress.add(tfResultValue);
	}

	public void createPlSymbol() {
		//加入组件
		plSymbol.add(btAdd);
		plSymbol.add(btSub);
		plSymbol.add(btMul);
		plSymbol.add(btDiv);
		plSymbol.add(btOk);
		//加入响应
		btAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				symbol = "+";
				btWhichSymbol.setText("+");
			}
		});
		
		btSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				symbol = "-";
				btWhichSymbol.setText("-");
			}
		});

		btMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				symbol = "*";
				btWhichSymbol.setText("*");
			}
		});

		btDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				symbol = "/";
				btWhichSymbol.setText("/");
			}
		});
		
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//判断是否是合法输入
				if(!Pattern.matches("^(-)?[0-9]+(.[0-9]+)?$",tfFirstValue.getText()) || !Pattern.matches("^(-)?[0-9]+(.[0-9]+)?$", tfSecondValue.getText())) {
					JOptionPane.showMessageDialog(null, "Please input number");
					return;
				}
				//result = mathCal.add(firstValue, secondValue);
				firstValue = Double.parseDouble(tfFirstValue.getText());
				secondValue = Double.parseDouble(tfSecondValue.getText());
				switch(symbol) {
					case "+":
						result = firstValue + secondValue;
						break;
					case "-":
						result = firstValue - secondValue;
						break;
					case "*":
						result = firstValue * secondValue;
						break;
					case "/":
						try {
							if(secondValue == 0) {
								JOptionPane.showMessageDialog(null,"div zero");
								return;
							}
							result = firstValue / secondValue;
							break;
						} catch (ArithmeticException e) { 
							JOptionPane.showMessageDialog(null, "除数是零");
						}
				}
				//tfResultValue.setText("" + df.format(result));
				//java.math.BigDecimal bd = new java.math.BigDecimal(result);
				tfResultValue.setText(result + "");
				tfResultValue.setCaretPosition(0);
			}
		});
	}

	public static void main(String[] args) {
		new VisualCal("This is a simple calculation");
	}
}

