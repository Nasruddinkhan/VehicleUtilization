package com.project.millatinventory.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.millatinventory.common.CommonUtil;
import com.project.millatinventory.controller.SearchBean;
import com.project.millatinventory.dao.ReportDao;
import com.project.millatinventory.model.DataEntry;
import com.project.millatinventory.model.Deal;
import com.project.millatinventory.model.Dormancy;
import com.project.millatinventory.model.Portfolio;
import com.project.millatinventory.model.Sites;
import com.project.millatinventory.model.Vehicle;
import com.project.millatinventory.service.ReportService;

@Service("ReportService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,value="mySQLTransactionManager")
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDao reportDao;
	private static Font chkBoxFont = FontFactory.getFont("C:\\Windows\\Fonts\\WINGDNG2.TTF", BaseFont.CP1252,
			BaseFont.EMBEDDED);
	private static Font chkBoxYesFont = FontFactory.getFont("C:\\Windows\\Fonts\\WINGDNG2.TTF", BaseFont.CP1252,
			BaseFont.EMBEDDED);
	private static Chunk noChkBox = new Chunk("\u002A", chkBoxFont);
	private static Chunk yesChkBox = new Chunk("\u0052", chkBoxYesFont);

	private static Font smallBold1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font medium10 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private static Font medium10bold1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	private static Font medium12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font medium10bold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	private static Font medium12bold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE);
	private static Font medium14bold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	Logger logger = Logger.getLogger(ReportServiceImpl.class);

	@Override
	public List<Portfolio> getPortfolioDetails() {

		return reportDao.getPortfolioDetails();
	}

	@Override
	public Deal getDealbyRef(int refNo) {

		return reportDao.getDealbyRef(refNo);
	}

	@Override
	public List<Dormancy> getDormancyLetterDetails() {

		return reportDao.getDormancyLetterDetails();
	}

	public void generatePortfolioReport(Portfolio portfolio, String file)
			throws DocumentException, MalformedURLException, IOException {

		// Create Document instance.
		Document document = new Document();

		// Create OutputStream instance.
		OutputStream outputStream = null;
		try {
			File file2 = new File(file);

			outputStream = new FileOutputStream(file2);
			// Create PDFWriter instance.
			PdfWriter.getInstance(document, outputStream);
			// Open the document.
			document.open();

			PdfPTable tableHeader = createHeader();
			document.add(tableHeader);

			PdfPTable table2 = new PdfPTable(1);
			table2.setWidthPercentage(90);
			PdfPCell pcell2 = new PdfPCell(new Phrase("Name of the Client: " + portfolio.getClientName(), smallBold));
			pcell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell2.setBorderWidth(0f);
			table2.setSpacingAfter(20f);
			table2.addCell(pcell2);
			document.add(table2);

			PdfPTable table3 = new PdfPTable(1);
			PdfPCell pcell3 = new PdfPCell(new Phrase("Figures in QAR", smallBold));
			pcell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pcell3.setRowspan(3);
			pcell3.setBorderWidth(0f);
			table3.setSpacingAfter(20f);
			table3.addCell(pcell3);
			document.add(table3);
			System.out.println("89");
			// Create Table object, Here 4 specify the no. of columns
			PdfPTable table = new PdfPTable(5);
			// Table.set(new int[]{1, 1, 1,1,1});
			table.setWidthPercentage(80);

			// Create cells
			PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Date", medium10bold));
			pdfPCell1.setFixedHeight(20);
			PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Total injections", medium10bold));
			PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Total withdrawals", medium10bold));
			PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Total accrued profits", medium10bold));
			PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("Account value*", medium10bold));
			PdfPCell pdfPCell6 = new PdfPCell(new Paragraph(CommonUtil.getToday("dd-MMM-yyyy")));
			pdfPCell6.setFixedHeight(20);
			PdfPCell pdfPCell7 = new PdfPCell(new Paragraph(portfolio.getTotalInjection().toString()));
			PdfPCell pdfPCell8 = new PdfPCell(new Paragraph(portfolio.getTotalWithdrawal().toString()));
			PdfPCell pdfPCell9 = new PdfPCell(new Paragraph(portfolio.getTotalProfit().toString()));
			PdfPCell pdfPCell10 = new PdfPCell(new Paragraph(portfolio.getAccountValue().toString()));

			// Add cells to table
			table.addCell(pdfPCell1);
			table.addCell(pdfPCell2);
			table.addCell(pdfPCell3);
			table.addCell(pdfPCell4);
			table.addCell(pdfPCell5);
			// Add cells to table
			table.addCell(pdfPCell6);
			table.addCell(pdfPCell7);
			table.addCell(pdfPCell8);
			table.addCell(pdfPCell9);
			table.addCell(pdfPCell10);

			// Add content to the document using Table objects.
			document.add(table);

			PdfPTable table4 = new PdfPTable(1);
			PdfPCell pcell4 = new PdfPCell(new Phrase("*Includes accrued profits"));
			pcell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell4.setRowspan(3);

			pcell4.setBorderWidth(0f);
			table4.addCell(pcell4);
			table4.setSpacingBefore(50f);
			table4.setSpacingAfter(60f);
			document.add(table4);
			// Close document and outputStream.

			PdfPTable tableFooter = createFooter();
			document.add(tableFooter);

		} finally {
			document.close();
			outputStream.close();
		}

	}

	private PdfPTable createHeader() throws DocumentException, MalformedURLException, IOException {
		System.out.println("header");
		Image image = null;
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/resources/headerPortfolio.png");
			System.out.println(in);
			image = Image.getInstance(IOUtils.toByteArray(in));
		} finally {
			in.close();
		}
		PdfPTable table1 = new PdfPTable(8);
		table1.setSpacingAfter(80f);
		table1.setWidthPercentage(480 / 5.23f);
		table1.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(image);
		cell.setColspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0f);
		table1.addCell(cell);
		return table1;
	}

	private PdfPTable createFooter() throws DocumentException, MalformedURLException, IOException {
		System.out.println("footer");
		Image image = null;
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/resources/footer.png");
			System.out.println(in);
			image = Image.getInstance(IOUtils.toByteArray(in));
		} finally {
			in.close();
		}

		PdfPTable table1 = new PdfPTable(8);
		// table1.setSpacingAfter(50f);
		table1.setWidthPercentage(480 / 5.23f);
		table1.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(image);
		cell.setColspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0f);
		table1.addCell(cell);
		return table1;
	}

	public static void main(String[] args) throws MalformedURLException, DocumentException, IOException {
		Portfolio p = new Portfolio();
		String file = "E:/khan.pdf";
		p.setAccountValue(new BigDecimal(10));
		p.setClientName("Shakil");
		p.setTotalInjection(new BigDecimal(10));
		p.setTotalProfit(new BigDecimal(10));
		p.setTotalWithdrawal(new BigDecimal(10));
		System.out.println(p);
		new ReportServiceImpl().generatePortfolioReport(p, file);
	}

	public void generateDormancyReport(Dormancy dormancy, String file) {
		try {
			// Create Document instance.
			Document document = new Document();

			// Create OutputStream instance.
			OutputStream outputStream = new FileOutputStream(file);

			// Create PDFWriter instance.
			PdfWriter instance = PdfWriter.getInstance(document, outputStream);

			instance.setEncryption(dormancy.getAccountNumber().getBytes(), "owner".getBytes(), PdfWriter.ALLOW_PRINTING,
					PdfWriter.ENCRYPTION_AES_128);
			// Open the document.
			document.open();

			PdfPTable tableHeader = createDormancyHeader();
			document.add(tableHeader);

			PdfPTable table1 = new PdfPTable(10);
			PdfPCell pcell = new PdfPCell(new Phrase("Date :", smallBold1));
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setBorderWidth(0f);
			pcell.setColspan(2);
			table1.addCell(pcell);

			PdfPCell tcell = new PdfPCell(new Phrase(CommonUtil.getToday("dd-MMM-yyyy hh:mm:ss")));

			tcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			tcell.setBorderWidth(0f);
			tcell.setColspan(8);
			table1.addCell(tcell);
			document.add(table1);

			PdfPTable table2 = new PdfPTable(10);
			PdfPCell pcell2 = new PdfPCell(new Phrase("Mr. : ", smallBold1));
			pcell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell2.setBorderWidth(0f);
			pcell2.setColspan(2);
			table2.addCell(pcell2);

			PdfPCell tcell2 = new PdfPCell(new Phrase(dormancy.getName()));

			tcell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			tcell2.setBorderWidth(0f);
			tcell2.setColspan(8);
			table2.addCell(tcell2);
			document.add(table2);

			PdfPTable table3 = new PdfPTable(10);
			PdfPCell pcell3 = new PdfPCell(new Phrase("Fax : ", smallBold1));
			pcell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell3.setBorderWidth(0f);
			pcell3.setColspan(2);
			table3.addCell(pcell3);

			PdfPCell tcell3 = new PdfPCell(new Phrase(dormancy.getAddress1()));

			tcell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			tcell3.setBorderWidth(0f);
			tcell3.setColspan(8);
			table3.addCell(tcell3);
			document.add(table3);

			PdfPTable table4 = new PdfPTable(10);
			PdfPCell pcell4 = new PdfPCell(new Phrase("P.O.Box :", smallBold1));
			pcell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell4.setBorderWidth(0f);
			pcell4.setColspan(2);
			table4.addCell(pcell4);

			PdfPCell tcell4 = new PdfPCell(new Phrase(dormancy.getAddress2()));

			tcell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			tcell4.setBorderWidth(0f);
			tcell4.setColspan(8);
			table4.addCell(tcell4);
			document.add(table4);

			PdfPTable table5 = new PdfPTable(4);
			table5.setSpacingAfter(12);
			PdfPCell pcell5 = new PdfPCell(new Phrase("Doha - Qatar", smallBold1));
			pcell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell5.setBorderWidth(0f);
			pcell5.setColspan(4);
			table5.addCell(pcell5);
			document.add(table5);

			PdfPTable table6 = new PdfPTable(10);
			PdfPCell pcell6 = new PdfPCell(new Phrase("Sub : ", smallBold1));
			pcell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell6.setBorderWidth(0f);
			pcell6.setColspan(1);
			table6.addCell(pcell6);

			PdfPCell tcell6 = new PdfPCell(new Phrase("Your current/Saving/Deposit Account", medium12));

			tcell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			tcell6.setBorderWidth(0f);
			tcell6.setColspan(9);
			table6.addCell(tcell6);
			document.add(table6);

			PdfPTable table7 = new PdfPTable(4);

			PdfPCell pcell7 = new PdfPCell(getCheckBox(dormancy.getAccountType()));
			pcell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell7.setColspan(4);
			pcell7.setBorderWidth(0f);
			table7.addCell(pcell7);
			document.add(table7);

			PdfPTable table8 = new PdfPTable(4);
			PdfPCell pcell8 = new PdfPCell(new Phrase("No :" + dormancy.getAccountNumber()));
			pcell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell8.setBorderWidth(0f);
			pcell8.setColspan(4);
			table8.addCell(pcell8);
			document.add(table8);

			PdfPTable table9 = new PdfPTable(4);
			PdfPCell pcell9 = new PdfPCell(new Phrase(
					"With us is inactive since " + CommonUtil.convertFormat(dormancy.getDate(), "dd-MMM-yyyy")
							+ " and since all the aattempts to contact you have been exhausted, we would like to daraw your kind attention that the above account($) has/have been frozen.",
					medium12));
			pcell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell9.setBorderWidth(0f);
			pcell9.setColspan(4);
			table9.addCell(pcell9);
			document.add(table9);

			PdfPTable table10 = new PdfPTable(4);
			PdfPCell pcell10 = new PdfPCell(
					new Phrase("Therefore and according to Qatar Central Bank's Instructions:", medium12));
			pcell10.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell10.setBorderWidth(0f);
			pcell10.setColspan(4);
			table10.addCell(pcell10);
			document.add(table10);

			PdfPTable table11 = new PdfPTable(4);
			PdfPCell pcell11 = new PdfPCell(new Phrase(
					"1. No withdrawal/deposite, will take place in your A/C without prior approval from the bank.",
					medium12));
			pcell11.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell11.setBorderWidth(0f);
			pcell11.setColspan(4);
			pcell11.setIndent(10);
			table11.addCell(pcell11);
			document.add(table11);

			PdfPTable table12 = new PdfPTable(4);
			PdfPCell pcell12 = new PdfPCell(
					new Phrase("2. Profit shall continue to be calculated on your account($)", medium12));
			pcell12.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell12.setBorderWidth(0f);
			pcell12.setColspan(4);
			pcell11.setIndent(10);
			table12.addCell(pcell12);
			document.add(table12);

			PdfPTable table13 = new PdfPTable(4);
			PdfPCell pcell13 = new PdfPCell(new Phrase(
					"3. The Bank shall charge QR. 10 being fees on balances less than QR. 500 in call and saving accounts.",
					medium12));
			pcell13.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell13.setBorderWidth(0f);
			pcell13.setColspan(4);
			pcell11.setIndent(10);
			table13.addCell(pcell13);
			document.add(table13);

			PdfPTable table14 = new PdfPTable(4);
			PdfPCell pcell14 = new PdfPCell(new Phrase(
					"4. Your account($) balance($) shall be transfered to suspence account($) (unclaimed balances) in case 10 years have passed since the last transaction made in such account($) and bank shall close the A/C and set off your outstanding balance($) against  any liabilities/obligation due to the Bank. ",
					medium12));
			pcell14.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell14.setBorderWidth(0f);
			pcell14.setColspan(4);
			pcell11.setIndent(10);
			table14.addCell(pcell14);
			document.add(table14);

			PdfPTable table15 = new PdfPTable(4);
			table15.setSpacingBefore(10);
			PdfPCell pcell15 = new PdfPCell(new Phrase(
					"You are kindly requested to approach the Bank during the official hours to take the necessary action in this regards.",
					medium12));
			pcell15.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell15.setBorderWidth(0f);
			pcell15.setColspan(4);
			table15.addCell(pcell15);
			document.add(table15);

			PdfPTable table16 = new PdfPTable(4);
			table16.setSpacingBefore(40);
			PdfPCell pcell16 = new PdfPCell(new Phrase("Thanks and Regards", smallBold1));
			pcell16.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell16.setBorderWidth(0f);
			pcell16.setColspan(4);
			table16.addCell(pcell16);
			document.add(table16);

			document.close();
			outputStream.close();

			System.out.println("Dormancy Pdf created successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Phrase getCheckBox(String accounType) {
		Phrase p = new Phrase("Your ");
		if (accounType.equalsIgnoreCase("Current")) {
			p.add(yesChkBox);
			p.add(new Chunk("Current", medium10bold1));

		} else {
			p.add(noChkBox);
			p.add(new Chunk("Current", medium10bold1));

		}

		if (accounType.equalsIgnoreCase("Saving")) {
			p.add(yesChkBox);
			p.add(new Chunk("Saving", medium10bold1));

		} else {
			p.add(noChkBox);
			p.add(new Chunk("Saving", medium10bold1));

		}
		if (accounType.equalsIgnoreCase("Deposit")) {
			p.add(yesChkBox);
			p.add(new Chunk("Deposit Account", medium10bold1));

		} else {
			p.add(noChkBox);
			p.add(new Chunk("Deposit Account", medium10bold1));

		}
		return p;
	}

	private PdfPTable createDormancyHeader() throws DocumentException, MalformedURLException, IOException {
		Image image = null;
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/resources/lettergenerate.jpg");
			image = Image.getInstance(IOUtils.toByteArray(in));
		} finally {
			in.close();
		}
		image.scaleToFit(120f, 160f);
		PdfPTable table1 = new PdfPTable(8);
		table1.setSpacingAfter(50);
		table1.setWidthPercentage(480 / 5.23f);
		table1.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(image);
		cell.setColspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0f);
		table1.addCell(cell);
		return table1;
	}
	@Override
	public void generateDealReport(Deal deal, String file)
			throws DocumentException, MalformedURLException, IOException {
		Document document = null;
		OutputStream outputStream = null;
		try{
		// Create Document instance.
		 document = new Document();

		// Create OutputStream instance.
		 outputStream = new FileOutputStream(file);

		// Create PDFWriter instance.
		PdfWriter.getInstance(document, outputStream);

		// Open the document.
		document.open();

		// Add new Row
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100);
		PdfPCell cell1 = new PdfPCell(getContent(deal));
		cell1.setBorderWidth(1f);
		cell1.setFixedHeight(600f);
		cell1.setColspan(12);

		// Add new Row
		PdfPCell cell2 = new PdfPCell(new Phrase("Checked"));
		cell2.setFixedHeight(50);
		cell2.setColspan(4);
		PdfPCell cell3 = new PdfPCell(new Phrase("Verified"));
		cell3.setColspan(4);
		PdfPCell cell4 = new PdfPCell(new Phrase("Authorized"));
		cell4.setColspan(4);
		// Add new Row
		PdfPCell cell5 = new PdfPCell(new Phrase(""));
		cell5.setColspan(12);
		cell5.setFixedHeight(70f);
		/*
		 * PdfPTable nestedTable = new PdfPTable(2); nestedTable.addCell(new
		 * PdfPCell(new Phrase("Nested 1"))); nestedTable.addCell(new
		 * PdfPCell(new Phrase("Nested 2"))); cell3.addElement(nestedTable);
		 */

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		document.add(table);
		// Close document and outputStream.
		PdfPTable tablefooter = new PdfPTable(12);
		tablefooter.setWidthPercentage(100);
		PdfPCell cellf = new PdfPCell(new Phrase(CommonUtil.getToday("dd-MMMMM-yyyy hh:mm a")));
		cellf.setColspan(12);
		cellf.setBorderWidth(0f);
		tablefooter.addCell(cellf);

		PdfPCell cellf1 = new PdfPCell(new Phrase("Remark:-", medium12bold));
		cellf1.setColspan(12);
		cellf1.setBorderWidth(0f);
		tablefooter.addCell(cellf1);

		document.add(tablefooter);
}finally{
		document.close();
		outputStream.close();
		}

	}

	private PdfPCell getContent(Deal deal) throws DocumentException, MalformedURLException, IOException {
		PdfPTable ntable = new PdfPTable(12);
		ntable.setSpacingAfter(50);
		ntable.setWidthPercentage(90);
		PdfPCell ncell1 = new PdfPCell(new Phrase(deal.getReceivedCompanyName(), medium14bold));
		ncell1.setBorderWidth(0f);
		ncell1.setColspan(12);
		ntable.addCell(ncell1);
		ncell1.addElement(ntable);

		PdfPCell ncell2 = new PdfPCell(new Phrase(deal.getReceivedCompanyAddress()+"\n\n", smallBold));
		ncell2.setBorderWidth(0f);
		ncell2.setColspan(8);
		ntable.addCell(ncell2);
		ncell2.addElement(ntable);

		PdfPCell logo = new PdfPCell(addDealLogo());
		logo.setBorderWidth(0f);
		logo.setColspan(4);
		ntable.addCell(logo);
		
		logo.addElement(ntable);

		PdfPCell ncell3 = new PdfPCell(new Phrase("TO\n\n", medium10));
		ncell3.setBorderWidth(0f);
		ncell3.setColspan(12);
		ntable.addCell(ncell3);
		ncell2.addElement(ntable);

		PdfPCell ncell4 = new PdfPCell(new Phrase(deal.getCustomerName(), medium10));
		ncell4.setBorderWidth(0f);
		ncell4.setColspan(8);
		ntable.addCell(ncell4);
		ncell4.addElement(ntable);

		PdfPCell ncell5 = new PdfPCell(new Phrase(new Phrase("Date:"+CommonUtil.getToday("dd-MM-yy"), medium10)));
		ncell5.setBorderWidth(0f);
		ncell5.setColspan(4);
		ntable.addCell(ncell5);
		ncell5.addElement(ntable);

		PdfPCell ncell6 = new PdfPCell(
				new Phrase(deal.getCustomerAddress1()+"\n"+deal.getCustomerAddress2()+"\nPIN CODE :"+deal.getCustomerPinCode() , medium12));
		ncell6.setBorderWidth(0f);
		ncell6.setColspan(8);
		ntable.addCell(ncell6);
		ncell6.addElement(ntable);

		PdfPCell ncell7 = new PdfPCell(new Phrase(new Phrase("EMP ID:"+deal.getRefNo(), medium10)));
		ncell7.setBorderWidth(0f);
		ncell7.setColspan(4);
		ntable.addCell(ncell7);
		ncell7.addElement(ntable);

		PdfPCell ncell8 = new PdfPCell(new Phrase("Confirmation \n PF Withdrawal", medium14bold));
		ncell8.setColspan(12);
		ncell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		ntable.addCell(ncell8);
		ncell8.addElement(ntable);

		PdfPCell ncell9 = new PdfPCell(new Phrase("\n"));
		ncell9.setColspan(12);
		ncell9.setBorderWidth(0f);
		ncell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		ntable.addCell(ncell9);
		ncell9.addElement(ntable);

		PdfPCell ncell10 = new PdfPCell(getContent2(deal));
		ncell10.setColspan(12);
		ncell10.setBorderWidth(1f);
		ncell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		ntable.addCell(ncell10);
		ncell10.addElement(ntable);

		PdfPCell ncell11 = new PdfPCell(new Phrase("\n\nIntermediary", medium12bold));
		ncell11.setColspan(12);
		ncell11.setBorderWidth(0f);
		ntable.addCell(ncell11);
		ncell11.addElement(ntable);

		PdfPCell ncell12 = new PdfPCell(new Phrase("PIN CODE:  XXXX \n"));
		ncell12.setColspan(12);
		ncell12.setBorderWidth(0f);
		ntable.addCell(ncell12);
		ncell12.addElement(ntable);

		PdfPCell ncell13 = new PdfPCell(new Phrase("Account with the Bank", medium12bold));
		ncell13.setColspan(12);
		ncell13.setBorderWidth(0f);
		ntable.addCell(ncell13);
		ncell13.addElement(ntable);

		PdfPCell ncell14 = new PdfPCell(new Phrase(" "+deal.getAcctBankName()+"\n "+deal.getAcctBankAddress1()+"\n PIN CODE:"+deal.getAcctBankPinCode()));
		ncell14.setColspan(12);
		ncell14.setBorderWidth(0f);
		ntable.addCell(ncell14);
		ncell14.addElement(ntable);

		PdfPCell ncell15 = new PdfPCell(new Phrase("\nWe Pay Through\n\n", medium12bold));
		ncell15.setColspan(6);
		ncell15.setBorderWidth(0f);
		ntable.addCell(ncell15);
		ncell15.addElement(ntable);

		PdfPCell ncell16 = new PdfPCell(new Phrase("\nWe Receive Through\n\n", medium12bold));
		ncell16.setColspan(6);
		ncell16.setBorderWidth(0f);
		ntable.addCell(ncell16);
		ncell16.addElement(ntable);

		PdfPCell ncell17 = new PdfPCell(new Phrase(deal.getPayBankName()+"\nPIN CODE:"+deal.getPayBankPinCode()));
		ncell17.setColspan(6);
		ncell17.setBorderWidth(0f);
		ntable.addCell(ncell17);
		ncell17.addElement(ntable);

		PdfPCell ncell18 = new PdfPCell(new Phrase(deal.getReceivedCompanyName()+"\nXXXXXXXX"));
		ncell18.setColspan(6);
		ncell18.setBorderWidth(0f);
		ntable.addCell(ncell18);
		ncell18.addElement(ntable);

		return ncell8;
	}

	private static PdfPCell getContent2(Deal deal) throws DocumentException, MalformedURLException, IOException {
		PdfPTable newtable = new PdfPTable(12);
		newtable.setWidthPercentage(100);
		PdfPCell ncell1 = new PdfPCell(new Phrase("\nPrincipal Amount\n\n"));
		ncell1.setColspan(6);
		ncell1.setBorderWidth(0f);
		newtable.addCell(ncell1);
		ncell1.addElement(newtable);

		PdfPCell ncell2 = new PdfPCell(new Phrase("\n:"));
		ncell2.setColspan(3);
		ncell2.setBorderWidth(0f);
		newtable.addCell(ncell2);
		ncell2.addElement(newtable);

		PdfPCell ncell3 = new PdfPCell(new Phrase("\nINR - "+deal.getPrincipalAmount()+"\n\n", medium12bold));
		ncell3.setColspan(3);
		ncell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ncell3.setBorderWidth(0f);
		newtable.addCell(ncell3);
		ncell3.addElement(newtable);

		PdfPCell ncell4 = new PdfPCell(new Phrase("Start Date\n\n"));
		ncell4.setColspan(6);
		ncell4.setBorderWidth(0f);
		newtable.addCell(ncell4);
		ncell4.addElement(newtable);

		PdfPCell ncell5 = new PdfPCell(new Phrase(":"));
		ncell5.setColspan(3);
		ncell5.setBorderWidth(0f);
		newtable.addCell(ncell5);
		ncell5.addElement(newtable);

		PdfPCell ncell6 = new PdfPCell(new Phrase(CommonUtil.convertFormat(deal.getStartDate(),"dd-MMM-yy")+"\n\n"));
		ncell6.setColspan(3);
		ncell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ncell6.setBorderWidth(0f);
		newtable.addCell(ncell6);
		ncell6.addElement(newtable);

		PdfPCell ncell7 = new PdfPCell(new Phrase("Maturity Date\n\n"));
		ncell7.setColspan(6);
		ncell7.setBorderWidth(0f);
		newtable.addCell(ncell7);
		ncell7.addElement(newtable);

		PdfPCell ncell8 = new PdfPCell(new Phrase(":"));
		ncell8.setColspan(3);
		ncell8.setBorderWidth(0f);
		newtable.addCell(ncell8);
		ncell8.addElement(newtable);

		PdfPCell ncell9 = new PdfPCell(new Phrase(CommonUtil.convertFormat(deal.getMaturityDate(),"dd-MMM-yy")+"\n\n"));
		ncell9.setColspan(3);
		ncell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ncell9.setBorderWidth(0f);
		newtable.addCell(ncell9);
		ncell9.addElement(newtable);

		PdfPCell ncell10 = new PdfPCell(new Phrase("Number of Days\n\n"));
		ncell10.setColspan(6);
		ncell10.setBorderWidth(0f);
		newtable.addCell(ncell10);
		ncell10.addElement(newtable);

		PdfPCell ncell11 = new PdfPCell(new Phrase(":"));
		ncell11.setColspan(3);
		ncell11.setBorderWidth(0f);
		newtable.addCell(ncell11);
		ncell11.addElement(newtable);

		PdfPCell ncell12 = new PdfPCell(new Phrase(deal.getNumberOfDays()+"\n\n"));
		ncell12.setColspan(3);
		ncell12.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ncell12.setBorderWidth(0f);
		newtable.addCell(ncell12);
		ncell12.addElement(newtable);

		PdfPCell ncell13 = new PdfPCell(new Phrase("Profit Rate\n\n"));
		ncell13.setColspan(6);
		ncell13.setBorderWidth(0f);
		newtable.addCell(ncell13);
		ncell13.addElement(newtable);

		PdfPCell ncell14 = new PdfPCell(new Phrase(":"));
		ncell14.setColspan(3);
		ncell14.setBorderWidth(0f);
		newtable.addCell(ncell14);
		ncell14.addElement(newtable);

		PdfPCell ncell15 = new PdfPCell(new Phrase(deal.getProfitRate()+"%\n\n"));
		ncell15.setColspan(3);
		ncell15.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ncell15.setBorderWidth(0f);
		newtable.addCell(ncell15);
		ncell15.addElement(newtable);

		PdfPCell ncell16 = new PdfPCell(new Phrase("Total Amount\n\n"));
		ncell16.setColspan(6);
		ncell16.setBorderWidth(0f);
		newtable.addCell(ncell16);
		ncell16.addElement(newtable);

		PdfPCell ncell17 = new PdfPCell(new Phrase(":"));
		ncell17.setColspan(3);
		ncell17.setBorderWidth(0f);
		newtable.addCell(ncell17);
		ncell17.addElement(newtable);

		PdfPCell ncell18 = new PdfPCell(new Phrase("INR - "+deal.getTotalAmount()+"\n\n", medium12bold));
		ncell18.setColspan(3);
		ncell18.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ncell18.setBorderWidth(0f);
		newtable.addCell(ncell18);
		ncell18.addElement(newtable);

		return ncell1;
	}

	private PdfPTable addDealLogo() throws DocumentException, MalformedURLException, IOException {

		Image image = null;
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/resources/MasrafAlRayan2.jpg");
			image = Image.getInstance(IOUtils.toByteArray(in));
		} finally {
			in.close();
		}

		image.scaleToFit(80f, 80f);
		PdfPTable table1 = new PdfPTable(8);
		//table1.setSpacingAfter(5);
		table1.setWidthPercentage(480 / 5.23f);
		table1.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(image);
		cell.setColspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(0f);
		table1.addCell(cell);
		return table1;
	}

	@Override
	public List<List<Map<Object, Object>>> getYearlyData() {
		return reportDao.getYearlyData();
	}
	@Override
	public List<List<Map<Object, Object>>> getMonthlyData() {
		return reportDao.getMonthlyData();
	}

	@Override
	public List<DataEntry> GetDataEntry() {
		
		return reportDao.GetDataEntry();
	}

	@Override
	public List<Sites> GetSite() {
		// TODO Auto-generated method stub
		return reportDao.GetSite();
	}

	@Override
	public List<Vehicle> GetVehicles() {
		// TODO Auto-generated method stub
		return reportDao.GetVehicles();
	}

	@Override
	public List<DataEntry> searchTrip(SearchBean searchBean) throws ParseException {
		return reportDao.searchTrip(searchBean);// TODO Auto-generated method stub
	
	}
}
