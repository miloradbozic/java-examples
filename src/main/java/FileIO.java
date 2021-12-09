import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    public static List<Model> parseFile(String file) {
        List<Model> list = new ArrayList();

        FileReader frs;
        StreamTokenizer in;

        String timezone;
        String gender;
        int year;
        String name;
        int occurences;

        try {
            frs = new FileReader(file);
            in = new StreamTokenizer(frs);

            //skip headers
            in.nextToken();
            in.nextToken();
            in.nextToken();
            in.nextToken();
            in.nextToken();


            in.nextToken();
            while (in.ttype != StreamTokenizer.TT_EOF) {
                timezone = in.sval;
                in.nextToken();

                gender = in.sval;
                in.nextToken();

                year = (int) in.nval;
                in.nextToken();

                name = in.sval;
                in.nextToken();

                occurences = (int) in.nval;
                in.nextToken();

                list.add(new Model(timezone, gender, year, name, occurences));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return list;

    }
}