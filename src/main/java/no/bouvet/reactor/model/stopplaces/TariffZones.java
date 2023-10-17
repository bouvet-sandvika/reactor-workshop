
package no.bouvet.reactor.model.stopplaces;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TariffZones {

    @Expose
    private String modificationSet;
    @Expose
    private List<TariffZoneRef> tariffZoneRef;

    public String getModificationSet() {
        return modificationSet;
    }

    public void setModificationSet(String modificationSet) {
        this.modificationSet = modificationSet;
    }

    public List<TariffZoneRef> getTariffZoneRef() {
        return tariffZoneRef;
    }

    public void setTariffZoneRef(List<TariffZoneRef> tariffZoneRef) {
        this.tariffZoneRef = tariffZoneRef;
    }

}
