package checkCorrectInput;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * CheckPathCorrect class
 */
public class CheckCorrectPath {

    /**
     *
     * @param args path is needed to check
     * @return is path correct
     */
    public boolean checkPath(String args) {

        if (args.length() == 0) {
            System.out.println("Не инициализирована переменная");
            return false;
        }

        Path path = Paths.get(args.trim());
        Path base = Paths.get("");
        Path absolutePath = base.resolve(path).toAbsolutePath();
        File file = new File(String.valueOf(absolutePath));

        if (file.isDirectory()) {
            System.out.print("Вы ввели путь до директории, а не файла." + "\n");
            return false;
        }

        if (!file.exists()) {
            System.out.print("Такого файла не существует." + "\n");
            System.out.println("Путь, по которому пытались найти файл: " + absolutePath.toString());
            return false;
        }
        return true;
    }
}
