import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[])
    {
        List<Model> list = FileIO.parseFile("CA.txt");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new GetMoreCommonName(list, "Margareth", "Dorothy"));

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.submit(new PrintOccurrences(list, "M", 2000));

        ScheduledExecutorService scheduledExe = Executors.newScheduledThreadPool(1);
        int delay = 3;
        int period = 5;
        scheduledExe.scheduleAtFixedRate(new PrintMostPopularName(list, "F"), delay, period, TimeUnit.SECONDS);

        executorService.shutdown();
    }

}

class GetMoreCommonName implements Callable<String> {

    List<Model> data;
    String name1;
    String name2;

    public GetMoreCommonName(List<Model> data, String name1, String name2) {
        this.data = data;
        this.name1 = name1;
        this.name2 = name2;
    }

    @Override
    public String call() throws Exception {

        double averageNumY1 = data.stream()
                .filter(v -> v.getName().equals(name1))
                .collect(Collectors.averagingDouble(Model::getOccurrences));

        double averageNumY2 = data.stream()
                .filter(v -> v.getName().equals(name2))
                .collect(Collectors.averagingDouble(Model::getOccurrences));

        return averageNumY1 > averageNumY2 ? name1 : name2;
    }
}

class PrintOccurrences implements Runnable {

    List<Model> list;
    String gender;
    int year;

    public PrintOccurrences(List<Model> list, String gender, int year) {
        this.list = list;
        this.gender = gender;
        this.year = year;
    }

    @Override
    public void run() {

        long count = list.stream()
                .filter(v -> v.getGender().equals(gender))
                .filter(v -> v.getYear() == year)
                .count();

        System.out.println(String.format("The number of %s born on year %d is: %d", gender.equals("F") ? "females" : "males", year, count));
    }
}

class PrintMostPopularName implements Runnable {

    List<Model> list;
    String gender;

    public PrintMostPopularName(List<Model> list, String gender) {
        this.list = list;
        this.gender = gender;
    }

    @Override
    public void run() {
        int year = ThreadLocalRandom.current().nextInt(1910, 2017);

        String name = list.stream()
                .filter(v -> v.getYear() == year)
                .filter(v -> v.getGender().equals(gender))
                .max(Comparator.comparing(Model::getOccurrences))
                .map(s -> s.getName())
                .get();

        System.out.println(
            String.format("Most common %s name for year %d is %s", gender.equals("Male") ? "male" : "female", year, name)
        );
    }
}