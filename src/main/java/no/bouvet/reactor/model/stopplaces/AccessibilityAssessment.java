
package no.bouvet.reactor.model.stopplaces;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AccessibilityAssessment {

    @Expose
    private String id;
    @Expose
    private Limitations limitations;
    @Expose
    private String mobilityImpairedAccess;
    @Expose
    private String modification;
    @Expose
    private String publication;
    @SerializedName("status_BasicModificationDetailsGroup")
    private String statusBasicModificationDetailsGroup;
    @Expose
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Limitations getLimitations() {
        return limitations;
    }

    public void setLimitations(Limitations limitations) {
        this.limitations = limitations;
    }

    public String getMobilityImpairedAccess() {
        return mobilityImpairedAccess;
    }

    public void setMobilityImpairedAccess(String mobilityImpairedAccess) {
        this.mobilityImpairedAccess = mobilityImpairedAccess;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
