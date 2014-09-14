/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.database;


import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.gameChecker.model.hibernateEntities.Company;
import pl.gameChecker.model.hibernateEntities.CompanyDao;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.GameDao;
import pl.gameChecker.model.hibernateEntities.GamesLibrariesDao;
import pl.gameChecker.model.hibernateEntities.Gametype;
import pl.gameChecker.model.hibernateEntities.GametypeDao;
import pl.gameChecker.model.hibernateEntities.LibraryDao;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MemberDao;
import pl.gameChecker.model.hibernateEntities.Role;
import pl.gameChecker.model.hibernateEntities.RoleDao;
import pl.gameChecker.model.hibernateEntities.SqfaAnswer;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerComment;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerCommentDao;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerDao;
import pl.gameChecker.model.hibernateEntities.SqfaQuestion;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionComment;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionCommentDao;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionDao;
import pl.gameChecker.model.pdf.PdfModel;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class TempDBMain {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        
        //<editor-fold defaultstate="collapsed" desc="Company Test Methods. Click on the + sign on the left to edit the code.">
        CompanyDao companyDao = context.getBean("company", CompanyDao.class);
        companyDao.create(new Company("GeForce"));
        companyDao.create(new Company("Radeon"));
        companyDao.create(new Company("Intel"));
        companyDao.create(new Company("AMD"));
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Gametype additions. Click on the + sign on the left to edit the code."> 
        GametypeDao gametypeDao = context.getBean("gametype", GametypeDao.class);
        Gametype fps = new Gametype("FPS");
        Gametype rts = new Gametype("RTS");
        Gametype rpg = new Gametype("RPG");
        Gametype moba = new Gametype("MOBA");
        Gametype mmo = new Gametype("MMO");
        Gametype action = new Gametype("Action");
        Gametype platform = new Gametype("Platform");
        Gametype adventure = new Gametype("Adventure");
        Gametype arcade = new Gametype("Arcade");
        Gametype racing = new Gametype("Racing");
        Gametype puzzle = new Gametype("Puzzle");
        Gametype simulator = new Gametype("Simulator");
        Gametype other = new Gametype("N/A");
        gametypeDao.create(fps);
        gametypeDao.create(rts);
        gametypeDao.create(rpg);
        gametypeDao.create(moba);
        gametypeDao.create(mmo);
        gametypeDao.create(action);
        gametypeDao.create(platform);
        gametypeDao.create(adventure);
        gametypeDao.create(arcade);
        gametypeDao.create(racing);
        gametypeDao.create(puzzle);
        gametypeDao.create(simulator);
        gametypeDao.create(other);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Games additions. Click on the + sign on the left to edit the code."> 
        GameDao gameDao = context.getBean("game", GameDao.class);
        gameDao.create(new Game("Jedi Academy", true, true, false, 5, 4, 2005, gametypeDao.getByName("Action")));
        gameDao.create(new Game("Nascar Racing", true, true, false, 4, 2, 1995, gametypeDao.getByName("Racing")));
        gameDao.create(new Game("Mario", true, false, true, 4, 2, 2000, gametypeDao.getByName("Platform")));
        gameDao.create(new Game("Tekken", true, true, true, 4, 2, 2000, gametypeDao.getByName("Arcade")));
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Members Test Methods. Click on the + sign on the left to edit the code."> 
        MemberDao memberDao = context.getBean("member", MemberDao.class);
        Member bbZ = new Member("bbZ", "tempPassword", "gamemajster@gmail.com", 22, 11, 1991);
        Member dspoko = new Member("dspoko", "tempPassword2", "dspoko@interia.pl", 22, 11, 2000);
        Member testUser = new Member("testuser", "test", "janek@dzbanek.pl", 05, 11, 2005);
        
        memberDao.create(bbZ);
        memberDao.create(dspoko);
        memberDao.create(testUser);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Add games to library. Click on the + sign on the left to edit the code."> 
        LibraryDao libraryDao = context.getBean("library", LibraryDao.class);
        GamesLibrariesDao gamesLibrariesDao = context.getBean("gamesLibraries", GamesLibrariesDao.class);
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(1));
        libraryDao.addGameToMembersLibrary(memberDao.getById(2), gameDao.getById(1));
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(2));
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(3));
        libraryDao.addGameToMembersLibrary(memberDao.getById(2), gameDao.getById(3));
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(4));
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Roles additions. Click on the + sign on the left to edit the code."> 
        RoleDao roleDao = context.getBean("role", RoleDao.class);
        Role admin = new Role("Admin");
        Role juniorAdmin = new Role("Junior Admin");
        Role moderator = new Role("Moderator");
        Role premiumMember = new Role("Premium Member");
        Role member = new Role("Member");
        roleDao.create(admin);
        roleDao.create(juniorAdmin);
        roleDao.create(moderator);
        roleDao.create(premiumMember);
        roleDao.create(member);
        //</editor-fold>
        
        SqfaAnswerDao sqfaAnswerDao = context.getBean("sqfaAnswer", SqfaAnswerDao.class);
        SqfaQuestionDao sqfaQuestionDao = context.getBean("sqfaQuestion", SqfaQuestionDao.class);
        SqfaAnswerCommentDao sqfaAnswerCommentDao = context.getBean("sqfaAnswerComment", SqfaAnswerCommentDao.class);
        SqfaQuestionCommentDao sqfaQuestionCommentDao = context.getBean("sqfaQuestionComment", SqfaQuestionCommentDao.class);

        SqfaQuestion sq = new SqfaQuestion(memberDao.getById(1), "Pytanie", "Czy to działa?");
        SqfaQuestionComment sqc = new SqfaQuestionComment(memberDao.getById(2), sq, "Rozwin pytanie...");
        SqfaAnswer sa = new SqfaAnswer(memberDao.getById(3), sq, "Oto odpowiedz");
        SqfaAnswer sa2 = new SqfaAnswer(memberDao.getById(2), sq, "Oto DOBRA odpowiedz");
        SqfaAnswerComment sac = new SqfaAnswerComment(memberDao.getById(1), sa, "To nie to");
        SqfaAnswerComment sac2 = new SqfaAnswerComment(memberDao.getById(1), sa, "To JEST to");
        SqfaAnswerComment sac3 = new SqfaAnswerComment(memberDao.getById(3), sa, "not to fest");
        
        sqfaQuestionDao.create(sq);
        sqfaQuestionCommentDao.create(sqc);
        sqfaAnswerDao.create(sa);
        sqfaAnswerCommentDao.create(sac);
        sqfaAnswerDao.create(sa2);
        sqfaAnswerCommentDao.create(sac2);
        sqfaAnswerCommentDao.create(sac3);
        
        sqfaAnswerDao.incrementSqfaAnswerPoints(sa);
        sqfaAnswerDao.incrementSqfaAnswerPoints(sa2);
        sqfaAnswerDao.incrementSqfaAnswerPoints(sa2);
                
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsTillNow = calendar.getTimeInMillis();
        Date dateTo = new Date(secondsTillNow);
        calendar.clear();
        calendar.set(1999, 10, 22);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        Date dateFrom = new Date(secondsSinceEpoch);
        
        if(dateFrom.before(dateTo)) {
            System.out.println("dateFrom is before dateTo");
        } else if(dateFrom.after(dateTo)) {
            System.err.println("dateFrom is after dateTo");
        }
                        
        try {
            PdfModel pdf = context.getBean("pdfModel", PdfModel.class);
            pdf.createNewMembersRaport(dateFrom, dateTo);
            pdf.createGamesAdditionsReport(dateFrom);
            pdf.createSqfaSummaryReport(dateFrom, dateTo);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(TempDBMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(memberDao.isMemberLoginMatchPassword(memberDao.getById(1).getName(), "tempPassword"))
            System.out.println("member 1 login pasuje do pasła = true (true)");
        else
            System.out.println("member 1 login NIE pasuje do pasła = false (true)");
        
        if(memberDao.isMemberLoginMatchPassword(memberDao.getById(2).getName(), "złehaslo"))
            System.out.println("member 2 login pasuje do pasła = true (false)");
        else
            System.out.println("member 2 login NIE pasuje do pasła = false (false)");
        
        if(memberDao.isMemberLoginMatchPassword(memberDao.getById(3).getName(), "test"))
            System.out.println("member 3 login pasuje do pasła = true (true)");
        else
            System.out.println("member 3 login NIE pasuje do pasła = false (true)");
        
        for(Game games : gameDao.getGamesFromMember(memberDao.getById(1))){
            System.out.println("Member 1 games:" + games.getName());
        }
        
        for(Game games : gameDao.getGamesFromMember(memberDao.getById(2))){
            System.out.println("Member 2 games:" + games.getName());
        }
        
        if(gamesLibrariesDao.isMemberGotGameInLibrary(memberDao.getById(2), gameDao.getByName("Mario")))
            System.out.println("Member posiada Mario");
        else
            System.out.println("Member nie posiada Mario - ERROR");
        
        if(gamesLibrariesDao.isMemberGotGameInLibrary(memberDao.getById(2), gameDao.getByName("Tekken")))
            System.out.println("Member posiada Tekken - ERROR");
        else
            System.out.println("Member nie posiada Tekken");
        
        
    }
}