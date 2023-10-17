
package no.bouvet.reactor.model.stopplaces;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class QuayRefOrQuay {

    @Expose
    private AccessibilityAssessment accessibilityAssessment;
    @Expose
    private Centroid centroid;
    @Expose
    private String changed;
    @Expose
    private Description description;
    @Expose
    private String id;
    @Expose
    private KeyList keyList;
    @Expose
    private String modification;
    @Expose
    private PrivateCode privateCode;
    @Expose
    private String publicCode;
    @Expose
    private String publication;
    @SerializedName("status_BasicModificationDetailsGroup")
    private String statusBasicModificationDetailsGroup;
    @Expose
    private String version;

    public AccessibilityAssessment getAccessibilityAssessment() {
        return accessibilityAssessment;
    }

    public void setAccessibilityAssessment(AccessibilityAssessment accessibilityAssessment) {
        this.accessibilityAssessment = accessibilityAssessment;
    }

    public Centroid getCentroid() {
        return centroid;
    }

    public void setCentroid(Centroid centroid) {
        this.centroid = centroid;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KeyList getKeyList() {
        return keyList;
    }

    public void setKeyList(KeyList keyList) {
        this.keyList = keyList;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public PrivateCode getPrivateCode() {
        return privateCode;
    }

    public void setPrivateCode(PrivateCode privateCode) {
        this.privateCode = privateCode;
    }

    public String getPublicCode() {
        return publicCode;
    }

    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode;
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
