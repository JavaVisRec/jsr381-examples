package jsr381.example.util;

import deepnetts.data.TabularDataSet;
import deepnetts.util.DeepNettsException;

import javax.visrec.ml.data.BasicDataSet;
import javax.visrec.ml.data.DataSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Kevin Berendsen
 */
public class DataSetExamples {

    public static BasicDataSet getSonarDataSet() throws IOException {
        return DataSetExamples.fromURL(new URL("https://raw.githubusercontent.com/JavaVisRec/jsr381-examples-datasets/master/sonar.csv"),
                ",", 60, 1, false);
    }

    public static DataSet getIrisClassificationDataSet() throws IOException {
        return DataSetExamples.fromURL(new URL("https://raw.githubusercontent.com/JavaVisRec/jsr381-examples-datasets/master/iris_data_normalised.txt"),
                ",", 4, 3, true);
    }

    public static BasicDataSet getSwedishAutoInsuranceDataSet() throws IOException {
        return DataSetExamples.fromURL(new URL("https://raw.githubusercontent.com/JavaVisRec/jsr381-examples-datasets/master/SwedenAutoInsurance.csv"),
                ",", 1, 1, false);
    }

    private static BasicDataSet fromURL(URL url, String delimiter, int inputsNum, int outputsNum, boolean hasColumnNames) throws IOException {
        TabularDataSet dataSet = new TabularDataSet(inputsNum, outputsNum);

        URLConnection conn = url.openConnection();
        String[] content;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            content = reader.lines().toArray(String[]::new);
        }
        if (content == null) {
            throw new NullPointerException("content == null");
        } else if (content.length <= 1 && hasColumnNames) {
            throw new IllegalArgumentException("content has one line of columns");
        } else if (content.length == 0) {
            throw new IllegalArgumentException("content has no lines");
        }

        int skipCount = 0;
        if (hasColumnNames) {    // get col names from the first line
            String[] colNames = content[0].split(delimiter);
            dataSet.setColumnNames(colNames);
            skipCount = 1;
        } else {
            String[] colNames = new String[inputsNum + outputsNum];
            for (int i = 0; i < inputsNum; i++)
                colNames[i] = "in" + (i + 1);

            for (int j = 0; j < outputsNum; j++)
                colNames[inputsNum + j] = "out" + (j + 1);

            dataSet.setColumnNames(colNames);
        }


        Arrays.stream(content)
                .skip(skipCount)
                .filter(l -> !l.isEmpty())
                .map(l -> toBasicDataSetItem(l, delimiter, inputsNum, outputsNum))
                .forEach(dataSet::add);
        return dataSet;
    }

    private static File downloadSingleFile(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        String[] content;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            content = reader.lines().toArray(String[]::new);
        }

        File architectureFile = Paths.get(System.getProperty("java.io.tmpdir"), "visrec-datasets", "mnist", "architecture.json").toFile();
        try (FileOutputStream outputStream = new FileOutputStream(architectureFile)) {
            for (String line : content) {
                outputStream.write((line + System.lineSeparator()).getBytes());
            }
        }
        return architectureFile;
    }

    @Deprecated
    public static File getMnistTestingSet() throws IOException {
        Path folderPath = Paths.get("datasets", "mnist", "testing");
        Path zipPath = Paths.get(folderPath.toString(), "dataset.zip");
        File folder = folderPath.toFile();

        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("Couldn't create temporary directories to download the Mnist testing dataset.");
            }
        }

        downloadZip("https://github.com/JavaVisRec/jsr381-examples-datasets/raw/master/mnist_testing_data_png.zip", zipPath);

        return folder;
    }

    /**
     * Download and unzip MNIST dataset
     *
     * @return path to downloaded data set
     * @throws IOException
     */
    public static MnistDataSet getMnistDataSet() throws IOException { // print out message since it can take a while
        Path mnistPath = Paths.get("datasets", "mnist");
        File mnistFolder = mnistPath.toFile();
        System.out.println(String.format("Downloading and/or unpacking MNIST training set to: %s - this may take a while!", mnistFolder.getAbsolutePath()));
        if (!mnistFolder.exists()) {
            if (!mnistFolder.mkdirs()) {
                throw new IOException("Couldn't create temporary directories to download the Mnist training dataset.");
            }
        }

        // check if mnist zip  already exists  - don't download it again if its there
        Path zipPath = Paths.get(mnistPath.toString(), "dataset.zip");
        downloadZip("https://github.com/JavaVisRec/jsr381-examples-datasets/raw/master/mnist_training_data_png.zip", zipPath);

        File trainingIndexFile = new File(Paths.get(mnistFolder.getAbsolutePath(), "training").toFile(), "train.txt");
        if (!trainingIndexFile.exists())
            throw new FileNotFoundException(trainingIndexFile + " not properly downloaded");

        File trainingLabelsFile = new File(Paths.get(mnistFolder.getAbsolutePath(), "training").toFile(), "labels.txt");
        if (!trainingLabelsFile.exists())
            throw new FileNotFoundException(trainingLabelsFile + " not properly downloaded");

        URL archUrl = DataSetExamples.class.getClassLoader().getResource("mnist1.json");
        if (archUrl == null)
            throw new FileNotFoundException("Architecture file not found");
        File architectureFile = new File(archUrl.getFile());
        if (!architectureFile.exists())
            throw new FileNotFoundException(architectureFile + " does not exist");

        return new MnistDataSet()
            //    .setNetworkArchitectureFile(architectureFile) // we dont need architecture in data set
                .setLabelsFile(trainingLabelsFile)
                .setTrainingFile(trainingIndexFile);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getMnistDataSet());
    }

    public static class MnistDataSet {
        private File labelsFile;
        private File trainingFile;
        //private File networkArchitectureFile;

        private MnistDataSet() {
        }

        private MnistDataSet setLabelsFile(File labelsFile) {
            this.labelsFile = labelsFile;
            return this;
        }

        private MnistDataSet setTrainingFile(File trainingFile) {
            this.trainingFile = trainingFile;
            return this;
        }

//        private MnistDataSet setNetworkArchitectureFile(File networkArchitectureFile) {
//            this.networkArchitectureFile = networkArchitectureFile;
//            return this;
//        }

        public File getLabelsFile() {
            return labelsFile;
        }

        public File getTrainingFile() {
            return trainingFile;
        }

//        public File getNetworkArchitectureFile() {
//            return networkArchitectureFile;
//        }

        @Override
        public String toString() {
            return "MnistDataSet{" +
                    "labelsFile=" + labelsFile +
                    ", trainingFile=" + trainingFile +
                    '}';
        }
    }


    private static TabularDataSet.Item toBasicDataSetItem(String line, String delimiter, int inputsNum, int outputsNum) {
        String[] values = line.split(delimiter);
        if (values.length != (inputsNum + outputsNum)) {
            throw new DeepNettsException("Wrong number of values found " + values.length + " expected " + (inputsNum + outputsNum));
        }
        float[] in = new float[inputsNum];
        float[] out = new float[outputsNum];

        try {
            // these methods could be extracted into parse float vectors
            for (int i = 0; i < inputsNum; i++) { //parse inputs
                in[i] = Float.parseFloat(values[i]);
            }

            for (int j = 0; j < outputsNum; j++) { // parse outputs
                out[j] = Float.parseFloat(values[inputsNum + j]);
            }
        } catch (NumberFormatException nex) {
            throw new DeepNettsException("Error parsing csv, number expected: " + nex.getMessage(), nex);
        }

        return new TabularDataSet.Item(in, out);
    }

    /**
     * Downloads and unzips file from specified url.
     *
     * @param httpsURL
     * @param path
     */
    private static void downloadZip(String httpsURL, Path path) {
        URL url = null;
        try {
            File toFile = path.toFile();
            url = new URL(httpsURL);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(toFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            unzip(toFile);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private static void unzip(File fileToBeUnzipped) {
        File dir = new File(fileToBeUnzipped.getParent());

        if (!dir.exists())
            dir.mkdirs();

        try {
            ZipFile zipFile = new ZipFile(fileToBeUnzipped.getAbsoluteFile());
            Enumeration<?> enu = zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enu.nextElement();
                String name = zipEntry.getName();

                if (name.contains(".DS_Store") || name.contains("__MACOSX"))
                    continue;

                File file = Paths.get(fileToBeUnzipped.getParent(), name).toFile();
                if (name.endsWith("/")) {
                    file.mkdirs();
                    continue;
                }
                if (file.exists()) {
                    continue;
                }

                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }

                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = is.read(bytes)) >= 0) {
                    fos.write(bytes, 0, length);
                }
                is.close();
                fos.close();
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static MnistDataSet getCatDogDataSet() throws IOException {
		Path mnistPath = Paths.get("datasets", "cats_and_dogs");
		File mnistFolder = mnistPath.toFile();
		System.out.println(
				String.format("Downloading and/or unpacking cat_and_dog training set to: %s - this may take a while!",
						mnistFolder.getAbsolutePath()));
		if (!mnistFolder.exists()) {
			if (!mnistFolder.mkdirs()) {
				throw new IOException(
						"Couldn't create temporary directories to download the cat_and_dog training dataset.");
			}
		}

		// check if cat_and_dog zip already exists - don't download it again if its
		// there
		Path zipPath = Paths.get(mnistPath.toString(), "dataset.zip");
		downloadZip(
				"https://github.com/JavaVisRec/jsr381-examples-datasets/raw/master/cats_and_dogs_training_data_png.zip",
				zipPath);

		File trainingIndexFile = new File(Paths.get(mnistFolder.getAbsolutePath(), "training").toFile(), "train.txt");
		if (!trainingIndexFile.exists())
			throw new FileNotFoundException(trainingIndexFile + " not properly downloaded");

		File trainingLabelsFile = new File(Paths.get(mnistFolder.getAbsolutePath(), "training").toFile(), "labels.txt");
		if (!trainingLabelsFile.exists())
			throw new FileNotFoundException(trainingLabelsFile + " not properly downloaded");

//		URL archUrl = DataSetExamples.class.getClassLoader().getResource("catdog_arch.json");
//		if (archUrl == null)
//			throw new FileNotFoundException("Architecture file not found");
//		File architectureFile = new File(archUrl.getFile());
//		if (!architectureFile.exists())
//			throw new FileNotFoundException(architectureFile + " does not exist");

		return new MnistDataSet()
				.setLabelsFile(trainingLabelsFile).setTrainingFile(trainingIndexFile);
	}
}
