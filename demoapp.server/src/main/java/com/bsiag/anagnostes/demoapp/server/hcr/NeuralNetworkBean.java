package com.bsiag.anagnostes.demoapp.server.hcr;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.eclipse.scout.rt.platform.ApplicationScoped;

import com.bsiag.anagnostes.demoapp.server.nn.NeuralNetwork;
import com.bsiag.anagnostes.demoapp.server.nn.util.ImageUtility;
import com.bsiag.anagnostes.demoapp.server.nn.util.NumbersUtility;
import com.bsiag.anagnostes.demoapp.server.nn.util.NumbersUtility.NumberFileEntry;
import com.bsiag.anagnostes.demoapp.shared.hcr.Output;

@ApplicationScoped
public class NeuralNetworkBean {

	static final String MODEL_NUMBERS_ZIP_NAME = "model_numbers.zip";
	static final File MODEL_NUMBERS_FILE= new File(HcrService.class.getClassLoader().getResource(MODEL_NUMBERS_ZIP_NAME).getFile());
	
	private NeuralNetwork m_neuralNetwork = new NeuralNetwork
			.Builder()
			.fromFile(MODEL_NUMBERS_FILE);

	static {
		NeuralNetwork neuralNetwork = new NeuralNetwork
				.Builder()
				.fromFile(NeuralNetworkBean.MODEL_NUMBERS_FILE);
		
		Output output = neuralNetwork.output(ImageUtility.readImage(getRandomNumberImageS(NumbersUtility.getAllFolderNames().get(0)).getFilename()));
		System.out.println(output);
	}
	
	public NeuralNetwork getNeuralNetwork() {
		return m_neuralNetwork;
	}
	
	private static NumberFileEntry getRandomNumberImageS(String testPerson) {
		List<NumberFileEntry> allFileNames = NumbersUtility.getAllFileNamesForFolder(testPerson);
		if (allFileNames == null || allFileNames.size() < 1) {
			return null;
		}
		return allFileNames.get(new Random().nextInt(allFileNames.size()));
	}
}
