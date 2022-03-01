public class Main {
    public static void main(String[] args) {
        LocationService locationService = new LocationService();
//        UserInterface.legend();

//        System.out.println(locationService.getLocation());

//        WriterToFile.writeLocationToFile(locationService.getLocation());

//        ReaderFromFile.readFromFile();

        for (String allCity : ReaderFromFile.getAllCities()) {
            System.out.println(allCity);
        }


    }
}
