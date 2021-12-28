import java.util.Scanner;

public class Barang implements Penjualan {
		
		public String kasir;
		public static Integer noFaktur;
		public static String namaBarang;
		public static Integer hargaBarang;
		public static Integer jumlahBarang;
		public static Integer subTotal;
		public static Integer disc;
		public static Integer totalHarga;
		
		Scanner input = new Scanner(System.in);

		@Override
    	public void Kasir(){
        	kasir = null;
    	}
		
		@Override
		public void NoFaktur() {
			System.out.print("Inputkan No Faktur : ");
			noFaktur = input.nextInt();
		}

		@Override
		public void NamaBarang() {
			System.out.print("Inputkan Nama Barang : ");
			namaBarang = input.next();
		}

		@Override
		public void HargaBarang() {
			System.out.print("Inputkan Harga Barang : ");
			hargaBarang = input.nextInt();	
		}

		@Override
		public void Jumlah() {
			System.out.print("Inputkan Jumlah Barang : ");
			jumlahBarang = input.nextInt();
		}

		@Override
		public void SubTotal() {
			subTotal = null;
		}

		@Override
		public void Discount() {
			disc = null;
		}
		
		@Override
		public void TotalHarga() {
			totalHarga = null;
		}
}