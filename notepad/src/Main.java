import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.awt.print.PrinterException;
import java.io.*;


class notepad extends JFrame implements ActionListener{
    JTextArea t;
    JFrame f;
    JScrollPane sp;
    notepad() {
        //intializing the frame and textarea
        f = new JFrame("Notepad");
        t = new JTextArea();

        sp = new JScrollPane(t);


        //intialization menubar and individual menus
        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem f1 = new JMenuItem("new");
        JMenuItem f2 = new JMenuItem("open");
        JMenuItem f3 = new JMenuItem("save");
        JMenuItem f4 = new JMenuItem("print");

        //adding actionListener to individual menuitems
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding menuitems to file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //creating the edit menu
        JMenu edit = new JMenu("Edit");

        JMenuItem f5 = new JMenuItem("cut");
        JMenuItem f6 = new JMenuItem("copy");
        JMenuItem f7 = new JMenuItem("paste");


        //adding actionListener to individual menuitems
        f5.addActionListener(this);
        f6.addActionListener(this);
        f7.addActionListener(this);


        //adding menuitems to file menu
        edit.add(f5);
        edit.add(f6);
        edit.add(f7);

        JMenuItem close = new JMenuItem("Close");

        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //set the menu
        f.getContentPane().add(sp);

        f.setJMenuBar(menu);

        f.setSize(1000, 1000);
        f.show();

    }

    //functionality implementation
    public void actionPerformed(ActionEvent e){
        //getting the user clicked function in the form of string
        String s = e.getActionCommand();

        //processing the string
        switch (s){
            case "new":
                t.setText("");
                break;
            case "open":
                //creating object of filechooser class and intializing the loaction
                JFileChooser j = new JFileChooser("D:");
                int r = j.showOpenDialog(null);
                //if the user selects a file
                if(r == JFileChooser.APPROVE_OPTION){

                    //extracting the absolute path of the selected file
                    File fi = new File(j.getSelectedFile().getAbsolutePath());

                    String s1, s2;
                    try {
                        //place pointer at the starting of the file
                        FileReader fr = new FileReader(fi);

                       //use bufferreader to read line by line
                        BufferedReader br = new BufferedReader(fr);

                        //storing the first line in s1
                        s1 = br.readLine();

                        //appending subsequent lines till end of file is reached
                        while((s2 = br.readLine()) != null){
                            s1 = s1 + "\n" + s2;
                        }
                        t.setText(s1);

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f, "operation cancelled");
                }
                break;
            case "save":
                JFileChooser ji = new JFileChooser("D:");
                int r1 = ji.showSaveDialog(null);
                //if the user selects a file
                if(r1 == JFileChooser.APPROVE_OPTION){

                    //extracting the absolute path of the selected file
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());


                    try {
                        //place pointer at the starting of the file
                        FileWriter fr = new FileWriter(fi);


                        //use bufferreader to read line by line
                        BufferedWriter br = new BufferedWriter(fr);
                        br.write(t.getText());
                        br.flush();
                        br.close();

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(f, "operation cancelled");
                }


                break;
            case "print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "cut":
                t.cut();
                break;
            case "copy":
                t.copy();
                break;
            case "paste":
                t.paste();
                break;
            case "close":
                f.setVisible(false);
                break;

        }

    }
    public static void main(String args[]) {
        notepad note = new notepad();

}
}
