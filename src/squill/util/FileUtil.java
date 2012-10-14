package squill.util;

import java.io.*;

public class FileUtil {
	public static File javaFile(String baseDir, String packageName, String className) {
		return new File(new File(baseDir, packageName.replace('.', File.separatorChar)), className + ".java");
	}
	
	public static void writeFile(String content, File file) {
		final File path = file.getParentFile();
		if (!path.exists()) {
			try {
				path.mkdirs();
			} catch (SecurityException e) {
				throw new RuntimeException("No permissions to create a directory for file " + file, e);
			}
		}
		try {
			final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException ioe) {
			throw new RuntimeException("Error writing file " + file, ioe);
		}
	}
	
	public static String path(final Class<?> type) {
		return type.getPackage().getName().replace('.', '/') + '/';
	}
}
