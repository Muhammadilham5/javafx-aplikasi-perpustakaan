/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasiperpustakaan;

/**
 *
 * @author ASUS
 */
public class Pinjam {
    private int id;
    private String nama;
    private int kode;
    private int tglpinjam;
    private int tglkembali;
    private String ket;

    public Pinjam(int id, String nama, int kode, int tglpinjam, int tglkembali, String ket) {
        this.id = id;
        this.nama = nama;
        this.kode = kode;
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

    public int getTglpinjam() {
        return tglpinjam;
    }

    public int getTglkembali() {
        return tglkembali;
    }

    public String getKet() {
        return ket;
    }
    
    
}
