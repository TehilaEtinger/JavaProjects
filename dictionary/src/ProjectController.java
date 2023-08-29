
//package project;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.swing.*;

/**
 * @author Tehilla
 */
public class ProjectController implements Initializable {
    private Dictionary dictionary = new Dictionary();
    private File file; // for the dictionary file
    @FXML
    private ListView<Text> list;
    @FXML
    //for the button: "add"- to add new value to the dicrionary
    void addBtn(ActionEvent event) {
        String desc = "";
        String val = JOptionPane.showInputDialog(null, "Please enter a value:", "New Value", 1);
        if (val != null && !val.equals("") )//hold the new value
            desc = JOptionPane.showInputDialog(null, "Please enter a Description:", "New Value", 1);
        //enter to dictionary the new value
        if (desc != null && !desc.equals("") )//hold the explanation
        {
            dictionary.add(val, desc);
            list.getItems().clear();
            list.getItems().add(new Text(dictionary.toString()));        }
    }
    //for button: "open from file"- open file and put all the values from there to the dictionary
    @FXML
    void openBtn(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("select a file");
        fc.setInitialDirectory(new File("."));
        file = fc.showOpenDialog(null);
        if (file!= null) {
            try {
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fi);
        dictionary = (Dictionary) ois.readObject();
        System.out.println(dictionary.toString());
        list.getItems().clear();
        list.getItems().add(new Text(dictionary.toString()));
        ois.close();
        fi.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    //for the button: "delete" - delete an value
    @FXML
    void removeBtn(ActionEvent event) {
        String val = JOptionPane.showInputDialog(null, "Please enter a value to delete:", "Delete Value", 1);
        if (val != null && !val.equals("") )//looking for the value that have to be removed
        {
            dictionary.remove(val);
            list.getItems().clear();
            list.getItems().add(new Text(dictionary.toString()));
        }
    }
    //to the button:"save to file"- write all the values that there is in the dictionary- into a file
    @FXML
    void saveBtn(ActionEvent event) {

         FileChooser fc = new FileChooser();
        fc.setTitle("select a file");
        fc.setInitialDirectory(new File("."));
        file = fc.showSaveDialog(null);
        if (file!= null) {
            try {
                FileOutputStream fo = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(dictionary);
                out.close();
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //for the button:"search"- looking for a value to see the explanation
    @FXML
    void searchBtn(ActionEvent event) {
        String val = JOptionPane.showInputDialog(null, "Please enter a value to search for:", "Search Value", 1);
        if (val != null && !val.equals("") )//looking for the value
        {
            if (dictionary.search(val) != null)
                JOptionPane.showMessageDialog(null, dictionary.search(val), val ,1,null);
            else
                JOptionPane.showMessageDialog(null, "No such value", val ,1,null);
        }
    }
    //to the button:"update" - to change the explanation of an value
    @FXML
    void updateBtn(ActionEvent event) {
        String desc = "";
        String val = JOptionPane.showInputDialog(null, "Please enter a value to edit:", "Edit Value", 1);
        if (val != null && !val.equals("") && dictionary.search(val) != null )
            desc = JOptionPane.showInputDialog(null, "Please enter the new Description:", "Edit Value", 1);
        if (desc != null && !desc.equals("") )//looking for the value
        {
            dictionary.update(val, desc);
            list.getItems().clear();
            list.getItems().add(new Text(dictionary.toString()));
        }
    }
    public void initialize(URL location, ResourceBundle resources) {

    }
}