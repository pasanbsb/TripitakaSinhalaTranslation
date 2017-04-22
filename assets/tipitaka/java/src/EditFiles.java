import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pasan on 2/15/2016.
 */
public class EditFiles {
    public void walk( String path ) throws IOException {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                if(FilenameUtils.getExtension(f.getAbsolutePath()).equals("html"))
                process(f);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EditFiles fw = new EditFiles();
        fw.walk("D:/WORK/Projects/My/Android/SlidingMenu/assets/tipitaka" );
    }

    public void process(File f) throws IOException {
        List<String> newLines = new ArrayList<String>();
        List<String> lines = FileUtils.readLines(f);

        for (String line : lines) {
//            if((line.contains("</TITLE>") || line.contains("</title>"))) {
//                line = line + "\n<link rel=\"stylesheet\" href=\"file:///android_asset/tipitaka/css/styles.css\">";
//            }
//            if((line.contains("<BODY") || line.contains("<body"))) {
//                line = "<BODY class=\"content\">";
//            }

//            if((line.contains("\\n") || line.contains("\\q"))) {
//                line = "";
//            }

            if((line.contains("[\\"))) {
                line = "";
            }
            newLines.add(line);
        }
        FileUtils.writeLines(f, newLines);
    }
}
