package com.bsiag.anagnostes.server.hcr;

import static org.junit.Assert.assertNotNull;

import org.eclipse.scout.rt.platform.BEANS;
import org.junit.Test;

import com.bsiag.anagnostes.server.ServerTest;
import com.bsiag.anagnostes.server.neuralnetwork.NeuralNetwork;
import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility;

public class HcrServiceTest extends ServerTest {
	
	static final String IMG_A_0 = NumbersUtility.IMG_PATH + "/0011_CH2F/0/number-25.png";
	static final String IMG_A_1 = NumbersUtility.IMG_PATH + "/0011_CH2F/1/number-55.png";
	static final String IMG_A_2 = NumbersUtility.IMG_PATH + "/0011_CH2F/2/number-5.png";
	static final String IMG_A_3 = NumbersUtility.IMG_PATH + "/0011_CH2F/3/number-475.png";
	static final String IMG_A_4 = NumbersUtility.IMG_PATH + "/0011_CH2F/4/number-133.png";
	static final String IMG_A_5 = NumbersUtility.IMG_PATH + "/0011_CH2F/5/number-8.png";
	static final String IMG_A_6 = NumbersUtility.IMG_PATH + "/0011_CH2F/6/number-28.png";
	static final String IMG_A_7 = NumbersUtility.IMG_PATH + "/0011_CH2F/7/number-20.png";
	static final String IMG_A_8 = NumbersUtility.IMG_PATH + "/0011_CH2F/8/number-22.png";
	static final String IMG_A_9 = NumbersUtility.IMG_PATH + "/0011_CH2F/9/number-6.png";

	static final String IMG_B_0 = NumbersUtility.IMG_PATH + "/0021_CH4M/0/number-25.png";
	static final String IMG_B_1 = NumbersUtility.IMG_PATH + "/0021_CH4M/1/number-55.png";
	static final String IMG_B_2 = NumbersUtility.IMG_PATH + "/0021_CH4M/2/number-5.png";
	static final String IMG_B_3 = NumbersUtility.IMG_PATH + "/0021_CH4M/3/number-475.png";
	static final String IMG_B_4 = NumbersUtility.IMG_PATH + "/0021_CH4M/4/number-355.png";
	static final String IMG_B_5 = NumbersUtility.IMG_PATH + "/0021_CH4M/5/number-8.png";
	static final String IMG_B_6 = NumbersUtility.IMG_PATH + "/0021_CH4M/6/number-28.png";
	static final String IMG_B_7 = NumbersUtility.IMG_PATH + "/0021_CH4M/7/number-20.png";
	static final String IMG_B_8 = NumbersUtility.IMG_PATH + "/0021_CH4M/8/number-22.png";
	static final String IMG_B_9 = NumbersUtility.IMG_PATH + "/0021_CH4M/9/number-2.png";
	
	static final String SUBJECT_NAME = "test_subject";

	@Test
	public void loadFromNumbersFile() {
		BEANS.get(HcrService.class).loadModel(HcrService.MODEL_NUMBERS_ZIP_NAME);
		NeuralNetwork model = BEANS.get(HcrService.class).getNeuralNetwork();

		assertNotNull(model);
		printTestSetAOutput(model);
		printTestSetBOutput(model);
	}

	private void printTestSetAOutput(NeuralNetwork model) {
		System.out.println("\nArbitrary test set examples (Person A): ");

		System.out.println(model.recognize(IMG_A_0));
		System.out.println(model.recognize(IMG_A_1));
		System.out.println(model.recognize(IMG_A_2));
		System.out.println(model.recognize(IMG_A_3));
		System.out.println(model.recognize(IMG_A_4));
		System.out.println(model.recognize(IMG_A_5));
		System.out.println(model.recognize(IMG_A_6));
		System.out.println(model.recognize(IMG_A_7));
		System.out.println(model.recognize(IMG_A_8));
		System.out.println(model.recognize(IMG_A_9));
	}

	private void printTestSetBOutput(NeuralNetwork model) {
		System.out.println("\nArbitrary test set examples (Person B): ");

		System.out.println(model.recognize(IMG_B_0));
		System.out.println(model.recognize(IMG_B_1));
		System.out.println(model.recognize(IMG_B_2));
		System.out.println(model.recognize(IMG_B_3));
		System.out.println(model.recognize(IMG_B_4));
		System.out.println(model.recognize(IMG_B_5));
		System.out.println(model.recognize(IMG_B_6));
		System.out.println(model.recognize(IMG_B_7));
		System.out.println(model.recognize(IMG_B_8));
		System.out.println(model.recognize(IMG_B_9));
	}

}
