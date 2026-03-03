package jay.chess;

import jay.chess.gui.ChessJFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

//        URI uri = Main.class.getResource("/assets/icons").toURI();
//        var path = Paths.get(uri);
//
//        Files.list(path).forEach(p -> System.out.println("found " + p));

        SwingUtilities.invokeLater(() -> {
            ChessJFrame frame = new ChessJFrame();
//            frame.setSize(frame.getPreferredSize());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });

    }
}