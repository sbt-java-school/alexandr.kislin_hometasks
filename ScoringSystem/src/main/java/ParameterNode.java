import java.util.List;
import java.util.Map;

/**
 * Created by axel on 06.10.2016.
 */
public class ParameterNode extends AbstractNode {
    private final String keyName;

    public ParameterNode(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public double getResult(Map<String, Object> values) {
        Object value = values.get(keyName);
        if (value instanceof Number){
            return ((Number) value).doubleValue();
        }
        return 0;
    }
}
