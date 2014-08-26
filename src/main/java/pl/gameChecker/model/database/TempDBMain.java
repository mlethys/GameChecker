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
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.gameChecker.model.PdfModel;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.Gametype;
import pl.gameChecker.model.hibernateEntities.Role;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class TempDBMain {
    
    public static void main(String args[]) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Role myRole3 = new Role("Administrator");
        Role myRole2 = new Role("Junior Admin");
        Role myRole1 = new Role("Moderator");
        Role myRole0 = new Role("User");

        session.save(myRole0);
        session.save(myRole1);
        session.save(myRole2);
        session.save(myRole3);
        
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

        session.save(fps);
        session.save(rts);
        session.save(rpg);
        session.save(moba);
        session.save(mmo);
        session.save(action);
        session.save(platform);
        session.save(adventure);
        session.save(arcade);
        session.save(racing);
        session.save(puzzle);
        session.save(simulator);
        session.save(other);
        
        transaction.commit();
        session.close();

        DatabaseModelAddMethods dbmAdd = new DatabaseModelAddMethods();
        DatabaseModelUpdateMethods dbmUpdate = new DatabaseModelUpdateMethods();
        DatabaseModelGetMethods dbmGet = new DatabaseModelGetMethods();
        DatabaseModelOtherMethods dbmOther = new DatabaseModelOtherMethods();
        dbmAdd.addNewMember("bbZ", "tempPassword", "gamemajster@gmail.com", 22, 11, 1991);
        dbmAdd.addNewMember("dspoko", "tempPassword", "dspoko@interia.pl", 22, 11, 2000);
        dbmAdd.addNewMember("testuser", "test", "janek@dzbanek.pl", 05, 11, 2005);
        dbmAdd.addNewGame("Jedi Academy", true, true, false, 5, 4, 2005, "Action");
        dbmAdd.addNewGame("Acadjed Racing", true, true, false, 4, 2, 1995, "Racing");
        dbmAdd.addNewGame("Mario", true, false, true, 4, 2, 2000, "Platform");
        dbmAdd.addNewGame("Academy jedi", true, true, true, 4, 2, 2000, "Action");
        for(Game game : dbmGet.getGamesWhereNameLike("Jed")){
            System.out.println("Found: " + game.getName());
        }
        
        dbmOther.isLoginMatchPassword("bbZ", "wrongPass");
        dbmOther.isLoginMatchPassword("wrongUser", "wrongPass");
        dbmOther.isLoginMatchPassword("bbZ", "tempPassword");
        dbmAdd.addGameToMembersLibrary(dbmGet.getMemberById(1), dbmGet.getGameById(1));
        dbmAdd.addGameToMembersLibrary(dbmGet.getMemberById(2), dbmGet.getGameById(1));
        dbmAdd.addGameToMembersLibrary(dbmGet.getMemberById(1), dbmGet.getGameById(2));
        dbmAdd.addGameToMembersLibrary(dbmGet.getMemberById(1), dbmGet.getGameById(3));
        dbmAdd.addGameToMembersLibrary(dbmGet.getMemberById(2), dbmGet.getGameById(3));
        dbmAdd.addGameToMembersLibrary(dbmGet.getMemberById(1), dbmGet.getGameById(4));
        
        dbmAdd.addNewSqfaQuestion(dbmGet.getMemberById(1), "Pytanie", "Czy to działa?");
        dbmAdd.addNewSqfaQuestionComment(dbmGet.getMemberById(2), dbmGet.getSqfaQuestionById(1), "Rozwiń pytanie...");
        dbmAdd.addNewSqfaAnswer(dbmGet.getMemberById(2), dbmGet.getSqfaQuestionById(1), "Tak, działa.");
        dbmAdd.addNewSqfaAnswer(dbmGet.getMemberById(2), dbmGet.getSqfaQuestionById(1), "Tak, jest.");
        dbmAdd.addNewSqfaAnswer(dbmGet.getMemberById(1), dbmGet.getSqfaQuestionById(1), "test.");
        dbmAdd.addNewSqfaAnswer(dbmGet.getMemberById(2), dbmGet.getSqfaQuestionById(1), "Tak, tak.");
        dbmAdd.addNewSqfaAnswer(dbmGet.getMemberById(3), dbmGet.getSqfaQuestionById(1), "Dyntka");
        dbmAdd.addNewSqfaAnswerComment(dbmGet.getMemberById(1), dbmGet.getSqfaAnswerById(1), "Dobra robota, dzieki");
        
        dbmOther.incrementSqfaAnswerPoints(dbmGet.getSqfaAnswerById(1));
        dbmOther.incrementSqfaAnswerPoints(dbmGet.getSqfaAnswerById(2));
        dbmOther.incrementSqfaAnswerPoints(dbmGet.getSqfaAnswerById(2));
        dbmOther.incrementSqfaAnswerPoints(dbmGet.getSqfaAnswerById(3));
        
//        dbmUpdate.updateMemberProfile(dbmGet.getMemberById(1), "bbZ", "newPassword", "dspoko2@interia.pl", 22, 11, 1991);
//        dbmUpdate.updateGameInfo(dbmGet.getGameById(1), "JK3", true, true, true, 22, 11, 1991, "Simulator");
        
        System.out.println("Popularność game1: " + dbmGet.getGamePopularityProcent(dbmGet.getGameById(1)) + "%");
        System.out.println("Popularność game2: " + dbmGet.getGamePopularityProcent(dbmGet.getGameById(2)) + "%");
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsTillNow = calendar.getTimeInMillis();
        Date dateTo = new Date(secondsTillNow);
        calendar.clear();
        calendar.set(2013, 10, 22);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        Date dateFrom = new Date(secondsSinceEpoch);
        
        if(dateFrom.before(dateTo)) {
            System.out.println("dateFrom is before dateTo");
        } else if(dateFrom.after(dateTo)) {
            System.err.println("dateFrom is after dateTo");
        }
                
        try {
            PdfModel pdf = new PdfModel();
            pdf.createNewMembersRaport(dateFrom, dateTo);
            pdf.createGamesAdditionsReport(dateFrom);
            pdf.createSqfaSummaryReport(dateFrom, dateTo);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(TempDBMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  
}
