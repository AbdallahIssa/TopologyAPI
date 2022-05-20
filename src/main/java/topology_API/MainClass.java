package topology_API;

import com.fasterxml.jackson.databind.JsonNode;


import java.util.List;
import java.util.Scanner;


/**
 * Here we go to run our methods that were implemented
 *
 * @author Abdallah Issa
 * @version 1.0
 * @since 2022-05-10
 */

public class MainClass {

    private static Scanner scanner;

    /**
     * the main entry point program for establishing
     * an open dialog with the user.
     *
     * @param args the command-line arguments.
     * */
    public static void main(String[] args) {
        // ElectronicDevice instantiation
        ElectronicDevice device = new ElectronicDevice();
        // instantiation of an input object
        scanner = new Scanner(System.in);
        // console dialog for taking actions
        //makeUserDialog(device);
        read(device);
    }



    private static void read(ElectronicDevice device) {
        System.out.print("[Input] Enter Json file path: ");
        String filePath = scanner.nextLine();
        Topology tpg = device.readFromJSON(filePath);
        if (tpg != null) {
            // System.out.println(tpg.toString()); // printing topology in a readable manner
            System.out.println("[MESSAGE]: File has been read successfully !\n");
        }
    }

    private static void write(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            System.out.print("[Input] Your New File Path (e.g.C:\\Users\\Abdallah\\filename): ");
            String filePath = scanner.nextLine();
            Topology tpg = device.writeToJSON(tpgID, filePath);
            if (tpg != null)
                System.out.println("[MESSAGE]: File has been wrote successfully !\n");
        } else System.out.println("[MESSAGE]: No Topologies in Memory at the Moment.");
    }

    private static void topologiesQuery(ElectronicDevice device) {
        List<Topology> topologies = device.queryTopologies();
        if (topologies.isEmpty())
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
        else for (Topology tp : topologies)
            System.out.println(tp.toString());
    }

    private static void deleteTopology(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            Topology tpg = device.deleteTopology(tpgID);
            if (tpg != null)
                System.out.println("[MESSAGE]: Topology has been Deleted Successfully !");
        } else
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
    }

    private static void componentsQuery(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            JsonNode components = device.queryComponents(tpgID);
            if (components != null)
                System.out.println(components.toPrettyString());
            else
                System.out.println("[MESSAGE]: No Components found matched !");
        } else
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
    }

    private static void netlistQuery(ElectronicDevice device) {
        String tpgID = chooseTopology(device);
        if (!tpgID.equals("-1")) {
            System.out.print("[Input] Enter Netlist Node ID: e.g.(\"gate\"): ");
            String netlistNodeID = scanner.nextLine();
            List<JsonNode> components = device.queryDevicesWithNetlistNode(tpgID, netlistNodeID);
            if (components.isEmpty())
                System.out.println("[MESSAGE]: No Match Found !!");
            else
                for (int i = 0; i < components.size(); ++i) {
                    System.out.print(components.get(i).toPrettyString());
                    if (i != components.size()-1) System.out.print(",");
                    System.out.println();
                }
        }
        else
            System.out.println("[Message]: No Topologies in Memory at the Moment.");
    }

    // **************** Helper Methods ********************** //
    private static String chooseTopology(ElectronicDevice device) {
        List<Topology> topologiesList = device.queryTopologies();
        String tpgID = "-1";
        if (!topologiesList.isEmpty()) {
            System.out.println("[MESSAGE]: Please Choose topologyID From the List Below...");
            for (Topology tpg : topologiesList)
                System.out.println(tpg.getId());

            System.out.print("[Input] Your Topology ID Choice: ");
            tpgID = scanner.nextLine();
        }
        return tpgID;
    }

}