package topology_API;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <span class="strong">Electronic Device Test!</span>
 * Automated Testing Class tests the ElectronicDevice API Leve.
 *
 * @author Abdallah Issa
 * @version 1.0
 * @since 2022-05-10
 */
public class ElectronicDeviceTest {

    /**
     * Tests queryComponents method in ElectronicDevice class.
     */
    @Test
    @DisplayName("Test Read from File")
    public void testReadFromFile() {
        ElectronicDevice device = new ElectronicDevice();
        Topology topology = device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology.json");
        Assertions.assertAll(() -> assertEquals(topology.getId(), "tpg_1"),
                () -> assertEquals(topology.getComponentsJsonNode().toString(),
                        "[{\"type\":\"resistor\",\"id\":\"res1\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000},\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"}},{\"type\":\"nmos\",\"id\":\"m1\",\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"netlist\":{\"drain\":\"n1\",\"gate\":\"vin\",\"source\":\"vss\"}}]"));
    }

    /**
     * Tests writeToJSON method in ElectronicDevice class.
     */
    @Test
    @DisplayName("Test Write to File")
    public void testWriteToFile() {
        ElectronicDevice device = new ElectronicDevice();
        Topology readTopology = device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology.json");
        Topology writeTopology = device.writeToJSON(readTopology.getId(), "src/main/resources/test_tpg_5");

        Assertions.assertAll(() -> assertEquals(writeTopology.getId(), "test_tpg_5"),
                () -> assertEquals(writeTopology.getComponentsJsonNode().toString(),"[{\"type\":\"resistor\",\"id\":\"res1\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000},\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"}},{\"type\":\"nmos\",\"id\":\"m1\",\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"netlist\":{\"drain\":\"n1\",\"gate\":\"vin\",\"source\":\"vss\"}}]"));
    }

    /**
     * Tests queryComponents method in ElectronicDevice class.
     */
    @Test
    @DisplayName("Test Query All Topologies")
    public void testQueryAllTopologies() {
        ElectronicDevice device = new ElectronicDevice();
        device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology.json");
        device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology 2.json");

        Assertions.assertEquals(device.queryTopologies().size(), 2);
    }

    /**
     * Tests deleteTopology method in ElectronicDevice class.
     */
    @Test
    @DisplayName("Test Delete Topology")
    public void testDeleteTopology() {
        ElectronicDevice device = new ElectronicDevice();
        device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology.json");
        device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology 2.json");

        Topology deletedTopology = device.deleteTopology("test_tpg_5");
        boolean makeAssertion = false;
        for (Topology topology : device.queryTopologies())
            if (topology.getId().equals(deletedTopology.getId()))
            {
                makeAssertion = true;
                break;
            }

        Assertions.assertFalse(makeAssertion);
    }

    /**
     * Tests queryComponents method in ElectronicDevice class.
     */
    @Test
    @DisplayName("Test Query Components")
    public void testQueryComponents() {
        ElectronicDevice device = new ElectronicDevice();
        Topology tpg = device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology.json");

        Assertions.assertEquals(device.queryComponents(tpg.getId()).toString(), "[{\"type\":\"resistor\",\"id\":\"res1\",\"resistance\":{\"default\":100,\"min\":10,\"max\":1000},\"netlist\":{\"t1\":\"vdd\",\"t2\":\"n1\"}},{\"type\":\"nmos\",\"id\":\"m1\",\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"netlist\":{\"drain\":\"n1\",\"gate\":\"vin\",\"source\":\"vss\"}}]");
    }

    /**
     * Tests queryDevicesWithNetlistNode method in ElectronicDevice class.
     */
    @Test
    @DisplayName("Test Query Devices With Netlist Node")
    public void testQueryDevicesWithNetlistNode() {
        ElectronicDevice device = new ElectronicDevice();
        Topology tpg = device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology.json");
        device.readFromJSON("C:\\Users\\Abdallah Issa\\Desktop\\Test\\topology.json");

        Assertions.assertEquals(device.queryDevicesWithNetlistNode(tpg.getId(), "drain").toString(),
                "[{\"type\":\"nmos\",\"id\":\"m1\",\"m(l)\":{\"default\":1.5,\"min\":1,\"max\":2},\"netlist\":{\"drain\":\"n1\",\"gate\":\"vin\",\"source\":\"vss\"}}]");
    }
}