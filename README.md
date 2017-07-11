# Anagnostes
"ἀναγνώστης a reader, one who reads aloud (Lat. lector): noster." -- [Charlton T. Lewis, An Elementary Latin Dictionary](http://www.perseus.tufts.edu/hopper/text?doc=Perseus:text:1999.04.0060:entry=anagnostes)

## Introduction
Anagnostes is an [Eclipse Scout](http://www.eclipse.org/scout/) application that uses a convolutional neural network for human character recognition. As an example use case, the application analyzes handwritten numbers on a Swiss payment slip. 

The neural network was implemented using the [Deeplearning4j](https://deeplearning4j.org/) framework and parametrized like the [LeNet MNIST example](https://github.com/deeplearning4j/dl4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/convolution/LenetMnistExample.java) of Deeplearning4j. Since the MNIST data set contains mostly digits in a North American writing style, we created our [own training and test set](https://github.com/kensanata/numbers) to better suite the digits used on a Swiss payment slip.

## GUI

The user interface of anagnostes is implemented using [Eclipse Scout](http://www.eclipse.org/scout/). The GUI displays a Swiss payment slip with 10 handwritten digits. For each digit the MNIST-transformed image is shown together with the recognized digit and the confidence with which it was identified.

In the console, the complete output of the neural network is printed. The output vector of the network contains the likelihood for each possible digit. The sum of all values in the output vector is equal to one.

![Screenshot](/doc/screenshot_02.png)
***Screenshot of Anagnostes:***
*The fourth digit was recognized as 6 with a low confidence (the output of the neural network for this digit was: 0: 0.392, 1: 0.000, 2: 0.000, 3: 0.000, 4: 0.004, 5: 0.000, 6: 0.602, 7: 0.000, 8: 0.001, 9: 0.000)*

## Image Processing

Before the images are fed to the neural network we transform the images into the same format as used in the [MNIST database of handwritten digits](http://yann.lecun.com/exdb/mnist/). 
The following steps are performed:

1. apply [Otsu's method](https://en.wikipedia.org/wiki/Otsu%27s_method) to threshold the image
2. scale to fit into a 20x20 pixel box while preserving the aspect ratio; this gives us a grayscale image because of anti-aliasing
3. center image using center of gravity in a 28x28 pixel box

![Scanned image](/doc/number-470.png) ![Normalized image](/doc/number-470.normalized.png)

***Image Normalization:***
*The original scanned image (left) and the preprocessed image as described above (right)*

## Neural Network

For our example, we used the same structure of the neural network as the one described in [Gradient-Based Learning Applied to Document Recognition](http://yann.lecun.com/exdb/publis/pdf/lecun-01a.pdf). This structure was already modeled in the  [LeNet MNIST example](https://github.com/deeplearning4j/dl4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/convolution/LenetMnistExample.java) of the [Deeplearning4j](https://deeplearning4j.org/) Project. The configuration of the network looks like the following code snipped:

```java
new NeuralNetConfiguration.Builder()
   .seed(SEED)
   .iterations(NUM_ITERATIONS)
   .regularization(true)
   .l2(0.0005)
   .learningRate(.01)
   .weightInit(WeightInit.XAVIER)
   .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
   .updater(Updater.NESTEROVS)
   .momentum(0.9).list()
      .layer(0, new ConvolutionLayer.Builder(5, 5)
         .nIn(NUM_CHANNELS).stride(1, 1)
         .nOut(20).activation(Activation.IDENTITY).build())
      .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
         .kernelSize(2, 2).stride(2, 2).build())
      .layer(2, new ConvolutionLayer.Builder(5, 5).stride(1, 1)
         .nOut(50).activation(Activation.IDENTITY).build())
      .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
         .kernelSize(2, 2).stride(2, 2).build())
      .layer(4, new DenseLayer.Builder()
         .activation(Activation.RELU).nOut(500).build())
      .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
         .nOut(NUM_OUTPUTS).activation(Activation.SOFTMAX).build())
   .setInputType(InputType.convolutionalFlat(28, 28, 1))
   .backprop(true)
   .pretrain(false).build();
```

## Test Data

To collect the [test data](https://github.com/kensanata/numbers), we asked 21 people – mostly Swiss residents – to hand copy a page of 600 machine printed digits. 

The hand written pages where then scanned and cut into one image per digit. These images are stored in a 
folder structure so that information about the digit and the author (age rounded to the nearest decade, country of origin, gender) are available.

The images of 19 people were used to train the neural network and the images of two people were used as test set.

The [test data project](https://github.com/kensanata/numbers) is open for everyone to consume and contribute. Ideally we can substantially increase the sitze of the database with data from writers across Europe and other parts of the world. Feel free to join!

## Clone the Project and Import it into the Eclipse IDE

1. Cloning the repository into a folder of your choice
2. Open the Eclipse IDE with an empty workspace folder
3. Use menu 'File', 'Import'. In the import wizard select 'Existing Maven Project'
4. Select the folder of step 1 as your root folder

This will import a number of Maven modules into your workspace. To launch the demo application you need two additional modules that are not automatically imported:

1. In the Package Explorer expand module anagnostes-root
2. Click on sub-folder anagnostes.server.app.dev and select the context menu 'Import ...'
3. Select option 'Existing Maven Project'. You should now have an additional Maven module 'anagnostes.server.app.dev' in your Package explorer
4. Repeat the above process for sub-folder anagnostes.ui.html.app.dev

To launch the demo application first run product launcher '[webapp] dev server.launch' from Maven module'anagnostes.server.app.dev'. Then launch '[webapp] dev ui.launch' from Maven module 'anagnostes.ui.html.app.dev'.

You are now ready to use the application in your browser using link http://localhost:8082/

## Links
* [DZone Blog Post](https://dzone.com/articles/machine-learning-with-deeplearning4j-and-eclipse-s)
* [Scout Blog Post](https://www.bsi-software.com/en/scout-blog)
* [Eclipse Scout](http://www.eclipse.org/scout/)
* [Deeplearning4j](https://deeplearning4j.org/)
* [Numbers](https://github.com/kensanata/numbers)
