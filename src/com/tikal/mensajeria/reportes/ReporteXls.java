package com.tikal.mensajeria.reportes;

import java.io.*;
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
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.Font;
import com.tikal.mensajeria.modelo.vo.ReporteVo; 



public class ReporteXls {
	
	

	@SuppressWarnings("deprecation")
	public  ReporteXls(List<ReporteVo> regs, String archivo) throws IOException  { //, OutputStream ops

		HSSFFont headerFont;
		HSSFFont contentFont;
		HSSFWorkbook workbook;
	    
				int renglon=0;
			  	int columna=0;
				System.out.println("ya esta escribiendo..........:");
	    		 	FileInputStream file = new FileInputStream(new File(archivo));
	    		 	
	    		 	
	    		 //	HSSFWorkbook workbook = new HSSFWorkbook();
	    		 	workbook = new HSSFWorkbook();
	    		 	
	    		 	
	    		  	HSSFSheet sheet = workbook.createSheet();
	    		  	
	    		  	workbook.setSheetName(0, "Ventas");
	    		  	System.out.println("nombre de hoja:"+ 	sheet.getSheetName());
	    			////////// fuentes
    		             
	    		  	headerFont  = createFont(HSSFColor.BLACK.index, (short)12, true);
	    			contentFont = createFont(HSSFColor.BLACK.index, (short)10, false);
	    	 
	    		  	
    		        //HSSFFont font = workbook.createFont();
	    		      // font.setBoldweight(Font.BOLDITALIC);
    		        
	    		  	//////////estilos de celda
	    		 	 CellStyle headerStyle = workbook.createCellStyle();
	    		       
	    		        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	    		        headerStyle.setFont(headerFont);

	    		    CellStyle style = workbook.createCellStyle();
	    		        
	    		        style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
	    		       // style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    		  
	    		 	//file.close();
	    		        String[] titulos = {"Fecha", "Folio", 
	                            "Remitente", "Guia", "Rastreo", "TipoPaquete", "Tipo Envío", "Empresa","Precio", "Costo Seguro", "Total Cobrado", "Acumulado" };
	    		        
	    		        
	    		       Integer[] wd = {256*20, 256*20, 256*40, 256*40, 256*20,256*15,256*15,256*15,256*10,256*10,256*10,256*10,};        
	    		        // Creamos una fila en la hoja en la posicion 0
	    		        Row fila = sheet.createRow(0);
	    		        
	    		        // Creamos el encabezado
	    		        for(int i = 0; i < titulos.length; i++) {
	    		            // Creamos una celda en esa fila, en la posicion 
	    		            // indicada por el contador del ciclo
	    		        	System.out.println("IIIIIIIII numero de registros:"+regs.size());
	    		            Cell celda = fila.createCell(i);
	    		            	          
	    		            celda.setCellValue(titulos[i]);
	    		         //   sheet.autoSizeColumn(i);
	    		            sheet.setColumnWidth(i,wd[i]);
	    		       
	    		            celda.setCellStyle(headerStyle);
	    		        }
	    		     
	    		     //   fila = sheet.createRow(1);
	    		        
	    		        // Y colocamos los datos en esa fila
	    		        for (int ren=1; ren<regs.size()+1; ren++){
	    		        	fila = sheet.createRow(ren);
		    		        for(ReporteVo r:regs) {
		    		        	System.out.println("1111111");
		    		        	System.out.println("1-+"+r.getFecha());
		    		        	System.out.println("2-+"+r.getFolio().toString());
		    		        	System.out.println("3-+"+r.getRemitente());
		    		        	System.out.println("4-+"+r.getGuia());
		    		        	System.out.println("5-+"+r.getRastreo());
		    		        	System.out.println("6-+"+r.getTipoPaquete());
		    		        	System.out.println("7-+"+r.getTipoEnvio());
		    		        	System.out.println("8-+"+r.getEmpresa());
		    		        	System.out.println("9-+"+r.getCostoSeguro());
		    		        	System.out.println("10-+"+r.getTotal());
		    		        	System.out.println("prec-+"+r.getPrecio());
		    		        	
		    		        	 String[] datos = { r.getFecha(),r.getFolio().toString(),r.getRemitente(), r.getGuia(), r.getRastreo().toString(), r.getTipoPaquete()
		    		        			 ,r.getTipoEnvio(), r.getEmpresa(), r.getPrecio().toString(),"0.00", r.getTotal().toString()};
		    		        	 
		    		        	for (int x=0; x < 11; x++){   		    		
		    		        	    		        
			    		            // Creamos una celda en esa fila, en la
			    		            // posicion indicada por el contador del ciclo
			    		            Cell celda = fila.createCell(x);
			    		            celda.setCellStyle(style);
			    		            System.out.println("datpsssssssss:"+datos[x]);
			    		            celda.setCellValue(datos[x]);   
			    		            //sheet.autoSizeColumn(0,true);
		    		        	}
		    		        	 HSSFRow dataRow = sheet.createRow(datos.length+1);
				    		        HSSFCell total = dataRow.createCell(datos.length+1);
				    		        total.setCellType(Cell.CELL_TYPE_FORMULA);
				    		        total.setCellStyle(style);
				    		        total.setCellFormula(String.format("SUM(K2:K5)"));
		    		        }
		    		     
		    		       
	    		        }
	    		       
	    	            FileOutputStream fileOut = new FileOutputStream(archivo); //Doy la ruta y el nombre del archivo nuevo que se generará
	    	            workbook.write(fileOut); //Escribo el nuevo archivo
	    	            fileOut.close(); //Cierro el archivo
	    		
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