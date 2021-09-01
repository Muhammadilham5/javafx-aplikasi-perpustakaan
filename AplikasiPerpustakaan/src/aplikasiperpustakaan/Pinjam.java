/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasiperpustakaan;

import java.text.SimpleDateFormat;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

/**
 *
 * @author ASUS
 */
public class Pinjam {
    private int id;
    private String nama;
    private int kode;
    private String judul;
    private String tglpinjam;
    private String tglkembali;
    private String ket;

    public Pinjam(int id, String nama, int kode, String judul, String tglpinjam, String tglkembali, String ket) {
        this.id = id;
        this.nama = nama;
        this.kode = kode;
        this.judul = judul;
        this.tglpinjam = tglpinjam;
        this.tglkembali = tglkembali;
        this.ket = ket;
    }

   

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }


    public int getKode() {
        return kode;
    }
    
    public String getJudul() {
        return judul;
    }

    public String getTglpinjam() {
        return tglpinjam;
    }

    public String getTglkembali() {
        return tglkembali;
    }

    public String getKet() {
        return ket;
    }
}
