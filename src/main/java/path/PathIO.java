package path;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * A class containing static convenience methods for performing simple path read/write operations.
 */
public class PathIO {
    /**
     * Writes the path to a file in CSV format.
     *
     * @param filepath the file's path
     * @param path the path that will be written to the file
     */
    public static void write(java.nio.file.Path filepath, Path path) {
        try(BufferedWriter writer = Files.newBufferedWriter(filepath, StandardCharsets.US_ASCII)) {
            StringBuilder sb = new StringBuilder("x,y,velocity,distance,curvature\n");
            for(Waypoint w : path)
                sb.append(w.getX()).append(",").append(w.getY()).append(",").append(w.getV()).append(",")
                        .append(w.getD()).append(",").append(w.getCurvature()).append("\n");
            writer.write(sb.toString(), 0, sb.length());
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads a path from a file in CSV format.
     *
     * @param filepath the file's path
     * @return the imported path
     */
    public static Path read(java.nio.file.Path filepath) {
        Path path = new Path();
        try {
            List<String> lines = Files.readAllLines(filepath);
            lines.remove(0);
            for(String line : lines) {
                String[] values = line.split(",");
                Waypoint point = new Waypoint(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
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
