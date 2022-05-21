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

public class MainClass extends  API_implementation{

    //API_implementation api = new API_implementation();


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
        //scanner = new Scanner(System.in);
        //the topology access method you want
        read(device);
        write(device);
        topologiesQuery(device);
        deleteTopology(device);
        componentsQuery(device);
        netlistQuery(device);

    }


}