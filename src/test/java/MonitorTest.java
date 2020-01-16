import accumulate.http.HttpRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonitorTest {

    @Test
    public void test() {
        HttpRequest httpRequest = new HttpRequest();

        String [] origin = new String[]{"Vulture", "rocket", "carrier", "Micius", "payment", "virtualReality", "Online", "Economy", "Support", "Authentication", "Targeted", "Interests", "Like", "SmallCat", "DoubleFirstClass", "keystore", "SlowMist", "wayish", "SNZPool", "HashQuark", "InfStones", "Cultural", "Majorpower", "Energy", "Traffic", "Connectivity", "Vehicle", "Flight", "Overseas", "Confidence", "Alternative", "Cooldown", "Merry", "Cynosure", "Stash", "water-node", "Depth", "song", "hopeless", "crossborder", "Kio", "craftsmanship", "domestically", "Initative", "Strikegold", "waiter", "soulgaze", "hotnormal", "ChineseDream", "Craftsman", "Independent", "Sing", "Fater", "Skills", "Zero", "Superstition", "TextBookstyle", "official", "fighter", "intelligence", "Economic", "freestyle", "invisible", "whitefragility", "Store", "Driving", "Super1", "Unicorn", "apparent", "Peak1", "Milkshake", "Parents", "Habitable", "Innovation", "Stream", "Destroyer", "ormal", "Regional", "Tiered", "Ambitious", "E", "Subcenter", "Opportunity", "Management", "Modern", "Super", "Reform", "Pilot", "Sharing", "Production", "Series", "Peak", "Couch", "Secret", "Calorie", "Naughty", "Star", "Status", "Proof", "Friend", "Personality", "platon.node.3", "platon.node.6", "platon.node.4", "platon.node.2", "platon.node.1", "platon.node.5", "platon.node.7"};
        String url = "https://platscan.platon.network/browser-server/staking/aliveStakingList";
        String param = "{\"pageNo\": 1, \"pageSize\": 150, \"key\": \"\", \"queryStatus\": \"all\"}";

        boolean flag = true;

        while (flag) {
            String result = httpRequest.post(url, JSONObject.fromObject(param));
            JSONArray array = JSONObject.fromObject(result).getJSONArray("data");

            List<String> nodeNames = new ArrayList<>();

            for (Object obj : array) {
                String nodeName = ((JSONObject)obj).getString("nodeName");
                nodeNames.add(nodeName);
            }

            for (int i = 0; i < 10; i++) {
                if (!nodeNames.get(i).equals(origin[i])) {
                    System.out.println("发生变化了.");
                    System.out.println(nodeNames.subList(0, 10));
                    flag = false;
                    break;
                }
            }
        }

    }
}
