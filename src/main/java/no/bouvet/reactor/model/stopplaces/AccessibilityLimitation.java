
package no.bouvet.reactor.model.stopplaces;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AccessibilityLimitation {

    @Expose
    private String audibleSignalsAvailable;
    @Expose
    private String escalatorFreeAccess;
    @Expose
    private String id;
    @Expose
    private String liftFreeAccess;
    @Expose
    private String modification;
    @Expose
    private String publication;
    @SerializedName("status_BasicModificationDetailsGroup")
    private String statusBasicModificationDetailsGroup;
    @Expose
    private String stepFreeAccess;
    @Expose
    private String version;
    @Expose
    private String wheelchairAccess;

    public String getAudibleSignalsAvailable() {
        return audibleSignalsAvailable;
    }

    public void setAudibleSignalsAvailable(String audibleSignalsAvailable) {
        this.audibleSignalsAvailable = audibleSignalsAvailable;
    }

    public String getEscalatorFreeAccess() {
        return escalatorFreeAccess;
    }

    public void setEscalatorFreeAccess(String escalatorFreeAccess) {
        this.escalatorFreeAccess = escalatorFreeAccess;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiftFreeAccess() {
        return liftFreeAccess;
    }

    public void setLiftFreeAccess(String liftFreeAccess) {
        this.liftFreeAccess = liftFreeAccess;
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

    public String getStepFreeAccess() {
        return stepFreeAccess;
    }

    public void setStepFreeAccess(String stepFreeAccess) {
        this.stepFreeAccess = stepFreeAccess;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWheelchairAccess() {
        return wheelchairAccess;
    }

    public void setWheelchairAccess(String wheelchairAccess) {
        this.wheelchairAccess = wheelchairAccess;
    }

}
