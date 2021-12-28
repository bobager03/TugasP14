import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program{

    //static Scanner scanner;
	static Connection conn;
    public static void main(String[] args) throws Exception {
        boolean isLanjutkan = true;
        String pilih;
        Scanner terimaInput = new Scanner(System.in);

        Transaksi t = new Transaksi();

        String url = "jdbc:mysql://localhost:3306/penjualan";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan");
			
			while (isLanjutkan) {
				System.out.println("\n----------------------------");
				System.out.println("Database Transaksi Pembelian");
				System.out.println("------------------------------");
				System.out.println("1. Lihat Data Transaksi Pembelian");
				System.out.println("2. Tambah Data Transaksi Pembelian");
				System.out.println("3. Ubah Data Transaksi Pembelian");
				System.out.println("4. Hapus Data Transaksi Pembelian");
				System.out.println("5. Cari Data Transaksi Pembelian");
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilih = terimaInput.next();
				
				switch (pilih) {
				case "1":
					t.viewData();
					break;
				case "2":
					t.insertData();
					break;
				case "3":
					t.updateData();
					break;
				case "4":
					t.deleteData();
					break;
				case "5":
					t.searchData();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilih = terimaInput.next();
				isLanjutkan = pilih.equalsIgnoreCase("y");
			}
			System.out.println("\n===TERIMAKASIH!===");
			
		}
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
		}
    }

}

