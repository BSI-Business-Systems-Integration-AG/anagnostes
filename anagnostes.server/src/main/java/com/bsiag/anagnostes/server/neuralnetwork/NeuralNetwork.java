package com.bsiag.anagnostes.server.neuralnetwork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bsiag.anagnostes.server.neuralnetwork.util.ImageUtility;
import com.bsiag.anagnostes.shared.hcr.Output;
import com.bsiag.anagnostes.shared.hcr.OutputEntry;

/**
 * Implements a convolutional neural network for handwritten character digit using a LeNet architecture and MNIST image dimensons. 
 */
public class NeuralNetwork {
	
	private static final Logger log = LoggerFactory.getLogger(NeuralNetwork.class);

	private MultiLayerNetwork m_network;

	/**
	 * Builds a network with initial (random) weights/parameters.
	 */
	public NeuralNetwork() {
		MultiLayerConfiguration configuration = LeNet.networkConfiguration();
		m_network = new MultiLayerNetwork(configuration);
		m_network.init();
	}
	
	/**
	 * Constructs a network using weights/parameters from the specified file.
	 */
	public NeuralNetwork(File modelFile) {
		load(modelFile);
	}

	/** 
	 * Train the network for the specified number of epochs. 
	 */
	public void train(DataSetIterator trainData, DataSetIterator validationData, int epochs) {
		for(int epoch = 1; epoch <= epochs; epoch++) {
			
			// train the network using training data
			log.info("Starting epoch {}, samples: {}", epoch, trainData.numExamples());
			trainData.reset();
			m_network.fit(trainData);
			
			// evaluate performance using validation data
			validationData.reset();			
			evaluate(validationData);
		}
	}
	
	/** 
	 * Train the network for the specified number of epochs. 
	 */
	public void trainBackup(DataSetIterator trainData, DataSetIterator validationData, int epochs) {
		for(int epoch = 1; epoch <= epochs; epoch++) {
			
			// train the network using training data
			log.info("Starting epoch {}, samples: {}", epoch, trainData.numExamples());
			trainData.reset();
			m_network.fit(trainData);
			log.info("Epoch {} completed", epoch);
			
			// evaluate performance using validation data
			evaluate(validationData);
			validationData.reset();			
		}
	}
	
	/**
	 * Saves the current state of the network to the specified file.
	 */
	public void store(File modelFile)  {
		log.info("Writing network model to file {}", modelFile.getAbsolutePath());
		
		try {
			ModelSerializer.writeModel(m_network, modelFile, true);
		} catch (IOException e) {
			throw new RuntimeException("Failed to store model: " + e.getMessage(), e);
		}
	}

	/**
	 * Loads a network from the specified file.
	 */
	public void load(File modelFile) {
		log.info("Loading network model from file {}", modelFile.getAbsolutePath());
		
		try {
			m_network = ModelSerializer.restoreMultiLayerNetwork(modelFile);
		} catch (IOException e) {
			log.error("Failed to load model", e);
		}
	}

	/**
	 * Evaluates the network performance using the specified test data.
	 */
	public void evaluate(DataSetIterator testData) {
		if(testData == null || testData.numExamples() == 0) {
			return;
		}
		
		log.info("Evaluate model....");
		long timeStart = System.currentTimeMillis();
		
		Evaluation evaluation = new Evaluation(LeNet.NUM_OUTPUTS);
		int samples = 0;
		
		while (testData.hasNext()) {
			DataSet ds = testData.next();
			if(ds.getFeatureMatrix() != null) {
				INDArray output = m_network.output(ds.getFeatureMatrix(), false);
				evaluation.eval(ds.getLabels(), output);
				samples += ds.numExamples();
			}
		}
		
		long timeStop = System.currentTimeMillis();
		
		log.info(evaluation.stats());
		log.info("Number of samples: {}, processing time [ms]: {}", samples, timeStop - timeStart);
	}

	/**
	 * Normalizes the image, performs recognition and returns the recognition result.
	 */
	public Output recognize(String imageFileName) {
		try {
			File imageFile = new File(imageFileName);
			BufferedImage image = ImageIO.read(imageFile);
			return recognize(image);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read image: " + imageFileName, e);
		}
	}

	/**
	 * Normalizes image, performs recognition and returns the result.
	 */
	public Output recognize(BufferedImage image) {
		float[] normalizeImage = normalizeImage(image);

		INDArray input = Nd4j.create(normalizeImage);
		INDArray output = m_network.output(input);

		List<OutputEntry> entries = new ArrayList<>();
		for (int i = 0; i < LeNet.NUM_OUTPUTS; i++) {
			entries.add(new OutputEntry("" + i, output.getDouble(i)));
		}

		return new Output(entries);
	}

	/**
	 * Normalizes the image into the MNIST format (28x28 gray scale image).
	 */
	private float[] normalizeImage(BufferedImage image) {
		float[] normalizedRawData;
		try {
			normalizedRawData = ImageUtility.transformToMnistIteratorFormat(image);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't normalize the image", e);
		}
		return normalizedRawData;
	}
}
