
package no.bouvet.reactor.model.stopplaces;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PlaceEquipments {

    @Expose
    private String id;
    @Expose
    private List<InstalledEquipmentRefOrInstalledEquipment> installedEquipmentRefOrInstalledEquipment;
    @Expose
    private String modificationSet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<InstalledEquipmentRefOrInstalledEquipment> getInstalledEquipmentRefOrInstalledEquipment() {
        return installedEquipmentRefOrInstalledEquipment;
    }

    public void setInstalledEquipmentRefOrInstalledEquipment(List<InstalledEquipmentRefOrInstalledEquipment> installedEquipmentRefOrInstalledEquipment) {
        this.installedEquipmentRefOrInstalledEquipment = installedEquipmentRefOrInstalledEquipment;
    }

    public String getModificationSet() {
        return modificationSet;
    }

    public void setModificationSet(String modificationSet) {
        this.modificationSet = modificationSet;
    }

}
