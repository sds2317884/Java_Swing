package main;

//MenuJTabaleExam.java
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class MenuJTabaleExam extends JFrame implements ActionListener {
	JButton insert = new JButton("등록");
	JButton update = new JButton("수정");
	JButton delete = new JButton("삭제");
	JButton quit = new JButton("종료");
	JButton select = new JButton("조회");

	JTextField t_name = new JTextField();
	JTextField t_id = new JTextField();
	JTextField t_email = new JTextField();
	JTextField t_tel01 = new JTextField();
	JTextField t_tel02 = new JTextField();
	JTextField t_tel03 = new JTextField();

	JLabel lable_Name = new JLabel("이름");
	JLabel lable_Id = new JLabel("학번");
	JLabel lable_email = new JLabel("이메일");
	JLabel lable_tel = new JLabel("연락처");
	JLabel lable_telText01 = new JLabel("-");
	JLabel lable_telTex02 = new JLabel("-");
	JLabel lable_gender = new JLabel("성별");
	JRadioButton female = new JRadioButton("여");
	JRadioButton male = new JRadioButton("남");

	
	String[] name = { "학번", "이름", "성별", "연락처", "이메일" };

	DefaultTableModel dt = new DefaultTableModel(name, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);

	/*
	 * South 영역에 추가할 Componet들
	 */
	JPanel p = new JPanel();
	String[] comboName = { "  ALL  ", "  s_id  ", " s_name ", " tel " };

	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(10);
	JButton serach = new JButton("검색");

	UserDefaultJTableDAO dao = new UserDefaultJTableDAO();

	/**
	 * 화면구성 및 이벤트등록
	 */
	public MenuJTabaleExam() {

		super("학사관리시스템 - DB연동");

		// 메뉴아이템을 메뉴에 추가
		getContentPane().add(insert);
		getContentPane().add(update);
		getContentPane().add(delete);
		getContentPane().add(quit);
		getContentPane().add(select);
		
		p.setBounds(0, 506, 750, 39);

		insert.setBounds(6, 465, 117, 29);
		update.setBounds(133, 465, 117, 29);
		delete.setBounds(262, 465, 117, 29);
		quit.setBounds(403, 465, 117, 29);
		select.setBounds(530,465,117,29);

		// South영역
		p.setBackground(Color.yellow);
		p.add(combo);
		p.add(jtf);
		p.add(serach);
		getContentPane().setLayout(null);
		jsp.setBounds(0, 211, 750, 185);

		getContentPane().add(jsp);
		getContentPane().add(p);

		lable_Name.setHorizontalAlignment(SwingConstants.CENTER);
		lable_Id.setHorizontalAlignment(SwingConstants.CENTER);
		lable_email.setHorizontalAlignment(SwingConstants.CENTER);
		lable_tel.setHorizontalAlignment(SwingConstants.CENTER);
		lable_telText01.setHorizontalAlignment(SwingConstants.CENTER);
		lable_telTex02.setHorizontalAlignment(SwingConstants.CENTER);
		lable_gender.setHorizontalAlignment(SwingConstants.CENTER);

		lable_Name.setBounds(30, 25, 61, 16);
		lable_Id.setBounds(317, 25, 61, 16);
		lable_email.setBounds(30, 71, 61, 16);
		lable_tel.setBounds(30, 132, 61, 16);
		lable_telText01.setBounds(184, 132, 28, 16);
		lable_telTex02.setBounds(275, 132, 28, 16);
		lable_gender.setBounds(317, 71, 61, 16);

		t_name.setBounds(120, 20, 130, 26);
		t_id.setBounds(407, 20, 130, 26);
		t_email.setBounds(120, 66, 130, 26);
		t_tel01.setBounds(120, 127, 61, 26);
		t_tel02.setBounds(213, 127, 61, 26);
		t_tel03.setBounds(305, 127, 61, 26);
		female.setBounds(485, 67, 52, 23);
		male.setBounds(417, 67, 52, 23);

		t_name.setColumns(10);
		t_id.setColumns(10);
		t_email.setColumns(10);
		t_tel01.setColumns(10);
		t_tel02.setColumns(10);
		t_tel03.setColumns(10);

		getContentPane().add(lable_Name);
		getContentPane().add(lable_Id);
		getContentPane().add(lable_email);
		getContentPane().add(lable_tel);
		getContentPane().add(lable_telText01);
		getContentPane().add(lable_telTex02);
		getContentPane().add(lable_gender);
		getContentPane().add(t_name);
		getContentPane().add(t_id);
		getContentPane().add(t_email);
		getContentPane().add(t_tel01);
		getContentPane().add(t_tel02);
		getContentPane().add(t_tel03);
		getContentPane().add(female);
		getContentPane().add(male);
		
		ButtonGroup genderBtn = new ButtonGroup();
		genderBtn.add(female);
		genderBtn.add(male);

		setSize(750, 600);
		setVisible(true);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 이벤트등록
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(t_id.getText());
				String gender = null;
				String tel = (t_tel01.getText() + "-" + t_tel02.getText() + "-" + t_tel03.getText());
				
				if(male.isSelected()) {
					gender = "남";
				}else {
					gender = "여";
				}
				UserDefaultJTableDAO userDefaultJTableDAO = new UserDefaultJTableDAO();
				userDefaultJTableDAO.userListInsert(id, t_name.getText(), gender, tel, t_email.getText());
				dao.userSelectAll(dt); //새로고침
				
				
				t_id.setText("");
				t_name.setText("");
				t_tel01.setText("");
				t_tel02.setText("");
				t_tel03.setText("");
				t_email.setText("");
				
			}
		});

		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		serach.addActionListener(this);
		select.addActionListener(this);

		// 모든레코드를 검색하여 DefaultTableModle에 올리기
		dao.userSelectAll(dt);

		// 첫번행 선택.
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);

	}// 생성자끝

	/**
	 * main메소드 작성
	 */
	public static void main(String[] args) {
		new MenuJTabaleExam();
	}

	/**
	 * 가입/수정/삭제/검색기능을 담당하는 메소드
	 */

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == update) {// 수정 메뉴아이템 클릭
			new UserJDailogGUI(this, "수정");

		} else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
			// 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
			int row = jt.getSelectedRow();
			System.out.println("선택행 : " + row);

			Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
			System.out.println("값 : " + obj);

			if (dao.userDelete(obj.toString()) > 0) {
				UserJDailogGUI.messageBox(this, "레코드 삭제되었습니다.");

				// 리스트 갱신
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);

			} else {
				UserJDailogGUI.messageBox(this, "레코드가 삭제되지 않았습니다.");
			}

		} else if (e.getSource() == quit) {// 종료 메뉴아이템 클릭
			System.exit(0);

		} else if(e.getSource() == select) { // 조회 버튼 클릭
			new UserSelectJDailog(this);
		}
		else if (e.getSource() == serach) {// 검색 버튼 클릭
			// JComboBox에 선택된 value 가져오기
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 " + fieldName);

			if (fieldName.trim().equals("ALL")) {// 전체검색
				dao.userSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					UserJDailogGUI.messageBox(this, "검색단어를 입력해주세요!");
					jtf.requestFocus();
				} else {// 검색어를 입력했을경우
					dao.getUserSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			}
		}

	}// actionPerformed()----------
}