
package no.bouvet.reactor.model.stopplaces;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class StopPlace {

    @Expose
    private AccessibilityAssessment accessibilityAssessment;
    @Expose
    private Centroid centroid;
    @Expose
    private String changed;
    @Expose
    private String created;
    @Expose
    private Description description;
    @Expose
    private String id;
    @Expose
    private KeyList keyList;
    @Expose
    private String modification;
    @Expose
    private Name name;
    @Expose
    private ParentSiteRef parentSiteRef;
    @Expose
    private PlaceEquipments placeEquipments;
    @Expose
    private PrivateCode privateCode;
    @Expose
    private String publication;
    @Expose
    private Quays quays;
    @SerializedName("status_BasicModificationDetailsGroup")
    private String statusBasicModificationDetailsGroup;
    @Expose
    private String stopPlaceType;
    @Expose
    private TariffZones tariffZones;
    @Expose
    private TopographicPlaceRef topographicPlaceRef;
    @Expose
    private String transportMode;
    @Expose
    private String version;
    @Expose
    private String weighting;

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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public ParentSiteRef getParentSiteRef() {
        return parentSiteRef;
    }

    public void setParentSiteRef(ParentSiteRef parentSiteRef) {
        this.parentSiteRef = parentSiteRef;
    }

    public PlaceEquipments getPlaceEquipments() {
        return placeEquipments;
    }

    public void setPlaceEquipments(PlaceEquipments placeEquipments) {
        this.placeEquipments = placeEquipments;
    }

    public PrivateCode getPrivateCode() {
        return privateCode;
    }

    public void setPrivateCode(PrivateCode privateCode) {
        this.privateCode = privateCode;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Quays getQuays() {
        return quays;
    }

    public void setQuays(Quays quays) {
        this.quays = quays;
    }

    public String getStatusBasicModificationDetailsGroup() {
        return statusBasicModificationDetailsGroup;
    }

    public void setStatusBasicModificationDetailsGroup(String statusBasicModificationDetailsGroup) {
        this.statusBasicModificationDetailsGroup = statusBasicModificationDetailsGroup;
    }

    public String getStopPlaceType() {
        return stopPlaceType;
    }

    public void setStopPlaceType(String stopPlaceType) {
        this.stopPlaceType = stopPlaceType;
    }

    public TariffZones getTariffZones() {
        return tariffZones;
    }

    public void setTariffZones(TariffZones tariffZones) {
        this.tariffZones = tariffZones;
    }

    public TopographicPlaceRef getTopographicPlaceRef() {
        return topographicPlaceRef;
    }

    public void setTopographicPlaceRef(TopographicPlaceRef topographicPlaceRef) {
        this.topographicPlaceRef = topographicPlaceRef;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWeighting() {
        return weighting;
    }

    public void setWeighting(String weighting) {
        this.weighting = weighting;
    }

}
