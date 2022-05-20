package topology_API;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Here we have the attributes and getter methods
 * <p>
 * <b>Note:</b> You will use this class in ElectronicDevice class
 * </p>
 *
 * @author Abdallah Issa
 * @version 1.0
 * @since 2022-05-10
 */

public class Topology {

    // Jackson JsonNode to represent a topology.
    private final JsonNode tpgJsonNode;
    // topology id.
    private final String id;
    // Jackson JsonNode to represent the components in a topology.
    @JsonProperty("components")
    private final JsonNode componentsJsonNode;

    /**
     * Constructor for initializing topology node and components.
     *
     * @param tpgJsonNode Jackson JsonNode to represent a topology.
     */
    public Topology (JsonNode tpgJsonNode) {
        this.tpgJsonNode = tpgJsonNode;
        this.id = tpgJsonNode.findValue("id").asText();
        this.componentsJsonNode = tpgJsonNode.findValue("components");
    }

    /**
     * Method for converting to a pretty readable string.
     *
     * @return String tpgJsonNode as a string.
     */
    @Override
    public String toString() {
        return tpgJsonNode.toPrettyString();
    }

    //***************** getters ************************//

    /**
     * @return String topology ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return JsonNode topology components
     */
    public JsonNode getComponentsJsonNode() {
        return componentsJsonNode;
    }

}