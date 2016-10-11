import java.util.List;
import java.util.Map;

/**
 * Created by axel on 06.10.2016.
 */
public abstract class AbstractNode implements Node {
    private List<Node> nodes;
    @Override
    public abstract double getResult(Map<String, Object> values);

    @Override
    public final void addChild(Node item) {
        nodes.add(item);
    }

    @Override
    public final void removeChild(Node item) {
        nodes.remove(item);
    }

    @Override
    public final List<Node> getChildNodes() {
        return nodes;
    }
}
