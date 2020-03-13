package machine;

import java.util.ArrayList;

/*
 * The environment that runs and manages the different versions
 * */
public class MultiVersionEnvironment {

    private ArrayList<Version> versions;
    private static MultiVersionEnvironment environment;

    /*
     * we can define as many versions as we want
     * path : the path containing the executable
     * executable: the executable containing the common code
     * program : the program that will run the executable
     * */
    public void initialze() {

        String path1 = "versions";
        String executable1 = "JavaVersion";
        String program1 = "java";
        Version version1 = new Version("Java Version ", program1, path1, executable1);

        String path2 = "versions";
        String executable2 = "KotlinVersion.jar";
        String program2 = "java";
        Version version2 = new Version("Kotlin Version", program2, path2, executable2);
        version2.addOption("-jar");

        String path3 = "versions";
        String executable3 = "PhpVersion.php";
        String program3 = "php";
        Version version3 = new Version("Php Version ", program3, path3, executable3);

        String path4 = "versions";
        String executable4 = "CSharpVersion.exe";
        String program4 = "mono";
        Version version4 = new Version("C# Version ", program4, path4, executable4);

        versions = new ArrayList();
        versions.add(version1);
        versions.add(version2);
        versions.add(version3);
        versions.add(version4);
    }

    public double[] compute(double[] input) {
        ArrayList<double[]> acceptedOutputs = new ArrayList();

        // run the different versions
        for (Version v : versions) {
            try {
                v.setArguments(input);
                v.start();
            } catch (Exception e) {
                System.out.println("Version " + v.getName() + " Failed");
            }
        }

        // check for acceptance of the outputs
        for (Version v : versions) {
            try {
                v.join();
                if (isAcceptable(v.getOutput()))
                    acceptedOutputs.add(v.getOutput());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // make decision according to the outputs
        double[] finalOutput = new double[4];
        int N = acceptedOutputs.size();
        if(N <= 0)
            System.out.println("All versions failed");
       else
         for (int j = 0; j < 4; j++) {
            for (int i = 0; i < N; i++)
                finalOutput[j] += acceptedOutputs.get(i)[j];
            finalOutput[j] /= N;
        }

        return finalOutput;
    }

    public boolean isAcceptable(double[] output) {
        if (output == null)
            return false;
        for (double o : output)
            if (o > Machine.totalMagnitude)
                return false;
        return true;
    }

    public void addVersion(Version v) {
        versions.add(v);
    }

    private MultiVersionEnvironment() {
        versions = new ArrayList();
    }

    public static MultiVersionEnvironment getEnvironment() {
        if (environment == null)
            environment = new MultiVersionEnvironment();
        return environment;
    }


}


