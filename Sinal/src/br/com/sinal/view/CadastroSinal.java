package br.com.sinal.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.sinal.DAO.DAOMusica;
import br.com.sinal.dominio.Musica;
import java.awt.Dialog.ModalExclusionType;

public class CadastroSinal extends JFrame {

	private ProjetoSinal projetoSinal;
	private JPanel contentPane;
	private JTextField txtId;
	private JFormattedTextField txtHorario;
	private JTextField textTempo;
	private JTextField txtSom;
	private JTable table;
	
	DefaultTableModel model;

	JButton btnTeste = new JButton("teste");
	JButton btnAdicionar = new JButton("Adicionar");
	JButton btnUsarEmTodos = new JButton("Usar em todos");
	JButton btnExcluir = new JButton("Excluir");
	JButton btnLimpar = new JButton("Limpar");
	JButton btnAtualizar = new JButton("Atualizar");
	JScrollPane scrollPane = new JScrollPane();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroSinal frame = new CadastroSinal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	final Object[] row = new Object[4];
	DAOMusica sinalMusica = new DAOMusica();
	
	/**
	 * Create the frame.
	 */
	public CadastroSinal() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				carregaJTable();
				formataHora();
				
				
			}
		});
		setBounds(100, 100, 716, 498);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPosicao = new JLabel("Sinal:");
		lblPosicao.setBounds(35, 28, 41, 16);
		contentPane.add(lblPosicao);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(35, 46, 116, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblIncio = new JLabel("Hor\u00E1rio In\u00EDcio:");
		lblIncio.setBounds(37, 128, 87, 16);
		contentPane.add(lblIncio);
		
		txtHorario = new JFormattedTextField();
		txtHorario.setBounds(35, 147, 116, 22);
		contentPane.add(txtHorario);
		txtHorario.setColumns(10);
		
		JLabel lblTempo = new JLabel("Tempo:");
		lblTempo.setBounds(185, 128, 56, 16);
		contentPane.add(lblTempo);
		
		textTempo = new JTextField();
		textTempo.setBounds(185, 147, 116, 22);
		contentPane.add(textTempo);
		textTempo.setColumns(10);
		
		JLabel lblSom = new JLabel("Som:");
		lblSom.setBounds(35, 81, 56, 16);
		contentPane.add(lblSom);
		
		txtSom = new JTextField();
		txtSom.setBounds(35, 99, 193, 22);
		contentPane.add(txtSom);
		txtSom.setColumns(10);
		
		
		scrollPane.setBounds(35, 239, 586, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				txtId.setText(model.getValueAt(i, 0).toString());
				txtSom.setText(model.getValueAt(i, 1).toString());
				txtHorario.setText(model.getValueAt(i, 2).toString());
				textTempo.setText(model.getValueAt(i, 3).toString());
				btnExcluir.setEnabled(true);
				btnAtualizar.setEnabled(true);
				btnLimpar.setEnabled(true);
				btnUsarEmTodos.setEnabled(true);
				btnAdicionar.setEnabled(false);
			}
		});
		
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		model = new DefaultTableModel();
		Object[] column = {"ID", "M\u00FAsica", "Hor\u00E1rio", "Tempo"};
		
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		
		
	
		
		
		/*try {
			table.setModel(DbUtils.resultSetToTableModel(sinalMusica.listaSinal()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "M\u00FAsica", "Hor\u00E1rio", "Tempo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});*/
		
		
		btnTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					List<Musica> list = sinalMusica.listaSinal();
					//model = (DefaultTableModel) table.getModel();
					
					
					for(Musica musicax : list){
					
						row[0]=musicax.getCodigo();
						row[1]=musicax.getMusica();
						row[2]=musicax.getHorario();
						row[3]=musicax.getTempo();
						model.addRow(row);
						
					}
					
					
					
					//table.setModel(sinalMusica.listaSinal());
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnTeste.setBounds(524, 33, 97, 25);
		contentPane.add(btnTeste);
		
		
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtSom.getText().equals("") || txtHorario.getText().equals("") || textTempo.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos de horários!!!");
					return;
				}else{
					try {
					//row[0]=txtId.getText();
					row[1]=txtSom.getText();
					row[2]=txtHorario.getText();
					row[3]=textTempo.getText();
					//model.addRow(row); Removido, pois depois de adicionar ele carrega os dados novamente na tela
					
					Musica musica = new Musica();
					musica.setCodigo(null);
					musica.setMusica(txtSom.getText());
					musica.setHorario(txtHorario.getText());
					musica.setTempo(textTempo.getText());
					DAOMusica daoMusica = new DAOMusica();
				
					daoMusica.adicionaSinal(musica);
					carregaJTable();
					
					
					txtId.setText("");
					txtSom.setText("");
					txtHorario.setText("");
					textTempo.setText("");
					
					btnAdicionar.setEnabled(false);
					btnAtualizar.setEnabled(false);
					btnExcluir.setEnabled(false);
					btnLimpar.setEnabled(false);
					btnUsarEmTodos.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Horário Inserido com sucesso!");
					carregaJTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			}
		});
		btnAdicionar.setBounds(524, 64, 97, 25);
		contentPane.add(btnAdicionar);
		
		
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				System.out.println(i);
				if(i>=0){
					Musica musica = new Musica();
					musica.setCodigo(Long.parseLong(model.getValueAt(i, 0).toString()));
					musica.setMusica(model.getValueAt(i, 1).toString());
					musica.setHorario(model.getValueAt(i, 2).toString());
					musica.setTempo(model.getValueAt(i, 3).toString());
					
							
					model.removeRow(i);
					
					DAOMusica daoMusica = new DAOMusica();
					try {
						daoMusica.excluiSinal(musica);
						JOptionPane.showMessageDialog(null, "Horário Excluído com sucesso!");
						txtHorario.setText("");
						txtId.setText("");
						txtSom.setText("");
						textTempo.setText("");
						btnAdicionar.setEnabled(false);
						btnAtualizar.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnLimpar.setEnabled(false);
						btnUsarEmTodos.setEnabled(false);
						carregaJTable();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir o sinal!");
						e.printStackTrace();
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um horário para Excluir");
				}
			}
		});
		btnExcluir.setBounds(524, 90, 97, 25);
		contentPane.add(btnExcluir);
		
		
		btnLimpar.setEnabled(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtId.setText("");
				txtSom.setText("");
				txtHorario.setText("");
				textTempo.setText("");
				
				
				table.clearSelection();
				btnExcluir.setEnabled(false);
				btnAtualizar.setEnabled(false);
				btnLimpar.setEnabled(false);
				btnAdicionar.setEnabled(false);
			}
		});
		btnLimpar.setBounds(524, 116, 97, 25);
		contentPane.add(btnLimpar);
		
		
		btnAtualizar.setEnabled(false);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				System.out.println(i);
				if(i>=0){
					model.setValueAt(txtId.getText(), i, 0);
					model.setValueAt(txtSom.getText(), i, 1);
					model.setValueAt(txtHorario.getText(), i, 2);
					model.setValueAt(textTempo.getText(), i, 3);
					
					Musica musica = new Musica();
					musica.setCodigo(Long.parseLong(model.getValueAt(i, 0).toString()));
					musica.setMusica(model.getValueAt(i, 1).toString());
					musica.setHorario(model.getValueAt(i, 2).toString());
					musica.setTempo(model.getValueAt(i, 3).toString());
					
					DAOMusica daoMusica = new DAOMusica();
					try {
						daoMusica.atualizaSinal(musica);
						JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!!");
						carregaJTable();
						
						/*if(projetoSinal == null){
							projetoSinal = new ProjetoSinal();
							projetoSinal.setVisible(true);
						}else{
							projetoSinal.setVisible(true);
							projetoSinal.atualizaTudo();
						}*/
						//executaMetodo();
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar sinal!!");
						e1.printStackTrace();
					}
					
					
					
					
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um horário para Atualizar");
				}
				
			}
		});
		btnAtualizar.setBounds(524, 144, 97, 25);
		contentPane.add(btnAtualizar);
		
		JButton btnArquivo = new JButton("Arquivo...");
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtSom.setText(incluirEnderecoAudio());
					btnUsarEmTodos.setEnabled(true);
					btnAdicionar.setEnabled(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnArquivo.setBounds(229, 98, 87, 25);
		contentPane.add(btnArquivo);
		btnUsarEmTodos.setEnabled(false);
		
		
		btnUsarEmTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Todos os sinais receberão a mesma música.");
				DAOMusica daoMusica = new DAOMusica();
				daoMusica.atualizaTodos(txtSom.getText());
				carregaJTable();
				
				btnAdicionar.setEnabled(false);
				btnAtualizar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnLimpar.setEnabled(false);
				btnUsarEmTodos.setEnabled(false);
				txtHorario.setText("");
				txtSom.setText("");
				textTempo.setText("");
			}
		});
		btnUsarEmTodos.setBounds(320, 98, 116, 25);
		contentPane.add(btnUsarEmTodos);
		
		
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
	
	
	
	
	public void carregaJTable(){
		try {
			

			((DefaultTableModel) table.getModel()).setRowCount(0);
			
			List<Musica> list = new ArrayList<Musica>();
			list = sinalMusica.listaSinal();
			//model = (DefaultTableModel) table.getModel();
			
			
			for(Musica musicax : list){
			
				row[0]=musicax.getCodigo();
				row[1]=musicax.getMusica();
				row[2]=musicax.getHorario();
				row[3]=musicax.getTempo();
				model.addRow(row);
				
			}
			
			
			btnTeste.setVisible(false);
			//table.setModel(sinalMusica.listaSinal());
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void formataHora(){
		try {
			MaskFormatter mascaraHora = new MaskFormatter("##:##:##");
			mascaraHora.install(txtHorario);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo Hora.");
			e.printStackTrace();
		}
	}
	
	public void mostrarTela(ProjetoSinal telaSinal){
		this.projetoSinal = telaSinal;
		setVisible(true);
	}
	
	public void executaMetodo(){
		projetoSinal.atualizaTudo();
	}
}
