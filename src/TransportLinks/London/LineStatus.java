
package TransportLinks.London;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LineStatus {

    @SerializedName("$type")
    @Expose
    private String $type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("statusSeverity")
    @Expose
    private Integer statusSeverity;
    @SerializedName("statusSeverityDescription")
    @Expose
    private String statusSeverityDescription;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("validityPeriods")
    @Expose
    private List<Object> validityPeriods = null;

    public String get$type() {
        return $type;
    }

    public void set$type(String $type) {
        this.$type = $type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusSeverity() {
        return statusSeverity;
    }

    public void setStatusSeverity(Integer statusSeverity) {
        this.statusSeverity = statusSeverity;
    }

    public String getStatusSeverityDescription() {
        return statusSeverityDescription;
    }

    public void setStatusSeverityDescription(String statusSeverityDescription) {
        this.statusSeverityDescription = statusSeverityDescription;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Object> getValidityPeriods() {
        return validityPeriods;
    }

    public void setValidityPeriods(List<Object> validityPeriods) {
        this.validityPeriods = validityPeriods;
    }

}
