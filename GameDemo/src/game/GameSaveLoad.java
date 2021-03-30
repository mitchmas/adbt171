package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaveLoad {

    public static void save(GameLevel level, String fileName) throws IOException {
        //check if boss fight
        if (level.getLevelName() == "Level4") {
            //display error message
            System.out.println("ERROR: Cannot Save During Boss Fight!");
        } else {
            boolean append = false;
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileName, append);
                //write level to file
                writer.write(level.getLevelName() + "\n");

                for (DynamicBody body : level.getDynamicBodies()) {
                    if (body instanceof Player) {
                        //write player and coordinates/variables accordingly on a single line
                        writer.write("Player," + body.getPosition().x + "," +
                                body.getPosition().y + "," +
                                ((Player) body).getKillCount() + "," +
                                level.getGame().getCoinCount() + "," +
                                level.getEnemyCount() + "," +
                                level.getEnemyCount() + "\n");
                    } else if (body instanceof Enemy) {
                        //write enemy and coordinates/variables accordingly on a single line
                        writer.write("Enemy," + body.getPosition().x + "," +
                                body.getPosition().y + "," +
                                ((Enemy) body).getWalkspeed() + "\n");
                    } else if (body instanceof Coins) {
                        //write coordinates of coins to a single line
                        writer.write("Coins," + body.getPosition().x + "," +
                                body.getPosition().y + "\n");
                    }
                }
            } finally {
                if (writer != null) {
                    // close file and print message
                    writer.close();
                    System.out.println("Game saved successfully.");
                }
            }
        }
    }

    public static GameLevel load(Game game, String fileName) throws IOException
    {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            //read initial line for level and set accordingly
            String line = reader.readLine();
            GameLevel level = null;
            if (line.equals("Level1"))
                level = new Level1(game);
            else if (line.equals("Level2"))
                level = new Level2(game);
            else if (line.equals("Level3"))
                level = new Level3(game);

            line = reader.readLine();
            while (line!= null){
                //read through and split each item to a respective token
                String[] tokens = line.split(",");

                if (tokens[0].equals("Player")) {
                    //match token to player coords/variables
                    Player p = new Player(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    p.setPosition(new Vec2(x,y));
                    int kc = Integer.parseInt(tokens[3]);
                    p.setKillCount(kc);
                    int totalCoins = Integer.parseInt(tokens[4]);
                    game.setCoinCount(totalCoins);
                    int enemyCount = Integer.parseInt(tokens[5]);
                    level.setEnemyCount(enemyCount);
                    level.setPlayer(p);
                    p.addCollisionListener(new GoalReach(level,game));
                    p.addCollisionListener(new CoinPickup(game));
                    p.addCollisionListener(new PlayerDeath());
                }
                else if (tokens[0].equals("Enemy")) {
                    //match token to enemy coords/variables
                    Enemy e = new Enemy(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x,y));
                    e.addCollisionListener(new EnemyCollide(e));
                    int walkSpeed = Integer.parseInt(tokens[3]);
                    e.setWalkspeed(walkSpeed);
                    e.startWalking(walkSpeed);
                }
                else if (tokens[0].equals("Coins")) {
                    //match token to coins coordinates
                    Coins c = new Coins(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    c.setPosition(new Vec2(x, y));
                }
                line = reader.readLine();
            }
            //print message and return level loaded
            System.out.println("Game loaded successfully.");
            return level;

        }
        finally {
            //close both buffer and file reader in order of most recently opened becomes closed first.
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
