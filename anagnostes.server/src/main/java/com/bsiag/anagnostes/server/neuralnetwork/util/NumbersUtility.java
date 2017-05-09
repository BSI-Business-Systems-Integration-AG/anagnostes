package com.bsiag.anagnostes.server.neuralnetwork.util;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;
import org.eclipse.scout.rt.platform.config.CONFIG;

public class NumbersUtility {

	public static final String NUMBERS_FOLDER_DEFAULT = "../numbers";
	public static final List<String> TEST_FOLDER_NAMES = Arrays.asList("0020_CH4M", "0021_CH4M");
	
	public static final List<String> LABELS = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
	public static final String IMG_PATH = Paths.get(CONFIG.getPropertyValue(NumberFolderPath.class) + File.separator).toAbsolutePath().toString();
	
	private static final String FILE_REGEX = "^number-\\d+.png$";
	private static final String FOLDER_REGEX = "^\\d{4}_\\S{4}$";

	private static final Map<String, List<NumberFileEntry>> m_fileNameCache = new HashMap<>();
	
	public static class NumberFileEntry {
		private String m_label;
		private String m_fileName;

		public NumberFileEntry(String label, String fileName) {
			this.m_label = label;
			this.m_fileName = fileName;
		}

		public final String getLabel() {
			return m_label;
		}

		public final String getFilename() {
			return m_fileName;
		}
	}
	
	public static class NumberFolderPath extends AbstractStringConfigProperty {
		
		@Override
		public String getKey() {
			return "number.folder.path";
		}
		
		@Override
		protected String getDefaultValue() {
			return NUMBERS_FOLDER_DEFAULT;
		}
	}

	public static List<NumberFileEntry> getAllFileNames(List<String> folderNames) {
		return getAllFileNames(IMG_PATH, folderNames);
	}

	public static List<NumberFileEntry> getAllFileNames(String baseFolder, List<String> folderNames) {
		baseFolder = baseFolder.endsWith(File.separator) ? baseFolder : baseFolder + File.separator;

		List<NumberFileEntry> allFileNames = new ArrayList<>();
		for (String folder : folderNames) {
			allFileNames.addAll(getAllFileNamesForFolder(baseFolder, folder));
		}
		return allFileNames;
	}

	public static List<NumberFileEntry> getAllFileNamesForFolder(String folder) {
		return getAllFileNamesForFolder(IMG_PATH, folder);
	}
	
	public static List<NumberFileEntry> getAllFileNamesForFolder(String baseFolder, String folder) {
		List<NumberFileEntry> cachedList = m_fileNameCache.get(folder);
		if(cachedList != null) {
			return cachedList;
		}
		
		List<NumberFileEntry> allFileNames = new ArrayList<>();
		
		for (String label : LABELS) {
			File imageFolder = Paths.get(baseFolder).resolve(folder).resolve(label).toFile();
			String[] imageFileNames = imageFolder.list(new RegexFileFilter(FILE_REGEX));
			
			for (String imageFileName : imageFileNames) {
				File imageFile = new File(imageFolder, imageFileName);
				allFileNames.add(new NumberFileEntry(label,	imageFile.getAbsolutePath()));
			}
		}
		
		m_fileNameCache.put(folder, allFileNames);
		
		return allFileNames;
	}

	public static int getNumExamples(List<String> folderNames) {
		return getNumExamples(IMG_PATH, folderNames);
	}

	public static int getNumExamples(String baseFolder, List<String> folderNames) {
		baseFolder = baseFolder.endsWith(File.separator) ? baseFolder : baseFolder + File.separator;

		int numExamples = 0;
		for (String folderName : folderNames) {
			numExamples += getNumExamples(baseFolder, folderName);
		}
		return numExamples;
	}

	private static int getNumExamples(String baseFolder, String folder) {
		int numExamples = 0;
		for (String label : LABELS) {
			numExamples += new File(baseFolder + folder + File.separator + label)
					.list(new RegexFileFilter(FILE_REGEX)).length;
		}
		return numExamples;
	}

	public static List<String> getTestFolderNames() {
		return TEST_FOLDER_NAMES;
	}

	
	public static List<String> getAllFolderNames() {
		return getAllFolderNames(IMG_PATH);
	}
	
	public static List<String> getAllFolderNames(String baseFolder) {
		return Arrays.asList(new File(baseFolder).list(new RegexFileFilter(FOLDER_REGEX)));
	}
	
	public static List<String> getTrainFolderNames(String baseFolder) {
		String[] array = new File(baseFolder).list(new RegexFileFilter(FOLDER_REGEX));
		List<String> list = new ArrayList<>(Arrays.asList(array));
		list.removeAll(TEST_FOLDER_NAMES);
		return list;
	}
}
