import java.io.File;
import java.util.Scanner;

/**
 * Demonstrates the Driver History Application.
 *
 * @author Timothy Strawser
 * @version 9-5-18
 */
public class DriverHistoryDemo {

    public static void main (String[] args)
    {
        DriverHistoryReportGenerator report = new DriverHistoryReportGenerator();

        //get list of input files available for input
        File inputDirectory = new File(report.inputPath);
        File[] files = inputDirectory.listFiles();

        //prompt user to select input file from input directory to create report
        Scanner input = new Scanner(System.in);
        System.out.println("From the list of available input files below, " +
                "please enter the full name of an input file to " +
                "generate a report.");
        int counter = 1;
        for (File file : files) {
            System.out.println(counter + ". " + file.getName() + "\n");
            counter++;
        }

        String fileIn = input.nextLine();

        //Take in input file
        report.consumeInput(fileIn);

        //Output driver history report
        report.outputReportFile();
    }
}
