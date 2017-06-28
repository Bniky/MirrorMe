package sample;

import sun.tools.jar.resources.jar;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Nicholas on 20/06/2017.
 */
public class JarHelper {

    public Path extractJar(String hiddenfile, String fileName){
        Path appDirectory = Paths.get(System.getProperty("user.home"), hiddenfile);
        Path databaseFile = appDirectory.resolve(fileName);

        if (! Files.exists(databaseFile)) {
            try {
                // create the app directory if it doesn't already exist:
                Files.createDirectories(appDirectory);


                JarFile jar = new JarFile("MirrorMe.jar");
                JarEntry entry = jar.getJarEntry(fileName);

                if(entry != null) {
                    InputStream defaultDatabase = getClass().getClassLoader().getResourceAsStream(fileName);
                    Files.copy(defaultDatabase, databaseFile);

                }else{
                    InputStream defaultDatabase = getClass().getClassLoader().getResourceAsStream("weatherIcons/"+ fileName);
                    Files.copy(defaultDatabase, databaseFile);
                }
            } catch (IOException exc) {
                // handle exception here, e.g. if application can run without db,
                // set flag indicating it must run in non-db mode
                // otherwise this is probably a fatal exception, show message and exit...
                exc.printStackTrace();
            }
        }
        return databaseFile;
    }
}
