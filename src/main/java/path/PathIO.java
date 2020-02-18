package path;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class PathIO {
    /**
     * Exports the path to a CSV file with the following format:
     * x,y,velocity,distance,curvature.
     *
     * @param filepath the CSV file
     */
    public static void write(java.nio.file.Path filepath, Path path) {
        try(BufferedWriter writer = Files.newBufferedWriter(filepath, StandardCharsets.US_ASCII)) {
            StringBuilder s = new StringBuilder("x,y,velocity,distance,curvature\n");
            for(Waypoint w : path.getPoints()) {
                s.append(w.getX()).append(",").append(w.getY()).append(",").append(w.getV()).append(",")
                        .append(w.getD()).append(",").append(w.getCurvature()).append("\n");
            }
            writer.write(s.toString(), 0, s.length());
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static Path read(java.nio.file.Path filepath) {
        Path path = new Path();
        try {
            List<String> lines = Files.readAllLines(filepath);
            lines.remove(0);
            for(String line : lines) {
                String[] values = line.split(",");
                Waypoint point = new Waypoint(Double.parseDouble(values[0]),
                        Double.parseDouble(values[1]));
                point.setV(Double.parseDouble(values[2]));
                point.setD(Double.parseDouble(values[3]));
                point.setCurvature(Double.parseDouble(values[4]));
                path.add(point);
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return path;
    }
}
