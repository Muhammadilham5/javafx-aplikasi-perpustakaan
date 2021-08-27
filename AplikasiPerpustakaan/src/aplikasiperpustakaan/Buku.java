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
    private int kode2;
    private String judul;
    private String pengarang;
    private int thnterbit;

    public Buku(int kode2, String judul, String pengarang, int thnterbit) {
        this.kode2 = kode2;
        this.judul = judul;
        this.pengarang = pengarang;
        this.thnterbit = thnterbit;
    }

    public int getKode2() {
        return kode2;
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
