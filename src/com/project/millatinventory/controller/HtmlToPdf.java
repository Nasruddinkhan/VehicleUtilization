package com.project.millatinventory.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

/**
*
* @author iText
*/
public class HtmlToPdf {
   public static final String DEST = "C:/Users/Ban/Desktop/New folder (2)/inner.pdf";
   public static final String HTML = "C:/Users/Ban/Desktop/New folder (2)/index.html";

   public static void main(String[] args) throws IOException, DocumentException {
       File file = new File(DEST);
       file.getParentFile().mkdirs();
       new HtmlToPdf().createPdf(DEST);
   }

   /**
    * Creates a PDF with the words "Hello World"
    * @param file
    * @throws IOException
    * @throws DocumentException
    */
   public void createPdf(String file) throws IOException, DocumentException {
       // step 1
       Document document = new Document();
       // step 2
       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
       // step 3
       document.open();
       // step 4
      // /XMLWorkerHelper.getInstance().parseXHtml(writer, document,
      //         new FileInputStream(HTML),  new FileInputStream(DEST));
       // step 5
       document.close();
   }
}