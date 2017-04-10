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
import static com.bsiag.anagnostes.server.hcr.TestConstants.MODEL_MNIST_FILE;
import static com.bsiag.anagnostes.server.hcr.TestConstants.MODEL_MNIST_ZIP_NAME;
import static com.bsiag.anagnostes.server.hcr.TestConstants.MODEL_NUMBERS_FILE;
import static com.bsiag.anagnostes.server.hcr.TestConstants.MODEL_NUMBERS_ZIP_NAME;
import static com.bsiag.anagnostes.server.hcr.TestConstants.SEPARATOR;
import static com.bsiag.anagnostes.server.hcr.TestConstants.USER_HOME;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bsiag.anagnostes.server.ServerSession;
import com.bsiag.anagnostes.server.neuralnetwork.NeuralNetwork;

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
		NeuralNetwork model = new NeuralNetwork.Builder().fromFile(MODEL_NUMBERS_FILE);

		assertNotNull(model);

		printTestSetAOutput(model);
		printTestSetBOutput(model);
	}

	@Test
	public void loadFromMnistFile() {
		NeuralNetwork model = new NeuralNetwork.Builder().fromFile(MODEL_MNIST_FILE);

		assertNotNull(model);

		printTestSetAOutput(model);
		printTestSetBOutput(model);
	}

	@Test
	public void buildAndTrainNumbers() {
		NeuralNetwork model = new NeuralNetwork.Builder().leNetConfiguration().numbersTrainSetIterator()
				.numbersTestSetIterator().epochs(6).build();

		model.train();
		model.store(new File(USER_HOME + SEPARATOR + MODEL_NUMBERS_ZIP_NAME));
	}

	@Test
	public void buildAndTrainMnist() {
		NeuralNetwork model = new NeuralNetwork.Builder().leNetConfiguration().mnistTrainSetIterator()
				.mnistTestSetIterator().epochs(15).build();

		model.train();
		model.store(new File(USER_HOME + SEPARATOR + MODEL_MNIST_ZIP_NAME));
	}

	public static void main(String[] args) {
		NeuralNetwork model = new NeuralNetwork.Builder().numbersTestSetIterator().fromFile(MODEL_NUMBERS_FILE);

		model.evaluate();
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
