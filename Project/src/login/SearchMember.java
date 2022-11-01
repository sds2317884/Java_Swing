package login;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import loginDb.MemberDB;

public class SearchMember extends JFrame {
	
	private String sid;
	private String spw;
	
	public String getSid() {
		return sid;
	}
	
	public void setEmpno(String sid) {
		this.sid = sid;
	}

	
	public SearchMember() {
		JPanel p = new JPanel();
		setLocationRelativeTo(p);
		Label l1 = new Label("이름");
		l1.setAlignment(Label.CENTER);
		Label l2 = new Label("연락처");
		l2.setAlignment(Label.CENTER);
		
		getContentPane().add(l1);
		getContentPane().add(l2);

		TextField t1 = new TextField();
		TextField t2 = new TextField();

		getContentPane().add(t1);
		getContentPane().add(t2);

		JButton j1 = new JButton("확인");
		JButton j2 = new JButton("취소");

		getContentPane().add(j1);
		getContentPane().add(j2);

		l1.setBounds(100, 205, 60, 40);
		l2.setBounds(100, 245, 60, 40);

		t1.setBounds(160, 210, 200, 30);
		t2.setBounds(160, 250, 200, 30);

		j1.setBounds(145, 330, 80, 30);
		j2.setBounds(255, 330, 100, 30);

		getContentPane().add(p);
		setSize(500, 500);
		setTitle("IDPW 찾기");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		MemberVO bag = new MemberVO();
		// 확인버튼에 대한 이벤트처리
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 1단계 텍스트 상자의 값을 가지고 온다 2. DB연결하여 3.DB안 넣어주기
				String name = t1.getText();
				String tel = t2.getText();
	
				//2. DB연결 담당 클래스 MemberDAO()
				MemberDB dao = new MemberDB();
                //3. dao안에 데이터를 삽입을 담당할 메소드를 구현하기
				dao.searchId(tel);
				JOptionPane.showMessageDialog(null, sid);
				Login login = new Login();
				dispose();
			}
		});

		// 취소버튼에 대한 이벤트처리
		j2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 취소버튼을 누르면 회원가입창이 닫히고 로그인화면이 나오게 하기
				Login login = new Login();
				dispose();

			}
		});
	

	}
}
