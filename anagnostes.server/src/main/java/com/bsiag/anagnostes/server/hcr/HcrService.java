package com.bsiag.anagnostes.server.hcr;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bsiag.anagnostes.server.neuralnetwork.util.ImageUtility;
import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility;
import com.bsiag.anagnostes.server.neuralnetwork.util.NumbersUtility.NumberFileEntry;
import com.bsiag.anagnostes.shared.hcr.AbstractOutputFieldData;
import com.bsiag.anagnostes.shared.hcr.HcrFormData;
import com.bsiag.anagnostes.shared.hcr.IHcrService;
import com.bsiag.anagnostes.shared.hcr.Output;

/**
 * <h3>{@link HcrService}</h3>
 *
 * @author cbu
 */
public class HcrService implements IHcrService {
	private static final Logger log = LoggerFactory.getLogger(HcrService.class);
	
	private static final int NUMBER_WIDTH = 23;
	private static final int NUMBER_HEIGHT = 23;

	private static final int NUMBER_POSITION_Y = 295;
	private static final int[] NUMBER_POSITIONS_X = { 367, 396, 426, 455, 485, 514, 543, 573, 632, 661 };
	private static final String ESR_FILE = "esr.png";
	
	@Override
	public HcrFormData load(HcrFormData formData) throws ProcessingException {
		String scan = formData.getScan().getValue();
		if (scan == null || scan.isEmpty()) {
			scan = NumbersUtility.getAllFolderNames().get(0);
			formData.getScan().setValue(scan);
		}
		log.info("Human Character Recognition for " + scan + ":");

		BufferedImage paperForm = loadPaperForm();

		List<BufferedImage> numberImages = new ArrayList<BufferedImage>();

		for (int x : NUMBER_POSITIONS_X) {
			BufferedImage randomNumber = ImageUtility.readImage(getRandomNumberImage(scan).getFilename());
			numberImages.add(randomNumber);
			drawNumber(paperForm, randomNumber, x, NUMBER_POSITION_Y);
		}

		setImages(formData, numberImages);

		formData.setEsrFormImage(ImageUtility.toPngBinaryResource("Paper form", paperForm));
		return formData;
	}

	private void setImages(HcrFormData formData, List<BufferedImage> numberImages) throws ProcessingException {
		if(numberImages.size() != 10) {
			throw new ProcessingException("need exactly 10 images!");
		}
		
		setOutput(numberImages.get(0), formData.getOutput0());
		setOutput(numberImages.get(1), formData.getOutput1());
		setOutput(numberImages.get(2), formData.getOutput2());
		setOutput(numberImages.get(3), formData.getOutput3());
		setOutput(numberImages.get(4), formData.getOutput4());
		setOutput(numberImages.get(5), formData.getOutput5());
		setOutput(numberImages.get(6), formData.getOutput6());
		setOutput(numberImages.get(7), formData.getOutput7());
		setOutput(numberImages.get(8), formData.getOutput8());
		setOutput(numberImages.get(9), formData.getOutput9());
	}
	
	private void setOutput(BufferedImage numberImage, AbstractOutputFieldData outputField) {
		Output output = BEANS.get(NeuralNetworkBean.class).getNeuralNetwork().output(numberImage);
		outputField.getOutputValue().setValue("" + output.getCharacter());
		
		outputField.getConfidence().setValue(new DecimalFormat("0.000").format(output.getConfidence()));
		
		BufferedImage mnistImage = ImageUtility.transformToMnistFormat(numberImage);
		BufferedImage resizedImage = ImageUtility.resize(mnistImage, 84, 84);
		outputField.setImage(ImageUtility.toPngBinaryResource("Number", resizedImage));
		
		log.info(output.toString());
	}
	
	private BufferedImage loadPaperForm() {
		return ImageUtility.readImage(HcrService.class.getClassLoader().getResource(ESR_FILE).getFile());
	}

	private NumberFileEntry getRandomNumberImage(String testPerson) {
		List<NumberFileEntry> allFileNames = NumbersUtility.getAllFileNamesForFolder(testPerson);
		if (allFileNames == null || allFileNames.size() < 1) {
			return null;
		}
		return allFileNames.get(new Random().nextInt(allFileNames.size()));
	}

	private void drawNumber(BufferedImage paperForm, BufferedImage number, int x, int y) {
		paperForm.createGraphics().drawImage(number.getScaledInstance(NUMBER_WIDTH, NUMBER_HEIGHT, Image.SCALE_SMOOTH),
				x, y, null);
	}
}
