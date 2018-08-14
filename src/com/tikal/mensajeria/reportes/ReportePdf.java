package com.tikal.mensajeria.reportes;




import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
//import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.modelo.vo.ReporteVo;
import com.tikal.mensajeria.util.NumberToLetterConverter;

import java.io.*;
import java.net.MalformedURLException;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.servlet.ServletOutputStream;
//import java.util.Date;
//import java.util.List; 
public class ReportePdf { 
	
	private BaseColor tikalColor;
	
   

	


	public  ReportePdf( List<ReporteVo> regs, String sucursal,String inicio, String fin, Double total, OutputStream ops) throws MalformedURLException, IOException  {

    	
    	try {
    		
    		Document document = new Document(PageSize.LETTER.rotate(),3,3,15,15);   	  
	        PdfWriter.getInstance(document,ops);
	        document.open();
//    		Rectangle envelope = new Rectangle(320, 500);
//    	//	Document pdfDoc = new Document(envelope, 230f, 10f, 100f, 0f);
//    		Document document = new Document(envelope,0,0,0,0);   	  
//	        PdfWriter.getInstance(document,ops);
//	        document.open();
//	        
//    		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
//    		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//    		DocPrintJob pj = service.createPrintJob(); 
//    		
//    		
//    	    String ss=new String("Aquí lo que vamos a imprimir.");
//    	    byte[] bytes;
//    	    //Transformamos el texto a bytes que es lo que soporta la impresora
//    	    bytes=ss.getBytes();
//    	    //Creamos un documento (Como si fuese una hoja de Word para imprimir)
//    	    Doc doc=new SimpleDoc(bytes,flavor,null);
//    	    //Obligado coger la excepción PrintException
//    	    try {
//	    	    //Mandamos a impremir el documento
//	    	    pj.print(doc, null);
//    	    }
//    	    catch (PrintException x) {
//    	    	System.out.println("Error al imprimir: "+x.getMessage());
//    	    }
    	    
    	    Font f0 = new Font();
      	    f0.setStyle(1);
    	    f0.setSize(6);
      	    f0.setColor(BaseColor.BLACK);
    	    
    	    
    	    Font f1 = new Font();
    	  //  f1.setStyle(1);
    	    f1.setSize(6);
    	    f1.setColor(BaseColor.BLACK);
    	    
    	    Font f2 = new Font();
    	   // f2.setStyle(2);
    	    f2.setSize(6);
    	    f2.setColor(BaseColor.BLACK);
    	    f2.setStyle(1);
    	    
    	    Font f3 = new Font();
    	    f3.setStyle(1);
    	    f3.setSize(10);
    	    f3.setColor(BaseColor.BLACK);
    	   
    	    Font f4 = new Font();
        	  //  f1.setStyle(1);
        	    f4.setSize(8);
        	    f4.setColor(BaseColor.BLACK);
        	    
            PdfPTable table = new PdfPTable(6);   
            
            String g="";
            Image imagen = Image.getInstance("img/LogoMervel.png");
            imagen.scaleAbsolute(100, 40);
           
      
            PdfPCell c1 = new PdfPCell(imagen);
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            c1.setVerticalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(2);
            c1.setRowspan(1);
            c1.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);
           
            Paragraph p2 = new Paragraph("CORTE DE CAJA",f3);
            PdfPCell c2 = new PdfPCell(p2);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c2.setVerticalAlignment(Element.ALIGN_CENTER);
            c2.setHorizontalAlignment(Element.ALIGN_LEFT);
            c2.setColspan(2);c2.setRowspan(1);
            c2.setBorder(Rectangle.NO_BORDER);
            table.addCell(c2);
            
            Paragraph p211 = new Paragraph("DEL "+inicio.substring(0, 11)+" AL "+fin.substring(0,11),f3);
            PdfPCell c211 = new PdfPCell(p211);
            c211.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c211.setVerticalAlignment(Element.ALIGN_CENTER);
        
            c211.setColspan(2);c211.setRowspan(1);
            c211.setBorder(Rectangle.NO_BORDER);
            table.addCell(c211);
            
            Paragraph p100 = new Paragraph(sucursal,f3);
            PdfPCell c100 = new PdfPCell(p100);
            c100.setHorizontalAlignment(Element.ALIGN_CENTER);
            c211.setVerticalAlignment(Element.ALIGN_CENTER);        
            c100.setColspan(6);c100.setRowspan(1);
            c100.setBorder(Rectangle.NO_BORDER);
            table.addCell(c100);
            
            document.add(table);
            
           
            PdfPTable table2 = new PdfPTable(19);
            
            Paragraph p9 = new Paragraph("FECHA",f1);
            PdfPCell c9 = new PdfPCell(p9);
            //c9.setBackgroundColor(black);
            c9.setHorizontalAlignment(Element.ALIGN_CENTER);
            c9.setColspan(1);
            c9.setRowspan(1);
            c9.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c9);
            
            Paragraph p91 = new Paragraph("FOLIO",f1);
            PdfPCell c91 = new PdfPCell(p91);
            //c9.setBackgroundColor(black);
            c91.setHorizontalAlignment(Element.ALIGN_CENTER);
            c91.setColspan(1);
            c91.setRowspan(1);
            c91.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c91);
            
//            Paragraph p92 = new Paragraph("SUCURSAL",f1);
//            PdfPCell c92 = new PdfPCell(p92);
//            //c9.setBackgroundColor(black);ñlsfd
         
//            c92.setHorizontalAlignment(Element.ALIGN_LEFT);
//            c92.setColspan(3);
//            c92.setRowspan(1);
//       //     c92.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            table2.addCell(c92);
            
            Paragraph p93 = new Paragraph("REMITENTE",f1);
            PdfPCell c93 = new PdfPCell(p93);
            //c9.setBackgroundColor(black);
            c93.setHorizontalAlignment(Element.ALIGN_CENTER);
            c93.setColspan(3);
            c93.setRowspan(1);
            c93.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c93);
            
            Paragraph p94 = new Paragraph("GUIA",f1);
            PdfPCell c94 = new PdfPCell(p94);
            //c9.setBackgroundColor(black);
            c94.setHorizontalAlignment(Element.ALIGN_CENTER);
            c94.setColspan(3);
            c94.setRowspan(1);
            c94.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c94);
            
            Paragraph p95 = new Paragraph("RASTREO",f1);
            PdfPCell c95 = new PdfPCell(p95);
            //c9.setBackgroundColor(black);
            c95.setHorizontalAlignment(Element.ALIGN_CENTER);
            c95.setColspan(2);
            c95.setRowspan(1);
            c95.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c95);
            
            Paragraph p96 = new Paragraph("TIPO DE PAQUETE",f1);
            PdfPCell c96 = new PdfPCell(p96);
            //c9.setBackgroundColor(black);
            c96.setHorizontalAlignment(Element.ALIGN_CENTER);
            c96.setColspan(2);
            c96.setRowspan(1);
            c96.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c96);
            
            Paragraph p97 = new Paragraph("TIPO DE ENVÍO",f1);
            PdfPCell c97 = new PdfPCell(p97);
            //c9.setBackgroundColor(black);
            c97.setHorizontalAlignment(Element.ALIGN_CENTER);
            c97.setColspan(2);
            c97.setRowspan(1);
            c97.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c97);
            
            Paragraph p98 = new Paragraph("EMPRESA",f1);
            PdfPCell c98 = new PdfPCell(p98);
            //c9.setBackgroundColor(black);
            c98.setHorizontalAlignment(Element.ALIGN_CENTER);
            c98.setColspan(2);
            c98.setRowspan(1);
            c98.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c98);
            
            Paragraph p99 = new Paragraph("PRECIO",f1);
            PdfPCell c99 = new PdfPCell(p99);
            //c9.setBackgroundColor(black);
            c99.setHorizontalAlignment(Element.ALIGN_CENTER);
            c99.setColspan(1);
            c99.setRowspan(1);
            c99.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c99);
            
            Paragraph p900 = new Paragraph("SEGURO",f1);
            PdfPCell c900 = new PdfPCell(p900);
            //c9.setBackgroundColor(black);
            c900.setHorizontalAlignment(Element.ALIGN_CENTER);
            c900.setColspan(1);
            c900.setRowspan(1);
            c900.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c900);
            
            Paragraph p901= new Paragraph("TOTAL",f1);
            PdfPCell c901 = new PdfPCell(p901);
            //c9.setBackgroundColor(black);
            c901.setHorizontalAlignment(Element.ALIGN_CENTER);
            c901.setColspan(1);
            c901.setRowspan(1);
            c901.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table2.addCell(c901);
            
            
            for (ReporteVo rvo:regs){
            	 Paragraph p14 = new Paragraph(rvo.getFecha().substring(4,10),f1);
                 PdfPCell c14 = new PdfPCell(p14);
                 c14.setHorizontalAlignment(Element.ALIGN_LEFT);
                //.setBackgroundColor(BaseColor.BLACK);
                 c14.setColspan(1);
                 table2.addCell(c14);
                 
                 Paragraph p15 = new Paragraph(rvo.getFolio().toString(),f1);
                 PdfPCell c15 = new PdfPCell(p15);
                 c15.setHorizontalAlignment(Element.ALIGN_LEFT);
                // c15.setBackgroundColor(BaseColor.BLACK);
                 c15.setColspan(1);
                 table2.addCell(c15);
                 
//                 Paragraph p17 = new Paragraph(rvo.getSucursal(),f1);
//                 PdfPCell c17 = new PdfPCell(p17);
//                 c17.setHorizontalAlignment(Element.ALIGN_LEFT);
//                //.setBackgroundColor(BaseColor.BLACK);
//                 c17.setColspan(3);
//                 table2.addCell(c17);
                 
                 Paragraph p16 = new Paragraph(rvo.getRemitente(),f1);
                 PdfPCell c16 = new PdfPCell(p16);
                 c16.setHorizontalAlignment(Element.ALIGN_LEFT);
                // c15.setBackgroundColor(BaseColor.BLACK);
                 c16.setColspan(3);
                 table2.addCell(c16);
//
              
                 
                 if (rvo.getGuia()==null){
                	 g="";
                 }else{
                	 g=rvo.getGuia().toString();
                 }
                 Paragraph p18 = new Paragraph(g,f1);
                 PdfPCell c18= new PdfPCell(p18);
                 c18.setHorizontalAlignment(Element.ALIGN_LEFT);
                //.setBackgroundColor(BaseColor.BLACK);
                 c18.setColspan(3);
                 table2.addCell(c18);
                 
                 Paragraph p19 = new Paragraph(rvo.getRastreo(),f1);
                 PdfPCell c19 = new PdfPCell(p19);
                 c19.setHorizontalAlignment(Element.ALIGN_LEFT);
                // c15.setBackgroundColor(BaseColor.BLACK);
                 c19.setColspan(2);
                 table2.addCell(c19);
                 
                 Paragraph p20 = new Paragraph(rvo.getTipoPaquete(),f1);
                 PdfPCell c20= new PdfPCell(p20);
                 c20.setHorizontalAlignment(Element.ALIGN_LEFT);
                //.setBackgroundColor(BaseColor.BLACK);
                 c20.setColspan(2);
                 table2.addCell(c20);
                 
                 Paragraph p21 = new Paragraph(rvo.getTipoEnvio(),f1);
                 PdfPCell c21 = new PdfPCell(p21);
                 c21.setHorizontalAlignment(Element.ALIGN_LEFT);
                // c15.setBackgroundColor(BaseColor.BLACK);
                 c21.setColspan(2);
                 table2.addCell(c21);
                 
                 
                 
                 Paragraph p22 = new Paragraph(rvo.getEmpresa(),f1);
                 PdfPCell c22= new PdfPCell(p22);
                 c22.setHorizontalAlignment(Element.ALIGN_LEFT);
                //.setBackgroundColor(BaseColor.BLACK);
                 c22.setColspan(2);
                 table2.addCell(c22);
                 
                 Paragraph p23 = new Paragraph(rvo.getPrecio().toString(),f1);
                 PdfPCell c23 = new PdfPCell(p23);
                 c23.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // c15.setBackgroundColor(BaseColor.BLACK);
                 c23.setColspan(1);
                 table2.addCell(c23);
                 
                 Paragraph p24 = new Paragraph(rvo.getCostoSeguro().toString(),f1);
                 PdfPCell c24 = new PdfPCell(p24);
                 c24.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // c15.setBackgroundColor(BaseColor.BLACK);
                 c24.setColspan(1);
                 table2.addCell(c24);
                 
                 Paragraph p25 = new Paragraph(rvo.getTotal().toString(),f1);
                 PdfPCell c25 = new PdfPCell(p25);
                 c25.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // c15.setBackgroundColor(BaseColor.BLACK);
                 c25.setColspan(1);
                 table2.addCell(c25);
                 
//                
//                 Paragraph p266 = new Paragraph("Peso:",f1);
//                 PdfPCell c266= new PdfPCell(p266);
//                 c266.setHorizontalAlignment(Element.ALIGN_LEFT);
//                //.setBackgroundColor(BaseColor.BLACK);
//                 c266.setColspan(2);
//                 table2.addCell(c266);
//                 
//               
//                 
//                 Paragraph p277 = new Paragraph(en.getPaquete().getPeso().toString(),f3);
//                 PdfPCell c277 = new PdfPCell(p277);
//                 c277.setHorizontalAlignment(Element.ALIGN_LEFT);
//                // c15.setBackgroundColor(BaseColor.BLACK);
//                 c277.setColspan(2);
//                 table2.addCell(c277);
//                 
//                 Paragraph p26 = new Paragraph("Peso Vol.:",f1);
//                 PdfPCell c26= new PdfPCell(p26);
//                 c26.setHorizontalAlignment(Element.ALIGN_LEFT);
//                //.setBackgroundColor(BaseColor.BLACK);
//                 c26.setColspan(2);
//                 table2.addCell(c26);
//                 
//                 Paragraph p27 = new Paragraph(en.getPaquete().getPesoVol().toString(),f3);
//                 PdfPCell c27 = new PdfPCell(p27);
//                 c27.setHorizontalAlignment(Element.ALIGN_LEFT);
//                // c15.setBackgroundColor(BaseColor.BLACK);
//                 c27.setColspan(2);
//                 table2.addCell(c27);
//                 
//                
//
//                 Paragraph p28 = new Paragraph("",f3);
//                 PdfPCell c28= new PdfPCell(p28);
//                 c28.setHorizontalAlignment(Element.ALIGN_LEFT);
//                //.setBackgroundColor(BaseColor.BLACK);
//                 c28.setBorder(Rectangle.NO_BORDER);
//                 c28.setColspan(4);
//                 table2.addCell(c28);
//
            	
            	
            	
            }
            
            
            Paragraph p28 = new Paragraph("TOTAL DE VENTA:",f2);
          PdfPCell c28= new PdfPCell(p28);
          c28.setHorizontalAlignment(Element.ALIGN_RIGHT);
         //.setBackgroundColor(BaseColor.BLACK);
          c28.setBorder(Rectangle.NO_BORDER);
          c28.setColspan(18);
          table2.addCell(c28);
          
          Paragraph p29 = new Paragraph(total.toString(),f2);
          PdfPCell c29= new PdfPCell(p29);
          c29.setHorizontalAlignment(Element.ALIGN_RIGHT);
         //.setBackgroundColor(BaseColor.BLACK);
          c29.setBorder(Rectangle.NO_BORDER);
          c29.setColspan(1);
          table2.addCell(c29);
            
            
            
            
//                      
            document.add(table2);
            document.close();
    	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
    	} catch (DocumentException documentException) {
    	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
    	}
    }

      
//      public void GeneraComDisPdf(DetalleDiscrepanciaVo det, Document document) throws DocumentException {
//          // Aquí introduciremos el código para crear el PDF.      	  
//      
//    	  	PdfPTable table2 = new PdfPTable(12);      
//    	    Font fuente = new Font();
//	  	    fuente.setStyle(1);
//	  	    fuente.setSize(10);
//	  	    fuente.setStyle(Font.BOLD);
//	  	    Font f1 = new Font();
//	  	    f1.setStyle(2);
//	  	    f1.setSize(9);
//	  	    Font f2 = new Font();
//	  	    f2.setStyle(3);
//	  	    f2.setSize(8);
//            Paragraph d = new Paragraph("Logo");
//            PdfPCell c0 = new PdfPCell(d);
//            c0.setHorizontalAlignment(Element.ALIGN_LEFT);
//            c0.setColspan(12);
//            table2.addCell(c0);
//            Paragraph a = new Paragraph("CROSS AIR SERVICES, S.A. DE C.V.",f1);
//            PdfPCell celdaFinal = new PdfPCell(a);
//            celdaFinal.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            celdaFinal.setColspan(12);
//            table2.addCell(celdaFinal);
//            Paragraph b =new Paragraph("DISCREPANCY REPORT / REPORTE DE DISCREPANCIAS: \n",fuente);
//            PdfPCell c2 =new PdfPCell(b);
//            c2.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c2.setColspan(12);
//            table2.addCell(c2);
//          
//            Paragraph p2=new Paragraph("CUSTOMER / CLIENTE:"+det.getNombreEmpresa() ,f1);
//            PdfPCell c3 =new PdfPCell(p2);
//            c3.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c3.setColspan(12);
//            table2.addCell(c3);
//            
//            Paragraph p3=new Paragraph("MARK/MODEL - MARCA/MODELO:\n\n"+det.getModelo() ,f1);
//            PdfPCell c4 =new PdfPCell(p3);
//            c4.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c4.setColspan(3);
//            table2.addCell(c4);
//            
//            Paragraph p4=new Paragraph("N/S:\n\n"+det.getNoSerie() ,f1);
//            PdfPCell c5 =new PdfPCell(p4);
//            c5.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c5.setColspan(3);
//            table2.addCell(c5);
//            
//            Paragraph p5=new Paragraph("REG. / MATRICULA:\n\n"+det.getMatricula() ,f1);
//            PdfPCell c6 =new PdfPCell(p5);
//            c6.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c6.setColspan(3);
//            table2.addCell(c6);
//            
//            Paragraph p6=new Paragraph("O.T. #:\n\n"+det.getMatricula() ,f1);
//            PdfPCell c7 =new PdfPCell(p6);
//            c7.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c7.setColspan(3);
//            table2.addCell(c7);
//            
//            Paragraph p7=new Paragraph("DISCREPANCIA No:"+d.lastIndexOf(det) ,f1);
//            PdfPCell c8 =new PdfPCell(p7);
//            c8.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c8.setColspan(6);
//            table2.addCell(c8);
//            
//            Paragraph p8=new Paragraph("DATE / FECHA:"+det.getFechaOrden(),f1);
//            PdfPCell c9 =new PdfPCell(p8);
//            c9.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c9.setColspan(6);
//            table2.addCell(c9);
//            
//            Paragraph p9=new Paragraph("DISCREPANCY / DISCREPANCIA:",f1);
//            PdfPCell c10 =new PdfPCell(p9);
//            c10.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c10.setColspan(6);
//            table2.addCell(c10);
//            
//            Paragraph p10=new Paragraph("CORRECT ACCTION / ACCION CORRECTIVA:",f1);
//            PdfPCell c11 =new PdfPCell(p10);
//            c11.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c11.setColspan(6);
//            table2.addCell(c11);
//            
//            Paragraph p11=new Paragraph("\n"+det.getDescripcion()+"\n",fuente);
//            PdfPCell c12 =new PdfPCell(p11);
//            c12.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c12.setColspan(6);
//            table2.addCell(c12);
//            
//            Paragraph p12=new Paragraph("\n"+det.getAccion()+"\n",fuente);
//            PdfPCell c13 =new PdfPCell(p12);
//            c13.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c13.setColspan(6);
//            table2.addCell(c13);
//            
//            Paragraph p13=new Paragraph("\n",f1); ////celda invisible
//            PdfPCell c14 =new PdfPCell(p13);
//            c14.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c14.setColspan(6);
//            table2.addCell(c14);
//            
//            Paragraph p14=new Paragraph("MAN TIME HOURS / TIEMPO HORAS HOMBRE:",f1); 
//            PdfPCell c15 =new PdfPCell(p14);
//            c15.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c15.setColspan(6);
//            table2.addCell(c15);
//            
//            Paragraph p15=new Paragraph("DESCRIPTION / DESCRIPCION:",f1); 
//            PdfPCell c16 =new PdfPCell(p15);
//            c16.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c16.setColspan(2);
//            table2.addCell(c16);
//            
//            Paragraph p16=new Paragraph("PART NUMBER / NUMERO DE PARTE:",f1); 
//            PdfPCell c17=new PdfPCell(p16);
//            c17.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c17.setColspan(2);
//            table2.addCell(c17);
//            
//            Paragraph p17=new Paragraph("QUANTITY / CANTIDAD:",f1); 
//            PdfPCell c18=new PdfPCell(p17);
//            c18.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c18.setColspan(2);
//            table2.addCell(c18);
//            
//            Paragraph p18=new Paragraph("N/S REMOVABLE / N/S REMOVIDA:",f1); 
//            PdfPCell c19=new PdfPCell(p18);
//            c19.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c19.setColspan(2);
//            table2.addCell(c19);
//            
//            Paragraph p19=new Paragraph("N/S INSTALL / N/S INSTALADA:",f1); 
//            PdfPCell c20=new PdfPCell(p19);
//            c20.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c20.setColspan(2);
//            table2.addCell(c20);
//            
//            Paragraph p20=new Paragraph("OSERVATION/OBSERVATIONS:",f1); 
//            PdfPCell c21=new PdfPCell(p20);
//            c21.setHorizontalAlignment(Element.ALIGN_CENTER);             
//            c21.setColspan(2);
//            table2.addCell(c21);
//            
//            List<ComDisVo> cds = det.getComponentes();
//            System.out.println("cds:"+cds);
//            if (cds.isEmpty()){
//            	
//	            Paragraph pp=new Paragraph("\n",f1); 
//	            PdfPCell cp=new PdfPCell(pp);
//	            cp.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            cp.setColspan(2);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//            }
//            for (ComDisVo cd : cds){
//            	
//            	System.out.println("cds. nombre:"+cd.getNombre_componente());
//	            Paragraph p21=new Paragraph(cd.getNombre_componente(),f1); 
//	            PdfPCell c22=new PdfPCell(p21);
//	            c22.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c22.setColspan(2);
//	            table2.addCell(c22);
//	            
//	            System.out.println("cds. parte:"+cd.getNoParte());
//	            Paragraph p22=new Paragraph(cd.getNoParte(),f1); 
//	            PdfPCell c23=new PdfPCell(p22);
//	            c23.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c23.setColspan(2);
//	            table2.addCell(c23);
//	            
//	            Paragraph p23=new Paragraph(cd.getCantidad().toString(),f1); 
//	            PdfPCell c24=new PdfPCell(p23);
//	            c24.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c24.setColspan(2);
//	            table2.addCell(c24);
//	            
//	            Paragraph p24=new Paragraph("N/A",f1); 
//	            PdfPCell c25=new PdfPCell(p24);
//	            c25.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c25.setColspan(2);
//	            table2.addCell(c25);
//	            table2.addCell(c25);
//	            
//	            Paragraph p25=new Paragraph("las observaciones de horas hombre",f1); 
//	            PdfPCell c26=new PdfPCell(p25);
//	            c26.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            c26.setColspan(2);
//	            table2.addCell(c26);
//	            
//	            
//            
//            }
//            /////////////////////////validar cuantos comps vienen para poner los demas celdas vacias
//           // for (){
//            	
//          //  }
//            Paragraph p26=new Paragraph("REMOVIBLE BY/ REMOVIDO POR:\n",f1); 
//            PdfPCell c27=new PdfPCell(p26);
//            c27.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c27.setColspan(6);
//            table2.addCell(c27);
//            
//            Paragraph p27=new Paragraph("INSTALL BY/ INSTALADA POR:\n",f1); 
//            PdfPCell c28=new PdfPCell(p27);
//            c28.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c28.setColspan(6);
//            table2.addCell(c28);
//            
//            Paragraph p28=new Paragraph("SIGN / FIRMA:\n\n",f1); 
//            PdfPCell c29=new PdfPCell(p28);
//            c29.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c29.setColspan(6);
//            table2.addCell(c29);
//            table2.addCell(c29);
//            
//            Paragraph p29=new Paragraph("PERMISSION / LICENCIA:\n",f1); 
//            PdfPCell c30=new PdfPCell(p29);
//            c30.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c30.setColspan(6);
//            table2.addCell(c30);
//            table2.addCell(c30);
//            
//            Paragraph p30=new Paragraph("DATE / FECHA:\n",f1); 
//            PdfPCell c31=new PdfPCell(p30);
//            c31.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c31.setColspan(6);
//            table2.addCell(c31);
//            table2.addCell(c31);
//            
//            Paragraph p31=new Paragraph("CUSTOMER AUTORIZATION / AUTORIZADO POR EL CLIENTE:\n\n NAME / NOMBRE:\n\n SIGN / FIRMA:\n\n",f1); 
//            PdfPCell c32=new PdfPCell(p31);
//            c32.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c32.setColspan(12);
//            table2.addCell(c32);
//            
//            Paragraph p32=new Paragraph("                                  FORM AUTORIZATION / MEDIO DE AUTORIZACION:\n PERSONAL:\n\n BY TELEFON / VIA TELEFONICA:\n\n "
//            		+ "E-MAIL / CORREO ELECTRONICO:\n\n  HOUR / DATE / HORA Y FECHA \n\n",f1); 
//            PdfPCell c33=new PdfPCell(p32);
//            c33.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c33.setColspan(12);
//            table2.addCell(c33);
//            
//            Paragraph p33=new Paragraph("\n",f1); 
//            PdfPCell c34=new PdfPCell(p33);
//            c34.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c34.setColspan(6);
//            table2.addCell(c34);
//            
//            Paragraph p34=new Paragraph("Vo Bo INSPECTOR\n\n",f1); 
//            PdfPCell c35=new PdfPCell(p34);
//            c35.setHorizontalAlignment(Element.ALIGN_LEFT);             
//            c35.setColspan(6);
//            table2.addCell(c35);
//            
//            
//            document.add(table2);  
//            document.add(new Paragraph("\n\n\n"));
//            
//      }

      
     


	public String letras(Double number){
    	  Integer entero = number.intValue();//.longValue();
    	  double decimales = number - entero;
    	  
    	  
    	  StringBuffer resultado = new StringBuffer(); 
    	  String strEntero = letras(entero.doubleValue()); 
    	
    	  String strDecimales = letras(decimales); 
    	  resultado.append(strEntero); 
    	  resultado.append(" con "); 
    	  resultado.append(strDecimales); 
    	  return resultado.toString(); 
    	  
    	  //return "si";
      }
	
	
}

