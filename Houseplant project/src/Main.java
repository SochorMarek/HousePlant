import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PlantList list = new PlantList();

        try {
            // 1. Načtení ze souboru
            list.loadFromFile("kvetiny.txt");
        } catch (IOException e) {
            System.err.println("Chyba při čtení souboru: " + e.getMessage());
        }

        // 2. Výpis info o zálivce
        System.out.println("\nInformace o zálivce:");
        for (Plant p : list.getPlants()) {
            System.out.println(p.getWateringInfo());
        }

        try {
            // 3. Přidání nové květiny
            list.addPlant(new Plant("Monstera", "Oblíbená pokojovka", LocalDate.now().minusDays(30), LocalDate.now().minusDays(5), 10));

            // 4. Přidání 10 tulipánů
            for (int i = 1; i <= 10; i++) {
                list.addPlant(new Plant("Tulipán na prodej " + i, "", LocalDate.now(), LocalDate.now(), 14));
            }

            // 5. Odebrání květiny na indexu 2
            list.removePlant(2);

            // 6. Uložení do souboru
            list.saveToFile("vystup.txt");

            // 7. Znovu načíst a ověřit
            PlantList loadedList = new PlantList();
            loadedList.loadFromFile("vystup.txt");

            // 8. Seřazení podle názvu
            loadedList.sortByName();
            System.out.println("\nSeznam podle názvu:");
            for (Plant p : loadedList.getPlants()) {
                System.out.println(p.getName());
            }

            // 9. Seřazení podle zálivky
            loadedList.sortByLastWatering();
            System.out.println("\nSeznam podle poslední zálivky:");
            for (Plant p : loadedList.getPlants()) {
                System.out.println(p.getName() + " - " + p.getLastWatering());
            }

        } catch (Exception e) {
            System.err.println("Chyba: " + e.getMessage());
        }
    }
}