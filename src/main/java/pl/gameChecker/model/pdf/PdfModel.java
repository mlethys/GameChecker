/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.GameDao;
import pl.gameChecker.model.hibernateEntities.GamesLibraries;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MemberDao;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerDao;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class PdfModel implements ApplicationContextAware {
    private final String pdfPath;
    private DateFormat dateFormat;
    private java.util.Date date;
    private final double totalTablePayment[] = new double[11];
    
    private static ApplicationContext applicationContext;

    public PdfModel() throws DocumentException, FileNotFoundException, BadElementException, IOException {
        pdfPath = "Wystawiono.pdf";
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void createNewMembersRaport(Date dateFrom, Date dateTo) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        
        Document pdf = new Document();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = new java.util.Date();
        PdfWriter.getInstance(pdf, new FileOutputStream("New Users Raport between " + dateFrom + " and " + dateTo + " - Created " + dateFormat.format(date) + ".pdf"));

        Paragraph tittle = new Paragraph("GAME CHECKER");
        tittle.setAlignment(Element.ALIGN_CENTER);

        Image logo = Image.getInstance("logo1.png");
        logo.setAlignment(Element.ALIGN_RIGHT);

        Paragraph headerParagraph = new Paragraph("Summary of new members registred between " + dateFrom + " and " + dateTo);
        headerParagraph.setAlignment(Element.ALIGN_CENTER);

        pdf.open();
        pdf.add(logo);
        pdf.add(tittle);
        pdf.add(new Paragraph(" "));
        pdf.add(headerParagraph);
        if(createNewUsersTable(dateFrom, dateTo) != null) {
            pdf.add(createNewUsersTable(dateFrom, dateTo));
        }
        pdf.close();
        System.out.println("PDF with new members report closed correctly.");
    }

    public void createGamesAdditionsReport(Date afterThisDate) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document pdf = new Document();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = new java.util.Date();
        PdfWriter.getInstance(pdf, new FileOutputStream("Games Additions Raport after" + afterThisDate + " - Created " + dateFormat.format(date) + ".pdf"));

        Paragraph tittle = new Paragraph("GAME CHECKER");
        tittle.setAlignment(Element.ALIGN_CENTER);

        Image logo = Image.getInstance("logo1.png");
        logo.setAlignment(Element.ALIGN_RIGHT);

        Paragraph headerParagraph = new Paragraph("Summary of games added to members libraries after" + afterThisDate);
        headerParagraph.setAlignment(Element.ALIGN_CENTER);

        pdf.open();
        pdf.add(logo);
        pdf.add(tittle);
        pdf.add(new Paragraph(" "));
        pdf.add(headerParagraph);
        if(createGamesAdditionsTable(afterThisDate) != null) {
            pdf.add(createGamesAdditionsTable(afterThisDate));
        }
        pdf.close();
        System.out.println("PDF with games additions report closed correctly.");
    }
    
    public void createSqfaSummaryReport(Date dateFrom, Date dateTo) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document pdf = new Document();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = new java.util.Date();
        PdfWriter.getInstance(pdf, new FileOutputStream("SQFA Summary Raport between " + dateFrom + " and " + dateTo + " - Created " + dateFormat.format(date) + ".pdf"));

        Paragraph tittle = new Paragraph("GAME CHECKER");
        tittle.setAlignment(Element.ALIGN_CENTER);

        Image logo = Image.getInstance("logo1.png");
        logo.setAlignment(Element.ALIGN_RIGHT);

        Paragraph headerParagraph = new Paragraph("Summary of SQFA points earned between " + dateFrom + " and " + dateTo);
        headerParagraph.setAlignment(Element.ALIGN_CENTER);

        pdf.open();
        pdf.add(logo);
        pdf.add(tittle);
        pdf.add(new Paragraph(" "));
        pdf.add(headerParagraph);
        if(createSqfaSummaryTable(dateFrom, dateTo) != null) {
            pdf.add(createSqfaSummaryTable(dateFrom, dateTo));
        }
        pdf.close();
        System.out.println("PDF with SQFA points summary report closed correctly.");
    }
    
    private PdfPTable createSqfaSummaryTable(Date dateFrom, Date dateTo) throws DocumentException {
        PdfPTable newSqfaSummaryTable = new PdfPTable(3);
        newSqfaSummaryTable.setWidths(new float[]{7f, 4f, 4f});
        newSqfaSummaryTable.setSpacingAfter(5f);
        newSqfaSummaryTable.setSpacingBefore(5f);
        
        PdfPCell sqfaPointsCell, memberNameCell, sqfaAnswersCountCell;
        
        sqfaPointsCell = new PdfPCell(new Phrase("Points get in this peroid"));
        sqfaPointsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        sqfaPointsCell.setBackgroundColor(new BaseColor(180,180,180));
        
        memberNameCell = new PdfPCell(new Phrase("Member Name"));
        memberNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberNameCell.setBackgroundColor(new BaseColor(180,180,180));
        
        sqfaAnswersCountCell = new PdfPCell(new Phrase("SQFA Answers count"));
        sqfaAnswersCountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        sqfaAnswersCountCell.setBackgroundColor(new BaseColor(180,180,180));
        
        newSqfaSummaryTable.addCell(sqfaPointsCell);
        newSqfaSummaryTable.addCell(memberNameCell);
        newSqfaSummaryTable.addCell(sqfaAnswersCountCell);
        
        SqfaAnswerDao companyDao = applicationContext.getBean("sqfaAnswer", SqfaAnswerDao.class);
        List<Object[]> objects = companyDao.getSqfaSummaryBetween(dateFrom, dateTo);
        if(objects != null) {
            for (Object[] obj : objects) {
                for (int i = 0; i < obj.length; i++) {
                    switch(i) {
                        case 0:
                            sqfaPointsCell = new PdfPCell(new Phrase(obj[i].toString()));
                            sqfaPointsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            newSqfaSummaryTable.addCell(sqfaPointsCell);
                            break;
                        case 1:
                            memberNameCell = new PdfPCell(new Phrase(obj[i].toString()));
                            memberNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            newSqfaSummaryTable.addCell(memberNameCell);
                            break;
                        case 2:
                            sqfaAnswersCountCell = new PdfPCell(new Phrase(obj[i].toString()));
                            sqfaAnswersCountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            newSqfaSummaryTable.addCell(sqfaAnswersCountCell);
                            break;
                    }
                }
            }
        }
        
        return newSqfaSummaryTable;
    }
    
    private PdfPTable createGamesAdditionsTable(Date afterThisDate) throws DocumentException {
        PdfPTable newGamesAdditionsTable = new PdfPTable(3);
        newGamesAdditionsTable.setWidths(new float[]{7f, 4f, 4f});
        newGamesAdditionsTable.setSpacingAfter(5f);
        newGamesAdditionsTable.setSpacingBefore(5f);
        
        PdfPCell gameNameCell, memberNameCell, gameAdditionDateCell;
        
        gameNameCell = new PdfPCell(new Phrase("Game Name"));
        gameNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        gameNameCell.setBackgroundColor(new BaseColor(180,180,180));
        
        memberNameCell = new PdfPCell(new Phrase("Member Name"));
        memberNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberNameCell.setBackgroundColor(new BaseColor(180,180,180));
        
        gameAdditionDateCell = new PdfPCell(new Phrase("Addition to library date"));
        gameAdditionDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        gameAdditionDateCell.setBackgroundColor(new BaseColor(180,180,180));
        
        newGamesAdditionsTable.addCell(gameNameCell);
        newGamesAdditionsTable.addCell(memberNameCell);
        newGamesAdditionsTable.addCell(gameAdditionDateCell);

        
        GameDao gameDao = applicationContext.getBean("game", GameDao.class);
        List<Object[]> objects = gameDao.getGamesAdditionAfterDate(afterThisDate);
        if(objects != null) {
            for (Object[] obj : objects) {
                for (int i = 0; i < obj.length; i++) {
                    switch(i) {
                        case 0:
                            Game game = (Game) obj[i];
                            gameNameCell = new PdfPCell(new Phrase(game.getName()));
                            gameNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            newGamesAdditionsTable.addCell(gameNameCell);
                            break;
                        case 1:
                            Member member = (Member) obj[i];
                            memberNameCell = new PdfPCell(new Phrase(member.getName()));
                            memberNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            newGamesAdditionsTable.addCell(memberNameCell);
                            break;
                        case 2:
                            GamesLibraries gamesLibraries = (GamesLibraries) obj[i];
                            gameAdditionDateCell = new PdfPCell(new Phrase(gamesLibraries.getAdditionDate().toString()));
                            gameAdditionDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            newGamesAdditionsTable.addCell(gameAdditionDateCell);
                            break;
                    }
                }
            }
        }
        
        return newGamesAdditionsTable;
    }
    
    private PdfPTable createNewUsersTable (Date dateFrom, Date dateTo) throws DocumentException {
        PdfPTable newMembersTable = new PdfPTable(6);
        newMembersTable.setWidths(new float[]{2f, 3f, 3f, 5f, 3f, 2f});
        newMembersTable.setSpacingAfter(5f);
        newMembersTable.setSpacingBefore(5f);
        
        PdfPCell memberIdCell, memberNameCell, memberRegisterDateCell, memberMailCell, memberBirthdayCell,
                memberPointsCell;
        
        memberIdCell = new PdfPCell(new Phrase("id"));
        memberIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberIdCell.setBackgroundColor(new BaseColor(180,180,180));
        
        memberNameCell = new PdfPCell(new Phrase("Name"));
        memberNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberNameCell.setBackgroundColor(new BaseColor(180,180,180));
        
        memberRegisterDateCell = new PdfPCell(new Phrase("Register date"));
        memberRegisterDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberRegisterDateCell.setBackgroundColor(new BaseColor(180,180,180));
        
        memberMailCell = new PdfPCell(new Phrase("Mail"));
        memberMailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberMailCell.setBackgroundColor(new BaseColor(180,180,180));
        
        memberBirthdayCell = new PdfPCell(new Phrase("Birthday"));
        memberBirthdayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberBirthdayCell.setBackgroundColor(new BaseColor(180,180,180));
        
        memberPointsCell = new PdfPCell(new Phrase("Points"));
        memberPointsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        memberPointsCell.setBackgroundColor(new BaseColor(180,180,180));
        
        newMembersTable.addCell(memberIdCell);
        newMembersTable.addCell(memberNameCell);
        newMembersTable.addCell(memberRegisterDateCell);
        newMembersTable.addCell(memberMailCell);
        newMembersTable.addCell(memberBirthdayCell);
        newMembersTable.addCell(memberPointsCell);
        
        MemberDao memberDao = applicationContext.getBean("member", MemberDao.class);
        List<Member> members = memberDao.getMembersWhereRegisterDateBetween(dateFrom, dateTo);
        if(members != null) {
            for(Member member : members) {
                memberIdCell = new PdfPCell(new Phrase(member.getId() + ""));
                memberIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                memberNameCell = new PdfPCell(new Phrase(member.getName()));
                memberNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                memberRegisterDateCell = new PdfPCell(new Phrase(member.getRegisterDate().toString()));
                memberRegisterDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                memberMailCell = new PdfPCell(new Phrase(member.getMail()));
                memberMailCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                memberBirthdayCell = new PdfPCell(new Phrase(member.getBirthDate().toString()));
                memberBirthdayCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                memberPointsCell = new PdfPCell(new Phrase(member.getPoints() + ""));
                memberPointsCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                newMembersTable.addCell(memberIdCell);
                newMembersTable.addCell(memberNameCell);
                newMembersTable.addCell(memberRegisterDateCell);
                newMembersTable.addCell(memberMailCell);
                newMembersTable.addCell(memberBirthdayCell);
                newMembersTable.addCell(memberPointsCell);
            }
        } else {
            return null;
        }
        
        return newMembersTable;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
    }
}
