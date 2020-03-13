package machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Version extends Thread {

    private String program;
    private String executable;
    private String path;
    private double[] arguments;
    private double[] output;
    private ArrayList<String> options;

    public Version(String name, String program, String path, String executable) {
        this.setName(name);
        this.program = program;
        this.path = path;
        this.executable = executable;
        options = new ArrayList();
    }

    @Override
    public void run() {

       try {
        String s;
        List<String> commands = new ArrayList();
        commands.add(program);
        for (String option : options)
            commands.add(option);
        commands.add(executable);
        commands.add("");
        for (double arg : arguments)
            commands.add("" + arg);
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File(path));
        Process process = null;

        
            process = pb.start();
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));
        int i = 0;
        output = new double[4];

            while (i < 4 && (s = stdInput.readLine()) != null) {
                if (!s.isEmpty()) {
                    output[i] = Double.parseDouble(s);
                    i++;
                }

            }
        }catch (Exception e){
            System.out.println("Version "+getName()+" failed. ");
        }
    }

    public void setArguments(double[] arguments) {
        this.arguments = arguments;
    }

    public double[] getOutput() {
        return output;
    }

    public void addOption(String op) {
        options.add(op);
    }

}
