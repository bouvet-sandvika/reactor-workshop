
package no.bouvet.reactor.model.stopplaces;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Centroid {

    @Expose
    private Location location;
    @Expose
    private String modification;
    @Expose
    private String publication;
    @SerializedName("status_BasicModificationDetailsGroup")
    private String statusBasicModificationDetailsGroup;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getStatusBasicModificationDetailsGroup() {
        return statusBasicModificationDetailsGroup;
    }

    public void setStatusBasicModificationDetailsGroup(String statusBasicModificationDetailsGroup) {
        this.statusBasicModificationDetailsGroup = statusBasicModificationDetailsGroup;
    }

}
