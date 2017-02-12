package de.ch4inl3ss.coveragecreator;

public class PathFindingService {
	private static final String JAVA_FILEEXTENSION = ".java";
	private static final String SRC_MAIN_JAVA = "src/main/java";
	private static final String SRC_TEST_JAVA = "src/test/java";

	public boolean checkFileName(String fileName) {
		if (!fileName.contains(SRC_MAIN_JAVA)) {
			return false;
		}
		if (!fileName.contains(JAVA_FILEEXTENSION)) {
			return false;
		}

		return true;
	}

	public String findAbsolutePath(String fileName) {
		return fileName.split(SRC_MAIN_JAVA)[0];
	}

	public String findClassName(String fileName) {
		String[] splittedBySlash = fileName.split("/");
		return splittedBySlash[splittedBySlash.length - 1];
	}

	public String findClassNameWithoutFileExtension(String fileName) {
		String[] splittedBySlash = fileName.split("/");
		return splittedBySlash[splittedBySlash.length - 1].replace(JAVA_FILEEXTENSION, "");
	}

	public String findFilePathToGenerateTo(String fileName) {
		String withSrcTestJava = fileName.replace(SRC_MAIN_JAVA, SRC_TEST_JAVA);
		String testClassName = findClassNameWithoutFileExtension(fileName) + "Test";
		return withSrcTestJava.replace(findClassNameWithoutFileExtension(fileName), testClassName);
	}

	public String findPackageName(String fileName) {
		String withoutAbsolutePath = fileName.replace(findAbsolutePath(fileName), "");
		String withoutClassName = withoutAbsolutePath.replace(findClassName(fileName), "");
		String withoutSrcMainJava = withoutClassName.replace(SRC_MAIN_JAVA, "");
		String withoutInitialSlash = withoutSrcMainJava.substring(1);
		String withoutLastSlash = withoutInitialSlash.substring(0, withoutInitialSlash.length() - 1);
		return withoutLastSlash.replace("/", ".");
	}

}
