package gen;

import javax.swing.*;

/**
 * <title>Library Database</title>
 * @author Dmitrii Charuiskii
 * @version 1.0
 * Description:
 * <p> This program making for up a practice level with clean Java. This is an experience for understanding
 * Java syntax. </p>
 * Purposes:
 * <ul>
 *     <li> Check Java opportunities </li>
 *     <li> Realising assign tasks by front myself (without ready code) </li>
 *     <li><strong> Finish it functionalible and then make good looking </strong></li>
 * </ul>
 *
 */
public class MainMVC {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View theView = new View();
            Model thModel = new Model();
            Controller theController = new Controller(theView, thModel);
            theView.setVisible(true);
        });
    }
}