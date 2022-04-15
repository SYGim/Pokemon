package DB;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Pframe {

	// ������ ��ü ����
    Frame frame = new Frame("Pokemon�� ���� ������");
    
    // ��ư, ����Ʈ ��Ʈ�� ����
    List randomList = new List();
    List pokemonList = new List();
    Button loadBtn = new Button("Pokemon�� �̱�");
    
    public void createFrame()
    {
        // ���̾ƿ� �Ŵ����� ������� �ʱ�
        frame.setLayout(null);
        
        // ������ ũ�� ����
        frame.setSize(720, 480);
        
        // ���� ��ư�� ������ �Ҵ��Ѵ�.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        // ������Ʈ ũ��, ��ġ ����
        loadBtn.setBounds(40,400,300,40);
        randomList.setBounds(40,80,300,300);
        pokemonList.setBounds(380, 80, 300, 300);
        
        // ��ư �̺�Ʈ ����
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            	pokemonList.removeAll(); // ����Ʈ ������ ���� �����Ѵ�.
                
                try{
                    Connection con = null;
                    String id = "scott";
                    String pw = "1234";
                    
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:BTDB", id, pw);
                    
                    // Statement�� ���� SQL���� �����ϰ� ����� ��ȯ�ޱ� ���� ��ü��.
                    //Statement�ϳ��� �Ѱ��� ResultSet ��ü���� �� �� �ִ�.
                    java.sql.Statement st = null;
                    ResultSet result = null;
                    st = con.createStatement();
                    st.execute("use /*DB �̸�*/;"); // ����� DB�� �����Ѵ�.
                    // executeQuery : ������ �����ϰ� ����� ResultSet ��ü�� ��ȯ�Ѵ�.
                    result = st.executeQuery("/*�����͸� �޾ƿ� ����*/");
                    
                    // ����� �ϳ��� ����Ѵ�.
                    while (result.next()){
                        String str = result.getNString(1);
                        pokemonList.add(str); // ����Ʈ�� �����͸� �߰��Ѵ�.
                    }
                }catch(SQLException sqle){
                    System.out.println("SQLException: " + sqle.getMessage());
                    System.out.println("SQLState: " + sqle.getSQLState());
                }
            }
        });
                
        //�����ӿ� ������Ʈ �߰�
        frame.add(loadBtn);
        frame.add(randomList);
        frame.add(pokemonList);
        
        //������ ���̱�
        frame.setVisible(true);
    }
}
