package read.com.pk;


import read.com.pk.*;

public class main {

	public static void main(String[] args) throws Exception {
		String urlFile = "C:\\Users\\nguyenanh\\Documents\\abc.xlsx";
		
		ReadExcel fileExcel =new ReadExcel(urlFile);
		fileExcel.setFile(urlFile);
		fileExcel.setLastRow();
		//System.out.println("dong cuoi cung la: "+fileExcel.getLastRow());
		
		int i=0;
		while(i < fileExcel.getLastRow()){
			fileExcel.setLastCellInRow(i);
			//System.out.println("phan tu thu "+ (i) + "la: "+fileExcel.readFile(i, 0)+"-----o cuoi cung o vi tri thu: "+fileExcel.getLastCellInRow());
			
			switch(fileExcel.readFile(i, 0)){
			//kiem tra xem phan dau tien la gi de co su lua chon
				case "get" :
					//neu la get thi phong toi url(o thu 4 co index =3)
					System.out.println("GET ne");
					System.out.println(fileExcel.readFile(i,3));
					break;
				case "click" :
					//neu la click thi phong toi targer(o thu 2 co index=1)
					System.out.println("CLICK ne");
					System.out.println(fileExcel.readFile(i, 1));
					break;
				case "sendKeys" :
					//neu la sendKeys thi lay o tager + value(index 1,2)
					System.out.println("sendKeys");
					System.out.println(fileExcel.readFile(i, 1)+"----"+fileExcel.readFile(i, 2));
					break;
			}
			i++;
		}
	}
}
