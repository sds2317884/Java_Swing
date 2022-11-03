package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class UserSelectJDailog extends JDialog implements ActionListener{
	JTextField t1;
	private static JButton searchbtn;
	private static JButton cancelbtn;
	private static JComboBox combo;
	
	
	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();
	
	MenuJTabaleExam me;
	
	
	public UserSelectJDailog(MenuJTabaleExam me) {
		super(me,"다이어로그");
		this.me = me;
		
		getContentPane().setLayout(null);
		
		
		String[] comboName = {"  ALL  ","  s_id  ", " s_name ", " s_gender "," s_tel "," s_email " };
		combo = new JComboBox(comboName);
		combo.setFont(new Font("굴림", Font.BOLD, 18));
		combo.setBounds(80, 42, 127, 23);
		getContentPane().add(combo);
		
		t1 = new JTextField();
		t1.setBounds(43, 98, 208, 29);
		getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton searchbtn = new JButton("찾기");
		searchbtn.setBackground(Color.CYAN);
		searchbtn.setFont(new Font("굴림", Font.BOLD, 21));
		searchbtn.setBounds(43, 151, 86, 39);
		getContentPane().add(searchbtn);
		
		JButton cancelbtn = new JButton("취소");
		cancelbtn.setFont(new Font("굴림", Font.BOLD, 21));
		cancelbtn.setBackground(Color.RED);
		cancelbtn.setBounds(165, 151, 86, 39);
		getContentPane().add(cancelbtn);
		
		setSize(300,250);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		searchbtn.addActionListener(this);
		cancelbtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand(); // 이벤트 주체 대한 라벨가져오기 
		
		if (btnLabel.equals("찾기")) {
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 "+fieldName);
			
			if (fieldName.trim().equals("ALL")) {
				dao.userSelectAll(me.dt);
				if (me.dt.getRowCount()>0) {
					me.jt.setRowSelectionInterval(0, 0);
					dispose();
				}
			}
			else {
				
			if (t1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "검색단어를 입력해주세요");
			t1.requestFocus();
		} 
		else { //검색어를 입력 했을 경우 
			dao.getUserSearch(me.dt, fieldName,t1.getText());
			if (me.dt.getRowCount() >0) {
				me.jt.setRowSelectionInterval(0,0);
			dispose();
			}
		}
		
			
			}
	}else if (btnLabel.equals("취소")) {
		dispose();
	}
		}
}