import java.util.List;
import java.util.Map;

/**
 * Created by axel on 06.10.2016.
 */
public interface Node {
    double getResult(Map<String, Object> values);
    void addChild(Node item);
    void removeChild(Node item);
    List<Node> getChildNodes();
}
