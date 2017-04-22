import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasan on 2/15/2016.
 */
public class ChangeLinks {

    public static void main(String[] args) throws IOException {
        File f = new File("D:\\WORK\\Projects\\My\\Android\\SlidingMenu\\assets\\tipitaka\\3Abhidhamma-Pitaka\\index.html");

        List<String> newLines = new ArrayList<String>();
        List<String> lines = FileUtils.readLines(f);
        String sutraName = "";
        try {
            for (String line : lines) {
                if (line.contains("\"sam\"")) {
                    newLines.add("\n");
                }
                if (line.contains("<td>")
                        && (!line.contains("<a")
                        && !line.contains("class")
                        && !line.contains("Sinhala")
                        && !line.contains("English")
                        && !line.contains("Polish")
                        && !line.contains("1")
                        && !line.contains("Pali")
                        && !line.contains("<td></td>"))) {
                    String[] name = line.split("<td>")[1].split("</td>");

                    sutraName = name[0];
                }


                if (line.contains("-s.html")) {
                    String[] linkA = line.split("<td>")[1].split("</td>");
                    String link = linkA[0];
                    link = link.replace("Sinhala", sutraName);
                    newLines.add(link);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : newLines) {
            System.out.println(line);
        }
    }
}
