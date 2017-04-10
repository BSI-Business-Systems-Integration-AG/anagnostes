package com.bsiag.anagnostes.server.hcr;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.eclipse.scout.rt.platform.ApplicationScoped;

import com.bsiag.anagnostes.server.neuralnetwork.NeuralNetwork;
import com.bsiag.anagnostes.server.neuralnetwork.util.ImageUtility;
import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility;
import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility.NumberFileEntry;

@ApplicationScoped
public class NeuralNetworkBean {

	static final String MODEL_NUMBERS_ZIP_NAME = "model_numbers.zip";
	static final File MODEL_NUMBERS_FILE= new File(HcrService.class.getClassLoader().getResource(MODEL_NUMBERS_ZIP_NAME).getFile());
	
	private NeuralNetwork m_neuralNetwork = new NeuralNetwork
			.Builder()
			.fromFile(MODEL_NUMBERS_FILE);

	/*
	 * Load deeplearning4j libraries on application start to prevent class loader issues while loading the deeplearning4j dlls
	 */
	static {
		NeuralNetwork neuralNetwork = new NeuralNetwork
				.Builder()
				.fromFile(NeuralNetworkBean.MODEL_NUMBERS_FILE);
		
		neuralNetwork.output(ImageUtility.readImage(getRandomNumberImages(NumbersUtility.getAllFolderNames().get(0)).getFilename()));
	}
	
	public NeuralNetwork getNeuralNetwork() {
		return m_neuralNetwork;
	}
	
	private static NumberFileEntry getRandomNumberImages(String testPerson) {
		List<NumberFileEntry> allFileNames = NumbersUtility.getAllFileNamesForFolder(testPerson);
		if (allFileNames == null || allFileNames.size() < 1) {
			return null;
		}
		return allFileNames.get(new Random().nextInt(allFileNames.size()));
	}
}
