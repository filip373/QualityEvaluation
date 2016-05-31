import correctness.Correctness;
import correctness.CorrectnessResults;
import correctness.measures.CorrectnessMeasure;
import correctness.measures.Precision;
import correctness.measures.Recall;
import measures.*;

import javax.imageio.ImageIO;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Filip-PC on 31.05.2016.
 */
public class Evaluation {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) throw new IllegalArgumentException("Invalid number of arguments");
        Raster result = ImageIO.read(new File(args[0])).getData();
        Raster reference = ImageIO.read(new File(args[1])).getData();

        List<QualityMeasure> measures = Arrays.asList(
                new MeanSquareError(), new MaximumDifference(), new PeakSNR(), new AverageDifference());
        for (QualityMeasure measure : measures) {
            System.out.println(measure.name());
            System.out.println(measure.get(result, reference));
        }

        CorrectnessResults correctnessResults = new Correctness().calculate(result, reference);
        List<CorrectnessMeasure> correctnessMeasures = Arrays.asList(
                new Precision(), new Recall());
        for (CorrectnessMeasure measure : correctnessMeasures) {
            System.out.println(measure.name());
            System.out.println(measure.get(correctnessResults));
        }
    }
}
