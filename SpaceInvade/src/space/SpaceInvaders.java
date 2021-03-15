package space;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.*;

/**
 *
 * @author
 */
public class SpaceInvaders extends JFrame implements Commons {

    /**
     *
     */
    private static final long serialVersionUID = -4905230094675077405L;

    private JButton start, help;

    /*
	 * Inicio
     */
    private static final String TOP_MESSAGE = "Space Invaders <br> Java Version";
    private static final String INITIAL_MESSAGE = "Rast gele";
    /*
	 * Ajuda
     */
    private static final String HELP_TOP_MESSAGE = "Yardim";
    private static final String HELP_MESSAGE = "Controles: "
            + "ok tuslari, space ates";

    JFrame frame = new JFrame("Space Invaders");
    JFrame frame2 = new JFrame("Space Invaders");
    JFrame frame3 = new JFrame("Yardim");

    /*
	 * Constructor
     */
    public SpaceInvaders() {
        String topmessage = "<html><br><br>" + TOP_MESSAGE + "</html>";
        String message = "<html>" + INITIAL_MESSAGE + "</html>";

        start = new JButton("Basla");
        start.addActionListener(new ButtonListener());
        start.setBounds(800, 800, 200, 100);

        JLabel tekst = new JLabel(message, SwingConstants.CENTER);
        JLabel toptekst = new JLabel(topmessage, SwingConstants.CENTER);

        Font font = new Font("Helvetica", Font.BOLD, 12);
        tekst.setFont(font);

        Font font2 = new Font("Helvetica", Font.BOLD, 20);
        toptekst.setFont(font2);

        frame2.setTitle("Space Invaders");

        frame2.add(tekst);

        frame2.add(toptekst, BorderLayout.PAGE_START);
        JPanel nedredel = new JPanel();

        nedredel.add(start);

        frame2.add(nedredel, BorderLayout.PAGE_END);
        frame2.setSize(500, 500);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
        frame2.setResizable(false);

    }

    public void closeIntro() {
        frame2.dispose();
        frame3.dispose();
    }

    public void closeHelp() {
        frame3.dispose();
    }

  

    

    public static boolean autoplot = true;
    public static FileHandler fhandler;

    public static void main(String[] args) throws Exception {
        
     
        new SpaceInvaders();

       

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(BOARD_WIDTH, BOARD_HEIGTH);
            //frame.getContentPane().add(new Board(0,147,300)); //En İyi Konum.
            //frame.getContentPane().add(new Board(0,500,300)); //En Kötü Konum.
            frame.getContentPane().add(new Board(30,0,300));
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            closeIntro();

        }
    }

    private class CloseHelp implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            closeHelp();
        }
    }

}
