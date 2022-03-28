package com.group6.careu.service;

import com.group6.careu.entity.User;
import com.group6.careu.model.BillModel;
import com.group6.careu.repository.UserRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.*;

@Service
@Transactional
public class BillServiceImpl implements BillService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void billProcessor(Integer id,Integer bankReferenceCode){

        User u=userRepository.getUserById(id);

        BillModel b=new BillModel();
        PDDocument bill=b.getBill();
        PDPage page = bill.getPage(0);

        Double cost=40.0;
        Double tax=15.0;
        Double total=40.0+(0.15*40.0);

        try {
            PDPageContentStream cs = new PDPageContentStream(bill, page);

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 28);
            cs.newLineAtOffset(60, 750);
            cs.showText("CareU");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 18);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 690);
            cs.showText("6056 University Ave,");
            cs.newLine();
            cs.showText("Halifax,");
            cs.newLine();
            cs.showText("NS B3H 1W5");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 610);
            cs.showText("Billing Questions ?");
            cs.newLine();
            cs.showText("Please call us at 902-412-7150,");
            cs.newLine();
            cs.showText("Monday-Friday 9am-5pm");
            cs.newLine();
            cs.showText("Saturday 11am-3pm");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 510);
            cs.showText("ADDRESSEE");
            cs.newLine();
            cs.showText(u.getFirstName()+" "+u.getLastName());
            cs.newLine();
            cs.showText(u.getPhone());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(140, 350);
            cs.showText("HOSPITAL STATEMENT");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.newLineAtOffset(80, 300);
            cs.showText("Services");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.newLineAtOffset(200, 300);
            cs.showText("Charges/Description");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(80, 280);
            cs.showText("Consultation");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 14);
            cs.newLineAtOffset(200, 280);
            cs.showText(cost.toString());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(80, 260);
            cs.showText("Tax");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 14);
            cs.newLineAtOffset(200, 260);
            cs.showText(tax.toString());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.newLineAtOffset(80, 210);
            cs.showText("Total");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.newLineAtOffset(200, 210);
            cs.showText(total.toString());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.newLineAtOffset(60, 180);
            cs.showText("Bank Reference Code: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
            cs.newLineAtOffset(240, 180);
            cs.showText(bankReferenceCode.toString());
            cs.endText();
            
            cs.close();

            bill.save(new File("src/main/resources/sample_bills/bill_"+bankReferenceCode+".pdf"));

            bill.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
