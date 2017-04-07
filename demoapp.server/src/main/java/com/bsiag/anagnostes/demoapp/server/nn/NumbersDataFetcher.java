package com.bsiag.anagnostes.demoapp.server.nn;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.deeplearning4j.datasets.fetchers.BaseDataFetcher;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import com.bsiag.anagnostes.demoapp.server.nn.util.ImageUtility;
import com.bsiag.anagnostes.demoapp.server.nn.util.NumbersUtility;
import com.bsiag.anagnostes.demoapp.server.nn.util.NumbersUtility.NumberFileEntry;

public class NumbersDataFetcher extends BaseDataFetcher {
	private static final long serialVersionUID = 1L;
	private final String m_baseFolder;
	private final boolean m_train;
	
	private List<NumberFileEntry> m_allFileNames;

	public NumbersDataFetcher() {
		this(NumbersUtility.IMG_PATH);
	}

	public NumbersDataFetcher(boolean train) {
		this(NumbersUtility.IMG_PATH, train);
	}

	public NumbersDataFetcher(final String baseFolder) {
		this(baseFolder, true);
	}

	public NumbersDataFetcher(final String baseFolder, boolean train) {
		m_baseFolder = baseFolder.endsWith(File.separator) ? baseFolder : baseFolder + File.separator;
		m_train = train;

		numOutcomes = NumbersUtility.LABELS.size();
		inputColumns = NumbersUtility.getNumExamples(getFolderNames());
		totalExamples = inputColumns;

		m_allFileNames = getAllFileNames();
	}

	private List<NumberFileEntry> getAllFileNames() {
		return NumbersUtility.getAllFileNames(m_baseFolder, getFolderNames());
	}

	@Override
	public void fetch(int numExamples) {
		float[][] featureData = new float[numExamples][0];
		float[][] labelData = new float[numExamples][0];

		int examplesRead = 0;

		for (; examplesRead < numExamples; examplesRead++) {
			if (cursor + examplesRead >= m_allFileNames.size()) {
				break;
			}
			NumberFileEntry entry = m_allFileNames.get(cursor + examplesRead);

			featureData[examplesRead] = imageFileNameToMnsitFormat(entry.getFilename());
			labelData[examplesRead] = toLabelArray(entry.getLabel());
		}
		cursor += examplesRead;

		INDArray features = Nd4j.create(featureData);
		INDArray labels = Nd4j.create(labelData);
		curr = new DataSet(features, labels);
	}

	private float[] toLabelArray(String label) {
		float[] labels = new float[NumbersUtility.LABELS.size()];
		labels[Integer.parseInt(label)] = 1F;
		return labels;
	}

	private float[] imageFileNameToMnsitFormat(String imageFileName) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(imageFileName));
			return ImageUtility.transformToMnsitIteratorFormat(image);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't read image: " + imageFileName);
		}
	}

	public int getNumExamples() {
		return totalExamples;
	}
	
	private List<String> getFolderNames() {
		return m_train ? NumbersUtility.getTrainFolderNames(m_baseFolder) : NumbersUtility.getTestFolderNames();
	}
}
