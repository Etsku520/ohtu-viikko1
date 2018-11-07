/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hceetu
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchLoyty() {
        Player player = stats.search("Semenko");
        
        assertEquals(player.getName(), "Semenko");
        assertEquals(player.getGoals(), 4);
        assertEquals(player.getAssists(), 12);
        assertEquals(player.getTeam(), "EDM");
    }
    
    @Test
    public void searchEiLoydy() {
        Player player = stats.search("sakki");
        
        assertTrue(player == null);
    }
    
    @Test
    public void teamTest() {
        List<Player> players = stats.team("EDM");
        String test = "Semenko Kurri Gretzky";
        
        assertEquals(3, players.size());
        assertTrue(test.contains(players.get(0).getName()));
        assertTrue(test.contains(players.get(1).getName()));
        assertTrue(test.contains(players.get(2).getName()));
    }
    
    @Test
    public void topScoreTest() {
        List<Player> players = stats.topScorers(3);
        
        assertTrue(players.get(1).getName().equals("Lemieux"));
        assertTrue(players.get(2).getName().equals("Yzerman"));
        assertTrue(players.get(0).getName().equals("Gretzky"));
    }
}
