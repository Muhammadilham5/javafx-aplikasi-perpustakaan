/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasiperpustakaan;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author ASUS
 */
public class Controller implements Initializable {
    
    //FXML//    
    @FXML private Pane MenuAkun;
    @FXML private Pane MenuTambah;
    @FXML private Pane MenuBuku;
    @FXML private Pane MenuExit;
    
    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnDelete;
    
    @FXML private TextField tfid;
    @FXML private TextField tfnama;
    @FXML private TextField tfkode;
    @FXML private TextField tfpinjam;
    @FXML private TextField tfkembali;
    @FXML private TextField tfket;
    
    @FXML private TableView<Pinjam> tbv;
    @FXML private TableColumn<Pinjam, Integer> colId;
    @FXML private TableColumn<Pinjam, String> colNama;
    @FXML private TableColumn<Pinjam, Integer> colKode;
    @FXML private TableColumn<Pinjam, Integer> colTglPinjam;
    @FXML private TableColumn<Pinjam, Integer> colTglKembali;
    @FXML private TableColumn<Pinjam, String> colKet;
    
    @FXML
    void AktifMenuAkun(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    @FXML
    void AktifMenuTambah(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    @FXML
    void AktifMenuBuku(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showpinjam();
    }    
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/perpustakaan","root","");
    		return conn;
    	}
    	catch (SQLException e){
    		System.out.println("error =" + e.getMessage());
    		return null;
    	}
    }
    
    public ObservableList<Pinjam> getPinjamList(){
    	ObservableList<Pinjam> pinjamList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM pinjam ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Pinjam pinjam;
			while(rs.next()) {
				pinjam = new Pinjam(rs.getInt("id"),rs.getString("nama"),rs.getInt("kode"),rs.getInt("tglpinjam"),rs.getInt("tglkembali"),rs.getString("ket"));
				pinjamList.add(pinjam);
				}
		} catch (SQLException e) {
			System.out.println("error =" + e.getMessage());
		}
    	return pinjamList;
    }
    
    public void showpinjam() {
    	ObservableList<Pinjam> list = getPinjamList();
    	
    	colId.setCellValueFactory(new PropertyValueFactory<Pinjam,Integer>("id"));
    	colNama.setCellValueFactory(new PropertyValueFactory<Pinjam,String>("nama"));
    	colKode.setCellValueFactory(new PropertyValueFactory<Pinjam,Integer>("kode"));
    	colTglPinjam.setCellValueFactory(new PropertyValueFactory<Pinjam,Integer>("tglpinjam"));
    	colTglKembali.setCellValueFactory(new PropertyValueFactory<Pinjam,Integer>("tglkembali"));
        colKet.setCellValueFactory(new PropertyValueFactory<Pinjam,String>("ket"));
    	
    	tbv.setItems(list);
    }
    
    @FXML
    private void btnAdd() {
    	String query = "INSERT INTO pinjam VALUES('"+tfid.getText()+"','"+tfnama.getText()+"','"+tfkode.getText()+"','"+tfpinjam.getText()+"','"+tfkembali.getText()+"','"+tfket.getText()+"')";
    	executeQuery(query);
    	showpinjam();
    }
    
    @FXML 
    private void btnEdit() {
        String query = "UPDATE pinjam SET nama='"+tfnama.getText()+"',kode='"+tfkode.getText()+"',tglpinjam='"+tfpinjam.getText()+"',tglkembali='"+tfkembali.getText()+"',ket='"+tfket.getText()+"' WHERE id='"+tfid.getText()+"'";
        executeQuery(query);
	showpinjam();
    }
    
    @FXML
    private void btnDelete() {
    	String query = "DELETE FROM pinjam WHERE ID="+tfid.getText()+"";
    	executeQuery(query);
    	showpinjam();
    }
    
    @FXML
    void Select(MouseEvent event) {
        Pinjam pinjam = tbv.getSelectionModel().getSelectedItem();
        tfid.setText(""+ pinjam.getId());
        tfnama.setText(""+ pinjam.getNama());
        tfkode.setText(""+ pinjam.getKode());
        tfpinjam.setText(""+ pinjam.getTglpinjam());
        tfkembali.setText(""+ pinjam.getTglkembali());
        tfket.setText(""+ pinjam.getKet());
        
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnection();
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("error =" + e.getMessage());
		}
    }
}
