package ucd.javafx.game_fx;

import models.*;
import service.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
//    To play the game turn by turn
    public static Queue<Player> turnQueue= new LinkedList<Player>();
    private double sceneWidth = 1024;
    private double sceneHeight = 768;

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter Player 1 name and starting lane");
//        Player p1 = new Player(sc.next(), sc.nextInt());
//        p1.setLocation(new Coordinates(p1.getLocation().getX(),9));
//        turnQueue.add(p1);
//        System.out.println("Enter Player 2 name and starting lane");
//        Player p2 = new Player(sc.next(), sc.nextInt());
//        p2.setLocation(new Coordinates(p2.getLocation().getX(),9));
//        turnQueue.add(p2);
        GameEssentials game = new GameEssentials();
//        GameBoard gameBoard = game.initBoard(10,10,2);
//        Player winner = game.startGame(gameBoard,turnQueue);
//        System.out.println("winner is : "+winner.getPlayerName());
        Player p = new Player("Pranit",1);
        p.setScore(150);
game.addPlayertoLeaderBoard(p);

    }

}