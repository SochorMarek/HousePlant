import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class PlantList {
    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant getPlant(int index) {
        return plants.get(index);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public List<Plant> getPlantsToWater() {
        List<Plant> needWatering = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Plant p : plants) {
            if (p.getLastWatering().plusDays(p.getFrequency()).isBefore(today) ||
                    p.getLastWatering().plusDays(p.getFrequency()).isEqual(today)) {
                needWatering.add(p);
            }
        }
        return needWatering;
    }

    public void sortByName() {
        plants.sort(Comparator.comparing(Plant::getName));
    }

    public void sortByLastWatering() {
        plants.sort(Comparator.comparing(Plant::getLastWatering));
    }

    public void loadFromFile(String filename) throws IOException {
        plants.clear();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            String[] parts = line.split("\t");
            try {
                if (parts.length != 5) throw new PlantException("Neplatný počet údajů na řádku.");
                Plant p = new Plant(
                        parts[0],
                        parts[1],
                        LocalDate.parse(parts[2]),
                        LocalDate.parse(parts[3]),
                        Integer.parseInt(parts[4])
                );
                plants.add(p);
            } catch (Exception e) {
                System.err.println("Chyba při načítání rostliny: " + line + " (" + e.getMessage() + ")");
            }
        }
    }

    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
        for (Plant p : plants) {
            writer.write(p.toString());
            writer.newLine();
        }
        writer.close();
    }
}