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
public class Buku {
    private int kode;
    private String judul;
    private String pengarang;
    private int thnterbit;

    public Buku(int kode, String judul, String pengarang, int thnterbit) {
        this.kode = kode;
        this.judul = judul;
        this.pengarang = pengarang;
        this.thnterbit = thnterbit;
    }

    public int getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public int getThnterbit() {
        return thnterbit;
    }
    
    
}
