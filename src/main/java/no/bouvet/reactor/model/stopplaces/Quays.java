
package no.bouvet.reactor.model.stopplaces;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Quays {

    @Expose
    private String modificationSet;
    @Expose
    private List<QuayRefOrQuay> quayRefOrQuay;

    public String getModificationSet() {
        return modificationSet;
    }

    public void setModificationSet(String modificationSet) {
        this.modificationSet = modificationSet;
    }

    public List<QuayRefOrQuay> getQuayRefOrQuay() {
        return quayRefOrQuay;
    }

    public void setQuayRefOrQuay(List<QuayRefOrQuay> quayRefOrQuay) {
        this.quayRefOrQuay = quayRefOrQuay;
    }

}
