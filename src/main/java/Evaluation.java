import measures.MaximumDifference;
import measures.MeanSquareError;
import measures.QualityMeasure;

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

        List<QualityMeasure> measures = Arrays.asList(new MeanSquareError(), new MaximumDifference());
        for (QualityMeasure measure : measures) {
            System.out.println(measure.name());
            System.out.println(measure.get(result, reference));
        }
    }
}
