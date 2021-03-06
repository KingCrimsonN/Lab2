import java.io.*;
import java.util.StringTokenizer;

public class FileInput {
    /**
     * main getting String from a file algorithm
     *
     * @param file file name
     * @return String that the file contains
     * @throws IOException
     */
    public static String gString(String file) throws IOException {
        String res = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s;
            do {
                s = br.readLine();
                if (s != null) {
                    res += "\n" + s;
                }
            } while (s != null);
        } catch (FileNotFoundException e) {
            return null;
        }
        return res;
    }

    /**
     * normal executable getString
     *
     * @param file file name
     * @return string in the file
     */
    public static String getString(String file) {
        String s = "";
        try {
            s = gString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static Store rc(String file) throws IOException{
        Store st = new Store();
        int depNum = -1;
        String res = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s;
            do {
                s = br.readLine();
                if (s != null) {
                    if (s.charAt(0)=='/'&&s.charAt(1)=='g') {
                        String nam="";
                        for (int i=3;i<s.length();i++)
                            nam+=s.charAt(i);
                        st.add(nam);
                        depNum++;
                    }
                    else {
                        String temnam="";
                        String nam="";
                        String temp="a";
                        String image="";
                        String desc="";
                        int val=-1,price=-1;
                        StringTokenizer tkn = new StringTokenizer(s);
                        while (!checkNum(temp)) {
                            temp = tkn.nextToken();
                            if (!checkNum(temp))
                                nam += " " + temp;
                        }
                        for (int i=1; i<nam.length(); i++){
                            temnam+=nam.charAt(i);
                        }
                        nam=temnam;
                        price = Integer.valueOf(temp);
                        temp = tkn.nextToken();
                        val = Integer.valueOf(temp);
                        temp = tkn.nextToken();
                        image = temp;
                        while (tkn.hasMoreTokens()){
                            temp = tkn.nextToken();
                             desc+=temp + " ";
                        }
                        Product prod = new Product(nam,price,val,st.getDepartment(depNum),image,desc);
                        st.getDepartment(depNum).add(prod);
                    }
                }
            } while (s != null);
        } catch (FileNotFoundException e) {
            return null;
        }
        return st;
    }

    public static Store readConfig(String file) {
        Store st = new Store();
        try {
            st = rc(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return st;
    }

    public static void sf(String s) throws IOException {
        BufferedWriter fos = null;
        try {
            FileWriter fw = new FileWriter("config.ttt");
            fos = new BufferedWriter(fw);
            fos.write(s);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void saveFile(String s){
        try{
            sf(s);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * check if the word is number
     * @param s word
     * @return oh that's a boolean
     */
    private static boolean checkNum(String s){
        for (int i=0; i<s.length(); i++){
            if(Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
