package search2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class MemberJDailog extends JDialog implements ActionListener{
	private static JTextField t1;
	private static JButton searchbtn;
	private static JButton cancelbtn;
	private static JComboBox combo;
	
	
	public MemberJDailog() {
		getContentPane().setLayout(null);
		
		
		String[] comboName = {"  ALL  ","  s_id  ", " s_name ", " s_gender "," s_tel "," s_email " };
		combo = new JComboBox(comboName);
		combo.setBounds(95, 10, 76, 23);
		getContentPane().add(combo);
		
		t1 = new JTextField();
		t1.setBounds(34, 66, 208, 29);
		getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton searchbtn = new JButton("찾기");
		searchbtn.setBackground(Color.ORANGE);
		searchbtn.setFont(new Font("굴림", Font.PLAIN, 21));
		searchbtn.setBounds(34, 135, 76, 39);
		getContentPane().add(searchbtn);
		
		JButton cancelbtn = new JButton("취소");
		cancelbtn.setFont(new Font("굴림", Font.PLAIN, 21));
		cancelbtn.setBackground(Color.ORANGE);
		cancelbtn.setBounds(166, 135, 76, 39);
		getContentPane().add(cancelbtn);
		
		setSize(300,250);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchbtn) {
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 "+fieldName);
		}
		if (t1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "검색단어를 입력해주세요");
			t1.requestFocus();
		} else {
			
		}
		
		
	}
}
