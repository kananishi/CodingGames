package generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileSearcher {
	private static final String sourceFolder = System.getProperty("user.home")
			+ "\\Documents\\Work\\sources\\CodingGames\\";
	private static final String javaProject = "src\\main\\java\\";

	private static final String actualGame = "SpringChallenge20\\";
	private static final String gameFolder = "game\\";

	private static final String fileGenerator = "FileGenerator\\";
	private static final String singleFileFolder = "\\generated\\singleFile\\Player.txt";

	private static final String defaultImport = "import java.util.*;\n" + "import java.io.*;\n"
			+ "import java.math.*;\n" + "import static java.util.stream.Collectors.toList;";

	/**
	 * Concatena todas as classes da classesFolder num unico arquivo na
	 * singleFileFolder
	 *
	 * @throws IOException
	 */
	private static void run(final String soruce, final String actualGame) throws IOException {
		final File fout = new File(sourceFolder + fileGenerator + javaProject + singleFileFolder);
		final PrintWriter pw = new PrintWriter(new FileWriter(fout));
		pw.print(defaultImport);

		final File dir = new File(sourceFolder + actualGame + javaProject + gameFolder);
		concatenateDirectoryFiles(dir, pw);
		pw.flush();
		pw.close();
	}

	private static void concatenateDirectoryFiles(final File dir, final PrintWriter fout) {
		if (dir.isDirectory()) {
			for (final File file : dir.listFiles()) {
				concatenateDirectoryFiles(file, fout);
			}
		}
		if (dir.isFile()) {
			copyFileToNewFile(dir, fout);
		}
		return;
	}

	private static void copyFileToNewFile(final File file, final PrintWriter fout) {
		try {
			final Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				final String line = in.nextLine();
				if (!ignoreOnCompile(line)) {
					if (isPublicClass(line)) {
						// como nos jogos nao eh possivel ter uma classe,
						// interface ou enum public eh preciso retirar a declaracao public
						fout.println(line.replace("public", ""));
						continue;
					}
					fout.println(line);
				}
			}
			in.close();
		} catch (final Exception e) {
		}
	}

	/**
	 * Retorna false se a linha for package, import, ou comentario
	 */
	private static boolean ignoreOnCompile(final String line) {
		return line.startsWith("package") || line.startsWith("import") || line.startsWith("//")
				|| line.startsWith("/**") || line.startsWith("*") || line.startsWith(" *") || line.startsWith("*/");
	}

	/**
	 * Retorna true se a linha for a declaracao de uma classe ou uma interface
	 * publica
	 */
	private static boolean isPublicClass(final String line) {
		return line.contains("public")
				&& (line.contains("class") || line.contains("interface") || line.contains("enum"));
	}
}