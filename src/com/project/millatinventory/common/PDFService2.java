package com.project.millatinventory.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.project.millatinventory.model.DetailInsert;


@Service("PDFService2")
public class PDFService2 {
	private static Font chkBoxFont = FontFactory.getFont("C:\\Windows\\Fonts\\WINGDNG2.TTF", BaseFont.CP1252,
			BaseFont.EMBEDDED);
	private static Font chkBoxYesFont = FontFactory.getFont("C:\\Windows\\Fonts\\WINGDNG2.TTF", BaseFont.CP1252,
			BaseFont.EMBEDDED);
	private static Chunk noChkBox = new Chunk("\u002A", chkBoxFont);
	private static Chunk yesChkBox = new Chunk("\u0052", chkBoxYesFont);

	// private static String FILE = "C:\\pdf\\FirstPdf10.pdf";
	// private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	// Font.BOLD);
	// private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	// Font.NORMAL, BaseColor.RED);
	// private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	// Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font medium = new Font(Font.FontFamily.TIMES_ROMAN, 12);
	private static Font mediumUnderline = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE);
	private static Font medium10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
	private static Font medium8 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
	private static Font medium10bold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	/*private static Font fontArabic = FontFactory.getFont("C:/SMS/SMS-Config/maged-lt-regular.otf", "Identity-H",
			BaseFont.EMBEDDED, 8);*/
	
	private static Font fontArabic = FontFactory.getFont("C:/Windows/Fonts/ARIALUNI.TTF", "Identity-H",
			BaseFont.EMBEDDED, 12);
	private static Font fontArabicbold = FontFactory.getFont("C:/Windows/Fonts/ARIALUNI.TTF", "Identity-H",
			BaseFont.EMBEDDED, 12, Font.BOLD);
	private static Font fontArabic10 = FontFactory.getFont("C:/Windows/Fonts/ARIALUNI.TTF", "Identity-H",
			BaseFont.EMBEDDED, 10);
	private static Font fontArabic10bold = FontFactory.getFont("C:/Windows/Fonts/ARIALUNI.TTF", "Identity-H",
			BaseFont.EMBEDDED, 10, fontArabic10.BOLD);
	private static Font fontArabic8 = FontFactory.getFont("C:/Windows/Fonts/ARIALUNI.TTF", "Identity-H", 8);
	private static Font fontArabic8bold = FontFactory.getFont("C:/Windows/Fonts/ARIALUNI.TTF", "Identity-H",
			BaseFont.EMBEDDED, 10, fontArabic8.BOLD);

	public void generatePdf(DetailInsert detailInsert, String file) {
		try {
			Document document = new Document(PageSize.A4, 8, 8, 5, 5);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			// addMetaData(document);
			// addTitlePage(document);

			addContent(document, detailInsert, writer);
			document.close();
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// iText allows to add metadata to the PDF which can be viewed in your Adobe
	// Reader
	// under File -> Properties
	private static void addMetaData(Document document) {
		document.addTitle("My first PDF");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Lars Vogel");
		document.addCreator("Lars Vogel");
	}

	
	private void addContent(Document document, DetailInsert ap, PdfWriter writer)
			throws DocumentException, MalformedURLException, IOException {
	
		
		document.add(createHeader());
		
		PdfPTable table2 = new PdfPTable(12);
		
		
		PdfPCell pcell = new PdfPCell(new Phrase("Shareholder's Name ", medium));
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setBorderWidth(0f);
		pcell.setColspan(3);
		table2.addCell(pcell);

		Phrase phrase = new Phrase(ap.getApplicantName(), medium);
		phrase.add(getDottedLine());
		pcell = new PdfPCell(phrase);
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderWidth(0f);
		pcell.setColspan(7);

		table2.addCell(pcell);

		pcell = new PdfPCell();
		Phrase ph = new Phrase();
		ph.add(new Chunk("عمرو دياب,/", fontArabic));
		pcell.addElement(ph);
		pcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pcell.setBorderWidth(0f);
		pcell.setColspan(2);
		table2.addCell(pcell);

		String shareHolderNumber = ap.getApplicantNIN();
		
		pcell = new PdfPCell(new Phrase("Shareholder's Number (NIN)", medium));
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setBorderWidth(0f);
		pcell.setColspan(4);
		table2.addCell(pcell);

		phrase = new Phrase(shareHolderNumber, medium);
		phrase.add(getDottedLine());
		pcell = new PdfPCell(phrase);
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setBorderWidth(0f);
		pcell.setColspan(5);
		table2.addCell(pcell);

		pcell = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الديابعموياب,/(NIN)", fontArabic));
		pcell.addElement(ph);
		pcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pcell.setBorderWidth(0f);
		pcell.setColspan(3);
		table2.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Please update my new information as mention bellow:", medium10));
		pcell.setBorderWidth(0f);
		pcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pcell.setColspan(7);
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setHorizontalAlignment(Element.MARKED);
		table2.addCell(pcell);

		pcell = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابالحليمعمرودياالحليمعمروديابعمويا", fontArabic8));
		pcell.addElement(ph);
		pcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		pcell.setBorderWidth(0f);
		pcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pcell.setColspan(5);
		pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table2.addCell(pcell);
		document.add(table2);

		
		PdfPTable table = new PdfPTable(4);
		table.setSpacingBefore(5);
		// table.setWidthPercentage(80);
		PdfPCell c1 = new PdfPCell(new Phrase("Information", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(1);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8bold));
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.addElement(ph);

		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Name", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getApplicantName()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("ID No. / C.R No.", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getApplicantID()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Shareholder Type", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(getShareHolderPharse(ap.getApplicantType()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Guardian's ID", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getGuardianID()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Passport No.", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getPassportNo()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Date of Birth Nationality", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getDob() + "    " + ap.getCountry()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Past Code", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(getPastCodePoBox(ap, writer));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("City / Country", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getCity() + "/" + ap.getCountry()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));

		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Telephone", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getTelephone()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("Fax", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getFax()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		c1 = new PdfPCell(new Phrase("E-mail", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(ap.getEmail()));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);

		c1 = new PdfPCell(new Phrase("Merge NIN's", medium10));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(1);
		table.setTotalWidth(100f);
		table.addCell(c1);

		c1 = new PdfPCell(getNINs(ap, writer));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(2);
		table.addCell(c1);

		c1 = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابا,/", fontArabic8));
		c1.addElement(ph);
		c1.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(1);
		table.addCell(c1);
		//table.setHeaderRows(1);
		/*
		 * c1 = new PdfPCell(new Phrase("Merge NIN's"));
		 * c1.setHorizontalAlignment(Element.ALIGN_CENTER); c1.setColspan(1);
		 * table.addCell(c1);
		 * 
		 * c1 = new PdfPCell(new Phrase("")); PdfPCell c12 = new PdfPCell(new
		 * Phrase("inner")); c1.setColspan(4); // innertable.addCell(c12); //
		 * innertable.addCell(c12); // innertable.addCell(c12); //
		 * innertable.addCell(c12); table.addCell(innertable);
		 * table.addCell(c1);
		 * 
		 * c1 = new PdfPCell(new Phrase("Table Header 3"));
		 * c1.setHorizontalAlignment(Element.ALIGN_CENTER); c1.setColspan(1);
		 * table.addCell(c1);
		 */
		table.setSpacingAfter(5);
		document.add(table);

		PdfPTable table3 = new PdfPTable(12);
		PdfPCell ppcell = new PdfPCell();
		/*
		 * PdfPCell ppcell = new PdfPCell(new Phrase(
		 * "document new hi hi hi hi hi of the:")); ppcell.setBorderWidth(0f);
		 * ppcell.setColspan(4);
		 * ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		 * table3.addCell(ppcell);
		 */

		/*
		 * ppcell = new PdfPCell(); Phrase phh = new Phrase(); phh.add(new
		 * Chunk(
		 * "الحليمعمروديابعمويابالحليمعمرودياالحليمعمروديابعمويابالحليمعمر,/",
		 * fontUni)); ppcell.addElement(phh);
		 * ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		 * ppcell.setBorderWidth(0f); ppcell.setColspan(4);
		 * ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 * table3.addCell(ppcell);
		 */

		pcell = new PdfPCell(new Phrase("Dividend Payment Details:", medium10));
		pcell.setBorderWidth(0f);
		pcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pcell.setColspan(8);
		pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pcell.setHorizontalAlignment(Element.MARKED);
		table3.addCell(pcell);

		pcell = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمرورودياالحليمعمروديابعمويا,/", fontArabic10));
		pcell.addElement(ph);
		pcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		pcell.setBorderWidth(0f);
		pcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		pcell.setColspan(4);
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setHorizontalAlignment(Element.MARKED);
		table3.addCell(pcell);
		// document.add(table3);

		ppcell = new PdfPCell(new Phrase("Account No:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		phrase = new Phrase(ap.getAccountNumber(), medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Account Name:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		phrase = new Phrase(ap.getAccountName(), medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("الحليمو", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table3.addCell(ppcell);
		// document.add(table3);

		// PdfPTable table4 = new PdfPTable(8);
		ppcell = new PdfPCell(new Phrase("Bank:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		phrase = new Phrase(ap.getBank(), medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Branch:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		phrase = new Phrase(ap.getBranch(), medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("الحليمو", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("*For foriegn account only :", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(8);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("لحليمعمرديابعمويابالحليمعو", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(4);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table3.addCell(ppcell);

		document.add(table3);

		PdfPTable table5 = new PdfPTable(18);

		ppcell = new PdfPCell(new Phrase("SWIFT:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		phrase = new Phrase(ap.getSwift(), medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("IBAN:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		phrase = new Phrase(ap.getIban(), medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(3);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("الحليمو", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Currency:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		phrase = new Phrase(ap.getCurrency(), medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);
		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("الحليمو", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Bank Stamp:", medium10bold));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(5);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		phrase = new Phrase("", medium10bold);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(10);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("الحليمو", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(3);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase(
				"I agree on depositing any dividends of the share's owned by me, related to companies listed on (QCSD) and resulting from IPO at the above bank account. The bank or (QCSD) shal bear on liability in respect  of those dividends. I will not hold the bank  or (QCSD) liable in the event where the above account is blocked or closed. I also undertake to update my account informatoin if changes need to be made.",
				medium10));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(9);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk(
				" أعضاء البيانات سبيل المثال سوف تحصل على الذاكرة في كل مرة عندما يتم إنشاء الكائن.كل طالب لها رولنو فريدة واسم حتى عضو البيانات مثيل هولنفترض أن هناك 500 طالب في كليتي، والآن جميع أعضاء البيانات سبيل المثال سوف تحصل على الذاكرة في كل مرة عندما يتم إنشاء الكائن.كل طالب لها رولنو فريدة واسم حتى عضو البيانات مثيل ،  الكائنات . إذا جعلنا ثابت، هذا الحقل سوف تحصل على الذاكرة مرة واحدة فقط.",
				fontArabic8));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(9);
		ppcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(ppcell);
		document.add(table5);

		PdfPTable table6 = new PdfPTable(12);
		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk(
				"لحليمعمروديابعمويابالحليمعمحليمعمروديابعمويابالحليمعمرودياالحليمرودياالحليمعمروديابعمويابالحليمعمر,/",
				fontArabic8));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

		ppcell.setBorderWidth(0f);
		ppcell.setColspan(12);
		table6.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase(
				"I confirm that all the above information and documents attached are complete , accurate  and valid ",
				medium10));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(12);
		ppcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(ppcell);

		document.add(table6);

		PdfPTable table7 = new PdfPTable(12);

		ppcell = new PdfPCell(new Phrase("Date:", medium10));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table7.addCell(ppcell);
		phrase = new Phrase(CommonUtil.getToday("dd-MMM-yyyy"), medium10);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table7.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic8));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table7.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Shareholder's Signature:", medium10));
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(3);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table7.addCell(ppcell);

		
		ppcell = createSignature(ap.getSignature());
		
		table7.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table7.addCell(ppcell);

		document.add(table7);

		PdfPTable table8 = new PdfPTable(12);


		ppcell = new PdfPCell(new Phrase("For Official Use Only", medium10bold));
		//ppcell.setBorderWidth(0f);
		ppcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		ppcell.setColspan(6);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ppcell.setHorizontalAlignment(Element.MARKED);
		disableBorderSide(ppcell,false,true,false,true);
		
		table8.addCell(ppcell);

		ppcell = new PdfPCell();
		ph = new Phrase();
		ph.add(new Chunk("الحليمعمروديابعمويابالحليمعمرودياالحليمعمروديابع", fontArabic10));
		ppcell.addElement(ph);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		//ppcell.setBorderWidth(0f);
		ppcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		ppcell.setColspan(6);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		ppcell.setHorizontalAlignment(Element.MARKED);
		disableBorderSide(ppcell,true,false,false,true);
		table8.addCell(ppcell);
		// document.add(table8);

		// PdfPTable table9= new PdfPTable(12);
		ppcell = new PdfPCell(new Phrase("Signature:", medium8));
		// ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		disableBorderSide(ppcell,false,true,true,true);
		table8.addCell(ppcell);

		phrase = new Phrase("", medium10);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		//disableBorderSide(ppcell,true,true,true,true);
		table8.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic8));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		 ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table8.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Date:", medium10));
		 ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8.addCell(ppcell);

		phrase = new Phrase("", medium10);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		 ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic8));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		 ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table8.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Employee Name:", medium10));
		 ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8.addCell(ppcell);

		phrase = new Phrase("", medium10);
		phrase.add(getDottedLine());

		ppcell = new PdfPCell(phrase);
	    ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic8));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	// ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		disableBorderSide(ppcell, true, false, true, true);
		table8.addCell(ppcell);

		// document.add(table8);

		// PdfPTable table10= new PdfPTable(12);

		ppcell = new PdfPCell(new Phrase("MIN:", medium10));
		// ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		// disableBorderSide(ppcell);
		disableBorderSide(ppcell, false, true, true, false);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8.addCell(ppcell);

		phrase = new Phrase("", medium10);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		// ppcell.setBorderWidth(0f);
		ppcell.setColspan(3);
		// disableBorderSide(ppcell);
		disableBorderSide(ppcell, true, true, true, false);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic8));
		ppcell.addElement(phrase);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		// ppcell.setBorderWidth(0f);
		ppcell.setColspan(2);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		disableBorderSide(ppcell, true, true, true, false);
		table8.addCell(ppcell);

		ppcell = new PdfPCell(new Phrase("Action:", medium10));
		// ppcell.setBorderWidth(0f);
		ppcell.setColspan(1);
		// disableBorderSide(ppcell);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		disableBorderSide(ppcell, true, true, true, false);
		table8.addCell(ppcell);

		phrase = new Phrase("", medium10);
		phrase.add(getDottedLine());
		ppcell = new PdfPCell(phrase);
		// ppcell.setBorderWidth(0f);
		ppcell.setColspan(4);
		// disableBorderSide(ppcell);
		disableBorderSide(ppcell, true, true, true, false);
		ppcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table8.addCell(ppcell);

		ppcell = new PdfPCell();
		phrase = new Phrase();
		phrase.add(new Chunk("عمروب", fontArabic8));
		ppcell.addElement(phrase);
		
		disableBorderSide(ppcell, true, false, true, false);
		ppcell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
		// ppcell.setBorderWidth(0f);

		ppcell.setColspan(1);
		ppcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table8.addCell(ppcell);

		document.add(table8);

		
	}

	private void disableBorderSide(PdfPCell ppcell, boolean left, boolean right, boolean top, boolean bottom) {
		if(left)ppcell.disableBorderSide(Rectangle.LEFT);
		if(right)ppcell.disableBorderSide(Rectangle.RIGHT);
		if(top)ppcell.disableBorderSide(Rectangle.TOP);
		if(bottom)ppcell.disableBorderSide(Rectangle.BOTTOM);

	}

	private DottedLineSeparator getDottedLine() {
		DottedLineSeparator dot = new DottedLineSeparator();
		dot.setGap(2);
		dot.setOffset(-2);
		return dot;
	}

	

	private static Phrase getPastCodePoBox(DetailInsert ap, PdfWriter writer) throws DocumentException, IOException {
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.EMBEDDED);

		Rectangle rect = new Rectangle(50, 15);
		rect.setBorder(Rectangle.BOX);
		rect.setBorderWidth(1);

		PdfTemplate template = writer.getDirectContent().createTemplate(50, 15);
		template.setColorFill(BaseColor.BLACK);
		template.rectangle(rect);
		template.beginText();
		template.setFontAndSize(bf, 10);
		template.showTextAligned(PdfContentByte.ALIGN_LEFT, ap.getPastCode(), 10, 3, 0);
		template.endText();
		writer.releaseTemplate(template);

		Chunk c = new Chunk(Image.getInstance(template), -35, -5);
		Chunk c1 = new Chunk("P.O.Box", medium10);
		PdfTemplate template1 = writer.getDirectContent().createTemplate(50, 15);
		template1.setColorFill(BaseColor.BLACK);
		template1.rectangle(rect);
		template1.beginText();
		template1.setFontAndSize(bf, 10);
		template1.showTextAligned(PdfContentByte.ALIGN_LEFT, ap.getPoBox(), 10, 3, 0);
		template1.endText();
		writer.releaseTemplate(template1);
		Chunk c2 = new Chunk(Image.getInstance(template1), 20, -5);

		Phrase p = new Phrase();
		p.add(c);
		p.add(c1);
		p.add(c2);

		return p;
	}

	private static Phrase getNINs(DetailInsert ap, PdfWriter writer) throws DocumentException, IOException {
		Phrase p = new Phrase();

		p.add(getNINs(writer, ap.getNin1(), -11, -15));
		p.add(getNINs(writer, ap.getNin2(), -3, -15));
		p.add(getNINs(writer, ap.getNin3(), 5, -15));
		p.add(getNINs(writer, ap.getNin4(), 13, -15));
		return p;
	}

	private static Chunk getNINs(PdfWriter writer, String value, float x, float y)
			throws DocumentException, IOException {
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Rectangle rect = new Rectangle(50, 15);
		rect.setBorder(Rectangle.BOX);
		rect.setBorderWidth(1);
		PdfTemplate template = writer.getDirectContent().createTemplate(50, 15);
		template.setColorFill(BaseColor.BLACK);
		template.rectangle(rect);
		template.beginText();
		template.setFontAndSize(bf, 10);
		template.showTextAligned(PdfContentByte.ALIGN_LEFT, value, 10, 3, 0);
		template.endText();
		writer.releaseTemplate(template);
		Chunk c = new Chunk(Image.getInstance(template), x, y);
		return c;
	}

	private static Phrase getShareHolderPharse(String shareHolderType) {
		Phrase p = new Phrase();
		Chunk companyChunk = new Chunk("Company");

		if ("Company".equals(shareHolderType)) {
			p.add(new Chunk("Company", medium10bold));
			p.add(yesChkBox);
		} else {
			p.add(new Chunk("Company", medium10));
			p.add(noChkBox);
		}
		p.add(" 	");

		p.add(" ");
		if ("Minor".equals(shareHolderType)) {
			p.add(new Chunk("	Minor", medium10bold));
			p.add(yesChkBox);
		} else {
			p.add(new Chunk("	Minor", medium10));
			p.add(noChkBox);
		}
		p.add(" 	");

		p.add(" ");
		if ("Individual".equals(shareHolderType)) {
			p.add(new Chunk("	Individual", medium10bold));
			p.add(yesChkBox);
		} else {
			p.add(new Chunk("	Individual", medium10));
			p.add(noChkBox);
		}
		return p;

	}

	private PdfPTable createHeader() throws DocumentException, MalformedURLException, IOException {
		System.out.println("header");
		InputStream in = null;
		Image image = null;
		try {
			in = this.getClass().getResourceAsStream("/resources/header.jpg");
			System.out.println(in);
			image = Image.getInstance(IOUtils.toByteArray(in));
		} finally {
			in.close();
		}
		image.scaleToFit(180f, 280f);
		PdfPTable table1 = new PdfPTable(8);
		table1.setSpacingAfter(0f);
		table1.setWidthPercentage(550 / 5.23f);
		table1.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(image);
		cell.setColspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0f);
		table1.addCell(cell);

		return table1;
	}

	private PdfPCell createSignature(byte[] signature) throws DocumentException, MalformedURLException, IOException {
		System.out.println("Signature"+signature);
		PdfPCell cell;
		if(null != signature){
		Image image = Image.getInstance(signature);
		image.scaleToFit(50f, 220f);
		cell = new PdfPCell(image);
		}
		else{
			cell = new PdfPCell();
		}
		
		
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0f);
		
		return cell;
	}


	 public static void main(String a[]) {
	    	System.out.println(File.separator);
	    	PDFService2 pdf = new PDFService2();
	    	  DetailInsert ap = new DetailInsert();
	          ap.setApplicantName("Waseem");
	          ap.setApplicantNIN("9857655");
	          ap.setApplicantID("12345");
	          ap.setGuardianID("5678");
	          ap.setPassportNo("123456789");
	         ap.setDob("05/05/1994");
	          ap.setPastCode("12345");
	          ap.setAccountNumber("1234567890");
	          ap.setCity("Mumbai");
	          ap.setCountry("India");
	          ap.setCurrency("INR");
	          ap.setIban("1234567899874");
	          ap.setTelephone("1234567890");
	          ap.setEmail("mdwas@gmail.com");
	          ap.setFax("122334");
	          ap.setNin1("1111");
	          ap.setNin2("2222");
	          ap.setNin3("3333");
	          ap.setNin4("4444");
	          ap.setPoBox("123");
	      	String file="E:/FirstPdf10.pdf";
	    	pdf.generatePdf(ap, file);
	    	
	    }
}