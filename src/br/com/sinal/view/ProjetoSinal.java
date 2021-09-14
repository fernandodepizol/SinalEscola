package br.com.sinal.view;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.netbeans.examples.lib.timerbean.Timer;
import org.netbeans.examples.lib.timerbean.TimerListener;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.sinal.DAO.DAOMusica;
import br.com.sinal.Utitilitarios.DataHora;
import br.com.sinal.dominio.Musica;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import resources.QuartzApp;

public class ProjetoSinal extends JFrame {
	
	
	CadastroSinal cadastroSinal = new CadastroSinal();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMusica;
	
	JLabel lblProximoSinal = new JLabel("Pr\u00F3ximo Sinal: ");
	
	DAOMusica daoMusica = new DAOMusica();

	
	public Musica musicaSelecionada = daoMusica.buscaProximoSinal();
	
	
	
	
	
	

	JFXPanel fxPanel = new JFXPanel();
	
	
	
	//*usado para teste
	//*public String  teste = daoMusica.teste(musicaSelecionada.getMusica());
	
	
	
	String uriString = new File(musicaSelecionada.getMusica()).toURI().toString();
	Media pick = new Media(uriString); 
	MediaPlayer player = new MediaPlayer(pick); 
	
	
	
	/*Date data = new Date();
	DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	String dataFormatada = formato.format(data);*/
	/**
	 * @wbp.nonvisual location=394,254
	 */
	private final Timer timer = new Timer();
	
	
	//DataHora dataHora = new DataHora(new Date());
	//JLabel lblHora = new JLabel(dataHora.diaDaSemana()+", "+dataHora.dataFormatada()+" "+dataHora.horaFormatada());
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ProjetoSinal frame = new ProjetoSinal();
					frame.setVisible(true);
					//QuartzApp.main(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProjetoSinal() {
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProjetoSinal.class.getResource("/br/com/sinal/imagens/bell.png")));
		
		
	
		setTitle("Homero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 587, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		txtMusica = new JTextField();
		txtMusica.setEnabled(false);
		txtMusica.setBounds(24, 41, 439, 22);
		contentPane.add(txtMusica);
		txtMusica.setColumns(10);
		txtMusica.setText(musicaSelecionada.getMusica());
		
		
		JLabel lblNome = new JLabel("Musica:");
		lblNome.setBounds(24, 25, 56, 16);
		contentPane.add(lblNome);
		JButton btnPause = new JButton("Pause");
		JButton btnPlay = new JButton("Play");
		btnPause.setEnabled(false);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		       //System.out.println(musicaSelecionada);
			   btnPlay.setEnabled(false);
			   btnPause.setEnabled(true);
		       player.play();
			}
		});
		btnPlay.setBounds(24, 215, 83, 25);
		contentPane.add(btnPlay);
		
		
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPause.setEnabled(false);
				btnPlay.setEnabled(true);
				player.pause();
			}
		});
		btnPause.setBounds(107, 215, 83, 25);
		contentPane.add(btnPause);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.stop();
				btnPlay.setEnabled(true);
				btnPause.setEnabled(false);
			}
		});
		btnStop.setBounds(190, 215, 83, 25);
		contentPane.add(btnStop);
		
		Button button = new Button("Arquivo...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					musicaSelecionada.setMusica(incluirEnderecoAudio()); 
					txtMusica.setText(musicaSelecionada.getMusica().replace("/", "\\"));
					//FileWriter arquivo = new FileWriter("C:\\Sinal\\arquivo.txt");
					//@SuppressWarnings("resource")
					//PrintWriter gravarArquivo = new PrintWriter(arquivo);
					
					//gravarArquivo.printf(musicaSelecionada.getMusica().replace("/", "\\"));
					
					
					//arquivo.close();
					selecionaMusica(musicaSelecionada.getMusica());
					player.stop();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(469, 39, 79, 24);
		contentPane.add(button);
		
		 
		JLabel lblHora = new JLabel();
		
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 23));
		
		timer.start();
		timer.addTimerListener(new TimerListener() {
			Musica sinalAtual = null;
			public void onTime(ActionEvent arg0) {
				
				DataHora dataHora = new DataHora(new Date());
				
				 lblHora.setText(dataHora.diaDaSemana()+", "+dataHora.dataFormatada()+" "+dataHora.horaFormatada());
				 lblProximoSinal.setText("Pr\u00F3ximo Sinal: "+musicaSelecionada.getHorario().substring(0,5));
				 //Musica sinalAtual = null;
				 if(musicaSelecionada == null){
					 try {
						musicaSelecionada = daoMusica.buscaProximoSinal();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				
				 
				 String horaAtual = dataHora.horaFormatada();
				 
				 
				 System.out.println(horaAtual);
				 
				
				 
				 if(horaAtual.compareTo(musicaSelecionada.getHorario()) == 0){
					 try {
						 player.play();
						 Thread.sleep(Integer.parseInt(musicaSelecionada.getTempo())*1000);
						 player.stop();
						 //musicaSelecionada = daoMusica.buscaProximoSinal();
						 
						 atualizaTudo();
						 
						 //txtMusica.setText(musicaSelecionada.getMusica());
					 } catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					 }
					 
				 }
				 
				 /*
				 
				 switch (dataHora.horaFormatada()){
				 case "07:00:00":
					 try {
					 player.play();
					 Thread.sleep(90000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "07:45:00":
					 try {
					 player.play();
					 Thread.sleep(20000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "08:30:00":
					 try {
					 player.play();
					 Thread.sleep(20000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "09:15:00":
					 try {
					 player.play();
					 Thread.sleep(20000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "10:00:00":
					 try {
					 player.play();
					 Thread.sleep(20000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "10:20:00":
					 try {
					 player.play();
					 Thread.sleep(90000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "11:05:00":
					 try {
					 player.play();
					 Thread.sleep(20000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "11:50:00":
					 try {
					 player.play();
					 Thread.sleep(20000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 case "12:35:00":
					 try {
					 player.play();
					 Thread.sleep(20000);
					 player.stop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 break;
				 }
				 
				 */
				 
			}
		});
		
		
		
		lblHora.setBounds(24, 88, 524, 44);
		contentPane.add(lblHora);
		
		
		lblProximoSinal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProximoSinal.setBounds(110, 145, 407, 44);
		contentPane.add(lblProximoSinal);
		
		JButton btnCadastrarNovoSinal = new JButton("Cadastrar Novo Sinal");
		btnCadastrarNovoSinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(cadastroSinal == null){
					cadastroSinal = new CadastroSinal();
					cadastroSinal.setVisible(true);
				}else{
					cadastroSinal.setVisible(true);
					
				}
				
			}
		});
		
		
		
		
		
		btnCadastrarNovoSinal.setBounds(393, 228, 176, 25);
		contentPane.add(btnCadastrarNovoSinal);
		
		
		
		
		
	}
	
	public void selecionaMusica(String musicaSelecionada){
		fxPanel = new JFXPanel();
		uriString = new File(musicaSelecionada).toURI().toString();
		pick = new Media(uriString); 
		player = new MediaPlayer(pick); 
	}
	public String incluirEnderecoAudio() throws FileNotFoundException, IOException {

       URL mediaURL = null;
       JFileChooser fileChooser = new JFileChooser();
       File arquivo = fileChooser.getSelectedFile();
      

       int result = fileChooser.showDialog(null, "ARQUIVOS");


       if (result == JFileChooser.APPROVE_OPTION) // user chose a file
       {
            // Obtém o arquivo selecionado
           // uma chamada à arquivo.getName() retornará o nome do arquivo

           arquivo = fileChooser.getSelectedFile();
           try {
            
               mediaURL = fileChooser.getSelectedFile().toURI().toURL();
              System.out.println(arquivo);
           } // end try
           catch (Exception e) {
               System.err.println("Could not create URL for the file");
           } 

       }
       String caminhoArquivo = arquivo.toString().replace("\\", "/");
      
       return caminhoArquivo;
}
	
	public void atualizaTudo(){
		musicaSelecionada = new Musica();
		musicaSelecionada = daoMusica.buscaProximoSinal();
		txtMusica.setText(musicaSelecionada.getMusica());
		lblProximoSinal.setText("Pr\u00F3ximo Sinal: "+musicaSelecionada.getHorario().substring(0,5));
		
	}

	public String proximoSinal(String horario){
		
		
		return musicaSelecionada.getMusica();
		
	}
	
}
