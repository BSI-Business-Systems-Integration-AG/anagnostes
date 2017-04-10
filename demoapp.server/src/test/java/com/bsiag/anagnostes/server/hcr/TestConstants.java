package com.bsiag.anagnostes.server.hcr;

import java.io.File;

public class TestConstants {
	static final String USER_HOME = System.getProperty("user.home");
	static final String SEPARATOR = File.separator;

	static final String MODEL_NUMBERS_ZIP_NAME = "model_numbers.zip";
	static final File MODEL_NUMBERS_FILE = new File(
			TestConstants.class.getClassLoader().getResource(MODEL_NUMBERS_ZIP_NAME).getFile());

	static final String MODEL_MNIST_ZIP_NAME = "model_mnist.zip";
	static final File MODEL_MNIST_FILE = new File(
			TestConstants.class.getClassLoader().getResource(MODEL_MNIST_ZIP_NAME).getFile());

	static final String NUMBERS_FOLDER_NAME = "numbers";
	static final String IMG_PATH = TestConstants.class.getClassLoader().getResource(NUMBERS_FOLDER_NAME).getFile()
			+ SEPARATOR;

	static final String IMG_A_0 = IMG_PATH + "0011_CH2F/0/number-25.png";
	static final String IMG_A_1 = IMG_PATH + "0011_CH2F/1/number-55.png";
	static final String IMG_A_2 = IMG_PATH + "0011_CH2F/2/number-5.png";
	static final String IMG_A_3 = IMG_PATH + "0011_CH2F/3/number-475.png";
	static final String IMG_A_4 = IMG_PATH + "0011_CH2F/4/number-133.png";
	static final String IMG_A_5 = IMG_PATH + "0011_CH2F/5/number-8.png";
	static final String IMG_A_6 = IMG_PATH + "0011_CH2F/6/number-28.png";
	static final String IMG_A_7 = IMG_PATH + "0011_CH2F/7/number-20.png";
	static final String IMG_A_8 = IMG_PATH + "0011_CH2F/8/number-22.png";
	static final String IMG_A_9 = IMG_PATH + "0011_CH2F/9/number-6.png";

	static final String IMG_B_0 = IMG_PATH + "0021_CH4M/0/number-25.png";
	static final String IMG_B_1 = IMG_PATH + "0021_CH4M/1/number-55.png";
	static final String IMG_B_2 = IMG_PATH + "0021_CH4M/2/number-5.png";
	static final String IMG_B_3 = IMG_PATH + "0021_CH4M/3/number-475.png";
	static final String IMG_B_4 = IMG_PATH + "0021_CH4M/4/number-355.png";
	static final String IMG_B_5 = IMG_PATH + "0021_CH4M/5/number-8.png";
	static final String IMG_B_6 = IMG_PATH + "0021_CH4M/6/number-28.png";
	static final String IMG_B_7 = IMG_PATH + "0021_CH4M/7/number-20.png";
	static final String IMG_B_8 = IMG_PATH + "0021_CH4M/8/number-22.png";
	static final String IMG_B_9 = IMG_PATH + "0021_CH4M/9/number-2.png";
}
