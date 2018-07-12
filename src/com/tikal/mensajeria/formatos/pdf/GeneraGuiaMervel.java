package com.tikal.mensajeria.formatos.pdf;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.tikal.mensajeria.modelo.entity.Envio;
import com.tikal.mensajeria.modelo.entity.Paquete;
import com.tikal.mensajeria.modelo.entity.Venta;
import com.tikal.mensajeria.modelo.login.Sucursal;
import com.tikal.mensajeria.util.NumberToLetterConverter;

import java.io.*;
import java.net.MalformedURLException;
import java.util.List;
//import java.util.Date;
//import java.util.List; 
public class GeneraGuiaMervel {
	
      public GeneraGuiaMervel(Venta v, Envio e, OutputStream ops) throws MalformedURLException, IOException {

    	
    	try {
    		Rectangle envelope = new Rectangle(200, 690);
        	//	Document pdfDoc = new Document(envelope, 230f, 10f, 100f, 0f);
        		Document document = new Document(envelope,0,0,0,0); 
    		//Document document = new Document(PageSize.A6,15,15,15,15);   	  
    	        PdfWriter.getInstance(document,ops);
    	    document.open();
    	    
    	    Font f0 = new Font();
      	  //  f1.setStyle(1);
      	    f0.setSize(4);
      	    f0.setColor(BaseColor.BLACK);
    	    
    	    
    	    Font f1 = new Font();
    	  //  f1.setStyle(1);
    	    f1.setSize(8);
    	    f1.setColor(BaseColor.BLACK);
    	    
    	    Font f2 = new Font();
    	    f2.setStyle(1);
    	    f2.setSize(12);
    	    f2.setColor(BaseColor.BLACK);
    	    
    	    Font f3 = new Font();
    	    f3.setStyle(1);
    	    f3.setSize(10);
    	    f3.setColor(BaseColor.BLACK);
    	    
    	    Font f4 = new Font();
    	    f4.setStyle(1);
    	    f4.setSize(20);
    	    f4.setColor(BaseColor.BLACK);
    	   
            PdfPTable table = new PdfPTable(3);              
            
            Image imagen = Image.getInstance("img/Mervel.png");
            imagen.scaleAbsolute(200, 50);
           
      
            PdfPCell c1 = new PdfPCell(imagen);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(3);
            c1.setRowspan(5);
            c1.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);
            
            Paragraph p36 = new Paragraph("    ",f2);
            PdfPCell c36 = new PdfPCell(p36);
            c36.setHorizontalAlignment(Element.ALIGN_CENTER);
            c36.setColspan(3);c36.setRowspan(5);
            c36.setBorder(Rectangle.NO_BORDER);
            table.addCell(c36);
           
            Paragraph p2 = new Paragraph("_______________________",f1);
            PdfPCell c2 = new PdfPCell(p2);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c2.setColspan(3);c2.setRowspan(1);
            c2.setBorder(Rectangle.NO_BORDER);
            table.addCell(c2);
            
            
            
           
            Paragraph p3 = new Paragraph("Firma de recibido",f1);
            PdfPCell c3 = new PdfPCell(p3);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            c3.setColspan(3);
            c3.setBorder(Rectangle.NO_BORDER);
            table.addCell(c3);
           
            Paragraph p33 = new Paragraph("       ",f2);
            PdfPCell c33 = new PdfPCell(p33);
            c33.setHorizontalAlignment(Element.ALIGN_CENTER);
            c33.setColspan(3);c33.setRowspan(2);
            c33.setBorder(Rectangle.NO_BORDER);
            table.addCell(c33);
            
            Paragraph p5 = new Paragraph(e.getGuia().toString(),f3);
            PdfPCell c5 = new PdfPCell(p5);
            c5.setHorizontalAlignment(Element.ALIGN_LEFT);
            c5.setColspan(3);c5.setRowspan(1);
            c5.setBorder(Rectangle.NO_BORDER);
            table.addCell(c5);
            
            Paragraph p4 = new Paragraph(v.getFolio().toString(),f3);
            PdfPCell c4 = new PdfPCell(p4);
            c4.setHorizontalAlignment(Element.ALIGN_LEFT);
            c4.setColspan(1);c4.setRowspan(1);
            c4.setBorder(Rectangle.NO_BORDER);
            table.addCell(c4);
                       
            
            Paragraph p6 = new Paragraph(v.getFecha().substring(0,19),f1);
            PdfPCell c6 = new PdfPCell(p6);
            c6.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c6.setColspan(2);
            c6.setBorder(Rectangle.NO_BORDER);
            table.addCell(c6);
            
            Paragraph p7 = new Paragraph(e.getRastreo().toString(),f3);
            PdfPCell c7 = new PdfPCell(p7);
            c7.setHorizontalAlignment(Element.ALIGN_LEFT);
            c7.setColspan(3);c7.setBorder(Rectangle.NO_BORDER);
            table.addCell(c7);
            table.addCell(c33);
            
            Paragraph p37 = new Paragraph(e.getCliente().getNombre(),f3);
            PdfPCell c37 = new PdfPCell(p37);
            c37.setHorizontalAlignment(Element.ALIGN_LEFT);
            c37.setColspan(3);c37.setRowspan(1);
            c37.setBorder(Rectangle.NO_BORDER);
            table.addCell(c37);
            
            Paragraph p8 = new Paragraph(e.getCliente().getCalle(),f1);
            PdfPCell c8 = new PdfPCell(p8);
            c8.setHorizontalAlignment(Element.ALIGN_LEFT);
            c8.setColspan(4);c8.setRowspan(5);
            c8.setBorder(Rectangle.NO_BORDER);
            table.addCell(c8);
           // table.addCell(c6);
            
            Paragraph pxx = new Paragraph(e.getCliente().getColonia(),f1);
            PdfPCell cxx = new PdfPCell(pxx);
            cxx.setHorizontalAlignment(Element.ALIGN_LEFT);
            cxx.setColspan(3);
            cxx.setBorder(Rectangle.NO_BORDER);
            table.addCell(cxx);
            
            Paragraph px1 = new Paragraph(e.getCliente().getLocalidad(),f1);
            PdfPCell cx1 = new PdfPCell(px1);
            cx1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx1.setColspan(3);
            cx1.setBorder(Rectangle.NO_BORDER);
            table.addCell(cx1);
       
            Paragraph px2 = new Paragraph(e.getCliente().getMunicipio(),f1);
            PdfPCell cx2 = new PdfPCell(px2);
            cx2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx2.setColspan(3);
            cx2.setBorder(Rectangle.NO_BORDER);
            table.addCell(cx2);
            
            Paragraph px3 = new Paragraph(e.getCliente().getEstado(),f1);
            PdfPCell cx3 = new PdfPCell(px3);
            cx3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx3.setColspan(3);
            cx3.setBorder(Rectangle.NO_BORDER);
            table.addCell(cx3);
            
            Paragraph px4 = new Paragraph(e.getCliente().getTelefono(),f1);
            PdfPCell cx4 = new PdfPCell(px4);
            cx4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx4.setColspan(3);
            cx4.setBorder(Rectangle.NO_BORDER);
            table.addCell(cx4);
            
            Paragraph px = new Paragraph(e.getCliente().getCodigoPostal().toString(),f4);
            PdfPCell cx = new PdfPCell(px);
            cx.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx.setColspan(3);
            cx.setBorder(Rectangle.NO_BORDER);
           // cx.setBorder(Rectangle.NO_BORDER);
            table.addCell(cx);
            
           
           
//    // segunda parte... tabla 2
//
            document.add(table);
            document.add(new Paragraph("\n"));
            
            PdfPTable table2 = new PdfPTable(3);        
            
            Paragraph px6 = new Paragraph(e.getDestinatario().getNombre(),f3);
            PdfPCell cx6 = new PdfPCell(px6);
            cx6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx6.setColspan(3);
            cx6.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cx6);
            
            
            Paragraph p1 = new Paragraph("calle:"+e.getDestinatario().getCalle(),f1);
            PdfPCell cw = new PdfPCell(p1);
            cw.setHorizontalAlignment(Element.ALIGN_LEFT);
            cw.setColspan(3);
            cw.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cw);
            
            Paragraph p9 = new Paragraph("col:"+e.getDestinatario().getColonia(),f1);
            PdfPCell c9 = new PdfPCell(p9);
           c9.setBorder(Rectangle.NO_BORDER);
            c9.setHorizontalAlignment(Element.ALIGN_LEFT);
            c9.setColspan(3);
            c9.setRowspan(1);
          //  c9.setBackgroundColor(BaseColor.BLACK);
            table2.addCell(c9);
            
            Paragraph pg = new Paragraph("Loc:"+e.getDestinatario().getLocalidad(),f1);
            PdfPCell c11 = new PdfPCell(pg);
            c11.setHorizontalAlignment(Element.ALIGN_LEFT);
            c11.setBorder(Rectangle.NO_BORDER);
            //c11.setBackgroundColor(BaseColor.BLACK);
            c11.setColspan(3);
            table2.addCell(c11);
            
            Paragraph p10 = new Paragraph("mun:"+e.getDestinatario().getMunicipio(),f1);
            PdfPCell c10 = new PdfPCell(p10);
            c10.setHorizontalAlignment(Element.ALIGN_LEFT);
            //c10.setBackgroundColor(BaseColor.BLACK);
            c10.setBorder(Rectangle.NO_BORDER);
            c10.setColspan(3);
            table2.addCell(c10);
            
            Paragraph p12 = new Paragraph("edo:"+e.getDestinatario().getEstado(),f1);
            PdfPCell c12 = new PdfPCell(p12);
            c12.setHorizontalAlignment(Element.ALIGN_LEFT);
            c12.setBorder(Rectangle.NO_BORDER);
          //  c12.setBackgroundColor(BaseColor.BLACK);
            c12.setColspan(5);
            table2.addCell(c12);
            
            Paragraph p13 = new Paragraph("tel:"+e.getDestinatario().getTelefono(),f1);
            PdfPCell c13 = new PdfPCell(p13);
            c13.setHorizontalAlignment(Element.ALIGN_LEFT);
            //c13.setBackgroundColor(BaseColor.BLACK);
            c13.setBorder(Rectangle.NO_BORDER);
            c13.setColspan(3);
            table2.addCell(c13);
//            
            Paragraph p14 = new Paragraph(e.getDestinatario().getCodigoPostal().toString(),f4);
            PdfPCell c14 = new PdfPCell(p14);
            c14.setHorizontalAlignment(Element.ALIGN_LEFT);
           //.setBackgroundColor(BaseColor.BLACK);
            c14.setBorder(Rectangle.NO_BORDER);
            c14.setColspan(3);
            table2.addCell(c14);
            
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


