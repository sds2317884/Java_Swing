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

public class JoinMember extends JFrame {

	public JoinMember() {
		JPanel p = new JPanel();
		setLocationRelativeTo(p);
		Label l1 = new Label("아이디");
		l1.setAlignment(Label.CENTER);
		Label l2 = new Label("비밀번호");
		l2.setAlignment(Label.CENTER);
		Label l3 = new Label("이름");
		l3.setAlignment(Label.CENTER);
		Label l4 = new Label("연락처");
		l4.setAlignment(Label.CENTER);
		
		getContentPane().add(l1);
		getContentPane().add(l2);
		getContentPane().add(l3);
		getContentPane().add(l4);

		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		getContentPane().add(t1);
		getContentPane().add(t2);
		getContentPane().add(t3);
		getContentPane().add(t4);
		t2.setEchoChar('*');

		JButton j1 = new JButton("저장");
		JButton j2 = new JButton("취소");
		JButton j3 = new JButton("ID중복체크");
		getContentPane().add(j1);
		getContentPane().add(j2);
		getContentPane().add(j3);

		l1.setBounds(100, 125, 60, 40);
		l2.setBounds(100, 165, 60, 40);
		l3.setBounds(100, 205, 60, 40);
		l4.setBounds(100, 245, 60, 40);

		t1.setBounds(160, 130, 200, 30);
		t2.setBounds(160, 170, 200, 30);
		t3.setBounds(160, 210, 200, 30);
		t4.setBounds(160, 250, 200, 30);

		j1.setBounds(145, 330, 80, 30);
		j2.setBounds(255, 330, 100, 30);
		j3.setBounds(370, 130, 100, 30);

		getContentPane().add(p);
		setSize(500, 500);
		setTitle("회원가입");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		//login MemberVO 생성
		MemberVO bag = new MemberVO();
		// 저장버튼에 대한 이벤트처리
		j1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 1단계 텍스트 상자의 값을 가지고 온다 2. DB연결하여 3.DB안 넣어주기
				bag.setId(t1.getText());
				bag.setPassword(t2.getText());
				bag.setName(t3.getText());
				bag.setTel(t4.getText());

				//2. DB연결 담당 클래스 MemberDAO()
				MemberDB dao = new MemberDB();
		
                //3. dao안에 데이터를 삽입을 담당할 메소드를 구현하기
				dao.insertMember(bag);
				JOptionPane.showMessageDialog(null, "회원 가입되었습니다. 축하합니다!");
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
		// ID중복체크버튼에 대한 이벤트처리
		j3.addActionListener(new ActionListener() {
			
            MemberDB dao = new MemberDB();
			
            @Override
			public void actionPerformed(ActionEvent e) {
				String id = t1.getText();// 화면에서 id를 입력한 것을 받아서
				if( dao.checkId(id) == true ) { 
					JOptionPane.showMessageDialog(null, "사용중인 아이디입니다");
				    t1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다");
				}
				
			}
		});

	}
}
