import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement
public class Train {
    @XmlAttribute(name = "id")
    private int id;
    private String from;
    private String to;
    private String date;
    private String departure;

    Train() {}

    public Train( int id, String from, String to, String date, String departure) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    public String getFrom() {
        return from;
    }
    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }
    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }
    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    public String getDeparture() {
        return departure;
    }
    @XmlElement
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "train id : " + id + "\nFrom : " + from + "\nTo : " + to +
                "\nDate: " + date + "\nDeparture : " + departure + "\n";
    }
}
