package fileworkers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.io.File;

public class FileSearcher extends RecursiveTask<String> {
    private final File folder;
    public FileSearcher(File folder) {
        this.folder = folder;
    }

    @Override
    protected String compute() {
        if (folder.isFile()) {
            if (folder.getAbsolutePath().contains(".json") ||
                folder.getAbsolutePath().contains(".csv")) {

                    return folder.getAbsolutePath() + "\n";
            }
            return "";
        }
        StringBuilder paths = new StringBuilder();
        List<FileSearcher> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();
        for (File file : files) {
            FileSearcher task = new FileSearcher(file);
            task.fork();
            subTasks.add(task);
        }
        for (FileSearcher task : subTasks) {
            paths.append(task.join());
        }
        return paths.toString();
    }
}