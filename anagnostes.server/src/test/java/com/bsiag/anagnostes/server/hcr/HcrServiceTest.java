package com.bsiag.anagnostes.server.hcr;

import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_0;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_1;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_2;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_3;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_4;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_5;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_6;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_7;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_8;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_A_9;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_0;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_1;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_2;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_3;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_4;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_5;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_6;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_7;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_8;
import static com.bsiag.anagnostes.server.hcr.TestConstants.IMG_B_9;
import static com.bsiag.anagnostes.server.hcr.TestConstants.MODEL_MNIST_ZIP_NAME;
import static com.bsiag.anagnostes.server.hcr.TestConstants.MODEL_NUMBERS_ZIP_NAME;
import static com.bsiag.anagnostes.server.hcr.TestConstants.USER_HOME;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bsiag.anagnostes.server.ServerSession;
import com.bsiag.anagnostes.server.neuralnetwork.LeNetMnistConfigurationFactory;
import com.bsiag.anagnostes.server.neuralnetwork.NeuralNetwork;
import com.bsiag.anagnostes.server.neuralnetwork.NumbersDatasetIterator;

/**
 * <h3>{@link HcrServiceTest}</h3>
 *
 * @author cbu
 */
@RunWith(ServerTestRunner.class)
@RunWithSubject(HcrServiceTest.SUBJECT_NAME)
@RunWithServerSession(ServerSession.class)
public class HcrServiceTest {
	public static final String SUBJECT_NAME = "test_subject";

	@Test
	public void loadFromNumbersFile() {
		BEANS.get(HcrService.class).loadModel(MODEL_NUMBERS_ZIP_NAME);
		NeuralNetwork model = BEANS.get(HcrService.class).getNeuralNetwork();

		assertNotNull(model);

		printTestSetAOutput(model);
		printTestSetBOutput(model);
	}

	@Test
	public void loadFromMnistFile() {
		BEANS.get(HcrService.class).loadModel(MODEL_MNIST_ZIP_NAME);
		NeuralNetwork model = BEANS.get(HcrService.class).getNeuralNetwork();

		assertNotNull(model);

		printTestSetAOutput(model);
		printTestSetBOutput(model);
	}

	@Test
	public void evaluateNumbersModel() {
		BEANS.get(HcrService.class).loadModel(MODEL_NUMBERS_ZIP_NAME);
		NeuralNetwork model = BEANS.get(HcrService.class).getNeuralNetwork();
		model.setTestSetIterator(new NumbersDatasetIterator(LeNetMnistConfigurationFactory.BATCH_SIZE, false));
		model.evaluate();
	}

	@Test
	@Ignore
	public void buildAndTrainNumbers() {
		NeuralNetwork model = new NeuralNetwork.Builder().leNetConfiguration().numbersTrainSetIterator()
				.numbersTestSetIterator().epochs(6).build();

		model.train();
		model.store(new File(USER_HOME + File.separator + MODEL_NUMBERS_ZIP_NAME));
	}

	@Test
	@Ignore
	public void buildAndTrainMnist() {
		NeuralNetwork model = new NeuralNetwork.Builder()
				.leNetConfiguration()
				.mnistTrainSetIterator()
				.mnistTestSetIterator()
				.epochs(15)
				.build();

		model.train();
		model.store(new File(USER_HOME + File.separator + MODEL_MNIST_ZIP_NAME));
	}

	public static void main(String[] args) {
		new HcrServiceTest().evaluateNumbersModel();
	}

	private void printTestSetAOutput(NeuralNetwork model) {
		System.out.println("\nArbitrary test set examples (Person A): ");

		System.out.println(model.output(IMG_A_0));
		System.out.println(model.output(IMG_A_1));
		System.out.println(model.output(IMG_A_2));
		System.out.println(model.output(IMG_A_3));
		System.out.println(model.output(IMG_A_4));
		System.out.println(model.output(IMG_A_5));
		System.out.println(model.output(IMG_A_6));
		System.out.println(model.output(IMG_A_7));
		System.out.println(model.output(IMG_A_8));
		System.out.println(model.output(IMG_A_9));
	}

	private void printTestSetBOutput(NeuralNetwork model) {
		System.out.println("\nArbitrary test set examples (Person B): ");

		System.out.println(model.output(IMG_B_0));
		System.out.println(model.output(IMG_B_1));
		System.out.println(model.output(IMG_B_2));
		System.out.println(model.output(IMG_B_3));
		System.out.println(model.output(IMG_B_4));
		System.out.println(model.output(IMG_B_5));
		System.out.println(model.output(IMG_B_6));
		System.out.println(model.output(IMG_B_7));
		System.out.println(model.output(IMG_B_8));
		System.out.println(model.output(IMG_B_9));
	}

}
