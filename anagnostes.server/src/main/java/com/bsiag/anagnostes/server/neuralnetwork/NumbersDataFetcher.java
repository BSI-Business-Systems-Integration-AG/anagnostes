package com.bsiag.anagnostes.server.neuralnetwork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import org.deeplearning4j.datasets.fetchers.BaseDataFetcher;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bsiag.anagnostes.server.neuralnetwork.util.ImageUtility;
import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility;
import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility.NumberFileEntry;

public class NumbersDataFetcher extends BaseDataFetcher {

	private static final Logger log = LoggerFactory.getLogger(NumbersDataFetcher.class);

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
		m_allFileNames = getAllFileNames();
		m_train = train;
		
		numOutcomes = NumbersUtility.LABELS.size();
		inputColumns = NumbersUtility.getNumExamples(getFolderNames());
		totalExamples = inputColumns;
		
		log.info("train modus: " + train + ", number of examples: " + totalExamples + ", number of folder names: " + m_allFileNames.size() + ", base folder: " + m_baseFolder);
	}

	/**
	 * Fetches the next batch of example data and updates this object's state accordingly.
	 * Prepares the specified amount of examples for the next batch. 
	 * To access the modified state use: {@link #cursor()} and {@link #next()}.
	 */
	@Override
	public void fetch(int numExamples) {
		float[][] featureData = new float[numExamples][0];
		float[][] labelData = new float[numExamples][0];

		int examplesRead = 0;

		for (; examplesRead < numExamples; examplesRead++) {
			// check that we still have more data
			if (cursor + examplesRead >= m_allFileNames.size()) {
				break;
			}

			// convert single number image to feature/label data
			NumberFileEntry entry = m_allFileNames.get(cursor + examplesRead);
			BufferedImage image = ImageUtility.readImage(entry.getFilename());
			featureData[examplesRead] = ImageUtility.toMnistArray(image);
			labelData[examplesRead] = toLabelArray(entry.getLabel());
		}

		// update cursor value
		cursor += examplesRead;

		// update pointer to current example data
		if(dataIsValid(featureData, labelData, numExamples, examplesRead)) {
			INDArray features = Nd4j.create(featureData);
			INDArray labels = Nd4j.create(labelData);

			curr = new DataSet(features, labels);
		}
		else {
			curr = new DataSet();
		}
	}

	private boolean dataIsValid(float [][] featureData, float [][] labelData, int numExamples, int examplesRead) {
		if(featureData.length != numExamples || labelData.length != numExamples || featureData.length != labelData.length) {
			log.warn("at cursor value "+ cursor() +" " +
					"featureData.length: " + featureData.length + " (expected value " + numExamples + ") " + 
					"labelData.length:   " + labelData.length + "(expected value " + numExamples + ")");

			return false;
		}

		int input_dimension = ImageUtility.SIZE_X * ImageUtility.SIZE_Y;
		int output_dimension = LeNet.NUM_OUTPUTS; 

		for(int i = 0; i < featureData.length; i++) {
			if(featureData[i].length != input_dimension) {
				log.warn("at cursor value "+ cursor() +" " +
						"featureData["+i+"].length: " + featureData[i].length + " (expected value " + input_dimension + ")");

				return false;
			}

			if(labelData[i].length != output_dimension) {
				log.warn("labelData["+i+"].length:   " + labelData[i].length + " (expected value " + output_dimension + ")");

				return false;
			}
		}

		return true;
	}

	private List<NumberFileEntry> getAllFileNames() {
		return NumbersUtility.getAllFileNames(m_baseFolder, getFolderNames());
	}

	private float[] toLabelArray(String label) {
		float[] labels = new float[NumbersUtility.LABELS.size()];
		labels[Integer.parseInt(label)] = 1F;
		return labels;
	}

	private List<String> getFolderNames() {
		return m_train ? NumbersUtility.getTrainFolderNames(m_baseFolder) : NumbersUtility.getTestFolderNames();
	}
}
