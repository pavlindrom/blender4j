package gr.pavlo.blender4j;

import java.io.File;

/**
 * Created by Pavlo on 1/29/2017.
 * Copied from https://github.com/ftomassetti/analyze-java-code-examples/blob/master/src/main/java/me/tomassetti/support/DirExplorer.java
 */
public class DirectoryExplorer {
    public interface FileHandler {
        void handle(int level, String path, File file);
    }

    public interface Filter {
        boolean interested(int level, String path, File file);
    }

    private FileHandler fileHandler;
    private Filter filter;

    public DirectoryExplorer(Filter filter, FileHandler fileHandler) {
        this.filter = filter;
        this.fileHandler = fileHandler;
    }

    public void explore(File root) {
        explore(0, "", root);
    }

    private void explore(int level, String path, File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                explore(level + 1, path + "/" + child.getName(), child);
            }
        } else {
            if (filter.interested(level, path, file)) {
                fileHandler.handle(level, path, file);
            }
        }
    }

}
