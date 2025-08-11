import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate lastWatering;
    private int frequency;

    // Konstruktor pro všechny atributy
    public Plant(String name, String notes, LocalDate planted, LocalDate lastWatering, int frequency) throws PlantException {
        if (frequency <= 0) throw new PlantException("Frekvence zálivky musí být větší než 0.");
        if (lastWatering.isBefore(planted)) throw new PlantException("Datum poslední zálivky nemůže být dříve než datum zasazení.");
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.lastWatering = lastWatering;
        this.frequency = frequency;
    }

    // Konstruktor bez poznámek, aktuální datum
    public Plant(String name, int frequency) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), frequency);
    }

    // Konstruktor pouze se jménem, frekvence 7 dní
    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    // Gettery a settery
    public String getName() { return name; }
    public String getNotes() { return notes; }
    public LocalDate getPlanted() { return planted; }
    public LocalDate getLastWatering() { return lastWatering; }
    public int getFrequency() { return frequency; }

    public void setName(String name) { this.name = name; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setPlanted(LocalDate planted) throws PlantException {
        if (lastWatering != null && lastWatering.isBefore(planted)) {
            throw new PlantException("Datum zasazení nesmí být po poslední zálivce.");
        }
        this.planted = planted;
    }    public void setLastWatering(LocalDate lastWatering) throws PlantException {
        if (lastWatering.isBefore(planted)) throw new PlantException("Datum poslední zálivky nemůže být dříve než datum zasazení.");
        this.lastWatering = lastWatering;
    }
    public void setFrequency(int frequency) throws PlantException {
        if (frequency <= 0) throw new PlantException("Frekvence zálivky musí být větší než 0.");
        this.frequency = frequency;
    }

    public String getWateringInfo() {
        return name + ": Poslední zálivka " + lastWatering + ", další doporučená zálivka " + lastWatering.plusDays(frequency);
    }

    public void doWateringNow() {
        this.lastWatering = LocalDate.now();
    }

    @Override
    public String toString() {
        return name + "\t" + notes + "\t" + planted + "\t" + lastWatering + "\t" + frequency;
    }
}
