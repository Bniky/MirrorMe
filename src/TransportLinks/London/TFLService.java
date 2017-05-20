package TransportLinks.London;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicholas on 19/05/2017.
 */


public class TFLService {

    @SerializedName("$type")
    @Expose
    private String $type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("modeName")
    @Expose
    private String modeName;
    @SerializedName("disruptions")
    @Expose
    private List<Object> disruptions = null;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("lineStatuses")
    @Expose
    private List<LineStatus> lineStatuses = null;
    @SerializedName("routeSections")
    @Expose
    private List<Object> routeSections = null;
    @SerializedName("serviceTypes")
    @Expose
    private List<ServiceType> serviceTypes = null;
    @SerializedName("crowding")
    @Expose
    private Crowding crowding;

    public String get$type() {
        return $type;
    }

    public void set$type(String $type) {
        this.$type = $type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public List<Object> getDisruptions() {
        return disruptions;
    }

    public void setDisruptions(List<Object> disruptions) {
        this.disruptions = disruptions;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public List<LineStatus> getLineStatuses() {
        return lineStatuses;
    }

    public void setLineStatuses(List<LineStatus> lineStatuses) {
        this.lineStatuses = lineStatuses;
    }

    public List<Object> getRouteSections() {
        return routeSections;
    }

    public void setRouteSections(List<Object> routeSections) {
        this.routeSections = routeSections;
    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Crowding getCrowding() {
        return crowding;
    }

    public void setCrowding(Crowding crowding) {
        this.crowding = crowding;
    }

}
