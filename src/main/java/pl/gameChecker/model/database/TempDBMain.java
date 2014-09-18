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
import pl.gameChecker.model.hibernateEntities.MembersCPU;
import pl.gameChecker.model.hibernateEntities.MembersCPUDao;
import pl.gameChecker.model.hibernateEntities.MembersGPU;
import pl.gameChecker.model.hibernateEntities.MembersGPUDao;
import pl.gameChecker.model.hibernateEntities.MembersPC;
import pl.gameChecker.model.hibernateEntities.MembersPCDao;
import pl.gameChecker.model.hibernateEntities.MembersRatesGamesDao;
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
        
        //<editor-fold defaultstate="collapsed" desc="Company and hardware additions. Click on the + sign on the left to edit the code.">
        CompanyDao companyDao = context.getBean("company", CompanyDao.class);
        companyDao.create(new Company("GeForce"));
        companyDao.create(new Company("Radeon"));
        companyDao.create(new Company("Intel"));
        companyDao.create(new Company("AMD"));
        
        MembersCPUDao membersCPUDao = context.getBean("membersCPU", MembersCPUDao.class);
        MembersGPUDao membersGPUDao = context.getBean("membersGPU", MembersGPUDao.class);
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(2009, 4, 26);
        long gpuDate = calendar.getTimeInMillis();
        membersGPUDao.create(new MembersGPU("GTX460", new Date(gpuDate), 1024, companyDao.getById(1))); 
        
        calendar.clear();
        calendar.set(2002, 1, 1);
        gpuDate = calendar.getTimeInMillis();
        membersGPUDao.create(new MembersGPU("GeForce4 Ti", new Date(gpuDate), 128, companyDao.getById(1)));
        
        calendar.clear();
        calendar.set(2005, 9, 5);
        gpuDate = calendar.getTimeInMillis();
        membersGPUDao.create(new MembersGPU("X1600", new Date(gpuDate), 512, companyDao.getById(2)));
        
        calendar.clear();
        calendar.set(2001, 1, 1);
        gpuDate = calendar.getTimeInMillis();
        membersGPUDao.create(new MembersGPU("R100", new Date(gpuDate), 64, companyDao.getById(2)));
        
        calendar.clear();
        calendar.set(2007, 1, 1);
        long cpuDate = calendar.getTimeInMillis();
        membersCPUDao.create(new MembersCPU("Athlon II x4 640", new Date(cpuDate), companyDao.getById(4)));
        
        calendar.clear();
        calendar.set(2008, 1, 1);
        cpuDate = calendar.getTimeInMillis();
        membersCPUDao.create(new MembersCPU("Phenom II x4 965", new Date(cpuDate), companyDao.getById(4)));
        
        calendar.clear();
        calendar.set(2006, 1, 1);
        cpuDate = calendar.getTimeInMillis();
        membersCPUDao.create(new MembersCPU("Celeron Cedar Mill", new Date(cpuDate), companyDao.getById(3)));
        
        calendar.clear();
        calendar.set(2009, 1, 1);
        cpuDate = calendar.getTimeInMillis();
        membersCPUDao.create(new MembersCPU("Core i5-750", new Date(cpuDate), companyDao.getById(3)));
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
        gameDao.create(new Game("Jedi Academy", true, true, false, 5, 4, 2005, 
                "Badrdzo dobra giereczka starwarsowa" , 2003, 2003, 512, 2005, 2005, 1024, gametypeDao.getByName("Action")));
        gameDao.create(new Game("Nascar Racing", true, true, false, 4, 2, 2005, 
                "Bardzo szybkie wyscigi, takie gta ale bez wychodzenia z auta", 2003, 2002, 256, 2006, 2006, 512, gametypeDao.getByName("Racing")));
        gameDao.create(new Game("Mario", true, false, true, 4, 2, 2000, 
                "Klasyka gatunku pierwsza platformowka jaka powstala - prekursor gatunku", 1994, 1994, 256, 1998, 1996, 512, gametypeDao.getByName("Platform")));
        gameDao.create(new Game("Tekken", true, true, true, 4, 2, 2010, 
                "Bijatyka, takie gta bez aut i z biciem tylko jednego chlopka", 2007, 2008, 1024, 2010, 2010, 2048, gametypeDao.getByName("Arcade")));
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
        
        //<editor-fold defaultstate="collapsed" desc="Members Test Methods. Click on the + sign on the left to edit the code."> 
        MemberDao memberDao = context.getBean("member", MemberDao.class);
        Member bbZ = new Member("bbZ", "tempPassword", "gamemajster@gmail.com", 22, 11, 1991, roleDao.getById(1));
        Member dspoko = new Member("dspoko", "tempPassword2", "dspoko@interia.pl", 22, 11, 2000);
        Member testUser = new Member("testuser", "test", "janek@dzbanek.pl", 05, 11, 2005);
        
        memberDao.create(bbZ);
        memberDao.create(dspoko);
        memberDao.create(testUser);
        
        MembersPCDao membersPCDao = context.getBean("membersPC", MembersPCDao.class);
        MembersPC bbZPC = new MembersPC(bbZ, "PC", 4096, membersCPUDao.getByName("Athlon II x4 640"), membersGPUDao.getByName("gtx460"));
        MembersPC dspokoPC = new MembersPC(dspoko, "Laptop", 1024, membersCPUDao.getById(4), membersGPUDao.getByName("X1600"));
        membersPCDao.create(bbZPC);
        membersPCDao.create(dspokoPC);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Add games to library. Click on the + sign on the left to edit the code."> 
        LibraryDao libraryDao = context.getBean("library", LibraryDao.class);
        GamesLibrariesDao gamesLibrariesDao = context.getBean("gamesLibraries", GamesLibrariesDao.class);
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(1));
        libraryDao.addGameToMembersLibrary(memberDao.getById(2), gameDao.getById(1));
        libraryDao.addGameToMembersLibrary(memberDao.getById(3), gameDao.getById(1));
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(2));
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(3));
        libraryDao.addGameToMembersLibrary(memberDao.getById(2), gameDao.getById(3));
        libraryDao.addGameToMembersLibrary(memberDao.getById(1), gameDao.getById(4));
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
        
        calendar.clear();
        calendar.set(1990, 1, 1);
        secondsSinceEpoch = calendar.getTimeInMillis();
        dateFrom = new Date(secondsSinceEpoch);
        calendar.clear();
        calendar.set(2014, 12, 31);
        secondsSinceEpoch = calendar.getTimeInMillis();
        dateTo = new Date(secondsSinceEpoch);
        
        for(Game game : gameDao.getSearchGameResults("jedi", dateFrom, dateTo, false, false, false, -1, -1, null, -34, -67)) {
            System.out.println("Search result: " + game.getName());
        }
        
        
        for(Game game : gameDao.getSearchGameResults("nascar", dateFrom, dateTo, false, false, false, 1, 5, gametypeDao.getByName("action"), 0, 100)) {
            System.out.println("Search result: " + game.getName());
        }
        
        libraryDao.removeGameFromMembersLibrary(memberDao.getById(1), gameDao.getById(2));
        gameDao.updateGamePopularity(gameDao.getById(2));
        
        for(Game game : gameDao.getByNameAndMember("je", memberDao.getById(1))){
            System.out.println("getByNameAndMember: " + game.getName());
        }

        gameDao.rateGame(memberDao.getById(1), gameDao.getById(2), 4);
        gameDao.rateGame(memberDao.getById(2), gameDao.getById(1), 5);

        MembersRatesGamesDao membersRatesGamesDao = context.getBean("membersRatesGames", MembersRatesGamesDao.class);
//        Member deleteMember = memberDao.getByName("bbZ");
//        memberDao.delete(deleteMember, sqfaAnswerDao.getAllAnswersFromMember(deleteMember), sqfaQuestionDao.getAllQuestionsFromMember(deleteMember), 
//                sqfaQuestionCommentDao.getAllQuestionCommentsFromMember(deleteMember), sqfaAnswerCommentDao.getAllAnswerCommentsFromMember(deleteMember),
//                membersRatesGamesDao.getMembersRatesGamesByMember(deleteMember));

//        gameDao.delete(gameDao.getById(1));
        
        System.out.println("bbZPC vs Jedi Academy score: " + gameDao.getGameVsPCResult(gameDao.getById(1), bbZPC));
        System.out.println("dspokoPC vs Jedi Academy score: " + gameDao.getGameVsPCResult(gameDao.getById(1), dspokoPC));
        System.out.println("bbZPC vs Nascar score: " + gameDao.getGameVsPCResult(gameDao.getById(2), bbZPC));
        System.out.println("dspokoPC vs Nascar score: " + gameDao.getGameVsPCResult(gameDao.getById(2), dspokoPC));
        System.out.println("bbZPC vs Mario score: " + gameDao.getGameVsPCResult(gameDao.getById(3), bbZPC));
        System.out.println("dspokoPC vs Mario score: " + gameDao.getGameVsPCResult(gameDao.getById(3), dspokoPC));
        System.out.println("bbZPC vs Tekken score: " + gameDao.getGameVsPCResult(gameDao.getById(4), bbZPC));
        System.out.println("dspokoPC vs Tekken score: " + gameDao.getGameVsPCResult(gameDao.getById(4), dspokoPC));
    }
}