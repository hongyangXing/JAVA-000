import java.io.*;

public class GCLogExec {
    public static void main(String[] args) {
        String[] xmArray = {"512m", "1g", "2g", "4g"};
        String[] gcArray = {"SerialGC", "ParallelGC", "ConcMarkSweepGC", "G1GC"};
        for (String gc : gcArray) {
            for (String xm : xmArray) {
                try {
                    String cmd = "java -XX:+Use" + gc + " -Xms" + xm + " -Xmx" + xm + " -XX:+PrintGCDetails -Xloggc:" + gc + "." + xm + ".log -XX:+PrintGCDateStamps GCLogAnalysis";
                    Process exec = Runtime.getRuntime().exec(cmd);
                    InputStream is = exec.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer result = new StringBuffer();
                    while ((line = reader.readLine()) != null) {
                        result.append(line + "\n");
                    }
                    System.out.println(cmd);
                    System.out.println(result);
                    exec.waitFor();
                    is.close();
                    reader.close();
                    exec.destroy();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
