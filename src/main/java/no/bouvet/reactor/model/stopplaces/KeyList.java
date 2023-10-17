
package no.bouvet.reactor.model.stopplaces;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class KeyList {

    @Expose
    private List<KeyValue> keyValue;

    public List<KeyValue> getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(List<KeyValue> keyValue) {
        this.keyValue = keyValue;
    }

}
