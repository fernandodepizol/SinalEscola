package br.com.sinal.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login {

	private JFrame frmLogin;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*try {
			Runtime rt = Runtime.getRuntime();
            
            	
            	Process protesto = rt.exec("cmd /c cd");
				Process proc = rt.exec("cmd /c start C:\\PROGRAMACAO_FERNANDO\\ProjetoSinal2\\derby\\bin\\startNetworkServer.bat");
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/br/com/sinal/imagens/bell.png")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setBounds(83, 77, 56, 16);
		frmLogin.getContentPane().add(lblUsurio);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(136, 74, 167, 22);
		frmLogin.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(83, 119, 56, 16);
		frmLogin.getContentPane().add(lblSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkLogin(txtLogin.getText(), new String(txtSenha.getPassword()))){
					
					
					
					
					
					acessarSistema();
				}else{
					JOptionPane.showMessageDialog(null, "Erro, usuário ou senha!");
					
				}
			}
		});
		btnLogin.setBounds(162, 165, 97, 25);
		frmLogin.getContentPane().add(btnLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(136, 116, 167, 22);
		frmLogin.getContentPane().add(txtSenha);
	}
	
	public boolean checkLogin(String usuario, String senha){
		return usuario.equals("adm") && senha.equals("123");
	}
	public void acessarSistema(){
		
		ProjetoSinal projetoSinal = new ProjetoSinal();
		frmLogin.setVisible(false);
		projetoSinal.setVisible(true);
		
	}
	
}
