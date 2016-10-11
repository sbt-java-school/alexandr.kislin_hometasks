import java.util.List;
import java.util.Map;

/**
 * Created by axel on 06.10.2016.
 */
public class Minus extends AbstractNode {
    @Override
    public double getResult(Map<String, Object> values) {
        List<Node> nodes = getChildNodes();
        double result = 0;
        for (Node node: nodes) {
            result = result - node.getResult(values);
        }
        return result;
    }
}
