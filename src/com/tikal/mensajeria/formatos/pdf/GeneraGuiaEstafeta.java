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
import java.text.SimpleDateFormat;
import java.util.List;
//import java.util.Date;
//import java.util.List; 
public class GeneraGuiaEstafeta {


	
      public GeneraGuiaEstafeta(Venta v, Envio e, OutputStream ops) throws MalformedURLException, IOException {

    	
    	try {
    		Rectangle envelope = new Rectangle(400, 690);
        	//	Document pdfDoc = new Document(envelope, 230f, 10f, 100f, 0f);
        		//Document document = new Document(envelope,10,10,20,20); 
    		Document document = new Document(PageSize.A4,15,15,15,15);   	  
    	        PdfWriter.getInstance(document,ops);
    	    document.open();
    	    
    	    Font f0 = new Font();
      	  //  f1.setStyle(1);
      	    f0.setSize(2);
      	    f0.setColor(BaseColor.BLACK);
    	    
    	    
    	    Font f1 = new Font();
    	  //  f1.setStyle(1);
    	    f1.setSize(6);
    	    f1.setColor(BaseColor.BLACK);
    	    
    	    Font f2 = new Font();
    	    f2.setStyle(1);
    	    f2.setSize(10);
//    	    f2.setColor(BaseColor.BLACK);
    	    
    	    Font f3 = new Font();
    	    f3.setStyle(1);
    	    f3.setSize(6);
    	    f3.setColor(BaseColor.BLACK);
    	    
    	    Font f4 = new Font();
    	    f4.setStyle(1);
    	    f4.setSize(12);
    	    f4.setColor(BaseColor.BLACK);
    	   
    	    String calle="";String calle1="";
            PdfPTable table = new PdfPTable(3);              
      
            Paragraph p33 = new Paragraph("    \n\n\n\n\n\n\n\n\n\n\n\n   ",f2);
            PdfPCell c33 = new PdfPCell(p33);
            c33.setHorizontalAlignment(Element.ALIGN_CENTER);
            c33.setColspan(3);c33.setRowspan(2);
            c33.setBorder(Rectangle.NO_BORDER);
            table.addCell(c33);
            
//            Paragraph bco = new Paragraph(" ",f2);
//            PdfPCell cco = new PdfPCell(bco);
//            cco.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cco.setColspan(1);cco.setRowspan(2);
//            cco.setBorder(Rectangle.NO_BORDER);
//            table.addCell(cco);
            
            Paragraph p5 = new Paragraph(e.getGuia().toString(),f1);
            PdfPCell c5 = new PdfPCell(p5);
            c5.setHorizontalAlignment(Element.ALIGN_LEFT);
            c5.setColspan(3);c5.setRowspan(1);
            c5.setBorder(Rectangle.NO_BORDER);
            table.addCell(c5);
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			String fec= formatter.format(v.getFecha()).substring(0, 16);
            
            Paragraph p4 = new Paragraph(v.getFolio().toString()+ " \b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+fec,f3);
            PdfPCell c4 = new PdfPCell(p4);
            c4.setHorizontalAlignment(Element.ALIGN_LEFT);
            c4.setColspan(3);c4.setRowspan(1);
            c4.setBorder(Rectangle.NO_BORDER);
            table.addCell(c4);
                       
          
          
//            Paragraph p6 = new Paragraph(fec,f1);
//            PdfPCell c6 = new PdfPCell(p6);
//            c6.setHorizontalAlignment(Element.ALIGN_LEFT);
//            c6.setColspan(2);
//            c6.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c6);
            
            Paragraph p7 = new Paragraph(e.getRastreo().toString(),f3);
            PdfPCell c7 = new PdfPCell(p7);
            c7.setHorizontalAlignment(Element.ALIGN_LEFT);
            c7.setColspan(3);c7.setBorder(Rectangle.NO_BORDER);
            table.addCell(c7);
          
            
//            Paragraph p333 = new Paragraph(".        ",f2);
//            PdfPCell c333 = new PdfPCell(p333);
          //  c333.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c333.setColspan(3);c333.setRowspan(2);
//            c333.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c333);
            
            Paragraph p37 = new Paragraph(e.getCliente().getNombre(),f3);
            PdfPCell c37 = new PdfPCell(p37);
            c37.setHorizontalAlignment(Element.ALIGN_LEFT);
            c37.setColspan(1);c37.setRowspan(1);
            c37.setBorder(Rectangle.NO_BORDER);
            table.addCell(c37);
            
            Paragraph p333 = new Paragraph(" ",f2);
          PdfPCell c333 = new PdfPCell(p333);
          c333.setHorizontalAlignment(Element.ALIGN_CENTER);
          c333.setColspan(1);c333.setRowspan(2);
          c333.setBorder(Rectangle.NO_BORDER);
          table.addCell(c333);table.addCell(c333);
          
            
            
            if (e.getCliente().getCalle()=="" && e.getCliente().getNoExterior()=="" && e.getCliente().getNoInterior()==""){
            	calle="";
            }else{
            	if (e.getCliente().getNoExterior()==""){
            		calle=e.getCliente().getCalle();
            	}
            	if (e.getCliente().getNoInterior()==""){
            		calle=e.getCliente().getCalle()+" #"+e.getCliente().getNoExterior();
            	} 
            	calle=e.getCliente().getCalle()+" #"+e.getCliente().getNoExterior()+", int:"+e.getCliente().getNoInterior();
            }
            Paragraph p8 = new Paragraph(calle,f1);
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
            
            Paragraph px = new Paragraph("C.P. "+e.getCliente().getCodigoPostal().toString(),f4);
            PdfPCell cx = new PdfPCell(px);
            cx.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx.setColspan(3);
            cx.setBorder(Rectangle.NO_BORDER);
           // cx.setBorder(Rectangle.NO_BORDER);
            table.addCell(cx);
            
           
           
//    // segunda parte... tabla 2
//
            document.add(table);
            
            document.add(new Paragraph("\n",f0));
            PdfPTable table2 = new PdfPTable(3);        
            
            Paragraph px6 = new Paragraph(e.getDestinatario().getNombre(),f3);
            PdfPCell cx6 = new PdfPCell(px6);
            cx6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx6.setColspan(3);
            cx6.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cx6);
            
            Paragraph px66 = new Paragraph(e.getDestinatario().getEnAtencion(),f3);
            PdfPCell cx66 = new PdfPCell(px66);
            cx66.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx66.setColspan(3);
            cx66.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cx66);
            
            
            if (e.getDestinatario().getCalle()=="" && e.getDestinatario().getNoExterior()=="" && e.getDestinatario().getNoInterior()==""){
            	calle1="";
            }else{
            	if (e.getDestinatario().getNoExterior()==""){
            		calle1=e.getDestinatario().getCalle();
            	}
            	if (e.getDestinatario().getNoInterior()==""){
            		calle1=e.getDestinatario().getCalle()+" #"+e.getDestinatario().getNoExterior();
            	} 
            	calle1=e.getDestinatario().getCalle()+" #"+e.getDestinatario().getNoExterior()+", int:"+e.getDestinatario().getNoInterior();
            }
            Paragraph p1 = new Paragraph(calle1,f1);
            PdfPCell cw = new PdfPCell(p1);
            cw.setHorizontalAlignment(Element.ALIGN_LEFT);
            cw.setColspan(3);
            cw.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cw);
            
            Paragraph p9 = new Paragraph(e.getDestinatario().getColonia(),f1);
            PdfPCell c9 = new PdfPCell(p9);
           c9.setBorder(Rectangle.NO_BORDER);
            c9.setHorizontalAlignment(Element.ALIGN_LEFT);
            c9.setColspan(3);
            c9.setRowspan(1);
          //  c9.setBackgroundColor(BaseColor.BLACK);
            table2.addCell(c9);
            
            Paragraph pg = new Paragraph(e.getDestinatario().getLocalidad(),f1);
            PdfPCell c11 = new PdfPCell(pg);
            c11.setHorizontalAlignment(Element.ALIGN_LEFT);
            c11.setBorder(Rectangle.NO_BORDER);
            //c11.setBackgroundColor(BaseColor.BLACK);
            c11.setColspan(3);
            table2.addCell(c11);
            
            Paragraph p10 = new Paragraph(e.getDestinatario().getMunicipio(),f1);
            PdfPCell c10 = new PdfPCell(p10);
            c10.setHorizontalAlignment(Element.ALIGN_LEFT);
            //c10.setBackgroundColor(BaseColor.BLACK);
            c10.setBorder(Rectangle.NO_BORDER);
            c10.setColspan(3);
            table2.addCell(c10);
            
            Paragraph p12 = new Paragraph(e.getDestinatario().getEstado(),f1);
            PdfPCell c12 = new PdfPCell(p12);
            c12.setHorizontalAlignment(Element.ALIGN_LEFT);
            c12.setBorder(Rectangle.NO_BORDER);
          //  c12.setBackgroundColor(BaseColor.BLACK);
            c12.setColspan(5);
            table2.addCell(c12);
            
            Paragraph p13 = new Paragraph(e.getDestinatario().getTelefono(),f1);
            PdfPCell c13 = new PdfPCell(p13);
            c13.setHorizontalAlignment(Element.ALIGN_LEFT);
            //c13.setBackgroundColor(BaseColor.BLACK);
            c13.setBorder(Rectangle.NO_BORDER);
            c13.setColspan(3);
            table2.addCell(c13);
//            
            Paragraph p14 = new Paragraph("C.P. "+e.getDestinatario().getCodigoPostal().toString(),f4);
            PdfPCell c14 = new PdfPCell(p14);
            c14.setHorizontalAlignment(Element.ALIGN_LEFT);
           //.setBackgroundColor(BaseColor.BLACK);
            c14.setBorder(Rectangle.NO_BORDER);
            c14.setColspan(3);
            table2.addCell(c14);
            
            Paragraph p15 = new Paragraph(e.getDestinatario().getReferencias(),f1);
            PdfPCell c15 = new PdfPCell(p15);
            c15.setHorizontalAlignment(Element.ALIGN_LEFT);
           //.setBackgroundColor(BaseColor.BLACK);
            c15.setBorder(Rectangle.NO_BORDER);
            c15.setColspan(3);
            table2.addCell(c15);
            
            document.add(table2);
                       
            document.close();
    	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
    	} catch (DocumentException documentException) {
    	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
    	}
    }

      
      
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


