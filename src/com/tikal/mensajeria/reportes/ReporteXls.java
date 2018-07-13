package com.tikal.mensajeria.reportes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.tikal.mensajeria.modelo.vo.ReporteVo; 



public class ReporteXls {
	
	
	private List<ReporteVo> renglones;

	public ReporteXls() {}
	
	public void setRenglones(List<ReporteVo> renglones) {
		this.renglones = renglones;
	}
	
	public List<ReporteVo> getRenglones() {
		return renglones;
	}
	@SuppressWarnings("deprecation")
	
	public HSSFWorkbook GetReporteXls() throws IOException  { //, OutputStream ops

		HSSFFont headerFont;
		HSSFFont contentFont;
		HSSFWorkbook workbook= new HSSFWorkbook();
	    
				int renglon=0;
			  	int columna=0;
				System.out.println("ya esta escribiendo..........:");
	    		// 	FileInputStream file = new FileInputStream(new File(archivo));
	    		 	
	    		 	
	    		 //	HSSFWorkbook workbook = new HSSFWorkbook();
	    		 	workbook = new HSSFWorkbook();
	    		 	
	    		 	
	    		  	HSSFSheet sheet = workbook.createSheet();
	    		  	
	    		  	workbook.setSheetName(0, "Ventas");
	    		  	System.out.println("nombre de hoja:"+ 	sheet.getSheetName());
	    			////////// fuentes
    		             
	    		  	 HSSFFont font = workbook.createFont();
	    		  	HSSFFont font2 = workbook.createFont();   
	    		  	HSSFFont font3 = workbook.createFont();    
	    	            
	    	            
	    		  	 CellStyle style = workbook.createCellStyle();
	    	          //  style.setBorderBottom(CellStyle.BORDER_THIN);
	    	            style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	    	            style.setAlignment(HorizontalAlignment.CENTER);
	    	            font.setBold(true);
	    	            font.setFontHeightInPoints((short)11);
	    	            font.setColor(HSSFColor.BLACK.index);
	    	            style.setFont(font);
	    	            
	    	            CellStyle style2 = workbook.createCellStyle();
	    	      //      style2.setBorderBottom(CellStyle.BORDER_THIN);
	    	            style2.setAlignment(HorizontalAlignment.CENTER);
	    	            style2.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	    	            font2.setBold(false);
	    	            font2.setFontHeightInPoints((short)10);
	    	            font2.setColor(HSSFColor.BLACK.index);
	    	            style2.setFont(font2);
	    	            
	    	            CellStyle style3 = workbook.createCellStyle();
	    	         //   style3.setBorderBottom(CellStyle.BORDER_THIN);
	    	            style3.setAlignment(HorizontalAlignment.RIGHT);
	    	            style3.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	    	            font3.setBold(false);
	    	            font3.setFontHeightInPoints((short)10);
	    	            font3.setColor(HSSFColor.BLACK.index);
	    	            style3.setFont(font3);
	    	            
	    	            
	    	            
	    		 
	    		        String[] titulos = {"Fecha", "Folio", 
	                            "Remitente", "Guia", "Rastreo", "TipoPaquete", "Tipo Envío", "Empresa","Precio", "Costo Seguro", "Total Cobrado" };
	    		        
	    		        
	    		       Integer[] wd = {256*22, 256*20, 256*40, 256*40, 256*20,256*15,256*15,256*15,256*10,256*15,256*15,256*15,};        
	    		        // Creamos una fila en la hoja en la posicion 0
	    		        Row fila = sheet.createRow(0);
	    		        
	    		        // Creamos el encabezado
	    		        for(int i = 0; i < titulos.length; i++) {
	    		            // Creamos una celda en esa fila, en la posicion 
	    		            // indicada por el contador del ciclo
	    		        	//System.out.println("IIIIIIIII numero de registros:"+regs.size());
	    		            Cell celda = fila.createCell(i);
	    		           
	    		            	          
	    		            celda.setCellValue(titulos[i]);
	    		         //   sheet.autoSizeColumn(i);
	    		            sheet.setColumnWidth(i,wd[i]);
	    		            celda.setCellStyle(style);
	    		           
	    		            //celda.setCellStyle(headerStyle);
	    		        }
	    		     
	    		   	     
		    		        
		    		       
	    		        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    		        for(int i=0; i<this.renglones.size();i++){
	    		        	ReporteVo reng= this.renglones.get(i); //TODO terminar para el reporte con la columna fecha Correctamente
	    		    		//reng.setStrFechaCertificacion(formatter.format(reng.getFecha()));
	    		        	HSSFRow dataRow = sheet.createRow(i + 1);
	    		        	reng.llenarRenglon(dataRow);
	    		        	
	    		        }
	    		       
	    		        return workbook;
	    	          ////  FileOutputStream fileOut = new FileOutputStream(archivo); //Doy la ruta y el nombre del archivo nuevo que se generará
	    	         //   workbook.write(fileOut); //Escribo el nuevo archivo
	    	          //  fileOut.close(); //Cierro el archivo
	    		
	}
	
	
	private HSSFFont createFont(short fontColor, short fontHeight, boolean fontBold) {
		 
		HSSFWorkbook workbook =new HSSFWorkbook();
		HSSFFont font = workbook .createFont();
		font.setBold(fontBold);
		font.setColor(fontColor);
		font.setFontName("Arial");
		font.setFontHeightInPoints(fontHeight);
 
		return font;
	}
}