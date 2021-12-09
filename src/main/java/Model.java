public class Model {

    private String timezone;
    private String gender;
    private int year;
    private String name;

    @Override
    public String toString() {
        return "Model{" +
                "timezone='" + timezone + '\'' +
                ", gender='" + gender + '\'' +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", occurrences=" + occurrences +
                '}';
    }

    private int occurrences;

    public Model(String timezone, String gender, int year, String name, int occurences) {
        this.timezone = timezone;
        this.gender = gender;
        this.year = year;
        this.name = name;
        this.occurrences = occurences;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }


}
