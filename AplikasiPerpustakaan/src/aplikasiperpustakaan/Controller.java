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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
import javafx.scene.text.Text;

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
    
    @FXML private Pane home;
    @FXML private Pane tambah;
    @FXML private Pane buku;
    
    @FXML private Text stbuku;
    @FXML private Text stpinjam;
    @FXML private Text stkembali;
    
    @FXML private TextField tfid;
    @FXML private TextField tfnama;
    @FXML private TextField tfkode1;
    @FXML private DatePicker tfpinjam;
    @FXML private DatePicker tfkembali;
    @FXML private TextField tfket;
    
    @FXML private TextField tfkode2;
    @FXML private TextField tfjudul;
    @FXML private TextField tfpengarang;
    @FXML private TextField tfthnterbit;
    
    @FXML private Button btnAdd1;
    @FXML private Button btnEdit1;
    @FXML private Button btnDelete1;
    
    @FXML private Button btnAdd2;
    @FXML private Button btnEdit2;
    @FXML private Button btnDelete2;
    
    @FXML private TableView<Pinjam> tbvpinjam;
    @FXML private TableColumn<Pinjam, Integer> colId;
    @FXML private TableColumn<Pinjam, String> colNama;
    @FXML private TableColumn<Pinjam, Integer> colKode1;
    @FXML private TableColumn<Pinjam, String> colJudul1;
    @FXML private TableColumn<Pinjam, String> colTglPinjam;
    @FXML private TableColumn<Pinjam, String> colTglKembali;
    @FXML private TableColumn<Pinjam, String> colKet;
    
    @FXML private TableView<Buku> tbvbuku;
    @FXML private TableColumn<Buku, Integer> colKode2;
    @FXML private TableColumn<Buku, String> colJudul;
    @FXML private TableColumn<Buku, String> colPengarang;
    @FXML private TableColumn<Buku, Integer> colThnTerbit;
    
    @FXML private LineChart<String ,Number> Linechart;
   
    @FXML
    void AktifMenuAkun(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        home.setVisible(true);
        tambah.setVisible(false);
        buku.setVisible(false);
    }
    
    @FXML
    void AktifMenuTambah(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        home.setVisible(false);
        tambah.setVisible(true);
        buku.setVisible(false);
    }
    
    @FXML
    void AktifMenuBuku(MouseEvent event) {
        MenuAkun.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuTambah.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        MenuBuku.setBackground(new Background(new BackgroundFill(Color.valueOf("#013a63"), CornerRadii.EMPTY, Insets.EMPTY)));
        MenuExit.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        home.setVisible(false);
        tambah.setVisible(false);
        buku.setVisible(true);
    }
    
    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showpinjam();
        showbuku();
        chart();
        chart1();
        tampilanjumlah();
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
    
    //table pinjam
    public ObservableList<Pinjam> getPinjamList(){
    	ObservableList<Pinjam> pinjamList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query;
        query = "SELECT `id`, `nama`, `pinjam`.`kode`, `judul`, `tglpinjam`, `tglkembali`, `ket` FROM `pinjam` INNER JOIN `buku` ON `pinjam`.`kode` = `buku`.`kode`;";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Pinjam pinjam;
			while(rs.next()) {
				pinjam = new Pinjam(rs.getInt("id"),rs.getString("nama"),rs.getInt("kode"),rs.getString("judul"),rs.getString("tglpinjam"),rs.getString("tglkembali"),rs.getString("ket"));
				pinjamList.add(pinjam);
				}
		} catch (SQLException e) {
			System.out.println("error =" + e.getMessage());
		}
    	return pinjamList;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showpinjam() {
    	ObservableList<Pinjam> list = getPinjamList();
    	
    	colId.setCellValueFactory(new PropertyValueFactory<Pinjam,Integer>("id"));
    	colNama.setCellValueFactory(new PropertyValueFactory<Pinjam,String>("nama"));
    	colKode1.setCellValueFactory(new PropertyValueFactory<Pinjam,Integer>("kode"));
        colJudul1.setCellValueFactory(new PropertyValueFactory<Pinjam,String>("judul"));
    	colTglPinjam.setCellValueFactory(new PropertyValueFactory<Pinjam,String>("tglpinjam"));
    	colTglKembali.setCellValueFactory(new PropertyValueFactory<Pinjam,String>("tglkembali"));
        colKet.setCellValueFactory(new PropertyValueFactory<Pinjam,String>("ket"));
    	tbvpinjam.setItems(list);
    }
    
    @FXML
    private void btnAdd1() {
    	String query = "INSERT INTO pinjam VALUES('"+tfid.getText()+"','"+tfnama.getText()+"','"+tfkode1.getText()+"','"+tfpinjam.getValue()+"','"+tfkembali.getValue()+"','"+tfket.getText()+"')";
    	executeQuery(query);
    	showpinjam();
    }
    
    @FXML 
    private void btnEdit1() {
        String query = "UPDATE pinjam SET nama='"+tfnama.getText()+"',kode='"+tfkode1.getText()+"',tglpinjam='"+tfpinjam.getValue()+"',tglkembali='"+tfkembali.getValue()+"',ket='"+tfket.getText()+"' WHERE id='"+tfid.getText()+"'";
        executeQuery(query);
	showpinjam();
    }
    
    @FXML
    private void btnDelete1() {
    	String query = "DELETE FROM pinjam WHERE ID="+tfid.getText()+"";
    	executeQuery(query);
    	showpinjam();
    }
    
    @FXML
    void Select(MouseEvent event) {
        Pinjam pinjam = tbvpinjam.getSelectionModel().getSelectedItem();
        tfid.setText(""+ pinjam.getId());
        tfnama.setText(""+ pinjam.getNama());
        tfkode1.setText(""+ pinjam.getKode());
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
    
    //table buku
    public ObservableList<Buku> getBukuList(){
    	ObservableList<Buku> bukuList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM buku ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Buku buku;
			while(rs.next()) {
				buku = new Buku(rs.getInt("kode"),rs.getString("judul"),rs.getString("pengarang"),rs.getInt("thnterbit"));
				bukuList.add(buku);
				}
		} catch (SQLException e) {
			System.out.println("error =" + e.getMessage());
		}
    	return bukuList;
    }
    
    public void showbuku() {
    	ObservableList<Buku> list = getBukuList();
    	
    	colKode2.setCellValueFactory(new PropertyValueFactory<Buku,Integer>("kode"));
    	colJudul.setCellValueFactory(new PropertyValueFactory<Buku,String>("judul"));
    	colPengarang.setCellValueFactory(new PropertyValueFactory<Buku,String>("pengarang"));
    	colThnTerbit.setCellValueFactory(new PropertyValueFactory<Buku,Integer>("thnterbit"));
    	
    	tbvbuku.setItems(list);
    }
    
    @FXML
    private void btnAdd2() {
    	String query = "INSERT INTO buku VALUES('"+tfkode2.getText()+"','"+tfjudul.getText()+"','"+tfpengarang.getText()+"','"+tfthnterbit.getText()+"')";
    	executeQuery(query);
    	showbuku();
    }
    
    @FXML 
    private void btnEdit2() {
        String query = "UPDATE buku SET judul='"+tfjudul.getText()+"',pengarang='"+tfpengarang.getText()+"',thnterbit='"+tfthnterbit.getText()+"' WHERE kode='"+tfkode2.getText()+"'";
        executeQuery(query);
	showbuku();
    }
    
    @FXML
    private void btnDelete2() {
    	String query = "DELETE FROM buku WHERE kode="+tfkode2.getText()+"";
    	executeQuery(query);
    	showbuku();
    }
    
    @FXML
    void Select2(MouseEvent event) {
        Buku buku = tbvbuku.getSelectionModel().getSelectedItem();
        tfkode2.setText(""+ buku.getKode());
        tfjudul.setText(""+ buku.getJudul());
        tfpengarang.setText(""+ buku.getPengarang());
        tfthnterbit.setText(""+ buku.getThnterbit());
    }
    
    private void chart(){
        String query = "SELECT `tglpinjam`, COUNT(`tglpinjam`) AS jumlah FROM pinjam GROUP BY `tglpinjam`;";
        XYChart.Series<String ,Number> series = new XYChart.Series<>();
        try{
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                series.setName("pinjam");
            }Linechart.getData().add(series); 
        }catch(SQLException e){
            
        }
    }
    private void chart1(){
        String query = "SELECT `tglkembali`, COUNT(`tglkembali`) AS jumlah FROM pinjam GROUP BY `tglkembali`;";
        XYChart.Series<String ,Number> series = new XYChart.Series<>();
        try{
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                series.setName("kembali");
            }Linechart.getData().add(series); 
        }catch(SQLException e){
            
        }
    }
    private void tampilanjumlah(){
        //tampilan jumlah buku
        try{
            String query ="SELECT COUNT(`kode`) AS jumlah FROM pinjam; ";
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while(rs.next()){
                stbuku.setText(rs.getString(1));
            }
        }catch(SQLException e){  
        }
        //tampilan jumlah dipinjam
        try{
            String query ="SELECT COUNT(`ket`) AS jumlah FROM pinjam WHERE ket = 'dipinjam';";
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while(rs.next()){
                stpinjam.setText(rs.getString(1));
            }
        }catch(SQLException e){  
        }
        //tampilan jumlah dikembalikan
        try{
            String query ="SELECT COUNT(`ket`) AS jumlah FROM pinjam WHERE ket = 'kembalikan';";
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while(rs.next()){
                stkembali.setText(rs.getString(1));
            }
        }catch(SQLException e){  
        }
    }
}
