
package TransportLinks.London;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crowding {

    @SerializedName("$type")
    @Expose
    private String $type;

    public String get$type() {
        return $type;
    }

    public void set$type(String $type) {
        this.$type = $type;
    }

}
