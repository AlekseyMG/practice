import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Date;
import java.util.HashMap;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class XMLHandler extends DefaultHandler {
    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private HashMap<Voter, Integer> voterCounts;
    public XMLHandler() {
        voterCounts = new HashMap<>();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && voter == null) {
                //String name = attributes.getValue("name");
                //String birthDayStr = attributes.getValue("birthDay");

                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);

                //DBConnection.countVoter(name, birthDayStr);
            }
            else if (qName.equals("visit") && voter != null) {
                int count = voterCounts.getOrDefault(voter, 0);
                voterCounts.put(voter, count + 1);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public void putDataIntoDB() throws SQLException {
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            DBConnection.countVoter(voter.getName(),
                    LocalDate.ofInstant(voter.getBirthDay().toInstant(), ZoneOffset.systemDefault()).toString(),
                    count);
        }
        DBConnection.executeMultiInsert();
    }

    public void printDuplicatedVoters() throws SQLException {
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + " - " + count);
            }
        }
    }
}
