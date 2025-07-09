/*
 * Versi perbaikan dari file koneksi.
 * Menggunakan metode koneksi yang lebih modern dan eksplisit menyebutkan port.
 */
package rumahsakit; // Pastikan nama package ini sesuai dengan proyek Anda

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author X230
 */
public class koneksi {
    
    // Variabel koneksi kita buat private agar tidak bisa diakses dari luar kelas
    private static Connection koneksiDatabase;

    /**
     * Metode ini membuat dan mengembalikan sebuah koneksi ke database.
     * Jika koneksi sudah ada, ia akan mengembalikan koneksi yang sama.
     * Jika belum, ia akan membuat koneksi baru.
     * @return Objek Connection yang siap digunakan.
     * @throws SQLException 
     */
    public static Connection koneksiDB() throws SQLException {
        // Cek apakah koneksi sudah pernah dibuat atau belum
        if (koneksiDatabase == null || koneksiDatabase.isClosed()) {
            try {
                // String untuk koneksi ke database
                // Format: jdbc:mysql://[host]:[port]/[nama_database]
                // Pastikan port 3306 ini sama dengan yang ada di XAMPP Anda.
                String url = "jdbc:mysql://localhost:3306/db_rs";
                String user = "root";
                String pass = "";

                // Tidak perlu lagi menggunakan DriverManager.registerDriver() di Java modern.
                // Baris di bawah ini sudah cukup.
                koneksiDatabase = DriverManager.getConnection(url, user, pass);
                
                System.out.println("Koneksi ke database berhasil!");

            } catch (SQLException e) {
                // Jika terjadi error, tampilkan pesan dan hentikan program
                JOptionPane.showMessageDialog(null, "Koneksi ke Database Gagal! Pastikan XAMPP/MySQL sudah berjalan.", "Koneksi Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("Error koneksi: " + e.getMessage());
                System.exit(0); // Hentikan program jika koneksi gagal
            }
        }
        return koneksiDatabase;
    }
    
    // Anda bisa menambahkan metode main di sini untuk testing koneksi secara cepat
    public static void main(String[] args) {
        try {
            Connection conn = koneksi.koneksiDB();
            if (conn != null) {
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil di-test!");
            }
        } catch (SQLException e) {
            System.err.println("Test koneksi gagal: " + e.getMessage());
        }
    }
}
