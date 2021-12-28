import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Transaksi extends Barang{
	//static Scanner scanner;
	static Connection conn;

	@Override
    public void Kasir()
    {
        System.out.print("Inputkan Nama Kasir : ");
        kasir = input.nextLine();
        kasir = kasir.toUpperCase();
    }

	@Override
	public void SubTotal() {
		subTotal = hargaBarang* jumlahBarang;

	}

	@Override
	public void Discount() {
            if(subTotal >200000 && subTotal <=350000) {
				disc = subTotal*5/100;
			}

			else if (subTotal >350000 && subTotal <=500000) {
				disc = subTotal*10/100;
			}

			else if (subTotal >500000 && subTotal <=700000) {
				disc = subTotal*20/100;
			}

			else if (subTotal >700000) {
				disc = subTotal*25/100;
			}
                
			else{
				disc = 0;
			}
	}

	@Override
	public void TotalHarga() {
			totalHarga = subTotal-disc;
		}

	public void tanggal()
    {
        Date Date = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE, dd MMM yyyy");
        System.out.println("Tanggal Transaksi = " + tgl.format(Date));
    }

    private void waktu()
    {
        Date Time = new Date();    
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");  
        System.out.println("Waktu Transaksi   = " + tm.format(Time));
    }

	public static void viewData() throws SQLException {
		String text1 = "\n===============DAFTAR TRANSAKSI PEMBELIAN===============";
		System.out.println(text1.toUpperCase());

		String url = "jdbc:mysql://localhost:3306/penjualan";
		conn = DriverManager.getConnection(url,"root","");
						
		String sql ="SELECT * FROM transaksi_penjualan";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNo. Faktur		: ");
            System.out.print(result.getInt("no_faktur"));
            System.out.print("\nNama Barang		: ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga Barang		: ");
            System.out.print(result.getInt("harga_barang"));
			System.out.print("\nJumlah barang		: ");
            System.out.print(result.getInt("jumlah_barang"));
			System.out.print("\nSub Total Harga		: ");
            System.out.print(result.getInt("sub_total_harga"));
            System.out.print("\nDiskon Barang		: ");
            System.out.print(result.getInt("diskon_barang"));
			System.out.print("\nTotal pembayaran	: ");
            System.out.print(result.getInt("total_pembayaran"));
            System.out.print("\n");
		}
	}

	public static void insertData() throws SQLException{
		String text2 = "\n===Tambah Data Transaksi Pembelian===";
		System.out.println(text2.toUpperCase());

		String url = "jdbc:mysql://localhost:3306/penjualan";
		conn = DriverManager.getConnection(url,"root","");
		
		Scanner terimaInput = new Scanner (System.in);
		Transaksi t = new Transaksi();
				
		try {
		t.NoFaktur();
        t.NamaBarang();
        t.HargaBarang();
        t.Jumlah();
        t.SubTotal();
        t.Discount();
        t.TotalHarga();
		
		String sql = "INSERT INTO transaksi_penjualan (no_faktur, nama_barang, harga_barang, jumlah_barang, sub_total_harga, diskon_barang, total_pembayaran) VALUES ('"+noFaktur+"','"+namaBarang+"','"+hargaBarang+"','"+jumlahBarang+"','"+subTotal+"','"+disc+"','"+totalHarga+"')";
					
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil input data");
	
	    } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}
	}

	public static void updateData() throws SQLException{
		String text3 = "\n===Ubah Data Transaksi Pembelian===";
		System.out.println(text3.toUpperCase());

		String url = "jdbc:mysql://localhost:3306/penjualan";
		conn = DriverManager.getConnection(url,"root","");
		
		Scanner terimaInput = new Scanner (System.in);
		Transaksi t = new Transaksi();
		
		try {
            viewData();
            System.out.print("Masukkan No. Faktur Pembelian yang akan di ubah atau update : ");
            t.noFaktur = Integer.parseInt(terimaInput.nextLine());
            
            String sql = "SELECT * FROM transaksi_penjualan WHERE no_faktur = " +t.noFaktur;
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Nama Barang ["+result.getString("nama_barang")+"]\t: ");
                t.namaBarang = terimaInput.nextLine();
                
                System.out.print("Harga Barang ["+result.getString("harga_Barang")+"]\t: ");
                t.hargaBarang = terimaInput.nextInt();

				System.out.print("Jumlah Barang ["+result.getString("jumlah_Barang")+"]\t: ");
                t.jumlahBarang = terimaInput.nextInt();

				t.SubTotal();
				t.Discount();
				t.TotalHarga();
                   
                sql = "UPDATE transaksi_penjualan SET nama_barang='"+t.namaBarang+"',harga_barang= '"+t.hargaBarang+"',jumlah_barang='"+t.jumlahBarang+"',sub_total_harga='"+t.subTotal+"',diskon_barang='"+t.disc+"',total_pembayaran='"+t.totalHarga+"' WHERE no_faktur='"+t.noFaktur+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data Transaksi Pembelian (No. Faktur "+t.noFaktur+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
		}

		public static void deleteData() {
			String text4 = "\n===Hapus Data Transaksi Pembelian===";
			System.out.println(text4.toUpperCase());
			
			Scanner terimaInput = new Scanner (System.in);
			
			try{
				viewData();
				System.out.print("Input No. Faktur pembelian yang akan Anda Hapus : ");
				Integer noFaktur= Integer.parseInt(terimaInput.nextLine());
				
				String sql = "DELETE FROM transaksi_penjualan WHERE no_faktur = "+ noFaktur;
				Statement statement = conn.createStatement();
				//ResultSet result = statement.executeQuery(sql);
				
				if(statement.executeUpdate(sql) > 0){
					System.out.println("Berhasil menghapus data Transaksi Pembelian (No. Faktur "+noFaktur+")");
				}
		   }catch(SQLException e){
				System.out.println("Terjadi kesalahan dalam menghapus data barang");
				}
		}

		public static void searchData () throws SQLException {
			String text5 = "\n===Cari Data Transaksi Pembelian===";
			System.out.println(text5.toUpperCase());

			String url = "jdbc:mysql://localhost:3306/penjualan";
			conn = DriverManager.getConnection(url,"root","");
			
			Scanner terimaInput = new Scanner (System.in);
					
			System.out.print("Masukkan Nama Barang : ");
			
			String keyword = terimaInput.nextLine();
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM transaksi_penjualan WHERE nama_barang LIKE '%"+keyword+"%'";
			ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
        	System.out.print("\nNo. Faktur			: ");
            System.out.print(result.getInt("no_faktur"));
            System.out.print("\nNama Barang			: ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga Barang		: ");
            System.out.print(result.getInt("harga_barang"));
			System.out.print("\nJumlah barang		: ");
            System.out.print(result.getInt("jumlah_barang"));
			System.out.print("\nSub Total Harga		: ");
            System.out.print(result.getInt("sub_total_harga"));
            System.out.print("\nDiskon Barang		: ");
            System.out.print(result.getInt("diskon_barang"));
			System.out.print("\nTotal pembayaran	: ");
            System.out.print(result.getInt("total_pembayaran"));
            System.out.print("\n");
        }
		}

	
		public void display(){
			System.out.println("===============TRANSAKSI PEMBELIAN===============");
			tanggal();
        	waktu();
        	System.out.println("Nama Kasir        = " + kasir);
        	System.out.println("---------------------------------------------");

			System.out.println("No Faktur         = " + noFaktur);
			System.out.println("Nama Barang       = " + namaBarang);
			System.out.println("Harga Barang      = " + hargaBarang);
			System.out.println("Jumlah Barang     = " + jumlahBarang);
			System.out.println("Sub Total Harga   = " + subTotal);
			System.out.println("Diskon Barang     = " + disc);
			System.out.println("Total Pembayaran  = " + totalHarga);
		}
	}
	