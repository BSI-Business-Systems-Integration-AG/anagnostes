package com.bsiag.anagnostes.server.neuralnetwork;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.junit.Ignore;
import org.junit.Test;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import com.bsiag.anagnostes.server.ServerTest;
import com.bsiag.anagnostes.server.hcr.HcrService;

public class NeuralNetworkTest extends ServerTest {
	
	public static final String USER_HOME = System.getProperty("user.home");
	public static final String USER_MODEL = USER_HOME + File.separator + "user_model.zip";
	public static final String USER_COMBINED_MODEL = USER_HOME + File.separator + "user_combined_model.zip";
	private static final String USER_TEMP_MODEL = USER_HOME + File.separator + "user_temp_model.zip";

	/**
	 * Use this test to play around with testing neural networks.
	 */
	@Test
	public void buildAndTrainTempModel() {
		NeuralNetwork model = new NeuralNetwork();
		DataSetIterator numbersTrainData = LeNet.numbersTrainSetIterator();
		DataSetIterator numbersValidationData = LeNet.numbersTestSetIterator();
		int epochs = 4;
		
		model.train(numbersTrainData, numbersValidationData, epochs);
		model.store(new File(USER_TEMP_MODEL));
	}

	@Test
	public void evaluateTempModel() {
		NeuralNetwork model = new NeuralNetwork();
		DataSetIterator numbersTestData = new NumbersDatasetIterator(LeNet.BATCH_SIZE, false);
		
		model.load(new File(USER_TEMP_MODEL));
		model.evaluate(numbersTestData);
	}

	@Test
	@Ignore
	public void buildAndTrainNumbersModel() {
		NeuralNetwork model = new NeuralNetwork();
		DataSetIterator numbersTrainData = LeNet.numbersTrainSetIterator();
		DataSetIterator numbersValidationData = LeNet.numbersTestSetIterator();
		int epochs = 7;
		
		model.train(numbersTrainData, numbersValidationData, epochs);
		model.store(new File(USER_MODEL));
	}

	@Test
	public void evaluateNumbersModel() {
		NeuralNetwork model = new NeuralNetwork();
		DataSetIterator numbersTestData = new NumbersDatasetIterator(LeNet.BATCH_SIZE, false);
		
		model.load(new File(USER_MODEL));
		model.evaluate(numbersTestData);
	}

	@Test
	@Ignore
	public void buildAndTrainMnistModel() {
		NeuralNetwork model = new NeuralNetwork();
		DataSetIterator mnistTrainData = LeNet.mnistTrainSetIterator();
		DataSetIterator mnistValidationData = LeNet.mnistTestSetIterator();
		int epochs = 15;
		
		model.train(mnistTrainData, mnistValidationData, epochs);
		model.store(new File(USER_HOME + File.separator + HcrService.MODEL_MNIST_ZIP_NAME));
	}

	@Test
	public void evaluateMnistModel() throws IOException {
		File modelFile = new File(NeuralNetworkTest.class.getClassLoader().getResource(HcrService.MODEL_MNIST_ZIP_NAME).getFile());
		NeuralNetwork model = new NeuralNetwork(modelFile);

		assertNotNull(model);
		DataSetIterator mnistTestData = new MnistDataSetIterator(LeNet.BATCH_SIZE, false, 0);
		model.evaluate(mnistTestData);
	}

	@Test
	public void evaluateMnistModelOnNumbers() throws IOException {
		File modelFile = new File(NeuralNetworkTest.class.getClassLoader().getResource(HcrService.MODEL_MNIST_ZIP_NAME).getFile());
		NeuralNetwork model = new NeuralNetwork(modelFile);

		assertNotNull(model);
		DataSetIterator numbersTestData = new NumbersDatasetIterator(LeNet.BATCH_SIZE, false);
		model.evaluate(numbersTestData);
	}

	@Test
	@Ignore
	public void buildAndTrainCombinedModel() {
		File modelFile = new File(NeuralNetworkTest.class.getClassLoader().getResource(HcrService.MODEL_MNIST_ZIP_NAME).getFile());
		NeuralNetwork model = new NeuralNetwork(modelFile);
		DataSetIterator numbersTrainData = LeNet.numbersTrainSetIterator();
		DataSetIterator numbersValidationData = LeNet.numbersTestSetIterator();
		int epochs = 7;
		
		model.train(numbersTrainData, numbersValidationData, epochs);
		model.store(new File(USER_COMBINED_MODEL));
	}

	@Test
	public void evaluateCombinedModel() {
		NeuralNetwork model = new NeuralNetwork();
		DataSetIterator numbersTestData = new NumbersDatasetIterator(LeNet.BATCH_SIZE, false);
		
		model.load(new File(USER_COMBINED_MODEL));
		model.evaluate(numbersTestData);
	}
}
