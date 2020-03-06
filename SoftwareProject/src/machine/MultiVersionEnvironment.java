package machine;

import exceptions.VersionFailureException;

import java.util.ArrayList;

public class MultiVersionEnvironment {
   private ArrayList<Version> versions ;
   private static MultiVersionEnvironment environment;

   public void initialze(){

       String path1="versions/";
       String executable1="JavaVersion";
       String program1="java";
       Version version1=new Version("Java Version 1", program1,path1,executable1);

       String path2="versions/";
       String executable2="KotlinVersion.jar";
       String program2="java";
       Version version2=new Version("Kotlin Version", program2,path2,executable2);
        version2.addOption("-jar");

       String path3="versions/";
       String executable3="JavaVersion";
       String program3="java";
       Version version3=new Version("Java Version 3", program3,path3,executable3);

       versions=new ArrayList();
       versions.add(version1);
       versions.add(version2);
      // versions.add(version3);
   }
    public double[] compute(double[] input) {
        ArrayList<double[]> acceptedOutputs = new ArrayList();

        // start
        for (Version v : versions) {
            try {
                v.setArguments(input);
                v.start();
            } catch (VersionFailureException e) {
                System.out.println("Version " + v.getName() + " Failed");
            }
        }

        // acceptance
        for (Version v : versions) {
            try {
                v.join();
                if (isAcceptable(v.getOutput()))
                    acceptedOutputs.add(v.getOutput());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // decision
        double[] finalOutput = new double[4];
        int N = acceptedOutputs.size();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < N; i++)
                finalOutput[j] += acceptedOutputs.get(i)[j];
            finalOutput[j] /= N;
        }

        return finalOutput;
    }

    public void addVersion(Version v) {
        versions.add(v);
    }

    public boolean isAcceptable(double[] output) {
        if (output != null)
            return true;
        else
            return false;
    }

    private MultiVersionEnvironment(){
        versions = new ArrayList();
    }

    public static MultiVersionEnvironment getEnvironment(){
        if(environment==null)
            environment=new MultiVersionEnvironment();
        return environment;
    }
}


