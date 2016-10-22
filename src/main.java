import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Trains trains = new Trains();
        trains.add(new Train(1, "Kyiv", "Donetsk", "19.12.2013", "15:05"));
        trains.add(new Train(2, "Lviv", "Sumy", "19.12.2013", "14:05"));
        trains.add(new Train(2, "Ney-York", "Chicago", "11.02.2016", "22:55"));
        trains.add(new Train(3, "Shostka", "Konotop", "19.12.2013", "17:05"));

        try {
            File file = new File("E:\\for_java/2/trains.xml");
            if (!file.exists())
                file.createNewFile();

            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            //write to file
            marshaller.marshal(trains, file);
            marshaller.marshal(trains, System.out);
            //read from file
            Trains trains1 = new Trains();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            trains1 = (Trains) unmarshaller.unmarshal(file);
            System.out.println("All fields:");
            System.out.println(trains1);
            System.out.println("____________________________________________");
            //selection between 15.00 and 19.00

            chooseTrains(trains, "15:00", "22:00");
            addTrain(trains, file.getAbsolutePath());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void chooseTrains (Trains trains, String after, String before) throws ParseException {
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy");

        String[] time_after= after.split(":");
        String[] time_before = before.split(":");

        LocalDateTime dateAfter;             //time after and before
        LocalDateTime dateBefore;
        LocalDateTime trainInfo;
        for (Train t : trains.getTrains()) {
            String[] date_departure = t.getDate().split("\\.");

            dateAfter = LocalDateTime.of(Integer.parseInt(date_departure[2]), Integer.parseInt(date_departure[1]) - 1,                      //creating two instances -
                    Integer.parseInt(date_departure[0]), Integer.parseInt(time_after[0]), Integer.parseInt(time_after[1]));                 //date, time before and after
            dateBefore = LocalDateTime.of(Integer.parseInt(date_departure[2]), Integer.parseInt(date_departure[1]) - 1,
                    Integer.parseInt(date_departure[0]), Integer.parseInt(time_before[0]), Integer.parseInt(time_before[1]));
            String[] trainDate = t.getDate().split("\\.");
            String[] trainTime = t.getDeparture().split(":");

            trainInfo = LocalDateTime.of(Integer.parseInt(trainDate[2]), Integer.parseInt(trainDate[1]) - 1, Integer.parseInt(trainDate[0]),//train departure date and time
                    Integer.parseInt(trainTime[0]), Integer.parseInt(trainTime[1]));
            if (trainInfo.isAfter(dateAfter) && trainInfo.isBefore(dateBefore)) {
                System.out.println(t.toString());
            }


        }
    }

    public static void addTrain (Trains trains, String path) throws JAXBException {             //Add train to the list
        int id = trains.getTrains().size() + 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter departure station");
        String from = sc.nextLine();
        System.out.println("Enter destination station");
        String to = sc.nextLine();
        System.out.println("Enter date in format \"dd.mm.yyyy\"");
        String date = sc.nextLine();
        System.out.println("Enter departure time in format: \"hh:mm\"");
        String departure = sc.nextLine();
        Train train = new Train(id, from, to, date, departure);
        trains.add(train);
        //write to the file
        JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(trains, new File(path));
        marshaller.marshal(trains, System.out);

    }

}





/*

DateFormat dateAfter = new DateFormat(Integer.parseInt(date_departure[2]), Integer.parseInt(date_departure[1]) - 1, Integer.parseInt(date_departure[0]));
String[] timeAfter = after.split(":");
dateAfter.setHours(Integer.parseInt(timeAfter[0]));
        dateAfter.setMinutes(Integer.parseInt(timeAfter[1]));
        Date dateBefore = new Date(Integer.parseInt(date_departure[2]), Integer.parseInt(date_departure[1])-1, Integer.parseInt(date_departure[0]));
        String[] timeBefore = before.split(":");
        dateBefore.setHours(Integer.parseInt(timeBefore[0]));
        dateBefore.setMinutes(Integer.parseInt(timeBefore[1]));
        dateAfter.toString();

        Date df = ((Date)departure.parse(t.getDeparture()));
        df.setYear(Integer.parseInt(date_departure[2]));
        df.setMonth(Integer.parseInt(date_departure[1])-1);
        df.setDate(Integer.parseInt(date_departure[0]));
        if (df.after(dateAfter)) {
        if (df.before(dateBefore)) {
        System.out.println(t.toString());
        }
        }*/
